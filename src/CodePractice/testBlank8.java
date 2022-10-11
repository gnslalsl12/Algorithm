package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank8 {
	static int N, M;
	static ArrayList<Node>[] LineList;
	static boolean[] visits;
	static int[] Dist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		visits = new boolean[N + 1];
		Dist = new int[N + 1];
		LineList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			LineList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int node = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			LineList[from].add(new Node(node, value));
		}
		DIJKSTRA(1);

	}

	private static void DIJKSTRA(int start) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(start, 0));
		Dist[start] = 0;
		while (!PQ.isEmpty()) {
			Node now = PQ.poll();
			if (visits[now.nodepoint])
				continue;
			visits[now.nodepoint] = true;
			for (Node next : LineList[now.nodepoint]) {
				if (visits[next.nodepoint])
					continue;
				if (Dist[next.nodepoint] > now.value + next.value) {
					Dist[next.nodepoint] = now.value + next.value;
					PQ.add(new Node(next.nodepoint, Dist[next.nodepoint]));
				}
				/*if (Dist[next.nodepoint] > Dist[now.nodepoint] + next.value) {
					Dist[next.nodepoint] = Dist[now.nodepoint] + next.value;
					PQ.add(next);
				}	이게 안되는 이유 : pop 된 next에 들어있는 next.value값은 
				이전 노드에서 next.point로 가는 비용이므로 그 비용을 다시 쓰는 게 아님
				다음에 비교해서 더해줘야하는 값은 바로 해당 포인트까지 쌓인 경로의 비용 합산값임 
				*/
			}
		}
	}

	private static class Node implements Comparable<Node> {
		int nodepoint;
		int value;

		public Node(int nodepoint, int value) {
			super();
			this.nodepoint = nodepoint;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}
}
