package day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606 {
	static int N,M;
	static boolean [][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N+1][N+1];
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = true;
		}
		floyd();
		int r =0;
		for(int i=1; i <= N; i++) {
			if(maps[1][i])r++;
			
		}
		System.out.println(r );
	}
	
	private static void floyd() {
		for(int k =1; k <= N; k++) {
			for(int i = 1; i<=N; i++){
				for(int j = 1; j <= N; j++) {
					if(maps[i][k] && maps[k][j]) {
						maps[i][j] = true;
					}
				}
			}
		}
	}

}
