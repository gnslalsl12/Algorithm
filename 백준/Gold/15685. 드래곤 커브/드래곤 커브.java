import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long[] MapLeft, MapRight;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		MapLeft = new long[101];
		MapRight = new long[101];
		Deltas = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
		for (int n = 0; n < N; n++) {
			setMap(readInt(), readInt(), readInt(), readInt());
		}
		Result = 0;
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean negative = false;
		do {
			n = System.in.read();
			if (n == 45)
				negative = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return negative ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult();
		write.write(Result + "\n");
		write.close();
	}

	private static void setMap(int y, int x, int d, int g) {
		int baseI = x;
		int baseJ = y;
		setPointOnMap(baseI, baseJ);
		baseI += Deltas[d][0];
		baseJ += Deltas[d][1]; // 0세대 한 번 이동
		setPointOnMap(baseI, baseJ);
		if (g == 0)
			return;
		ArrayList<Integer> moves = new ArrayList<>();
		moves.add((d + 1) % 4);
		for (int age = 1; age <= g; age++) {
			int size = moves.size();
			for (int index = size - 1; index >= 0; index--) {
				int nextDir = moves.get(index);
				baseI += Deltas[nextDir][0];
				baseJ += Deltas[nextDir][1];
				setPointOnMap(baseI, baseJ);
				if (age != g)
					moves.add((moves.get(index) + 1) % 4);
			}
		}

	}

	private static void setPointOnMap(int i, int j) {
		if (j >= 50) {
			MapRight[i] |= 1L << (j - 50);
		} else {
			MapLeft[i] |= 1L << j;
		}
	}

	private static void getResult() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (isPointed(i, j) && isPointed(i + 1, j) && isPointed(i, j + 1) && isPointed(i + 1, j + 1))
					Result++;
			}
		}
	}

	private static boolean isPointed(int i, int j) {
		if (j >= 50) {
			return (MapRight[i] & (1L << (j - 50))) != 0L;
		} else {
			return (MapLeft[i] & (1L << j)) != 0L;
		}
	}

}