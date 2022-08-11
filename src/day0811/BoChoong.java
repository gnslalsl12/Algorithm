package day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class XY {
	int x;
	int y;
	public XY() {
		super();
	}
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BoChoong {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		int [][] delta = {{1,0},{-1,0},{0,1},{0,-1}};
		int t = Integer.parseInt(read.readLine());

		for(int test = 1; test <= t; test++) {
			int Hs = 0;
			int N = Integer.parseInt(read.readLine());
			
			String [][] maps = new String [N][N];
			ArrayList<XY> ASensors = new ArrayList<>();
			ArrayList<XY> BSensors = new ArrayList<>();
			ArrayList<XY> CSensors = new ArrayList<>();
			
			for(int i = 0; i < N ; i++) {
				String line = read.readLine();
				maps[i] = line.split("");
				for(int j = 0 ; j < N ;j++) {
					if(maps[i][j].equals("H")) {
						Hs++;
					}else if(maps[i][j].equals("A")) {
						ASensors.add(new XY(i,j));
					}else if(maps[i][j].equals("B")) {
						BSensors.add(new XY(i,j));
					}else if(maps[i][j].equals("C")) {
						CSensors.add(new XY(i,j));
					}
					
				}
			}
			
			
			for(XY temp : ASensors) {
				for(int i = 1; i <= 1; i++) {
					for(int j = 0; j < 4; j++) {
						if(temp.x+delta[j][0]*i >= 0 && temp.y+delta[j][1]*i >= 0 && temp.x+delta[j][0]*i <= N-1 && temp.y+delta[j][1]*i <= N-1) {
							if(maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i].equals("H")) {
								maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i]="X";
								Hs--;
							}
						}
					}
				}
			}

			for(XY temp : BSensors) {
				for(int i = 1; i <= 2; i++) {
					for(int j = 0; j < 4; j++) {
						if(temp.x+delta[j][0]*i >= 0 && temp.y+delta[j][1]*i >= 0 && temp.x+delta[j][0]*i <= N-1 && temp.y+delta[j][1]*i <= N-1) {
							if(maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i].equals("H")) {
								maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i]="X";
								Hs--;
							}
						}
					}
				}
			}

			for(XY temp : CSensors) {
				for(int i = 1; i <= 3; i++) {
					for(int j = 0; j < 4; j++) {
						if(temp.x+delta[j][0]*i >= 0 && temp.y+delta[j][1]*i >= 0 && temp.x+delta[j][0]*i <= N-1 && temp.y+delta[j][1]*i <= N-1) {
							if(maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i].equals("H")) {
								maps[temp.x+delta[j][0]*i][temp.y+delta[j][1]*i]="X";
								Hs--;
							}	
						}
					}
				}
			}
			
			System.out.println("#" + test + " " + Hs);
		}
	}
}