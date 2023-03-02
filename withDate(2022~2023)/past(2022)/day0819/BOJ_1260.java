package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	static int N, M, V;
	static int[][] maps;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());
		maps = new int [N+1][N+1];
		for(int i = 0; i < M ; i++) {
			tokens =new StringTokenizer(read.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			maps[A][B] = maps[B][A] = 1;
		}
		boolean [] visitedDFS = new boolean[N+1];
		sb.append(V);
		DFS(V,visitedDFS);
		System.out.println(sb);
		
		sb = new StringBuilder();
		boolean [] visitedBFS = new boolean[N+1];
		BFS(V,visitedBFS);
		System.out.print(sb);
	}
	
	private static void DFS(int startpoint, boolean[] visit) {
		visit[startpoint] = true;
		if(startpoint != V) {
			sb.append(" " + startpoint);
		}
		for(int to = 1 ; to <= N ; to++) {
			if(maps[startpoint][to] == 1 && visit[to] == false) {
				DFS(to,visit);
			}
		}
	}
	
	private static void BFS(int startpoint, boolean[] flags) {
		sb.append(startpoint);
		Queue<Integer> BFSq = new LinkedList<>();
		
		BFSq.add(startpoint);
		flags[startpoint] = true;
		
		while(!BFSq.isEmpty()) {
			int from = BFSq.poll();
			if(from != startpoint) {
				sb.append(" " + from);
			}
			for(int to = 1; to <= N ; to++) {
				if(maps[from][to] == 1 && flags[to] == false) {
					flags[to] = true;
					BFSq.add(to);
				}
			}
		}
	}
	
}