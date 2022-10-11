package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//3 9
//1 2 2
//1 4 1
//1 3 5
//2 3 3
//2 4 2
//3 4 3 
//3 5 1
//4 5 1
//5 6 2
//3 6 5

public class testBlank6 { // BELLMANFORD
	static int N, M;
	static Node[] Conns;
	static long[] Dist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Conns = new Node[M];

		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			Conns[i] = new Node(from, to, value);
		}

		Dist = new long[N + 1]; // i 노드에서 출발하는 최소 거리의 값들
		Arrays.fill(Dist, Integer.MAX_VALUE);
		if (BELLMANFORD(1)) { // true : 음수 순환이 존재한다!
			System.out.println(-1);
		} else {
			
		}

	}

	private static boolean BELLMANFORD(int start) {
		Dist[start] = 0;
		// N번 반복 (음수 간선 순환을 체크 안 하려면 N-1번 반복하면 됨
		for (int i = 0; i < N; i++) {
			// i하나씩 모든 간선을 확인)
			for (int j = 0; j < M; j++) {
				int from = Conns[j].from;
				int to = Conns[j].to;
				int value = Conns[j].value;

				if (Dist[from] == Integer.MAX_VALUE) {//해당 노드로의 값이 아직 찾아지지 않음
					continue;
				}
				if (Dist[to] > Dist[from] + value) {
					Dist[to] = Dist[from] + value;
					if (i == N - 1) { // N-1번쨰까지 와서 다음으로(맨 첫 시작 노드) 갔는데 값이 갱신됨 => 음수 순환이 존재
						return true;
					}
				}
			}
		}
		return false;
	}

	private static class Node {
		int from;
		int to;
		int value;

		public Node(int from, int to, int value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}

	}
}
