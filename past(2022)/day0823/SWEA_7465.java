package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7465 {
	
	static int N, M;
	static int [] KnowShip;
	static int Bitmask;
	//BFS
	//Bitmasking
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= T; test++) {
			Bitmask = 0;
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			KnowShip = new int [N+1];	//N명(개)의 관계도 리스트
			for(int i = 0; i < M; i++) {
				tokens = new StringTokenizer(read.readLine());
				int A = Integer.parseInt(tokens.nextToken());
				int B = Integer.parseInt(tokens.nextToken());
				KnowShip[A] |= 1<<B;
				KnowShip[B] |= 1<<A;
			}
			int count = 0;
			for(int i = 1; i<= N; i++) {
				if((Bitmask & 1<<i) != 0) { //현재 보려는 애가 이전에 본 애임
					continue;
				}
				BFSbitmasking(i);
				count++;
			}
			sb.append("#" + test + " " + count + "\n");
			
		}
		System.out.print(sb);
		
	}

	private static void BFSbitmasking(int startpoint) {
		Queue<Integer> BFSq = new LinkedList<>();
		
		BFSq.add(startpoint);
		
		while(!BFSq.isEmpty()) {
			int temp = BFSq.poll();
			Bitmask |= 1<<temp;	//현재 보려는 애를 true
			
			for(int i = 1; i<= N; i++) {
				if((Bitmask & 1<<i) != 0) continue;
				if((KnowShip[temp] & 1<<i) != 0) { //temp(나)와 i(상대방)이 아는 사이다 : 
					Bitmask |= 1<<i;	//전체에서 다음 목적지 true
					BFSq.add(i);
				}
			}
		}
	}
}