import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long[] Map;
	static int[][] Deltas;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Map = new long[N];
		for (int i = 0; i < N; i++) {
			String inputLine = read.readLine();
			for (int j = 0; j < N; j++) {
				if (inputLine.charAt(j) == '1')
					Map[i] |= 1L << j;
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(getResultByBFS() + "\n");
		write.close();
	}

	private static int getResultByBFS() {
		Queue<Integer> bfsQ = new LinkedList<>();
		int[][] breakCountMap = new int[N][N];
		bfsQ.add(10000); // 부순 카운트 * 10000 + 위치
		breakCountMap[0][0] = 1; // 초기값 0과 구분하기 위해 시작을 1로 설정
		while (!bfsQ.isEmpty()) {
			int info = bfsQ.poll();
			int loc = info % 10000; // 위치
			int breakCount = info / 10000; // 해당 위치까지 가기 위한 벽 부수기 횟수
			if (breakCountMap[loc / N][loc % N] < breakCount) // 현재 위치에 더 나은 경우가 존재하게 됐다면 탐색 중지
				continue;
			for (int[] dir : Deltas) {
				int nextI = loc / N + dir[0];
				int nextJ = loc % N + dir[1];
				if (!isIn(nextI, nextJ))
					continue;
				int nextLocBreakCount = breakCount;
				if ((Map[nextI] & (1L << nextJ)) == 0) { // 다음 위치에는 벽을 부숴야함
					nextLocBreakCount++;
				}
				// 처음 가는 곳이거나 지금 경우로 더 적은 벽을 부수고 갈 수 있다면 추가
				if (breakCountMap[nextI][nextJ] == 0 || breakCountMap[nextI][nextJ] > nextLocBreakCount) {
					bfsQ.add(nextLocBreakCount * 10000 + nextI * N + nextJ);
					breakCountMap[nextI][nextJ] = nextLocBreakCount;
				}
			}
		}
		return breakCountMap[N - 1][N - 1] - 1;
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}
}