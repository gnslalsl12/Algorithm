import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maps[][];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		result = 0;
		PriorityQueue<dirXY> FromLow = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				maps[i][j] = templine.charAt(j) - '0';
				if (maps[i][j] != 9)
					FromLow.add(new dirXY(i, j));
			}
		} // input
		while (!FromLow.isEmpty()) {
			dirXY temp = FromLow.poll();
			if (maps[temp.x][temp.y] != temp.d)
				continue;
			BFS(temp);
		}
		write.write(result + "");
		write.close();
		read.close();
	}

	private static void BFS(dirXY input) {
		PriorityQueue<dirXY> Q = new PriorityQueue<>();
		Q.add(input);
		int minmax = 10;
		boolean[][] visits = new boolean[N][M];
		visits[input.x][input.y] = true;
		while (!Q.isEmpty()) {
			dirXY temp = Q.poll();
			if (temp.checked) {
				Q.add(temp);
				break;
			}
			temp.checked = true;
			Q.add(temp);
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0) { // 해당 영역에는 물 못 쌓음
					minmax = 0;
					continue;
				}
				if (visits[nextx][nexty])
					continue;
				visits[nextx][nexty] = true;
				if (maps[nextx][nexty] > temp.d) {
					minmax = Math.min(minmax, maps[nextx][nexty]); // 둘러싼 애들 중에 나보다 크지만 작은 애들
					continue;
				} else if (maps[nextx][nexty] == temp.d) {
					Q.add(new dirXY(nextx, nexty));
				}
			}

		}
		int temptotal = minmax * Q.size();
		while (!Q.isEmpty()) { // 체크한 곳들
			dirXY temp = Q.poll();
			if (minmax != 0) {
				temptotal -= maps[temp.x][temp.y];
			}
			maps[temp.x][temp.y] = minmax;
		}
		result += temptotal;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int d;
		boolean checked = false;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
			this.d = maps[x][y];
		}

		@Override
		public int compareTo(dirXY o) {
			if (this.checked == o.checked)
				return Integer.compare(this.d, o.d);
			return Boolean.compare(this.checked, o.checked); // false가 먼저
		}

	}

}