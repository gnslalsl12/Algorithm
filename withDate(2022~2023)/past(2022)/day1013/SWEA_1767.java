package day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1767 {
	static int[] maps;
	static int N;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<dirXY> Cores;
	static int count;
	static int corecount;
	static PriorityQueue<dirXY>result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 1; test <= T; test++) {
			result = new PriorityQueue<>();
			Cores = new ArrayList<>();
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			maps = new int[N];
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < N; j++) {
					if (tokens.nextToken().equals("1")) {
						maps[i] |= 1 << j;
						if (i != 0 && i != N - 1 && j != 0 && j != N - 1)
							Cores.add(new dirXY(i, j));
					}
				}
			}
			count = 0;
			if (Cores.size() != 0) {
				set(0);
				sb.append(String.format("#%d %d\n", test, result.peek().y));
			} else {
				sb.append(String.format("#%d %d\n", test, 0));
			}
		}
		System.out.print(sb);
	}

	private static void print() {
		System.out.println("///////////////////");
		StringBuilder sbb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((maps[i] & (1 << j)) != 0) {
					sbb.append("1 ");
				} else {
					sbb.append("0 ");
				}
			}
			sbb.append("\n");
		}
		System.out.print(sbb);
		System.out.println("///////////////////");
	}

	private static void set(int index) {
		dirXY temp = Cores.get(index);
		int x = temp.x;
		int y = temp.y;
		
		int countt = 0;
		for(int dir = 0; dir < 4; dir++) {
			if((maps[x + deltas[dir][0]] & (1<<(y+deltas[dir][1]))) != 0){
				countt++;
			}
		}
		if(countt == 4) {
			if(index == Cores.size()-1) {
				result.add(new dirXY(corecount, count));
			}else {
				set(index+1);
			}
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			for (int pow = 1; pow < N; pow++) {
				int nextx = x + deltas[dir][0] * pow;
				int nexty = y + deltas[dir][1] * pow;
				if (!isIn(nextx, nexty)) { // 끝에까지 도달함(연결됨)
					if (index == Cores.size() - 1) { // 전부 다 배치해봄
//						print();
						corecount++;
						result.add(new dirXY(corecount, count));
						corecount--;
						for (int i = 1; i < pow; i++) {
							int delx = x + deltas[dir][0] * i;
							int dely = y + deltas[dir][1] * i;
							maps[delx] &= ~(1 << dely);
							count--;
						}
						break;
					} else {
						corecount++;
						set(index + 1);
						corecount--;
						for (int i = 1; i < pow; i++) {
							int delx = x + deltas[dir][0] * i;
							int dely = y + deltas[dir][1] * i;
							maps[delx] &= ~(1 << dely);
							count--;
						}
						break;
					}
				}
				if ((maps[nextx] & (1 << nexty)) != 0) { // 코어나 전선 만남
					for (int i = 1; i < pow; i++) {
						int delx = x + deltas[dir][0] * i;
						int dely = y + deltas[dir][1] * i;
						maps[delx] &= ~(1 << dely);
						count--;
					}
					break; // 다른 방향
				}
				maps[nextx] |= 1 << nexty; // 전선 놔줌
				count++;
			}
		}
		
		if(index == Cores.size() -1) {
			result.add(new dirXY(corecount, count));
		}else {
			set(index+1);
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	private static class dirXY  implements Comparable<dirXY>{
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(dirXY o) {
			if(this.x == o.x)return Integer.compare(this.y, o.y); 
			return Integer.compare(o.x, this.x);
		}

	}
}
