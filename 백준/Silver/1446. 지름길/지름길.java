import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int D;
	static TreeMap<Integer, Queue<int[]>> ShortCuts;
	static int Result;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		D = readInt();
		ShortCuts = new TreeMap<>();
		for (int n = 0; n < N; n++) {
			int from = readInt();
			int to = readInt();
			int cost = readInt();
			if (from > D || to > D || to - from <= cost)
				continue;
			if (!ShortCuts.containsKey(to)) {
				ShortCuts.put(to, new LinkedList<>());
			}
			ShortCuts.get(to).add(new int[] { from, cost });
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

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getMinResult();
		write.write(Result + "\n");
		write.close();
	}

	private static void getMinResult() {
		int[] dist = new int[D + 1];
		for (int to = 0; to <= D; to++) { // 0~D까지 거리 탐색
			if (to == 0)
				continue;
			dist[to] = dist[to - 1] + 1;
			if (ShortCuts.containsKey(to)) { // 해당 좌표로 도착하는 지름길ㅇ ㅣ있다면
				while (!ShortCuts.get(to).isEmpty()) { // 해당 좌표로의 경로값 최적화화
					int[] info = ShortCuts.get(to).poll();
					int from = info[0];
					int cost = info[1];
					dist[to] = Math.min(dist[to], dist[from] + cost);
				}
			}
		}
		Result = dist[D];
	}

}