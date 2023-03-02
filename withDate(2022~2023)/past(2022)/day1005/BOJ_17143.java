package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_17143 {
	static int R, C, M;
	static int[] maps;
	static ArrayList<dirXY> orders = new ArrayList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static long result;

	public static void main(String[] args) throws IOException {

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[R];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int speed = Integer.parseInt(tokens.nextToken());
			int dir = Integer.parseInt(tokens.nextToken());
			if (dir == 1)
				dir = 0;
			else if (dir == 3)
				dir = 1;
			else if (dir == 4)
				dir = 3;
			int size = Integer.parseInt(tokens.nextToken());
			orders.add(new dirXY(x, y, speed, dir, size));
			maps[x] |= 1<<y;;
		}
		for (int i = 0; i < C; i++) {
			getShar(i); // 잡아서 죽임
			maps = new int[R];
			Collections.sort(orders);
			int len = orders.size();
			for (int k = len - 1; k >= 0; k--) {
				getNext(orders.remove(k));
			}
		}
		System.out.println(result);
	}

	private static void getShar(int col) {
		for (int line = 0; line < R; line++) { // 잡기
			if ((maps[line] & (1<<col)) != 0) {
				KillSH(line, col);
				break;
			}
		}
	}

	private static void KillSH(int x, int y) {
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).x == x && orders.get(i).y == y) {
				result += orders.size();
				System.out.println(orders.get(i).toString());
				orders.remove(i);
			}
		}
	}

	private static void getNext(dirXY input) {
		int x = input.x;
		int y = input.y;
		int dir = input.dir;
		x += deltas[dir][0] * input.speed;
		y += deltas[dir][1] * input.speed;
		if (dir == 0) {
			if (x < 0) {
				x *= -1;
				if (((x - 1) / (R - 1)) % 2 == 0) { // 홀수번 부딪힘
					dir = 2;
					x = (x - 1) % (R - 1) + 1;
				} else {
					int tempx = (x - 1) % (R - 1) + 1;
					x = (R - 1) - tempx;
				}
			}
		} else if (dir == 1) {
			if (y >= C) {
				if (((y - 1) / (C - 1)) % 2 == 0) { // 짝수번 부딪힘
					y = (y - 1) % (C - 1) + 1;
				} else { // 홀수번 부딪힘
					dir = 3;
					int tempy = (y - 1) % (C - 1) + 1;
					y = (C - 1) - tempy;
				}
			}
		} else if (dir == 2) {
			if (x >= R) {
				if (((x - 1) / (R - 1)) % 2 == 0) { // 짝수번 부딪힘
					x = (x - 1) % (R - 1) + 1;
				} else { // 홀수번 부딪힘
					dir = 0;
					int tempx = (x - 1) % (R - 1) + 1;
					x = (R - 1) - tempx;
				}
			}
		} else if (dir == 3) {
			if (y < 0) {
				y *= -1;
				if (((y - 1) / (C - 1)) % 2 == 0) { // 홀수번 부딪힘
					dir = 1;
					y = (y - 1) % (C - 1) + 1;
				} else {
					int tempy = (y - 1) % (C - 1) + 1;
					y = (C - 1) - tempy;
				}
			}
		}
		if ((maps[x] & (1<<y)) != 0) {
			return;
		} else {
			maps[x] |= 1<<y;
			orders.add(new dirXY(x, y, input.speed, dir, input.size));
		}
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int speed;
		int dir;
		int size;
		public dirXY(int x, int y, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
		@Override
		public String toString() {
			return "dirXY [x=" + x + ", y=" + y + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(this.size, o.size);
		}
	}
}