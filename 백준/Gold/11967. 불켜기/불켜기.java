import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char[][] Map;
	static Queue<Integer>[] Switches;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Map = new char[N][N];
		Switches = new LinkedList[N * N + 1];
		for (int n = 0; n < N * N + 1; n++) {
			Switches[n] = new LinkedList<>();
		}
		for (int m = 0; m < M; m++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int a = Integer.parseInt(tokens.nextToken()) - 1;
			int b = Integer.parseInt(tokens.nextToken()) - 1;
			Switches[x * N + y].add(a * N + b);
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setBashProceed();
		write.write(Result + "\n");
		write.close();
	}

	private static void setBashProceed() {
		Queue<Integer> BFSQ = new LinkedList<>();
		BFSQ.add(0);
		Result = 1;
		Map[0][0] = 'V';
		while (!BFSQ.isEmpty()) {
			int current = BFSQ.poll();
			while (!Switches[current].isEmpty()) { // 불 켜보기
				int newlyOn = Switches[current].poll();
				if (Map[newlyOn / N][newlyOn % N] == 'O' || Map[newlyOn / N][newlyOn % N] == 'V') {
					continue;
				} // 이미 켜진곳
				if (Map[newlyOn / N][newlyOn % N] == 'W') { // 켜지길 기다리던 곳
					Map[newlyOn / N][newlyOn % N] = 'V';
					BFSQ.add(newlyOn);
				} else
					Map[newlyOn / N][newlyOn % N] = 'O'; // 새로 켜지는 곳
				Result++; // 새로 켰다!
			}
			for (int[] dir : Deltas) {
				int nextI = current / N + dir[0];
				int nextJ = current % N + dir[1];
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 'V' || Map[nextI][nextJ] == 'W')
					continue;
				if (Map[nextI][nextJ] == 'O') { // 불이 켜져있는 곳(이동 가능)
					int nextLoc = nextI * N + nextJ;
					BFSQ.add(nextLoc);
					Map[nextI][nextJ] = 'V';
				} else if (Map[nextI][nextJ] != 'W') { // 불이 꺼져있는 곳(이동 대기);
					Map[nextI][nextJ] = 'W';
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}