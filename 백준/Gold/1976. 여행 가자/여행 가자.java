import java.util.*;
import java.io.*;

public class Main { // UnionFind
	static int N;
	static int M;
	static int[] Parents;
	static Queue<Integer> Plan;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Parents = new int[N];
		for (int n = 0; n < N; n++) {
			Parents[n] = n;
		}
		for (int n = 0; n < N; n++) {
			for (int nn = 0; nn < N; nn++) {
				if (readInt() == 1 && !isSameParent(n, nn)) {
					setParents(n, nn);
				}
			}
		}
		Plan = new LinkedList<>();
		for (int m = 0; m < M; m++) {
			Plan.add(readInt() - 1);
		}
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

	private static boolean isSameParent(int child1, int child2) {
		return getParent(child1) == getParent(child2);
	}

	private static void setParents(int child1, int child2) {
		int parent1 = getParent(child1);
		int parent2 = getParent(child2);
		if (parent1 <= parent2) {
			Parents[parent2] = parent1;
		} else {
			Parents[parent1] = parent2;
		}
	}

	private static int getParent(int child) {
		if (Parents[child] == child)
			return child;
		return Parents[child] = getParent(Parents[child]);
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write((isAble() ? "YES\n" : "NO\n"));
		write.close();
	}

	private static boolean isAble() {
		int before = Plan.poll();
		while (!Plan.isEmpty()) {
			int present = Plan.poll();
			if (!isSameParent(before, present))
				return false;
			before = present;
		}
		return true;
	}

}