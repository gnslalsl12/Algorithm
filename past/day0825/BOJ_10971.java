package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971 {
	static int N;
	static int min;
	static int [][] maps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		Perm(0, 0, new int [N]);
		System.out.println(min);
	}
	static void Perm(int countnow, int BM, int [] Value) {
		if(countnow == N) {
			min = Math.min(Calc(Value), min);
			return;
		}
		for(int i = 0; i < N ; i++) {
			if((BM & 1<<i) != 0) {
				continue;
			}
			BM |= 1<<i;
			Value[countnow] = i;
			Perm(countnow + 1, BM , Value);
			BM &= ~(1<<i);
		}
	}
	
	static int Calc(int[] Value) {
		int totalsum = 0;
		for(int i = 1; i < N ; i++) {
			int temp = maps[Value[i-1]][Value[i]];
			if(temp == 0) {
				return Integer.MAX_VALUE;
			}
			totalsum += temp;
		}
		int temp = maps[Value[N-1]][Value[0]];
		if(temp == 0) {
			return Integer.MAX_VALUE;
		}
		totalsum += temp;
		return totalsum;
	}
}