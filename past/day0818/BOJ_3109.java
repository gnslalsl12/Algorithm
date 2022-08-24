package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {
	static int R, C;
	static int[][] deltas = {{-1,1}, {0,1}, {1,1}};
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		char [][] maps = new char[R][C];
		char [][] tempmaps = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			String templine = read.readLine();
			for(int j = 0 ; j< C ; j++) {
				maps[i][j] = templine.charAt(j);
			}
		}//mapping
		int Maxcount = 0;
		for(int i = 0; i < R ; i++) {
			System.arraycopy(maps[i], 0, tempmaps[i], 0, maps[i].length);
		}//map 초기화
		count = 0;
		for(int i = 0 ; i < R ; i++) {
			if(maps[i][0] == '.') {
				UPback = true;
				DFStoUPSIDE(i,0, tempmaps);
			}
		}
		
		
		
		Maxcount = count;
		count = 0;
		for(int i = 0; i < R ; i++) {
			System.arraycopy(maps[i], 0, tempmaps[i], 0, maps[i].length);
		}//map 초기화
		for(int i = R-1 ; i >= 0 ; i--) {
			if(maps[i][0] == '.') {
				DOWNback = true;
				DFStoDOWNSIDE(i,0, tempmaps);
			}
		}
		System.out.println(Math.max(count, Maxcount));
	}
	static boolean UPback;
	static void DFStoUPSIDE(int x, int y, char [][] maps) {
		if(y == C-1) {
			maps[x][y] = '-';
			count++;
			UPback = false;
			return;
		}//끝에 도달(성공)
		else if(maps[x][y] == 'x') {
			UPback = true;
			return;
		}//벽에 도달(실패)
		for(int i = 0; i < 3 ; i++) {
			int tempx = x + deltas[i][0];
			int tempy = y + deltas[i][1];
			if(isIn(tempx,tempy)) {
				if(maps[tempx][tempy] == '.') {
					maps[x][y] = '-';					
					DFStoUPSIDE(tempx, tempy, maps);
				}
			}
			if(!UPback) {
				break;
			}
		}
	}
	static boolean DOWNback;
	static void DFStoDOWNSIDE(int x, int y, char [][] maps) {
		if(y == C-1) {
			maps[x][y] = '-';
			count++;
			DOWNback = false;
			return;
		}
		else if(maps[x][y] == 'x') {
			DOWNback = true;
			return;
		}//벽에 도달(실패)
		for(int i = 2; i >= 0 ; i--) {
			int tempx = x + deltas[i][0];
			int tempy = y + deltas[i][1];
			if(isIn(tempx,tempy)) {
				if(maps[tempx][tempy] == '.') {
					maps[x][y] = '-';
					DFStoDOWNSIDE(tempx, tempy, maps);
				}
			}
			if(!DOWNback) {
				break;
			}
		}
	}
	static boolean isIn(int x, int y) {
		return (x >= 0 && x < R && y >= 0 && y < C)? true : false;
	}
}