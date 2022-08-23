package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static int N, M;
	static ArrayList<Integer>[] Friends;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Friends = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			Friends[i] = new ArrayList<>();
		}
		for(int i = 0; i < M ; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			Friends[a].add(b);
			Friends[b].add(a);
			
		}
//		PriorityQueue<FriendsCountforPQ> LowestFirendsCount = new PriorityQueue<>();
//		for(int i = 0; i < N; i++) {
//			LowestFirendsCount.add(new FriendsCountforPQ(i, Friends[i].size()));	//친구 수가 적은 놈들부터
//		}
		
		
		result = 0;
		for(int i = 0; i < N; i++) {
			DFS(i);
			if(result == 1) break;
		}
		
//		while(!LowestFirendsCount.isEmpty()) {
//
//			DFS(LowestFirendsCount.poll().Num);
//			if(result == 1) break;
//		}
		System.out.println(result);
		
		
		
		
	}
	static int result;
	private static void DFS(int start) {
		Stack<withCount> Search = new Stack<>();
		boolean [] visits = new boolean[N];
		Search.add(new withCount(start, 0, visits));
		visits[start] = true;
		while(!Search.isEmpty()) {
			withCount temp = Search.pop();
			if(temp.Count == 4) {
				result = 1;
				return;
			}
			for(int i = 0; i < Friends[temp.Num].size(); i++) {	//temp의 친구 관계수 가지수 전부 다 탐색
				if(temp.visits[Friends[temp.Num].get(i)] == false) {
					temp.visits[Friends[temp.Num].get(i)] = true;
					Search.push(new withCount(Friends[temp.Num].get(i), temp.Count+1, temp.visits.clone()));
					temp.visits[Friends[temp.Num].get(i)] = false;
				}
			}
			
			
			
			
			
		}
		
		
		
	}
	
//	private static class FriendsCountforPQ implements Comparable<FriendsCountforPQ>{
//		int Num;
//		int friendscount;
//		public FriendsCountforPQ(int num, int friendscount) {
//			super();
//			Num = num;
//			this.friendscount = friendscount;
//		}
//		@Override
//		public int compareTo(FriendsCountforPQ o) {
//			// TODO Auto-generated method stub
//			return Integer.compare(this.friendscount, o.friendscount);
//		}
//		
//		
//		
//	}
	
	private static class withCount{
		int Num;
		int Count;
		boolean [] visits;
		public withCount(int num, int count, boolean [] visits) {
			super();
			Num = num;
			Count = count;
			this.visits = visits;
		}
		@Override
		public String toString() {
			return "[Num=" + Num + "번 친구, Count=" + Count + "카운트, visits=" + Arrays.toString(visits) + "]";
		}
		
		
		
	}
	
}
