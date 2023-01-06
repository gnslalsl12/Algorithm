package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N,K;
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Moveinfo starting = new Moveinfo(N,0);
		BFSQ(starting);
	}
	static int count;
	static class Moveinfo{
		int index;
		int count;
		public Moveinfo(int index, int count) {
			super();
			this.index = index;
			this.count = count;
		}
	}
	static boolean [] visited = new boolean [100001];
	static void BFSQ(Moveinfo start) {
		Queue<Moveinfo> BFSQueue = new LinkedList<>();
		BFSQueue.add(start);
		while(!BFSQueue.isEmpty()) {
			Moveinfo temp = BFSQueue.poll();
			if(temp.index < 0 || temp.index > 100000) continue;
			if(visited[temp.index]) continue;
			visited[temp.index] = true;
			if(temp.index == K) {
				System.out.println(temp.count);
				return;
			}
			if(temp.index > K) {
				BFSQueue.add(new Moveinfo(temp.index-1, temp.count+1));
			}else {
				BFSQueue.add(new Moveinfo(temp.index+1, temp.count+1));
				BFSQueue.add(new Moveinfo(temp.index-1, temp.count+1));
				BFSQueue.add(new Moveinfo(temp.index*2, temp.count+1));
			}
		}
	}
}