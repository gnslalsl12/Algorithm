package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		
		DupComb(0, 1, 0);
	}

	
	private static void DupComb(int countnow, int startnow, int SelBM) {
		if(countnow == N) {
			for(int i = 1; i <= N; i++) {
				if((SelBM & 1<<i) != 0) {
					System.out.printf("%d ", i);
				}
			}
			System.out.println();
			return;

		}
		
		SelBM |= 1<<startnow;
		DupComb(countnow+1, startnow+1, SelBM);
		SelBM &= ~(1<<startnow);
		DupComb(countnow+1, startnow+1, SelBM);
		
	}

}
