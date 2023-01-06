package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1167 {
	static ArrayList<Node>[] NodeList;
	static int N;
	static int MaxNodePoint1;
	static int ResultDist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			while (true) {
				int to = Integer.parseInt(tokens.nextToken());
				if (to == -1)
					break;
				int value = Integer.parseInt(tokens.nextToken());
				NodeList[from].add(new Node(to, value));
			}
		}
		DFS(1, 0, new boolean[N + 1]);
		DFS(MaxNodePoint1, 0, new boolean[N + 1]);
		System.out.println(ResultDist);

	}

	private static void DFS(int startpoint, int totaldist, boolean[] visited) {
		if (totaldist > ResultDist) {
			ResultDist = totaldist;
			MaxNodePoint1 = startpoint;
		}
		visited[startpoint] = true;
		for (Node pop : NodeList[startpoint]) {
			if (visited[pop.to])
				continue;
			DFS(pop.to, totaldist + pop.value, visited);
			visited[pop.to] = false;
		}
	}

	private static class Node {
		int to;
		int value;
		public Node(int to, int value) {
			super();
			this.to = to;
			this.value = value;
		}
	}
}