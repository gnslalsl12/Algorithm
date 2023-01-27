import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_25383 {
	static int H, W;
	static char[][] Maps;
	static Queue<int[]> BFSQ = new LinkedList<>();
	static boolean[][] Vis;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		H = Integer.parseInt(tokens.nextToken());
		W = Integer.parseInt(tokens.nextToken());
		Maps = new char[H][W];
		Vis = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			String templine = read.readLine();
			for (int j = 0; j < W; j++) {
				Maps[i][j] = templine.charAt(j);
			}
		}
		solv();
	}

	private static void print() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(Maps[i][j]);
			}
			System.out.println();
		}
	}

	private static void solv() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (!Vis[i][j] && Maps[i][j] == '+') {
					getOuter(i, j);
					System.out.println("?");
				}
			}
		}
	}

	private static void getOuter(int x, int y) {
		BFSQ.clear();
		Queue<int[]> DiceFlats = new LinkedList<>();
		BFSQ.add(new int[] { x, y });
		while (!BFSQ.isEmpty()) {
			int[] temp = BFSQ.poll();
			char point = Maps[temp[0]][temp[1]];
			Vis[temp[0]][temp[1]] = true;
			if (point == '+') {
				if (onCross(temp[0], temp[1]))
					DiceFlats.add(new int[] { temp[0] + 1, temp[1] + 1 });
			} else if (point == '|') {
				onVertical(temp[0], temp[1]);
			} else if (point == '-') {
				onHorizon(temp[0], temp[1]);
			}
		}
		while (!DiceFlats.isEmpty()) {
			int[] temp = DiceFlats.poll();
			Maps[temp[0]][temp[1]] = '?';
		}
	}

	private static boolean onCross(int x, int y) {
		int nextx, nexty;
		char nextchar;
		for (int dir = 0; dir < 4; dir++) {
			nextx = x + deltas[dir][0];
			nexty = y + deltas[dir][1];
			if (!isIn(nextx, nexty) || Vis[nextx][nexty])
				continue;
			nextchar = Maps[nextx][nexty];
			if ((dir % 2 == 0 && nextchar == '|') || (dir % 2 == 1 && nextchar == '-')) {
				BFSQ.add(new int[] { nextx, nexty });
			}
		}
		if (isIn(x + 4, y + 4)) {
			if (Maps[x + 1][y] == '|' && Maps[x][y + 1] == '-' && Maps[x + 4][y + 4] == '+') {
				return true;
			}
		}
		return false;
	}

	private static void onVertical(int x, int y) {
		if (isIn(x - 1, y) && Maps[x - 1][y] != '.' && !Vis[x - 1][y])
			BFSQ.add(new int[] { x - 1, y });
		if (isIn(x + 1, y) && Maps[x + 1][y] != '.' && !Vis[x + 1][y])
			BFSQ.add(new int[] { x + 1, y });
	}

	private static void onHorizon(int x, int y) {
		if (isIn(x, y - 1) && Maps[x][y - 1] != '.' && !Vis[x][y - 1])
			BFSQ.add(new int[] { x, y - 1 });
		if (isIn(x, y + 1) && Maps[x][y + 1] != '.' && !Vis[x][y + 1])
			BFSQ.add(new int[] { x, y + 1 });
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

}
