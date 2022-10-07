package day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21611 {
	static int N, M;
	static int[][] maps;
	static int B1, B2, B3;
	static Queue<dirXY> Empty = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B1 = B2 = B3 = 0;
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		setWheel();
		print();

//		for (int test = 0; test < M; test++) {
//			tokens = new StringTokenizer(read.readLine());
//			int d = Integer.parseInt(tokens.nextToken());
//			if (d == 1) {
//				d = 0;
//			} else if (d == 4) {
//				d = 1;
//			}
//			int[] fb = { d, Integer.parseInt(tokens.nextToken()) };
//			FireBall(fb);
//			while(true) {
//				setWheel();
//				boolean b = Bomb();
//				if(!b) break;	//터뜨릴 게 없음
//			}
//
//		}

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(maps[i]));
		}
	}

	private static boolean Bomb() throws CloneNotSupportedException {
		Queue<dirXY> sameXY = new LinkedList<>();
		boolean result = false;
		dirXY now = new dirXY(N / 2, N / 2 - 1, 2);
		int[] BM = new int[N];
		BM[N / 2] |= 1 << (N / 2);
		int samecount = 0;
		int Yuri = maps[now.x][now.y];
		while (true) {
			if (maps[now.x][now.y] == Yuri) { // 갖고있는 구슬고 ㅏ같은 애
				sameXY.add((dirXY) now.clone());
				samecount++;
			} else { // 다른 애
				if (samecount >= 4) { // 4개 이상 연속
					result = true;
					while (!sameXY.isEmpty()) {
						dirXY pop = sameXY.poll();
						maps[pop.x][pop.y] = 0;
					}
				}
				Yuri = maps[now.x][now.y];
				sameXY = new LinkedList<>();
				samecount = 1;
			}
			if (now.x == 0 && now.y == 0)
				break;
			nextXY(now, BM); // 현재 위치를 true해주고 다음 위치를 넘겨줌
			if (maps[now.x][now.y] == 0)
				break; // 다음 위치가 비어있음
		}
		return result;
	}

	private static void setWheel() throws CloneNotSupportedException { // 빈칸 채우기
		dirXY now = new dirXY(N / 2, N / 2 - 1, 2);
		dirXY emptystart = new dirXY(0, 0, 0); // 첫 빈칸
		dirXY restart = new dirXY(0, 0, 0); // 빈칸 이후 첫 구슬
		int[] BM = new int[N];
		BM[N / 2] |= 1 << (N / 2);
		while (true) {
			System.out.println("now : " + now.toString());
			if (maps[now.x][now.y] == 0) { // 빈 칸 발견
				int[] tempBM = BM.clone();
				int[] emptyBM = tempBM.clone();
				int[] restartBM = tempBM.clone();
				dirXY temp = (dirXY) now.clone();
				emptystart = (dirXY) temp.clone();
				boolean nul = false;
				System.out.println("-------------------0 탐색");
				while (true) {
					nextXY(temp, tempBM);
					System.out.println("0 탐색 중인 위치 : " + temp.toString());
					if (maps[temp.x][temp.y] != 0) { // 빈칸 이후 첫 구슬 발견
						restart = (dirXY) temp.clone();
						restartBM = tempBM.clone();
						break;
					}
					if(temp.x == 0 && temp.y == 0) {
						nul = true;
						break;
					}
				}
				if(nul) break;	//끝까지 0 유지이면 끝
				System.out.println("----------------0 탐색 끝 & 채우기");
				while (true) {
					maps[emptystart.x][emptystart.y] = maps[restart.x][restart.y];
					maps[restart.x][restart.y] = 0;
					if (restart.x == 0 && restart.y == 0) {
						break;
					}
					nextXY(restart, restartBM);
					System.out.println(restart.toString());
					if (maps[restart.x][restart.y] == 0) {
						now = (dirXY) emptystart.clone(); // 빈칸 밀어오기가 끝났으면 마지막 채운 빈칸잘리르 now로
						break;
					}
					nextXY(emptystart, emptyBM);
				} // 다 돌려놓음
				print();
				System.out.println("맵!");
				continue;
			} // 빈칸 채우기 끝
			if(now.x == 0 && now.y == 0) break;
			nextXY(now, BM);

		}

	}

	private static void nextXY(dirXY input, int[] BM) { // 자동으로 다음 위치로 옮겨줌
		BM[input.x] |= 1 << input.y;
		int dir = input.dir;
		if (!visitsLeft(input, BM)) { // 현재 위치의 왼쪽이 방문 전
			System.out.println("돌아 : " + dir);
			input.dir = R2(dir);
			System.out.println("돈 방향 :"  +dir);
		}
		input.x += deltas[input.dir][0];
		input.y += deltas[input.dir][1];
		System.out.println("다음 위치 : "+ input);
	}

	private static boolean visitsLeft(dirXY input, int BM[]) { // 현재 위치에서 왼쪽이 방문 전인가?
		if ((BM[input.x + deltas[R2(input.dir)][0]] & 1 << (input.y + deltas[R2(input.dir)][1])) == 0) {
			return false;
		}
		return true;
	}

	private static int R2(int R) {	//왼쪽 보기
		if (R == 0) {
			return 3;
		} else {
			return R - 1;
		}
	}

	private static void FireBall(int[] fb) {
		for (int i = 1; i <= fb[1]; i++) {
			if (!isIn(N / 2 + deltas[fb[0]][0] * fb[1], N / 2 + deltas[fb[0]][1] * fb[1]))
				return;
			maps[N / 2 + deltas[fb[0]][0] * fb[1]][N / 2 + deltas[fb[0]][1] * fb[1]] = 0;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class dirXY implements Cloneable {
		int x;
		int y;
		int dir;

		public dirXY(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}

	}

}
