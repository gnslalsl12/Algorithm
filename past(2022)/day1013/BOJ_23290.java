package day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23290 {
	static int S, M;
	static int[][] maps = new int[5][5];
	static ArrayList<dirXY> Fishes = new ArrayList<>();
	static ArrayList<dirXY> CopyFishes = new ArrayList<>();
	static int[][] deltaF = { {}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
			{ 1, -1 } };
	static dirXY Shark;
	static int[][] smellmaps = new int[5][5];
	static int[][] deltaS = { {}, { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static Queue<dirXY> Smells = new LinkedList<>();
	static int fishcount;
	static boolean[][] kills = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		int fc = 1;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			int dir = Integer.parseInt(tokens.nextToken());
			Fishes.add(new dirXY(x, y, dir, fc++));
			maps[x][y] -= 1;
		}
		tokens = new StringTokenizer(read.readLine());
		Shark = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), 0, 4);
//		maps[Shark.x][Shark.y] = 4;
//		System.out.println("초기 : ");
		print();
		for (int magic = 0; magic < S; magic++) {
//			System.out.println("/////////////////////////////////////" + magic);
			Collections.sort(Fishes);
//			System.out.println("초기 물고기들 : " + Fishes);
			Collections.sort(Fishes);
			kills = new boolean[5][5];
			fishcount = Fishes.size();
			// 복사 준비
			CopyFishes = new ArrayList<>();
			CopyFishes = (ArrayList<dirXY>) Fishes.clone();
//			System.out.println("카피 : " + CopyFishes);
			moveF(); // 물고기 이동
//			System.out.println("물고기 이동");
			print();
			// fish 리스트에는 새로운 물고기만 남아있음

//			System.out.println("현재 물고기들");
//			for (dirXY f : Fishes) {
//				System.out.println(f);
//			}
			dirXY move = readytomoveS(); // 상어 이동 경로 준비
			MoveS(move);
//			System.out.println("상어 이동");
			print();
			// kills맵이 갱신됨
			int fishsize = Fishes.size();
			for (int i = fishsize - 1; i >= 0; i--) {
				if (kills[Fishes.get(i).x][Fishes.get(i).y]) {
					Fishes.remove(i);
				} else {
					int t = smellmaps[Fishes.get(i).x][Fishes.get(i).y];
					if (t ==  3) {
						Fishes.remove(i);
					}
				}
			}
			int copyfishsize = CopyFishes.size();
//			for(int i = copyfishsize-1; i>= 0; i--) {
//				int t= maps[CopyFishes.get(i).x][CopyFishes.get(i).y];
//				if(t >= 1 && t <= 3) {
//					CopyFishes.remove(i);
//				}
//			}
			// 상어가 지나가면서 죽인

			removeSmell();
//			System.out.println("냄새 지욱");
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
//					System.out.printf("%d    ", smellmaps[i][j]);
				}
//				System.out.println();
			}
//			print();
			int siz = Fishes.size();
			cloneFishes();
//			System.out.println("물고기 복사");
			print();
//			System.out.println(Fishes);
//			System.out.println(CopyFishes);
		}
		int result = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (maps[i][j] < 0) {
					result -= maps[i][j];
				}
			}
		}
		System.out.println(result);
	}

	private static void print() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
//				System.out.printf("%d    ", maps[i][j]);
			}
