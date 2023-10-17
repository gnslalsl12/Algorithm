import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N;
	static ArrayList<dirXY> CVlist;
	static dirXY start, end;
	static Queue<dirXY> BFSQ;
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < T; test++) {
			CVlist = new ArrayList<>();
			BFSQ = new LinkedList<>();
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			start = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			result = false;
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				CVlist.add(new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}
			tokens = new StringTokenizer(read.readLine());
			end = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			CVlist.add(end);
			BFSQ.add(start);
			BFS();
			if (result)
				sb.append("happy\n");
			else
				sb.append("sad\n");
		}
		System.out.print(sb);
	}

	private static void BFS() {
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int i = 0; i < N + 1; i++) {
				dirXY out = CVlist.get(i);
				if (out.visits)
					continue;
				if (!getDist(temp, out))
					continue;
				if (out.x== end.x && out.y == end.y) {
					result = true;
					return;
				}
				out.visits = true;
				BFSQ.add(out);
			}
		}
	}
	
	private static boolean getDist(dirXY from, dirXY to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y) <= 1000;
	}

	private static class dirXY {
		int x;
		int y;
		boolean visits = false;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}