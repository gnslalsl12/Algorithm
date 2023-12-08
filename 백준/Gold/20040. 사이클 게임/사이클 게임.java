import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] Parents;

	public static void main(String args[]) throws IOException {
		init();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Parents = new int[N];
		for (int n = 0; n < N; n++) {
			Parents[n] = n;
		}
		int result = 0;
		for (int m = 0; m < M; m++) {
			int n1 = readInt();
			int n2 = readInt();
			if (setConnect(n1, n2) && result == 0) {
				result = m + 1;
			}
		}
		solv(result);
	}

	private static int readInt() throws IOException {
		int n, c;
		do {
			n = System.in.read();
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static boolean setConnect(int n1, int n2) {
		int pN1 = getParent(n1);
		int pN2 = getParent(n2);
		if (pN1 < pN2) {
			Parents[pN2] = pN1;
		} else if (pN1 > pN2) {
			Parents[pN1] = pN2;
		} else {
			return true;
		}
		return false;
	}

	private static boolean isSameParent(int n1, int n2) {
		return Parents[n1] == Parents[n2];
	}

	private static int getParent(int n) {
		if (Parents[n] == n)
			return n;
		return Parents[n] = getParent(Parents[n]);
	}

	private static void solv(int result) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(result + "\n");
		write.close();
	}
}