//			System.out.println();
		}
	}

	private static void cloneFishes() {

		for (dirXY t : CopyFishes) {
//			if (maps[t.x][t.y] <= 0) {
			maps[t.x][t.y] -= 1;
			Fishes.add(t);
			
//			}
		}
	}

	private static void removeSmell() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (smellmaps[i][j] > 0)
					smellmaps[i][j]--;
			}
		}
	}

	private static void MoveS(dirXY move) {
//		maps[Shark.x][Shark.y] = 0;
//		System.out.println(move.y);
		int dir1 = move.y / 100;
		int dir2 = (move.y % 100) / 10;
		int dir3 = move.y % 10;
//		System.out.println(dir1 + ", " + dir2 + ", " + dir3);
//		System.out.println("원래 위치 : " + Shark.x + ", " + Shark.y);
		int tempx = Shark.x + deltaS[dir1][0];
		int tempy = Shark.y + deltaS[dir1][1];
		if (maps[tempx][tempy] < 0) {
			maps[tempx][tempy] = 0;
			smellmaps[tempx][tempy] = 3;
			kills[tempx][tempy] = true;
		}
//		System.out.println("첫 이동 : " + tempx + ", " + tempy);
		tempx += deltaS[dir2][0];
		tempy += deltaS[dir2][1];
		if (maps[tempx][tempy] < 0) {
			maps[tempx][tempy] = 0;
			smellmaps[tempx][tempy] = 3;
			kills[tempx][tempy] = true;
		}
//		System.out.println("이동 : " + tempx + ", " + tempy);
		tempx += deltaS[dir3][0];
		tempy += deltaS[dir3][1];
		if (maps[tempx][tempy] < 0) {
			maps[tempx][tempy] = 0;
			smellmaps[tempx][tempy] = 3;
			kills[tempx][tempy] = true;
		}
//		System.out.println("이동 : " + tempx + ", " + tempy);
		Shark.setX(tempx);
		Shark.setY(tempy);
//		maps[Shark.x][Shark.y] = 4;
	}

	private static dirXY readytomoveS() {
		PriorityQueue<dirXY> orders = new PriorityQueue<>();
		for (int dir1 = 1; dir1 <= 4; dir1++) { // 첫 이동
			int killcount = 0;
			int tempx = Shark.x + deltaS[dir1][0];
			int tempy = Shark.y + deltaS[dir1][1];
			if (!isIn(tempx, tempy))
				continue;
			if (maps[tempx][tempy] < 0) {
				killcount -= maps[tempx][tempy];
			}
			for (int dir2 = 1; dir2 <= 4; dir2++) {
				if (Math.abs(dir1 - dir2) == 2)
					continue;
				int tempxx = tempx + deltaS[dir2][0];
				int tempyy = tempy + deltaS[dir2][1];
				if (!isIn(tempxx, tempyy))
					continue;
				if (maps[tempxx][tempyy] < 0) {
					killcount -= maps[tempxx][tempyy];
				}
				for (int dir3 = 1; dir3 <= 4; dir3++) {
					if (Math.abs(dir3 - dir2) == 2)
						continue;
					int tempxxx = tempxx + deltaS[dir3][0];
					int tempyyy = tempyy + deltaS[dir3][1];
					if (!isIn(tempxxx, tempyyy))
						continue;
					if (maps[tempxxx][tempyyy] < 0) {
						killcount -= maps[tempxxx][tempyyy];
					}
					int ord = 100 * dir1 + 10 * dir2 + dir3;
					orders.offer(new dirXY(killcount, ord));
					if (maps[tempxxx][tempyyy] < 0) {
						killcount += maps[tempxxx][tempyyy];
					}
				}
				if (maps[tempxx][tempyy] < 0) {
					killcount += maps[tempxx][tempyy];
				}
			}
		}
		return orders.peek();
	}

	private static void moveF() {
//		System.out.println("이동 전 물고기들");
		int fishcount2 = Fishes.size();
		for (int i = 0; i < fishcount2; i++) {
//			System.out.println(i);
			boolean set = false;
			dirXY fish = Fishes.remove(0);
//			System.out.println(fish);
//			System.out.println(fish);
			if (kills[fish.x][fish.y]) { // 잡아먹힘
				Fishes.remove(i);
				continue;
			}
			boolean first = true;
			int tempdir = fish.dir;
//			System.out.println(tempdir);
			int dir = fish.dir;
			for (int ic = 0; ic < 8; ic++) {
				if(!first) {
					dir--;
				}
				first = false;
				if (dir <= 0)
					dir = 8;
//				if(first == false && (dir == (tempdir+1))) {
//					break;
//				}
				first = false;
//				System.out.println(dir + ", " + (tempdir+1) + ", " + first + ", " + fishcount2);
				int tempx = fish.x + deltaF[dir][0];
				int tempy = fish.y + deltaF[dir][1];
				if (!isIn(tempx, tempy))
					continue;
				if (tempx == Shark.x && tempy == Shark.y)
					continue;
				if (smellmaps[tempx][tempy] != 0)
					continue; // 냄새 안돼
				if (maps[tempx][tempy] <= 0) {
					maps[fish.x][fish.y] += 1; // 원래 자리 없애
					maps[tempx][tempy] -= 1;
					Fishes.add(new dirXY(tempx, tempy, dir, fish.type));
					set = true;
					break;
				}
			}
			if(!set) {
				Fishes.add(new dirXY(fish.x, fish.y, fish.dir, fish.type));
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 1 && x < 5 && y >= 1 && y < 5;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int dir;
		int type; // -1:물고기 2:냄새 4: 상어

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public dirXY(int x, int y, int dir, int type) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.type = type;
		}

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + ", dir=" + dir + ", type=" + type + "]";
		}

		@Override
		public int compareTo(dirXY o) { // 앞이 물고기 수, 뒤가 이동 좌표
			if (this.x == o.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(o.x, this.x);
		}

	}
}
