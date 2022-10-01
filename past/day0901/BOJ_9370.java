package day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_9370 {
	static int n,m,t;
	static int s,g,h;
	static int [][] maps;
	static ArrayList<Integer> Destination;
	static boolean [] result;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int Total = Integer.parseInt(tokens.nextToken());
		for(int test = 1; test <= Total; test++) {
			tokens = new StringTokenizer(read.readLine());
			n = Integer.parseInt(tokens.nextToken());	//교차로 개수
			m = Integer.parseInt(tokens.nextToken());	//도로 개수
			t = Integer.parseInt(tokens.nextToken());	//목적지 후보 개수
			tokens = new StringTokenizer(read.readLine());
			s = Integer.parseInt(tokens.nextToken());	//출발지
			g = Integer.parseInt(tokens.nextToken());	//g와
			h = Integer.parseInt(tokens.nextToken());	//h사이의 도로를 지나감
			maps = new int [n+1][n+1];
			for(int i = 0; i < m; i++) {
				tokens = new StringTokenizer(read.readLine());
				int A = Integer.parseInt(tokens.nextToken());
				int B = Integer.parseInt(tokens.nextToken());
				int dist = Integer.parseInt(tokens.nextToken());
				maps[A][B] = maps[B][A] = dist;
			}
			Destination = new ArrayList<>();
			
			for(int i = 0; i < t; i++) {
				tokens = new StringTokenizer(read.readLine());
				Destination.add(Integer.parseInt(tokens.nextToken()));
			}
			Collections.sort(Destination);
			result = new boolean [n+1];
			ArrayList<Integer> start = new ArrayList<>();
			start.add(s);
			boolean [] visited = new boolean [n+1];
			visited[s] = true;
			BFSWAY(visited, 0,start);
			//목적지들끼리 연결돼있으면 안대
			for(int i = 0; i < t; i++) {
				for(int j = i+1; j < t; j++) {
					if(maps[i][j] != 0) continue; //목적지까지 이어져있음
				}
			}
			
			for(int d : Destination) {
				if(result[d]) sb.append(d + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);

		
	}

	private static void BFSWAY(boolean [] visited, int count, ArrayList<Integer> way) {
		int startpoint = way.get(way.size()-1);
		if(Destination.contains(startpoint) && way.contains(g) && way.contains(h)) {
			System.out.println("도착 : " + way.toString() + ", " + count);
			result[startpoint] = true;
			return;
		}
		for(int dest = 1; dest <= n; dest++) {
			if(visited[dest]) continue;
			if(maps[startpoint][dest] == 0) continue;
			visited[dest] = true;
			way.add(dest);
			BFSWAY(visited, count + maps[startpoint][dest], way);
			visited[dest] = false;
		}
		
	}
	
	private static class WalkWay implements Comparable<WalkWay>{
		int dest;
		int count;
		public WalkWay(int dest, int count) {
			super();
			this.dest = dest;
			this.count = count;
		}
		public int compareTo(WalkWay o) {
			return (this.count == o.count)? Integer.compare(this.dest, o.dest) : Integer.compare(this.count, o.count);  
		}
		@Override
		public String toString() {
			return "WalkWay [dest=" + dest + ", count=" + count + "]";
		}
		
		
	}
	
}