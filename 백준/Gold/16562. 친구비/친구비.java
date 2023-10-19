import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[] Costs;
	static int[] Parent;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		K = readInt();
		Costs = new int[N + 1];
		Parent = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			Costs[n] = readInt();
			Parent[n] = n;
		}
		for (int m = 0; m < M; m++) {
			int v = readInt();
			int w = readInt();
			if (v == w)
				continue;
			setUnion(v, w);
		}
		Result = 0;
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

	private static void setUnion(int a, int b) {
		int pA = getFind(a);
		int pB = getFind(b);
		if (Costs[pA] < Costs[pB])
			Parent[pB] = pA; // A를 뽑는 게 더 싸다면
		else
			Parent[pA] = pB;
	}

	private static int getFind(int n) {
		if (Parent[n] == n)
			return n;
		return getFind(Parent[n]);
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult();
		write.write((K >= Result ? Result : "Oh no") + "\n");
		write.close();
	}

	private static void getResult() {
		boolean[] payed = new boolean[N + 1];
		for (int n = 1; n <= N; n++) {
			int parentN = getFind(n);
			if (!payed[parentN]) {
				Result += Costs[parentN];
				payed[parentN] = true;
			}
		}
	}

}