import java.util.*;
import java.io.*;

class Main {
	static int N;
	static ArrayList<dirXY> CVlist;
	static dirXY start, end;
	static Queue<dirXY> BFSQ;
	static boolean result;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		for (int test = 0; test < T; test++) {
			CVlist = new ArrayList<>();
			BFSQ = new LinkedList<>();
			N = readInt();
			start = new dirXY(readInt(), readInt());
			result = false;
			for (int i = 0; i < N; i++) {
				CVlist.add(new dirXY(readInt(), readInt()));
			}
			end = new dirXY(readInt(), readInt());
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
    
    	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
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