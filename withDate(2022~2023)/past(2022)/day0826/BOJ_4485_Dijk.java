package day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_4485_Dijk {
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N;
	static int [][] maps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = 1;
		while(true) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			if(N == 0) {
				System.out.print(sb);
				return;
			}
			maps = new int [N][N];
			for(int xx = 0; xx < N; xx++) {
				tokens = new StringTokenizer(read.readLine());
				for(int yy = 0; yy < N; yy++) {
					maps[xx][yy] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			int start = XYtoInt(0,0);
			int end = XYtoInt(N-1,N-1);
			
			int [] minRuppee = new int [N*N];	//해당 지점까지 가는 데에 최소 비용
			boolean [] visited = new boolean [N*N];
			
			Arrays.fill(minRuppee, Integer.MAX_VALUE);
			
			minRuppee[start] = maps[0][0];
			
			int minVertex = -1;
			int min = 0;
			for(int i = 0; i < N*N; i++) {
				
				min = Integer.MAX_VALUE;
				for(int tempxy = 0; tempxy < N*N; tempxy++) {
					if(!visited[tempxy] && min > minRuppee[tempxy]) {
						System.out.println("출발지 " + minVertex + " 에서 가는 곳 : " + tempxy);
						min = minRuppee[tempxy];
						minVertex = tempxy;
								
					}
					
					
					visited[minVertex] = true; //최소로 갈 수 있는 곳 방문처리
					if(minVertex == end) {
						break;
					}
					for(int dir = 0; dir < 4; dir++) {
						int nexttemp = Connected(tempxy, dir);
						if(!isIn(nexttemp)) {
							continue;
						}//범위 밖임
						if(visited[nexttemp]) {
							continue;
						}
						if(minRuppee[nexttemp] > minRuppee[tempxy] + maps[InttoX(nexttemp)][InttoY(nexttemp)]) {
							minRuppee[nexttemp] =  minRuppee[tempxy] + maps[InttoX(nexttemp)][InttoY(nexttemp)];
						}//범위 안이고 이동 가능하ㅕㅁ 지금 구한 값이 더 작을 떄
						
					}
					System.out.println("=====================");
					for(int ii = 0; ii <N ;ii++) {
						for(int jj = 0; jj < N ; jj++) {
							System.out.printf("%d ", minRuppee[(ii*N)+jj]);
						}
						System.out.println();
					}
/*					for(int j = 0; j < N*N; j++) {
						if(!visited[j] && isIn(j) && Connected(minVertex, j) && minRuppee[j] > minRuppee[minVertex] + maps[InttoX(j)][InttoY(j)]) {
							minRuppee[j] = minRuppee[minVertex] + maps[InttoX(j)][InttoY(j)];
							System.out.println("갱신되는 위치 : " + InttoX(j) + ", " + InttoY(j) + "-- " + minRuppee[j]);
							if(tempxy == 7) {
								System.out.println("발견 === " + (minRuppee[minVertex] + maps[InttoX(j)][InttoY(j)]));
							}
							System.out.println("=====================");
							for(int ii = 0; ii <N ;ii++) {
								for(int jj = 0; jj < N ; jj++) {
									System.out.printf("%d ", minRuppee[(ii*N)+jj]);
								}
								System.out.println();
							}
						}
						
						
					}
*/					

					
					
				}
				
				
			}
			int tempR = minRuppee[end];
			
			sb.append(String.format("Problem %d: %d\n", test++, tempR));
			
			
			
		}

	}
	
	private static boolean isIn(int xy) {
		int x = InttoX(xy);
		int y = InttoY(xy);
		return  x >= 0 && x < N && y >= 0 && y < N;
	}
	
	private static int Connected(int from, int dir) {
		int nextx = InttoX(from) + deltas[dir][0];
		int nexty = InttoY(from) + deltas[dir][1];
		return XYtoInt(nextx, nexty);
		
	}
	
	private static int InttoX(int xy) {
		return xy/N;
	}
	private static int InttoY(int xy) {
		return xy%N;
	}
	
	private static int XYtoInt(int x, int y) {
		return x*N + y;
	}
	
	
	
}
