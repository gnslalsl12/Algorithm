package day0831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
	//BitMasking
	//층마다 한 번씩 돌려보는 케이스 4^5가지
public class BOJ_16985_editting {
	static int [][][] mapbranches = new int [5][4][5];	//[층][돌리는 경우의 수][해당 층의 맵]
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
		}//매핑 끝
		
		int totalresult = Integer.MAX_VALUE;
		for(int layer1 = 0; layer1 < 5; layer1++) {
			for(int layer2 = 0; layer2 < 5; layer2++) {
				if(layer1 == layer2) continue;
				for(int layer3 = 0; layer3 < 5; layer3++) {
					if(layer1 == layer3 || layer2 == layer3) continue;
					for(int layer4 = 0; layer4 < 5; layer4++) {
						if(layer1 == layer4 || layer2 == layer4 || layer3 == layer4) continue;
						for(int layer5 = 0; layer5 < 5; layer5++) {
							if(layer1 == layer5 || layer2 == layer5 || layer3 == layer5 || layer4 == layer5) continue;	//층마다 맵 순열
							
							int [] L1 = maps[layer1].clone();
							int [] L2 = maps[layer2].clone();
							int [] L3 = maps[layer3].clone();
							int [] L4 = maps[layer4].clone();
							int [] L5 = maps[layer5].clone();	//순열 처리된 index값의 맵 복사해오기
							
							for(int R1 = 0; R1 < 4; R1++) {
								L1 = OnlyOneLayerRotate(L1);	//돌려보고
								if(!CheckAvailStart(L1)) continue;	//출발 가능한 맨 윗층인가?
								for(int R2 = 0; R2 < 4; R2++) {
									L2 = OnlyOneLayerRotate(L2);
									for(int R3 = 0; R3 < 4; R3++) {
										L3 = OnlyOneLayerRotate(L3);
										for(int R4 = 0; R4 < 4; R4++) {
											L4 = OnlyOneLayerRotate(L4);
											for(int R5 = 0; R5 < 4; R5++) {
												L5 = OnlyOneLayerRotate(L5);
												if(!CheckAvailEnd(L5)) continue;	//도착 가능한 맨 아래층인가?
												totalresult = Integer.min(totalresult, BFS(new int [][] {L1,L2,L3,L4,L5}));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(totalresult == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(totalresult - 4);
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
					return tempnow.count+1;	//도착
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