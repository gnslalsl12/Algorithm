package day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
	static int N, L;
	static int [][] maps;
	static int [][] maps_rev;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
		maps_rev = new int [N][N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(tokens.nextToken());
				maps[i][j] = num;
				maps_rev[j][N-i-1] = num;
			}
		}//mapping
		int linecount = 0;
		for(int i = 0; i < N; i++) {
			if(CheckLine(maps[i])) linecount++;
			if(CheckLine(maps_rev[i])) linecount++;
		}
		System.out.println(linecount);
	}
	
	private static boolean CheckLine(int [] templine) {
		int startpoint = templine[0];
		int count = 1;
		for(int i = 1; i < N; i++) {
			if(templine[i] == startpoint) {	//높이가 같음
				count++;
				continue;
			}else if(Math.abs(templine[i] - startpoint) == 1) {	//높이가 1 차이남
				if(startpoint < templine[i]) {	//높은 곳으로
					if(count < L) return false;
					//높이가 바뀜
					startpoint = templine[i];
					count = 1;
					continue;
				}else {							//낮은 곳으로
					if(count < 0) return false;
					startpoint = templine[i];
					count = 1-L;
					continue;
				}
			}else {		//높이가 2 이상임
				return false;
			}
		}
		if(count < 0) return false;
		return true;
	}
}