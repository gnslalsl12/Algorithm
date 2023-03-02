package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_re {
	static int N;
	static int M;
	static int [][] maps;
	static ArrayList<dirXY> CCTVlist;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌
	static int [][][] CCTVdeltas = 
			{{},	//0
				{{0},{1},{2},{3}},	//1
					{{0,2},{1,3}},	//2
						{{0,1},{1,2},{2,3},{3,0}},	//3
							{{0,1,2},{1,2,3},{2,3,0},{3,0,1}}, // 4
								{{0,1,2,3}}};	//5
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		//상 좌 하 우(시계방향)
		int ways = 0;
		maps = new int[N][M];
		CCTVlist = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0 ; j < M ; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] > 0 && maps[i][j] < 6) {
					CCTVlist.add(new dirXY(i,j,maps[i][j]));
				}else if(maps[i][j] == 0) {
					ways++;			//빈공간
				}
			}
		}							//매핑 완료 & CCTVlist 정렬(CCTV 종류 큰 거부
		max = Integer.MIN_VALUE;
		DFScctv(0, 0);
		System.out.println(ways-max);
	}
	static int max;
	static void DFScctv(int index, int count) {
		if(index == CCTVlist.size()) {
			max = Math.max(max, count);
			return;
		}
		dirXY tempCCTV = CCTVlist.get(index);
		for(int i = 0; i < CCTVdeltas[tempCCTV.number].length; i++) {
			int views = tempCCTV.CheckViews(CCTVdeltas[tempCCTV.number][i], -1); //봤다 체크하면서 보기
			DFScctv(index+1, count + views);	//현재 맵 상태에서 재귀 해보기
			tempCCTV.CheckViews(CCTVdeltas[tempCCTV.number][i], +1);//되돌리기
		}
	}
	static boolean isIn(int x, int y) {
		return (x < N && x >= 0 && y < M && y >= 0)? true : false;
	}
	static class dirXY implements Comparable<dirXY>{
		int x;
		int y;
		int number;
		public dirXY(int x, int y, int number) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
		}
				//dir = {2,3} 오고 {0,1} 오고 그럼. 또는 {1,2,3},{2,3,0},{3,0,1},{0,1,2}
		public int CheckViews(int [] dir, int viwed) {
			int howmanyviews = 0;
			for(int v = 0; v < dir.length; v++) {
				for(int far = 1;; far++) {
					int tempx = this.x + deltas[dir[v]][0]*far;
					int tempy = this.y + deltas[dir[v]][1]*far;
					if(!isIn(tempx,tempy)) break;	//영역 벗어나면 break;
					if(maps[tempx][tempy] == 6) break; //벽 만나면 break;
					if(maps[tempx][tempy] >0  && maps[tempx][tempy] < 6) continue; //카메라 만나면 건너뛰고
					//영역 안 벗어나고 벽 아니고 카메라 아니면 +1
					if(maps[tempx][tempy] == 0) howmanyviews++;
					maps[tempx][tempy] += viwed;		//viwed = 1 : 봤다, viwed = -1 : 이제 안 보겠다
				}
			}
			return howmanyviews;
		}
		@Override
		public int compareTo(dirXY o) {
			return o.number - this.number;
		}
	}
}