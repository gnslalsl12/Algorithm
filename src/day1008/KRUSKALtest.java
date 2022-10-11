package day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*7
 * 9
 * 1 2 29
 * 1 5 75
 * 2 3 35
 * 2 6 34
 * 3 4 7
 * 4 6 23
 * 4 7 13
 * 5 6 53
 * 6 7 25
*/
public class KRUSKALtest {
	static int N;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Node> NodeList = new PriorityQueue<>();
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList.add(new Node(to, from, value));
		}

		int size = NodeList.size();
		int totalvalue = 0;
		for (int i = 0; i < size; i++) {
			Node tempnode = NodeList.poll();
			int pto = findingParent(tempnode.to);
			int pfrom = findingParent(tempnode.from);
			if (!isSameParent(pto, pfrom)) {
				totalvalue += tempnode.value;
				union(tempnode.to, tempnode.from);
			}
		}
		System.out.println(totalvalue);

	}

	private static int findingParent(int x) { // 부모님 모시고와
		if (parents[x] == x) {
			return x;
		}
		return parents[x] = findingParent(parents[x]);
	}

	private static void union(int x, int y) { // 그룹에 넣기
		x = findingParent(x);
		y = findingParent(y);
		if (x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}

	static boolean isSameParent(int x, int y) { // 같은 그룹이니?
		if (x == y)
			return true;
		return false;
	}

	private static class Node implements Comparable<Node> {
		int to;
		int from;
		int value;

		public Node(int to, int from, int value) {
			super();
			this.to = to;
			this.from = from;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}
}
