import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static PriorityQueue<int[]> NodeList;
	static int[] Parent;
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
		N = Integer.parseInt(read.readLine());
		M = Integer.parseInt(read.readLine());
		Parent = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			Parent[n] = n;
		}
		Result = 0;
		NodeList = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[2], n2[2]));
		for (int m = 0; m < M; m++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if (a == b)
				continue;
			int c = Integer.parseInt(tokens.nextToken());
			NodeList.add(new int[] { a, b, c });
		}
		read.close();
	}

	private static void solv() {
		getKRUS();
	}

	private static void getKRUS() {
		while (!NodeList.isEmpty()) {
			int[] node = NodeList.poll();
			int a = node[0];
			int b = node[1];
			int dist = node[2];
			if (getParent(a) == getParent(b)) {
				continue;
			} else {
				Result += dist;
				setUnion(a, b);
			}
		}
	}

	private static int getParent(int child) {
		return Parent[child] == child ? child : getParent(Parent[child]);
	}

	private static void setUnion(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a < b)
			Parent[b] = a;
		else
			Parent[a] = b;
	}

}