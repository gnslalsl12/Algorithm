package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18405 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens= new StringTokenizer(read.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int k = Integer.parseInt(tokens.nextToken());
		int [][] maps = new int [n+1][n+1];
		for(int i = 1; i<=n; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 1; j <= n ; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}//maps 끝
		
		tokens = new StringTokenizer(read.readLine());
		int s = Integer.parseInt(tokens.nextToken());
		int x = Integer.parseInt(tokens.nextToken());
		int y = Integer.parseInt(tokens.nextToken());
		int t = 0;
		
		for(int i = -s; i <= s; i++) {
			if(maps[x-i][y]!=0) {
				
			}
			if(maps[x][y-i]!=0) {
				
			}
			if(maps[x+i][y+(s-i)]!=0)
		}
		
		
		
		
	}

}
