import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maps[][],
			deltas[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		PriorityQueue<dirXY> fromHigh = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int temph = Integer.parseInt(tokens.nextToken());
				maps[i][j] = temph;
				if (temph == 0)
					continue;
				fromHigh.add(new dirXY(i, j, temph));
			}
		}
		int result = 0;
		while (!fromHigh.isEmpty()) {
			dirXY temp = fromHigh.poll();
			if (maps[temp.x][temp.y] != temp.h)
				continue;
			pump(temp);
			result++;
		}
		System.out.println(result);
	}

	private static void pump(dirXY input) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		int maxh = input.h + 1;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 8; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0 || maps[nextx][nexty] > temp.h)
					continue;
				BFSQ.add(new dirXY(nextx, nexty, maps[nextx][nexty]));
				maps[nextx][nexty] = maxh;
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x, y, h;

		public dirXY(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(o.h, this.h);
		}
	}
}