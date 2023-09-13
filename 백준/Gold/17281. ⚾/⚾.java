import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] Points;
	static Queue<Integer> Cases;
	static int Result;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Points = new int[N][10];
		for (int n = 0; n < N; n++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int p = 1; p <= 9; p++) {
				Points[n][p] = Integer.parseInt(tokens.nextToken());
			}
		}
		Cases = new LinkedList<>();
		Result = 0;
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getPermCases(0, 2, 1000);
		getMaxResult();
		write.write(Result + "\n");
		write.close();
	}
    
	private static void getPermCases(int count, int vis, int sel) {
		if (count == 9) {
			Cases.add(sel);
			return;
		}

		if (count == 3) {
			getPermCases(count + 1, vis, sel);
		}

		for (int num = 2; num <= 9; num++) {
			if ((vis & (1 << num)) != 0)
				continue;
		    int pow = (int)Math.pow(10, count);
			vis |= 1 << num;
			sel += num*pow;
			getPermCases(count + 1, vis, sel);
			sel -= num*pow;
			vis &= ~(1 << num);
		}
	}

	private static void getMaxResult() {
		while (!Cases.isEmpty()) {
			int thisCase = Cases.poll();
			int point = 0;
			int order = 8;
			char[] orderChar = Integer.toString(thisCase).toCharArray();
			for (int inning = 0; inning < N; inning++) {
				int onBoard = 0;
				int out = 0;
				while (true) {
					onBoard++;
					int thisHitter = orderChar[order] - '0';
					order = (order + 8)%9;
					int pointOfThisHitter = Points[inning][thisHitter];
					if (pointOfThisHitter == 0) {
						out++;
						if (out == 3)
							break;
						else
							onBoard--;
					} else {
						onBoard = (onBoard << pointOfThisHitter);
						point += ((onBoard & 16) != 0 ? 1 : 0) + ((onBoard & 32) != 0 ? 1 : 0)
								+ ((onBoard & 64) != 0 ? 1 : 0) + ((onBoard & 128) != 0 ? 1 : 0);
						onBoard &= 15;
					}
				}
			}
			Result = Math.max(Result, point);
		}
	}
}