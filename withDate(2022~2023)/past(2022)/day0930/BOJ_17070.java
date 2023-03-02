package day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BitMasking

public class BOJ_17070 {
	static int N;
	static int[] maps;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new int[N];
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int templine = 0;
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(tokens.nextToken()) == 1) {
					templine |= 1<<j;
				}
			}
			maps[i] = templine;
		} // 매핑 완료
		dirXY startpoint = new dirXY(0, 1, 1);
		
		search(startpoint);
		
		if (result > 1000000)
			result = 1000000;
		System.out.println(result);

	}

	static void search(dirXY point) {
		int nowx = point.x;
		int nowy = point.y;
		if (nowx == N - 1 && nowy == N - 1) {
			result++;
			return;
		}
		int newx;
		int newy;
		switch (point.type) {
		case (1): {
			//그대로 오른쪽으로 한 칸 이동
			newx = nowx;
			newy = nowy+1;
			if(isIn(newx, newy) && (maps[newx] & (1<<newy)) == 0) {
				search(new dirXY(newx, newy, 1));
			}
			//대각선으로 회전시키고 오른쪽으로 한 칸 이동
			newx = nowx+1;
			newy = nowy + 1;
			if(isIn(newx, newy) &&  (maps[newx] & (1<<newy)) == 0 &&  (maps[nowx+1] & (1<<nowy)) == 0 &&  (maps[nowx] & (1<<nowy+1)) == 0) {
				search(new dirXY(newx, newy, 3));
			}
			break;
		}
		case (2): {
			//그대로 아로 한 칸 이동
			newx= nowx+1;
			newy= nowy;
			if(isIn(newx, newy) && (maps[newx] & (1<<newy)) == 0) {
				search(new dirXY(newx, newy, 2));
			}
			//대각선으로 회전시키고 아래로 한 칸 이동
			newx = nowx+1;
			newy = nowy+1;
			if(isIn(newx, newy) &&  (maps[newx] & (1<<newy)) == 0 &&  (maps[nowx+1] & (1<<nowy)) == 0 &&  (maps[nowx] & (1<<nowy+1)) == 0) {
				search(new dirXY(newx, newy, 3));
			}
			break;
		}
		case (3): {
			newx = nowx;
			newy = nowy+1;
			if(isIn(newx, newy) && (maps[newx] & (1<<newy)) == 0) {
				search(new dirXY(newx, newy, 1));
			}
			newx = nowx+1;
			newy = nowy;
			if(isIn(newx, newy) && (maps[newx] & (1<<newy)) == 0) {
				search(new dirXY(newx, newy, 2));
			}
			newx = nowx+1;
			newy = nowy+1;
			if(isIn(newx, newy) &&  (maps[newx] & (1<<newy)) == 0 &&  (maps[nowx+1] & (1<<nowy)) == 0 &&  (maps[nowx] & (1<<nowy+1)) == 0) {
				search(new dirXY(newx, newy,3));
			}
			break;
		}
		}

	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static class dirXY {
		int x;
		int y;
		int type;
		public dirXY(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}


	}

}
