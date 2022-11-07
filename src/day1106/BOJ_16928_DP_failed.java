package day1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16928_DP_failed {
	static int N, M;
	static PriorityQueue<hoons> lads = new PriorityQueue<>();
	static PriorityQueue<hoons> snks = new PriorityQueue<>();
	static int [] count = new int [101];
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Arrays.fill(count, 110);
		count[1] = 0;
		for(int i = 2; i <= 7; i++) {
			count[i] = 1;
		}
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			lads.add(new hoons(from, to - from));
		}
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from  = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			snks.add(new hoons(from, from - to));
		}
		int start = 2;
		while(start <= 99) {
//			System.out.println("현재 : " + start);
			if(!snks.isEmpty() && snks.peek().index == start) {	//밟으면 안돼!
//				System.out.println("여기는 밟으면 안돼!!!!");
				snks.poll();
				count[start] = -1;
				start++;
				continue;
			}
			for(int i = 6; i >=1; i--) {
				if(start <= 7) break;
				if(count[start-i] != -1) {
					count[start] = Math.min(count[start-i] + 1, count[start]);
				}
			}
			if(!lads.isEmpty() && lads.peek().index == start) {	//밟아!
				count[start + lads.peek().value] = Math.min(count[start + lads.peek().value], count[start]);
				count[start] = -1;
//				System.out.println("사다리!!!!!!!!!!! : " + (start + lads.peek().value));
//				System.out.println(count[start+lads.peek().value]+ "<=" + count[start] );
				lads.poll();
			}
//			System.out.println("결과 값 : [" + count[start] + "]");
//			System.out.println("===================");
			start++;
		}
		for(int i = 94; i <= 99; i++) {
//			System.out.println(i + "번쨰 : " +  count[i]);
			if(count[i] == -1) continue;
			count[100] = Math.min(count[100], count[i] + 1);
		}
		
		System.out.println(count[100]);
		
	}
	
	private static class hoons implements Comparable<hoons>{
		int index;
		int value;
		public hoons(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
		@Override
		public int compareTo(hoons o) {
			return Integer.compare(this.index, o.index);
		}
		
	}

}
