import java.util.*;
import java.io.*;

public class Main {

	static int R, C;
	static int[][] Map = new int[1505][1505];
	static int[] SwanLoc = { -1, -1 };

	static int[][] Deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<Integer> LocList = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		char tempChar;
		for (int r = 0; r < R; r++) {
			String inputLine = read.readLine();
			for (int c = 0; c < C; c++) {
				tempChar = inputLine.charAt(c);
				if (tempChar == 'L') {
					SwanLoc[SwanLoc[0] == -1 ? 0 : 1] = r * C + c;
				} else if (tempChar == 'X') {
					Map[r][c] = -1;
				}
			}
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getMeltedTiming(); // 각 얼음이 녹게되는 타이밍 구해놓기
		write.write(getResultTiming() + "\n");
		write.close();
	}

	private static void getMeltedTiming() { // 각 얼음이 녹게되는 타이밍 구해놓기
		// 먼저 물과 붙은 얼음 위치만 구하기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (Map[r][c] == -1) { // 얼음 발견
					for (int[] dir : Deltas) {
						int nextR = r + dir[0];
						int nextC = c + dir[1];
						if (!isIn(nextR, nextC))
							continue;
						if (Map[nextR][nextC] == 0) { // 얼음 바로 옆에 물
							LocList.add(r * C + c); // 리스트에 추가
							Map[r][c] = 1; // 해당 얼음 녹는 타이밍 1
							break;
						}
					}
				}
			}
		}
		setThisTiming(1); // 추가된 얼음들의 바로 옆 얼음들 녹는 타이밍 구하기
	}

	private static void setThisTiming(int timing) { // 추가된 얼음들의 바로 옆 얼음들 녹는 타이밍 구하기
		int size = LocList.size(); // 현재 추가된 얼음들 위치만 고려
		while (size-- > 0) {
			int loc = LocList.poll();
			int r = loc / C;
			int c = loc % C;
			for (int[] dir : Deltas) {
				int nextR = r + dir[0];
				int nextC = c + dir[1];
				if (!isIn(nextR, nextC))
					continue;
				if (Map[nextR][nextC] == -1) { // 바로 옆 얼음중 아직 녹기 전(타이밍 기록 X)인 얼음 발견
					Map[nextR][nextC] = timing + 1; // 바로 다음에 해당 얼음이 녹음 표시
					LocList.add(nextR * C + nextC); // 해당 얼음 위치 추가
				}
			}
		}
		if (LocList.isEmpty())
			return;
		setThisTiming(timing + 1); // 추가된 얼음들의 바로 옆 얼음들 녹는 타이밍 구하기
	}

	private static int getResultTiming() { // 결과값 (백조에서 백조로) 구하기
		PriorityQueue<int[]> tempPQ = new PriorityQueue<>((x1, x2) -> x1[0] - x2[0]); // 걸리는 날짜가 짧은 것부터 고려
		tempPQ.add(new int[] { 0, SwanLoc[0] }); // [도착 위치까지 걸리는 최대 날짜, 도착 위치]
		boolean[][] vis = new boolean[R][C]; // 방문배열
		vis[SwanLoc[0] / C][SwanLoc[0] % C] = true;
		int result = Integer.MAX_VALUE; // 결과값(최소값 갱신 고려)

		while (!tempPQ.isEmpty()) {
			int[] loc = tempPQ.poll();
			int timing = loc[0]; // r,c까지 오는데 걸리는 날
			int r = loc[1] / C; // 도착 위치
			int c = loc[1] % C;
			for (int[] dir : Deltas) {
				int nextR = r + dir[0];
				int nextC = c + dir[1];
				if (!isIn(nextR, nextC) || vis[nextR][nextC]) {
					continue;
				}
				if (nextR * C + nextC == SwanLoc[1]) { // 다른 백조에게 도착
					result = Math.min(result, timing); // 결과값 갱신(최소 날짜값)
					continue;
				}
				vis[nextR][nextC] = true; // 방문처리
				tempPQ.add(new int[] { Math.max(timing, Map[nextR][nextC]), nextR * C + nextC }); // 현재 도착까지의 최대날짜값 갱신
			}
		}
		return result;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

}