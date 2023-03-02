package BOJ_G4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16768_MooyoMooyo_Imple {
	static int N, K;
	static ArrayList<Integer>[] Map;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		init();
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < 10; j++) {
				int temp = templine.charAt(j) - '0';
				Map[j].add(0, temp);
			}
		}
		solv();
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < 10; j++) {
				write.write(Map[j].get(i) + "");
			}
			write.write("\n");
		}
		write.close();
		read.close();
	}

	private static void init() {
		Map = new ArrayList[10];
		for (int i = 0; i < 10; i++) {
			Map[i] = new ArrayList<>();
		}
	}

	private static void solv() {
		while (true) {
			if (!getSearch())
				break;
			cleanPopped();
		}
	}

	private static boolean getSearch() { // 한 곳씩 둘러보기
		boolean hasPop = false;
		vis = new boolean[N][10];
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < N; i++) {
				if (Map[j].get(i) != 0 && !vis[i][j]) {
					if (BFS(i, j, Map[j].get(i)))
						hasPop = true;
				}
			}
		}
		if (hasPop) { // 둘러봤는데 팝이 있다
			return true;
		} else {
			return false;
		}
	}

	private static boolean BFS(int x, int y, int color) { // 돌아다녀보기
		ArrayList<int[]> BFSQ = new ArrayList<>();
		BFSQ.add(new int[] { x, y });
		vis[x][y] = true;
		for (int i = 0; i < BFSQ.size(); i++) {
			int[] temp = BFSQ.get(i);
			int tempx = temp[0];
			int tempy = temp[1];
			for (int dir = 0; dir < 4; dir++) {
				int nextx = tempx + deltas[dir][0];
				int nexty = tempy + deltas[dir][1];
				if (!isIn(nextx, nexty) || vis[nextx][nexty] || Map[nexty].get(nextx) != color)
					continue;
				vis[nextx][nexty] = true;
				BFSQ.add(new int[] { nextx, nexty });
			}
		}
		if (BFSQ.size() >= K) { // K개 이상 모여있으면 터뜨리기 => -1
			for (int[] pop : BFSQ) {
				Map[pop[1]].set(pop[0], -1);
			}
			return true;
		} else {
			return false;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < 10;
	}

	private static void cleanPopped() { // 터진 거 정리하기
		for (ArrayList<Integer> line : Map) {
			for (int i = 0; i < N;) {
				if (line.get(i) == -1) {
					line.remove(i);
					line.add(0);
				} else {
					i++;
				}
			}
		}
	}

}
