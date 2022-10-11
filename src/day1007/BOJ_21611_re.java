package day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21611_re {
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
		ArrayList<int[]> fireballs = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int dir = Integer.parseInt(tokens.nextToken());
			if(dir == 1) dir = 0;
			else if(dir == 4) dir = 1;
			int s= Integer.parseInt(tokens.nextToken());
			fireballs.add(new int [] {dir, s});
		}
		for(int i = 0; i < M; i++) {
			FireBall(fireballs.get(i));
//			System.out.println("fireball");
//			print();
			setWheel();
//			System.out.println("첫 setwheel");
//			print();
			while(Bomb()) {
//				System.out.println("BOMB!!!");
//				print();
				setWheel();
//				System.out.println("정렬!!!");
//				print();
			}
//			System.out.println("진화!!!!!");
			Revolution();
//			print();
		}

//		print();
		System.out.println(B1 + B2*2 + B3*3);
		

	}

	private static void print() {
		System.out.println("////////////////////");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(String.format("%d ", maps[i][j]));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void Revolution() throws CloneNotSupportedException {
		dirXY temp = new dirXY(N / 2, N / 2, 0);
		long [] BM = new long [N];
		nex1tXY(temp, BM);
		int ball = maps[temp.x][temp.y];
		int ballcount = 0;
		Queue<ballstochange> changed = new LinkedList<>();
		while (true) {
			if (maps[temp.x][temp.y] == 0 || (temp.x == 0 && temp.y == 0)) {
				changed.offer(new ballstochange(ball, ballcount));
				break;
			}
			if (maps[temp.x][temp.y] == ball) {
				ballcount++;
			} else {
				changed.offer(new ballstochange(ball, ballcount));
				ball = maps[temp.x][temp.y];
				ballcount = 1;
			}
			maps[temp.x][temp.y] = 0;
			nex1tXY(temp, BM);
		}
		temp = new dirXY(N/2,N/2,0);
		BM = new long [N];
		nex1tXY(temp, BM);
		while(!changed.isEmpty()){
			ballstochange newone = changed.poll();
			if(temp.x == 0 && temp.y == 0) {
				maps[temp.x][temp.y] = newone.ballcount;
				return;
			}
			maps[temp.x][temp.y] = newone.ballcount;
			nex1tXY(temp,BM);
			if(temp.x == 0 && temp.y == 0) {
				maps[temp.x][temp.y] = newone.ball;
				return;
			}
			maps[temp.x][temp.y] = newone.ball;
			nex1tXY(temp,BM);
			
		}
	}

	private static boolean Bomb() throws CloneNotSupportedException {
		dirXY temp = new dirXY(N / 2, N / 2, 0);
		long[] BM = new long[N];
		nex1tXY(temp, BM);
		int ball = maps[temp.x][temp.y];
		int ballcount = 0;
		boolean changed = false;
		ArrayList<dirXY> needtoremove = new ArrayList<>();
		while (true) {
			if (maps[temp.x][temp.y] == 0) {
//				System.out.println(ballcount);
				if(ballcount < 4) {
					for(int i = 0; i < ballcount ; i++) {
						needtoremove.remove(needtoremove.size()-1);
					}
				}
				break;
			}
			if (maps[temp.x][temp.y] == ball) {
				ballcount++;
				needtoremove.add(temp.clone());
			} else {
				if (ballcount < 4) {
					for(int i = 0; i < ballcount; i++) {
						needtoremove.remove(needtoremove.size()-1);
					}
				}
				ball = maps[temp.x][temp.y];
				ballcount = 1;
				needtoremove.add(temp.clone());
			}
			nex1tXY(temp, BM);
			if(temp.x == 0 && temp.y == 0) {
				if(maps[0][0] == ball) {
					ballcount++;
					needtoremove.add(temp.clone());
				}
				if(ballcount < 4) {
					for(int i = 0; i < ballcount ; i++) {
						needtoremove.remove(needtoremove.size()-1);
					}
				}
				break;
			}
		}
		if(needtoremove.size() > 0) changed = true;
		while (!needtoremove.isEmpty()) {
			dirXY pop = needtoremove.remove(0);
			int tempball = maps[pop.x][pop.y];
			if(tempball == 1) {
				B1++;
			}else if(tempball == 2) {
				B2++;
			}else if(tempball == 3) {
				B3++;
			}
			maps[pop.x][pop.y] = 0;
		}
		return changed;

	}

	private static void setWheel() throws CloneNotSupportedException { // 빈칸 채우기
		dirXY temptofill = new dirXY(N / 2, N / 2, 0);
		long [] BMtofill = new long[N];
		dirXY fromhere = new dirXY(0, 0, 0);
		long[] BMhere = new long[N];
		nex1tXY(temptofill, BMtofill);
		while (true) {
			if(temptofill.x == 0 && temptofill.y == 0) {
				break;
			}
			if (maps[temptofill.x][temptofill.y] == 0) { // 0인 곳 찾음
				fromhere = temptofill.clone();
				BMhere = BMtofill.clone();// 출발 준비
				while (!(fromhere.x == 0 && fromhere.y == 0)) {
					System.out.println(fromhere.toString());
					nex1tXY(fromhere, BMhere); // 다음 위치로 먼저 감
					System.out.println(fromhere.toString());
					if(fromhere.x == 0 && fromhere.y == 0) {
						return;
					}
					if (maps[fromhere.x][fromhere.y] == 0) { // 옮긴 위치도 0임 => 다시 옮겨
						continue;
					}
					// 0이 아닌 곳 찾았다
					maps[temptofill.x][temptofill.y] = maps[fromhere.x][fromhere.y];
					maps[fromhere.x][fromhere.y] = 0;
					// 넣어주고
					if(temptofill.x == 0 && temptofill.y == 0) break;
					nex1tXY(temptofill, BMtofill);
					if(temptofill.x == 0 && temptofill.y == 0) break;
					
				}
			}
			if(temptofill.x == 0 && temptofill.y == 0) break;
			nex1tXY(temptofill, BMtofill);

		}
	}

	private static void nex1tXY(dirXY input, long[] BM) { // 자동으로 다음 위치로 옮겨줌
		BM[input.x] |= 1 << input.y;
		int dir = input.dir;
		if (!visitsLeft(input, BM)) { // 현재 위치의 왼쪽이 방문 전
			input.dir = R2(dir);
		}
		input.x += deltas[input.dir][0];
		input.y += deltas[input.dir][1];
	}

	private static boolean visitsLeft(dirXY input, long BM[]) { // 현재 위치에서 왼쪽이 방문 전인가?
		if ((BM[input.x + deltas[R2(input.dir)][0]] & 1 << (input.y + deltas[R2(input.dir)][1])) == 0) {
			return false;
		}
		return true;
	}

	private static int R2(int R) { // 왼쪽 보기
		if (R == 0) {
			return 3;
		} else {
			return R - 1;
		}
	}

	private static void FireBall(int[] fb) {
		for (int i = 1; i <= fb[1]; i++) {
			if (!isIn(N / 2 + deltas[fb[0]][0] * i, N / 2 + deltas[fb[0]][1] *i))
				return;
			maps[N / 2 + deltas[fb[0]][0] * i][N / 2 + deltas[fb[0]][1] * i] = 0;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class ballstochange {
		int ball;
		int ballcount;

		public ballstochange(int ball, int ballcount) {
			super();
			this.ball = ball;
			this.ballcount = ballcount;
		}

		@Override
		public String toString() {
			return "[ball=" + ball + ", ballcount=" + ballcount + "]";
		}
		

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
		protected dirXY clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return (dirXY) super.clone();
		}

	}

}
