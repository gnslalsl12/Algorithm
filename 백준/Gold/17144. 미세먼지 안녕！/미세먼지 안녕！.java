import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int T;
	static dirXYairc AirC;
	static int [][] maps;
	static Queue<dirXYdirt> DirtsD = new LinkedList<>();//공청 위
	static Queue<dirXYdirt> DirtsU = new LinkedList<>(); //공처 아래
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		maps = new int [R][C];
		AirC = new dirXYairc(0, R, 0);	//공청 발견 전까지는 모두 윗쪽 미세먼지 처리하기 위해 R-1
		boolean AirF = false;
		for(int i = 0; i < R; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < C; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == -1) {
					if(AirF == false) {//아직 못찾았을 때만
						AirC = new dirXYairc(i, i+1, j);
						AirF = true;	//찾음
					}
				}else if(maps[i][j] != 0) {
					SplitDirt(new dirXYdirt(i, j));
				}
			}
		}
		
		//맵 완성하고 공청 위치 얻고 위아래 구분지어서 미세먼지 분류
		int dirtcount = 0;
		for(int test= 0; test < T; test++) {
			while(!DirtsU.isEmpty()) {
				SpreadDirt(DirtsU.poll());	//하나씩 빼서 맵에 확산시켜줌
			}
			while(!DirtsD.isEmpty()) {
				SpreadDirt(DirtsD.poll());	//하나씩 빼서 맵에 확산시켜줌
				
				
			}//미세먼지 확산시키고 분류하고 값 없애고
			//바람 불기
			
//			System.out.println("=========먼지===========");
//			for(int [] temp : maps) {
//				for(int tt : temp) {
//					System.out.printf("%d " , tt);
//				}
//				System.out.println();
//			}
			
			BlowU();
			BlowD();	//맵 값이 확산됨
//			if(test == 1) {
//				continue;
//			}
//			System.out.println("=========바람 분 후===========");
//			for(int [] temp : maps) {
//				for(int tt : temp) {
//					System.out.printf("%d " , tt);
//				}
//				System.out.println();
//			}
			dirtcount = 0;
//			System.out.println("=========블로우===========");
//			for(int [] temp : maps) {
//				for(int tt : temp) {
//					System.out.printf("%d " , tt);
//				}
//				System.out.println();
//			}
			for(int i = 0; i < R; i++) {
				for(int j = 0 ; j < C; j++) {
					if(maps[i][j] >0) {//미세먼지 있으면
						dirtcount += maps[i][j];
						SplitDirt(new dirXYdirt(i, j));
					}
				}
			}
			
		}
		System.out.println(dirtcount);
		//바람 불고 맵 돌면서 있으면 split추가
		
		
		
		
		
	}
	
	private static int[][] deltasU = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; //반시계방향
	private static int[][] deltasD = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }};	//시계
	private static void BlowU() {
		int RotateLines = Math.min(C, AirC.xD+1)/2;	//돌아가는 선의 수
		for(int l = 0; l < 1; l++) { //돌아가는 라인 하나씩
			int x = l;
			int y = l;
			int EdgeValue = maps[x][y];
			
			int idx = 0;
			
			while(idx < 4) {
				
				int tempx = x + deltasU[idx][0];
				int tempy = y + deltasU[idx][1];

				
				if(tempx >= l && tempy >= l && tempx <= AirC.xU-l && tempy <= C-l-1) {
					if(x == AirC.xU && y == 0) {
						x = tempx;
						y = tempy;
						continue;
					}
					if(tempx == AirC.xU && tempy ==0) {
						maps[x][y] = 0;
						x = tempx;
						y = tempy;
						continue;
					}
					maps[x][y] = maps[tempx][tempy];
					x = tempx;
					y = tempy;
					continue;
				}
				idx++;
			}
			
			
			maps[l+1][l] = EdgeValue;
		}
		
	}
	private static void BlowD() {
		int RotateLines = Math.min(C, R-AirC.xD)/2;	//돌아가는 선의 수
		for(int l = 0; l < 1; l++) { //돌아가는 라인 하나씩
			int x = AirC.xD + l;
			int y = l;
			int EdgeValue = maps[x][y];
			int idx = 0;
			
			while(idx < 4) {
				int tempx = x + deltasD[idx][0];
				int tempy = y + deltasD[idx][1];
				
				
				if(tempx >= AirC.xD + l && tempy >= l && tempx <= R-l-1 && tempy <= C-l-1) {
					if(tempx == AirC.xD && tempy ==0) {
						maps[x][y] = 0;
						x = tempx;
						y = tempy;
						continue;
					}
					if(x == AirC.xD && y == 0) {
						x = tempx;
						y = tempy;
						continue;
					}
					
					maps[x][y] = maps[tempx][tempy];
					x = tempx;
					y = tempy;
					continue;
				}
				idx++;
			}
//			maps[l][l+1] = EdgeValue;
			
		}
		
	}

	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static void SpreadDirt(dirXYdirt input) {
//		System.out.println("=========이전 ===========");
//		System.out.println(input.toString());
//		for(int [] temp : maps) {
//			for(int tt : temp) {
//				System.out.printf("%d " , tt);
//			}
//			System.out.println();
//		}
		
		int spreadcount = 0;
		for(int dir = 0; dir < 4 ; dir++) {
			int tempx = input.x + deltas[dir][0];
			int tempy = input.y + deltas[dir][1];
			if(!isIn(tempx, tempy)) {
				continue;
			}
			spreadcount++;
			maps[tempx][tempy] += input.value/5;	//맵에 미세먼지 확산
		}
		maps[input.x][input.y] -= (input.value/5)*spreadcount ;  
//		System.out.println("=========블로우===========");
//		for(int [] temp : maps) {
//			for(int tt : temp) {
//				System.out.printf("%d " , tt);
//			}
//			System.out.println();
//		}
	}
	
	
	private static boolean isIn(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C && maps[x][y] != -1;
	}
	
	private static void SplitDirt(dirXYdirt input) {	//공청 x 기준으로 위 아래 따로
		if(input.x < AirC.xD) {	//xD보다 높으면 위에로
			DirtsU.add(input);
		}else {
			DirtsD.add(input);	//같거나 작으면 아래로
		}
	}
	
	private static class dirXYairc {
		int xU;
		int xD;
		int y;
		public dirXYairc(int xU, int xD, int y) {
			super();
			this.xU = xU;
			this.xD = xD;
			this.y = y;
		}

	}
	

	private static class dirXYdirt {
		int x;
		int y;
		int value;
		public dirXYdirt(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.value = maps[x][y];
		}
		@Override
		public String toString() {
			return " [x=" + x + ", y=" + y + ", value=" + value + "]";
		}
		
		
	}
	
	
}
