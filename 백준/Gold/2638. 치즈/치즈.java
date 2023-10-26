import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] maps;
	static boolean[][] visits;
	static Queue<dirXY> Cheeses = new LinkedList<>();
	static Queue<dirXY> tempQ = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new char[N][M];
		visits = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(tokens.nextToken());
				if (temp == 0) {
					maps[i][j] = 'b';
				} else {
					Cheeses.add(new dirXY(i, j));
					maps[i][j] = 'c';
				}
			}
		}
		tempQ.add(new dirXY(0, 0));
		int time = 0;
		while (true) {
			if (Cheeses.size() == 0)
				break;
			time++;
			setOutBlank();
//			print();
			melting();
//			print();
		}
		System.out.println(time);
	}

	private static void print() {
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(maps[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void melting() {
		int size = Cheeses.size();
		while (size-- > 0) {
			dirXY temp = Cheeses.poll();
			int count = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] != 'X')
					continue;
				count++;
			}
			if (count < 2) {
				Cheeses.add(temp);
				continue;
			}
			maps[temp.x][temp.y] = 'b';
			tempQ.add(new dirXY(temp.x, temp.y));
//			firstout = new dirXY(temp.x, temp.y);
		}
	}

	private static void setOutBlank() {
		while (!tempQ.isEmpty()) {
			dirXY temp = tempQ.poll();
			maps[temp.x][temp.y] = 'X';
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] != 'b')
					continue;
//				if(maps[nextx][nexty] == 'b') {
//					tempQ.add(new dirXY(nextx, nexty));
//					maps[nextx][nexty] = 'X'; // 외곽의 빈공간임
//				}
				maps[nextx][nexty] = 'X';
				tempQ.add(new dirXY(nextx, nexty));
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY {
		int x, y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}