package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {
	static int R, C;
	static int [][] maps;
	static int [][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		maps = new int[R][C];
		for(int i = 0; i < R ; i++) {
			String templine = read.readLine();
			for(int j = 0; j < C ; j++) {
				maps[i][j] = (templine.charAt(j) - 'A');
			}
		}//매핑 완료
		
		boolean [] Visited = new boolean[26];
		Visited[maps[0][0]] = true;				//처음 좌표값은 무조건 지남
		BFSalpha(new dirXY(0,0,1), Visited);;
		
		System.out.println(max);
	}
	
	private static boolean isIn(dirXY test) {
		return test.x>=0 && test.x<R && test.y>=0 && test.y<C;
	}
	
	private static void BFSalpha(dirXY now, boolean[] visits) {
		for(int i = 0; i < 4 ; i++) {
			dirXY nextXY = new dirXY(now.x + deltas[i][0], now.y + deltas[i][1], now.count + 1);
			if(isIn(nextXY) && visits[getNumber(nextXY)] == false) {
				visits[getNumber(nextXY)] = true;
				BFSalpha(nextXY, visits);
				visits[getNumber(nextXY)] = false;
			}
		}
		max = Math.max(max, now.count);
	}
	
	private static int getNumber(dirXY get) {	//dirXY로 지도상의 값 바로 구하기
		return maps[get.x][get.y];
	}
	
	private static class dirXY{
		int x;
		int y;
		int count;
		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
	}
}
