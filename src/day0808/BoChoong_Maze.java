package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoChoong_Maze {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(read.readLine());
		
		for(int test = 1; test <= t; test++) {
			
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int n = Integer.parseInt(tokens.nextToken());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			int js = Integer.parseInt(tokens.nextToken());
			int	[] jsxy = new int [js];

			
			tokens = new StringTokenizer(read.readLine());
			for(int i = 0; i < js; i++) {
				jsxy[i] = Integer.parseInt(tokens.nextToken())*100 + Integer.parseInt(tokens.nextToken());
			}
			
			int movecount = Integer.parseInt(read.readLine());
			tokens = new StringTokenizer(read.readLine());
			outpoint :
			for(int i = 0; i < movecount ; i++) {
//				System.out.println("x, y : " + x + ", " + y);
				int dir = Integer.parseInt(tokens.nextToken());
				int mov = Integer.parseInt(tokens.nextToken());
				switch(dir) {
				case(1):{
					x -= mov;
					break;
				}
				case(2):{
					y += mov;
					break;
				}
				case(3):{
					x+=mov;
					break;
				}
				case(4):{
					y-=mov;
					break;
				}
				}
				for(int tempj : jsxy) {
					if(tempj == x*100 + y) {
						x = 0;
						y = 0;
						break outpoint;
					}
				}
				if(x > n-1 || x < 1 || y > n-1 || y < 1) {
//					System.out.println("범위 밖");
					x = 0;
					y = 0;
					break outpoint;
				}
				
			}
			
			
			
			System.out.printf("#%d %d %d\n", test, x, y);
//			System.out.println("===============");
			
			
		}
	}

}
