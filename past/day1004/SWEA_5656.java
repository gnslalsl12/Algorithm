package day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5656 {
	static int N, W, H;
	static int N1;
	static int Ns;
	static int tempN1, tempNs;
	static int[][] maps;
	static int[][] tempmaps;
	static ArrayList<dirXY> AvailShot;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<int[]> order;
	static boolean restart;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			int result = 0;
			AvailShot = new ArrayList<>();
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			H = Integer.parseInt(tokens.nextToken());
			maps = new int[H][W];

			N1 = 0;
			for (int i = 0; i < H; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < W; j++) {
					int a = Integer.parseInt(tokens.nextToken());
					maps[i][j] = a;
					if (a == 1)
						N1++;
					if (a > 1) {
						Ns++;
						int blockcount = 0;
						for (int tempi = 0; tempi < i; tempi++) { // 2이상의 블럭이 N번 이하에 부술 수 있는 애면 후보로
							if (maps[tempi][j] != 0)
								blockcount++;
						}
						if (blockcount < N) {
							AvailShot.add(new dirXY(i, j, blockcount));
						}
					}
				}
			}
			System.out.println(Ns);
			System.out.println(N1);
			if (Ns == 0 || AvailShot.size() == 0) {
				result = N1 - N;
			} else {
				result = Integer.MAX_VALUE;
				order = new ArrayList<>();
				perm(new int[AvailShot.size()], 0, 0);
				
				for (int[] pop : order) {
					tempmaps = new int[H][W];
					for (int i = 0; i < H; i++) {
						tempmaps[i] = maps[i].clone();
					}
					restart = false;
					System.out.println("////////////////////");
					System.out.println(Arrays.toString(pop));
					int remainshot = N;
					tempN1 = N1;
					tempNs = Ns;
					boolean breaked = true;
					
					
					for (int i = 0; i < pop.length; i++) {
						remainshot--;
						breaked = firstShot(AvailShot.get(pop[i]));
						downwell();
						if (restart)
							break;
						if (remainshot == 0)
							break;
						if (!breaked) {
							i--;
							continue;
						}
					}
					
					
					result = Math.min(result, tempN1 + tempNs);
					int tempresult = 0;
					for (int k = 0; k < H; k++) {
						System.out.println(Arrays.toString(tempmaps[k]));
						for (int kk = 0; kk < W; kk++) {

							if (tempmaps[k][kk] != 0) {
								tempresult++;
							}
						}
					}
					System.out.println("이번 맵 탐색값 : " + tempresult);
				}
			}
			sb.append(String.format("#%d %d\n", test, result));
		}

		System.out.print(sb);

	}


	private static boolean firstShot(dirXY point) {
		if (tempmaps[point.x][point.y] == point.value)	//뿌실랬눈데 다른 애가 먼저 뿌심
			return true;
//		System.out.println("쏴서 부신 애 : " + point.toString());
		int y = point.y;
		boolean breaked = false;
		for (int i = 0; i < H; i++) {	//공 떨어져유
			if (tempmaps[i][y] == 1) {
				tempmaps[i][y] = 0;
				tempN1--;
//				System.out.println("뻐스트1짜리 부심");
				return false; // 내려가다보니 1인 블럭을 먼저 꺠부셨다
			} else if (tempmaps[i][y] > 1) {
				if (i == point.x) {
					breaked = true;
//					System.out.println("뻐스트 큰 거 부심");
					serialShot(new dirXY(i, y));
					break;
				} else { // 내려오다보니 1이 아닌 애를 부심
					restart = true;
//					System.out.println(point.toString() + "를 깨려다 " + i + "를 깸");
//					System.out.println(Arrays.toString(tempmaps[i]));
					return breaked;
				}
			}
		}
		return breaked;
	}

	private static void serialShot(dirXY breakpoint) { // 1이상인 블럭 하나를 깨부시고 전파

//		System.out.println("punch연쇄 : " + breakpoint.toString());
		int x = breakpoint.x;
		int y = breakpoint.y;
//		System.out.println("power : " + tempmaps[x][y]);
		int power = tempmaps[x][y];
//		if(tempmaps[x][y] != 0) {
		tempmaps[x][y] = 0;
		tempNs--;
//		}
		for (int p = 1; p <= power; p++) {
			for (int dir = 0; dir < 4; dir++) {
				int tempx = x + deltas[dir][0] * p;
				int tempy = y + deltas[dir][1] * p;
				if (!isIn(tempx, tempy))
					continue;
				if (tempmaps[tempx][tempy] != 0) {
					if (tempmaps[tempx][tempy] == 1) {
						tempmaps[tempx][tempy] = 0;
//						System.out.println("연쇄 1짜리 부심");
//						System.out.println(tempx + ", " + tempy);
						tempN1--;
						continue;
					}
//					System.out.println("연쇄 큰 거 부심");
					serialShot(new dirXY(tempx, tempy));
				}
			}
		}
	}

	

	private static void perm(int[] sel, int count, int visits) {
		if (count == AvailShot.size()) {
			order.add(sel.clone());
			return;
		}
		for (int i = 0; i < AvailShot.size(); i++) {
			if ((visits & 1 << i) != 0) {
				continue;
			}
			visits |= 1 << i;
			sel[count] = i;
			perm(sel.clone(), count + 1, visits);
			visits &= ~(1 << i);
		}
	}

	private static void downwell() {
		System.out.println("떨");
		for(int j = 0; j < W; j++) {
			int downcount = 0;
			for(int i = H-1; i >= 0; i--) {
				if(tempmaps[i][j] == 0) {
					downcount++;
				}
				if(i == H-1 && tempmaps[i][j] != 0) {
					continue;
				}
				if(tempmaps[i][j] != 0 && tempmaps[i+1][j] == 0) {	//블록이 있고 그 아래에 0
					tempmaps[i+downcount][j] = tempmaps[i][j];
					tempmaps[i][j] = 0;
					downcount = 0;
					i = H-1;
					continue;
				}
			}
		}
	}
	private static boolean isIn(int tempx, int tempy) {
		return tempx >= 0 && tempx < H && tempy >= 0 && tempy < W;
	}

	private static class dirXY {
		int x;
		int y;
		int count; // 해당 블럭에 도달하기 위해 먼저 부셔야하는 위 블럭
		int value;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.value = tempmaps[x][y];
		}

		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.value = tempmaps[x][y];
			this.count = count;
		}

		@Override
		public String toString() {
			return "dirXY [x=" + x + ", y=" + y + ", count=" + count + "]";
		}

	}

}
