package day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1700 {

	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		PriorityQueue<Integer> AbscUseCount = new PriorityQueue<>(Collections.reverseOrder());
		int [] UseCount = new int [K+1];
		for(int i = 0; i < K; i++) {
			UseCount[Integer.parseInt(tokens.nextToken())]++;
		}
		for(int i = 0; i < K; i++) {
			if(UseCount[i] != 0) AbscUseCount.add(UseCount[i]);
		}
//		while(!AbscUseCount.isEmpty()) {
//			
//		}
		int size = AbscUseCount.size() - N;
		if(size < 0) System.out.println(0);
		else System.out.println(size);
	}

}
