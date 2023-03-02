package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_비상연락 {
	static Queue<CallP> CallQ = new LinkedList<>();
	static boolean [] visited;
	static CallP Finalone;
	static boolean [][] maps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for(int test = 1; test <= 10; test++) {
			StringBuilder sb = new StringBuilder();
			Finalone = new CallP(-1, -1);
			maps = new boolean [101][101];
			visited = new boolean [101];
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			if(!tokens.hasMoreTokens()) break;
			int Total = Integer.parseInt(tokens.nextToken());
			int startpoint = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			while(tokens.hasMoreTokens()) {
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				maps[from][to] = true;
				
			}
			visited[startpoint] = true;
			CallMain(startpoint);
			
			sb.append("#" + test + " "+ Finalone.num + "\n");
			System.out.print(sb);
		}
	}

	private static void CallMain(int startpoint) {
		
		CallQ.add(new CallP(startpoint, 0));
		
		while(!CallQ.isEmpty()) {
			CallP temp = CallQ.poll();
			if(Finalone.compareTo(temp) >= 0) {	//temp가 더 클때
				Finalone = temp;
			}
			Call(temp);
		}
		
	}
	private static void Call(CallP startpoint) {
		for(int i = 1; i< 100; i++) {
			if(maps[startpoint.num][i] && visited[i] == false) {
				visited[i] = true;
				CallQ.add(new CallP(i,startpoint.count + 1));
			}
		}
	}
	
	private static class CallP implements Comparable<CallP>{
		int num;
		int count;
		public CallP(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(CallP o) {
			return (this.count == o.count)? o.num - this.num : o.count - this.count;
		}
		
	}
}