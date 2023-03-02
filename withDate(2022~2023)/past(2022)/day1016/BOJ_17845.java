package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17845 {
	static int N, K;
	static int[] result;
	static CLS[] classes;
	static int output;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		int [][] result = new int [K+1][N+1];
		classes = new CLS[K+1];
		for (int i = 1; i <= K; i++) {
			tokens = new StringTokenizer(read.readLine());
			int imp = Integer.parseInt(tokens.nextToken());
			int time = Integer.parseInt(tokens.nextToken());
			classes[i] = new CLS(imp, time);
		}
		
		for(int i = 1; i <= K; i++) {
			for(int temptime = 1; temptime <= N; temptime++) {
				if(classes[i].time <= temptime) {
					result[i][temptime] = Math.max(result[i-1][temptime], result[i-1][temptime - classes[i].time] + classes[i].imp);
				}else {
					result[i][temptime] = result[i-1][temptime];
				}
			}
		}
		System.out.println(result[K][N]);
	}
	private static class CLS {
		int imp;
		int time;

		public CLS(int imp, int time) {
			super();
			this.imp = imp;
			this.time = time;
		}
	}
}