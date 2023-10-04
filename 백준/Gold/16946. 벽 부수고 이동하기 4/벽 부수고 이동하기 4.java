import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static inmap[][] maps;
	static ArrayList<dirXY> ZeroList = new ArrayList<>();
	static Queue<dirXY> OneList = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new inmap[N][M];
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				int t = templine.charAt(j) - '0';
				if (t == 0) {
					ZeroList.add(new dirXY(i, j));
					maps[i][j] = new inmap(t, -1);
				} else {
					OneList.add(new dirXY(i, j));
					maps[i][j] = new inmap(t, -2);
				}
			}
		}
		num = 1;
		for (int i = 0; i < ZeroList.size(); i++) {
			dirXY temp = ZeroList.get(i);
			if (maps[temp.x][temp.y].num != -1)
				continue;
			countZero(temp);
		}
//		printN();
//		printV();
		while (!OneList.isEmpty()) {
			ArrayList<Integer> check = new ArrayList<>();
			int count = 1;
			dirXY temp = OneList.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || check.contains(maps[nextx][nexty].num))
					continue;
				if (maps[nextx][nexty].num == -2)
					continue;
				check.add(maps[nextx][nexty].num);
				count += maps[nextx][nexty].v;
			}
			maps[temp.x][temp.y].v = count;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j].num == -2) {
					write.write((maps[i][j].v % 10) + "");
				} else {
					write.write("0");
				}
			}
			write.write("\n");
		}
		write.close();
	}

	private static void printV() {
		System.out.println("VVVVVVVVVVVVVVV");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(maps[i][j].v);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printN() {
		System.out.println("NNN");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(maps[i][j].num);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void countZero(dirXY temp) {
		inmap base = new inmap(1, num);
		Stack<dirXY> st = new Stack<>();
		st.add(temp);
		maps[temp.x][temp.y] = base;
		while (!st.isEmpty()) {
			dirXY pop = st.pop();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = pop.x + deltas[dir][0];
				int nexty = pop.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty].num != -1) // 범위 밖 / 이미 확인된 곳
					continue;
				if (maps[nextx][nexty].num == -2)	//벽
					continue;
				maps[nextx][nexty] = base;
				base.v++;
				st.add(new dirXY(nextx, nexty));
			}
		}
		num++;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	private static class inmap {
		int v = -1;
		int num = -1;

		public inmap(int v, int num) {
			this.v = v;
			this.num = num;
		}

	}

}