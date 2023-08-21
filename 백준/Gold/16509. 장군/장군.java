import java.util.*;
import java.io.*;

public class Main {

	static int[] Visits;
	static int S, K;
	static int[][] StDeltas;
	static int[][] CrDeltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		Visits = new int[10];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		S = Integer.parseInt(tokens.nextToken()) * 10 + Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		K = Integer.parseInt(tokens.nextToken()) * 10 + Integer.parseInt(tokens.nextToken());
		StDeltas = new int[][] { { -1, 0, 0 }, { 0, 1, 1 }, { 1, 0, 2 }, { 0, -1, 3 } };
		CrDeltas = new int[][] { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };
		Result = -1;
	}

	private static void solv() {
		getBFS();
	}

	private static void getBFS() {
		Visits[S / 10] |= 1 << (S % 10);
		Queue<Integer> BFSQ = new LinkedList<>();
		BFSQ.add(S);
		while (!BFSQ.isEmpty()) {
			int tempLoc = BFSQ.poll();
			int count = tempLoc / 100;
			tempLoc %= 100;
			for (int[] stDelta : StDeltas) {
				anotherCrDelta: for (int var = 0; var <= 1; var++) {
					int nextI = 0;
					int nextJ = 0;
					for (int add = 0; add <= 2; add++) {
						nextI = tempLoc / 10 + stDelta[0] + CrDeltas[(stDelta[2] + var) % 4][0] * add;
						nextJ = tempLoc % 10 + stDelta[1] + CrDeltas[(stDelta[2] + var) % 4][1] * add;
						if (!isIn(nextI, nextJ) || (add != 2 && (nextI * 10 + nextJ == K))) // 범위 밖이거나 중간에 킹
							continue anotherCrDelta;
					}
					if (nextI * 10 + nextJ == K) {
						Result = count + 1;
						return;
					}
					if ((Visits[nextI] & (1 << nextJ)) != 0)
						continue;
					Visits[nextI] |= 1 << nextJ;
					BFSQ.add((count + 1) * 100 + nextI * 10 + nextJ);
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < 10 && j >= 0 && j < 9;
	}

}