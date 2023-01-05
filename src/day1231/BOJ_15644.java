package day1231;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15644 {
	static int N, M;
	static char[][] maps;
	static dirXY RED, BLUE;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[] dirs = { 'U', 'R', 'D', 'L', 'U', 'R' };
	static PriorityQueue<cases> MoveStack = new PriorityQueue<>();
	static String result;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new char[N][M];
		result = "";
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				maps[i][j] = templine.charAt(j);
				if (templine.charAt(j) == 'R') {
					maps[i][j] = '.';
					RED = new dirXY(i, j, true);
				}
				if (templine.charAt(j) == 'B') {
					maps[i][j] = '.';
					BLUE = new dirXY(i, j, false);
				}
			}
		}
		MoveStack.add(new cases(RED, BLUE, ""));
		solv();
		if (result.length() == 0) {
			write.write(-1 + "\n");
		} else {
			write.write(result.length() + "\n" + result);
		}
		write.close();
		read.close();
	}

	private static void solv() throws CloneNotSupportedException {
		char beforedir = 'f';
		while (!MoveStack.isEmpty()) {
			cases pop = MoveStack.poll();
			if (pop.stacks.length() >= 10) {
				return;
			}

			if (pop.stacks.length() > 0)
				beforedir = pop.stacks.charAt(pop.stacks.length() - 1);
			for (int dir = 0; dir < 4; dir++) {
				if (dirs[dir] == beforedir || dirs[dir + 2] == beforedir)
					continue; // 안 가도 되는 방향
				cases temp = new cases(pop.RED.clone(), pop.BLUE.clone(), pop.stacks);
				boolean suc = Move(temp, dir); // temp상황의 공들을 dir방향으로 움직여라
				if (somethingMoved(pop, temp) || !suc) { // 움직임이 없거나 추가하면 안되는 경우
					continue;
				}
				if (result.length() != 0)
					return;
				else {
					MoveStack.add(temp);
				}
			}
		}
	}

	private static boolean somethingMoved(cases origin, cases moved) {
		return origin.RED.x == moved.RED.x && origin.RED.y == moved.RED.y && origin.BLUE.x == moved.BLUE.x
				&& origin.BLUE.y == moved.BLUE.y;
	}

	private static boolean Move(cases input, int dir) {
		int tempresult;
		if (dir == 0) {
			if (input.RED.x < input.BLUE.x) {
				tempresult = input.orders(dir, input.RED, input.BLUE);
			} else {
				tempresult = input.orders(dir, input.BLUE, input.RED);
			}
		} else if (dir == 1) {
			if (input.RED.y > input.BLUE.y) {
				tempresult = input.orders(dir, input.RED, input.BLUE);
			} else {
				tempresult = input.orders(dir, input.BLUE, input.RED);
			}
		} else if (dir == 2) {
			if (input.RED.x > input.BLUE.x) {
				tempresult = input.orders(dir, input.RED, input.BLUE);
			} else {
				tempresult = input.orders(dir, input.BLUE, input.RED);
			}
		} else {
			if (input.RED.y < input.BLUE.y) {
				tempresult = input.orders(dir, input.RED, input.BLUE);
			} else {
				tempresult = input.orders(dir, input.BLUE, input.RED);
			}
		}
		if (tempresult == 2)
			return false;
		else {
			input.addStack(dirs[dir]);
			if (tempresult == 1) {
				result = input.stacks;
			}
			return true;
		}
	}

	private static class cases implements Comparable<cases> {
		dirXY RED;
		dirXY BLUE;
		String stacks;

		public cases(dirXY rED, dirXY bLUE, String stacks) {
			this.RED = rED;
			this.BLUE = bLUE;
			this.stacks = stacks;
		}

		public void addStack(char next) {
			this.stacks += next;
		}

		public int orders(int dir, dirXY first, dirXY second) { // 0 : 추가해도 되는 경우, 1 : 성공, 2 : 추가X
			boolean Fhole, Shole;
			Fhole = first.GO(dir, -1, -1);
			Shole = second.GO(dir, first.x, first.y);
			int end = 0;
			if (Fhole && Shole) // 둘 다 성공
				end = 2;
			else if (!Fhole && !Shole) // 들 다 실패
				end = 0;
			else { // 하나만 성공
				if (Fhole) {
					if (first.red) // 성공한 게 빨간공이면
						end = 1;
					else
						end = 2;
				} else if (Shole) { // 성공한 게 빨간 공이면
					if (second.red)
						end = 1;
					else
						end = 2;
				}
			}
			return end;
		}

		@Override
		public int compareTo(cases o) {
			return Integer.compare(this.stacks.length(), o.stacks.length());
		}

	}

	private static class dirXY implements Cloneable {
		int x;
		int y;
		boolean red;

		@Override
		protected dirXY clone() throws CloneNotSupportedException {
			return (dirXY) super.clone();
		}

		public dirXY(int x, int y, boolean red) {
			super();
			this.x = x;
			this.y = y;
			this.red = red;
		}

		public boolean GO(int dir, int noX, int noY) {
			int movex = 0;
			int movey = 0;
			if (dir == 0)
				movex = -1;
			else if (dir == 1)
				movey = 1;
			else if (dir == 2)
				movex = 1;
			else
				movey = -1;
			int i = 1;
			boolean holed = false;
			for (; i < 10; i++) {
				int nextx = this.x + movex * i;
				int nexty = this.y + movey * i;
				if (maps[nextx][nexty] == 'O')
					holed = true;
				if (maps[nextx][nexty] == '#' || (nextx == noX && nexty == noY)) {
					i--;
					break;
				}
			}
			this.x += movex * i;
			this.y += movey * i;
			return holed;
		}

	}

}
