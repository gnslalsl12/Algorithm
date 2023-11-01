import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int K;
	static int[][] Map;
	static int[][] Deltas;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Map = new int[N][10];
		for (int i = 0; i < N; i++) {
			String inputLine = read.readLine();
			for (int j = 0; j < 10; j++) {
				Map[i][j] = inputLine.charAt(j) - '0';
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		read.close();
	}

	private static void solv() throws IOException {
		while (true) {
			if (hasConnect()) { // 연결된 K개 이상이 있는가
				setMapOrg(); // 맵 중력 정리리
			} else {
				break;
			}
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				write.write(Map[i][j] + "");
			}
			write.write("\n");
		}
		write.close();
	}

	private static boolean hasConnect() { // 연결된 K개 이상의 구간이 있는가
		boolean[][] vis = new boolean[N][10];
		boolean found = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				if (!vis[i][j] && Map[i][j] > 0) {
					if (hasOverK(i, j, Map[i][j], vis)) { // i,j 위치에서 bfs탐색으로 같은 색 연결
						found = true; // K개 이상 연결돼있음음
					}
				}
			}
		}
		return found;
	}

	private static boolean hasOverK(int i, int j, int color, boolean[][] vis) { // i,부터 시작해서 같은 색 개수 탐색
		ArrayList<Integer> bfsArr = new ArrayList<>();
		bfsArr.add(i * 10 + j);
		vis[i][j] = true;
		int index = 0;
		while (index < bfsArr.size()) {
			int x = bfsArr.get(index) / 10;
			int y = bfsArr.get(index) % 10;
			for (int[] dir : Deltas) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				if (!isIn(nextX, nextY) || vis[nextX][nextY] || Map[nextX][nextY] != color)
					continue;
				bfsArr.add(nextX * 10 + nextY);
				vis[nextX][nextY] = true;
			}
			index++;
		}
		if (bfsArr.size() >= K) { // K개 이상인가
			while (--index >= 0) { // 전부 삭제
				Map[bfsArr.get(index) / 10][bfsArr.get(index) % 10] = 0;
			}
			return true;
		}
		return false;

	}

	private static void setMapOrg() { // 맵 정리
		for (int j = 0; j < 10; j++) {
			int index = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (Map[i][j] > 0) {
					int color = Map[i][j];
					Map[i][j] = 0;
					Map[index--][j] = color;
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < 10;
	}

}