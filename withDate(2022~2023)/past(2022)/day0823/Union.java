package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Union {
	static int V,E;
	static Edge [] edgeList;
	static int [] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		edgeList = new Edge[E];
		
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(read.readLine());
			edgeList[i] = new Edge(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			
		}
		make();
		Arrays.sort(edgeList); //
		int result = 0;
		int count = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) { // true => 서로 떨어져있던 집합임
				result += edge.w;
				if(++count == V-1) break;
				
			}
		}
		System.out.println(result);
		
		
	}
	static class Edge implements Comparable<Edge>{	//간선 리스트
		int from;
		int to;
		int w;
		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}

	static void make() {	//크기가 1인 서로소 집합 생성기
		parents = new int[V];
		for(int i = 0; i < V; i++) {
			parents[i] = i;
		}
		
	}
	
	static int find(int a) { // a의 대표자 찾기
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]); //나의 부모의 부모는 누구? => 재귀
	}	//path Compression
	
	static boolean union(int a, int b) { //리턴값 true : union 성공
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;	//이미 같은 집합
		
		parents[bRoot] = aRoot; //흡수시키기
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
