import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
	//BitMasking
	//층마다 한 번씩 돌려보는 케이스 4^5가지
public class Main {
	static int [][][] mapbranches = new int [5][4][5];	//[층][돌리는 경우의 수][해당 층의 맵]
	static ArrayList<Integer> PermBranches = new ArrayList<>();	//순열 저장값
	static int [][] deltas = {{-1,0,0},{0,1,0},{1,0,0},{0,-1,0},{0,0,1},{0,0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int [][] maps = new int [5][5];
		for(int layers = 0; layers < 5; layers++) {
			for(int i = 0; i < 5; i ++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				int BMLayer = 0;
				for(int j = 4; j >= 0; j--) {
					if(Integer.parseInt(tokens.nextToken()) == 0) {	//문제를 잘못 봐서 1이 막혀있는 건줄??
						BMLayer |= 1<<j;
					}
				}
				maps[layers][i] = BMLayer;
			}
		}//매핑 완료
		
		for(int Layers = 0; Layers < 5; Layers++) {
			int [] R = maps[Layers].clone();
			mapbranches[Layers][0] = R.clone();		//기본 판
			for(int rotate = 1; rotate < 4; rotate++) {
				R = OnlyOneLayerRotate(R);
				mapbranches[Layers][rotate] = R.clone(); //1번, 2번, 3번 회전시킨 판들
			}
		}
		
		Perm(0,0,new boolean[6]);	//순번 순열화
		int totalresult = Integer.MAX_VALUE;
		for(int order : PermBranches) {	//order = 54321, 53421, ... 12345
			
			int L5 = order/10000;	//5층에 올라갈 mapbranche 속 L5번째 맵
			order %= L5*10000;
			int L4 = order/1000;
			order %= L4*1000;
			int L3 = order/100;
			order %= L3*100;
			int L2 = order/10;
			int L1 = order%(L2*10);
			
			for(int br5 = 0; br5 < 4; br5++) {	//해당 층을 돌려본 경우 4가지 모두
				if(!CheckAvailStart(mapbranches[(L5)-1][br5])) continue;				//시작할 수 없는 경우의 판
				for(int br4 = 0; br4 < 4; br4++) {
					for(int br3 = 0; br3 < 4; br3++) {
						for(int br2 = 0; br2 < 4; br2++) {
							for(int br1 = 0; br1 < 4; br1++) {
								if(!CheckAvailEnd(mapbranches[L1-1][br1])) continue; //끝낼 수 없는 경우의 판
								int [][] tempmaps = {mapbranches[L5-1][br5],	//임시로 만든 3차원 맵
														mapbranches[L4-1][br4],
															mapbranches[L3-1][br3],
																mapbranches[L2-1][br2],
																	mapbranches[L1-1][br1]};
								totalresult = Integer.min(totalresult, BFS(tempmaps));
							}
						}
					}
				}
			}
		}
		if(totalresult == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(totalresult-4);
		}
	}
	
	private static int BFS(int [][] maps) {
		boolean [][][] visited = new boolean [5][5][5];
		Queue<CountWay> BFSQ = new LinkedList<>();
		BFSQ.offer(new CountWay(0,0,0,4));
		visited[0][0][4] = true;
		
		while(!BFSQ.isEmpty()) {
			CountWay tempnow = BFSQ.poll();
			for(int dir = 0; dir < 6; dir++) {
				int tempx = tempnow.x + deltas[dir][0];
				int tempy = tempnow.y + deltas[dir][1];
				int tempz = tempnow.z + deltas[dir][2];
				if(!isIn(tempx, tempy, tempz)) continue;			//범위 밖
				if(!AvailToGo(maps, tempx, tempy, tempz)) continue;	//막혀있음
				if(visited[tempx][tempy][tempz]) continue;			//방문함
				if(tempx == 4 && tempy == 4 && tempz == 4) {
					return tempnow.count+1;
				}
				//범위 안이고 갈 수 있는 곳
				visited[tempx][tempy][tempz] = true;
				BFSQ.add(new CountWay(tempx, tempy, tempz, tempnow.count+1));
				
			}
		}
		return Integer.MAX_VALUE;
		
	}
	
	private static boolean AvailToGo(int [][] maps, int x, int y, int z) {
		return ((maps[z][x] & 1<<(4-y)) == 0)? true : false;
	}
	
	private static boolean isIn(int x, int y, int z) {
		return x >= 0 && y >= 0 && z >= 0 && x < 5 && y < 5 && z < 5;
	}

	private static void Perm(int countnow, int Sel, boolean [] visited) {
		if(countnow == 5) {
			PermBranches.add(Sel);
			return;
		}
		for(int i = 1; i <= 5; i++) {
			if(!visited[i]) {
				visited[i] = true;
				Sel += i*((int)Math.pow(10, countnow));
				Perm(countnow+1, Sel, visited);
				visited[i] = false;
				Sel -= i*((int)Math.pow(10, countnow));
			}
		}
	}
	
	private static int[] OnlyOneLayerRotate(int [] inputLayer) {	//맵 시계방향으로 돌리기
		int [] tempLayer = new int [5];
		for(int index = 4; index >= 0; index--) {
			int tempBM = 0;
			for(int dirx = 0; dirx < 5; dirx++) {
				if((inputLayer[dirx] & 1<<index) != 0) {
					tempBM |= 1<<dirx;
				}
			}
			tempLayer[4-index] = tempBM;
		}
		return  tempLayer.clone();
	}
	
	private static boolean CheckAvailStart(int [] Layer) {	//맨 윗층에 왼쪽 위가 0이어야함
		return ((Layer[0] & 1<<4) != 0)? false : true;
	}
	
	private static boolean CheckAvailEnd(int [] Layer) {	//맨 아래층 오른쪽 밑이 0이어야함
		return ((Layer[4] & 1<<0) != 0)? false : true;
	}
	
	private static class CountWay{
		int x;
		int y;
		int z;
		int count;
		public CountWay(int x, int y, int z, int count) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.count = count;
		}
	}
	
}