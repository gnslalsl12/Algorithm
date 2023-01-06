package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16236 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		int [][] maps= new int[n][n];
		int temp = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < n ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < n ; j++) {
				temp = Integer.parseInt(tokens.nextToken());
				if(temp == 9) {
					x = i;
					y = j;
				}
				maps[i][j] = temp;
			}
		}
		
		for(int i = -n; i<=n; i++) {
			for(int j = -n; j <= n ; j++) {
				if(maps[x+i+j])
			}
		}
		
		
		
	}

}
