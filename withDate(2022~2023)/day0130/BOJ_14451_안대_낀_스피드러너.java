package day0130;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14451_안대_낀_스피드러너 {
	static int N;
	static int[] Maps;
	static int Time;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		Maps = new int[N];
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < N; j++) {
				if (templine.charAt(j) == 'H')
					Maps[i] |= 1 << j;
			}
		}
		solv();
		write.write(Time + "\n");
		write.close();
		read.close();
	}

	private static void solv() {
		BFS();
	}

	private static void BFS() {
		Time = 0;
		Queue<Player> BFSQ = new LinkedList<>();
		BFSQ.add(new Player());
		while (Time < N * N + 5) {
			Time++;
			int size = BFSQ.size();
			while (size-- > 0) {
				Player temp = BFSQ.poll();
				int[] next = isAvail(1, temp); // 현재 위치에서 우회전했을 때 이동 가능한가
				if (next[0] != -1) {
					BFSQ.add(new Player(temp, next)); // 방향만 바구고 다음 위치 저장해서 넘김
				}
				next = isAvail(2, temp);
				if (next[0] != -1) {
					BFSQ.add(new Player(temp, next));
				}
				if (temp.NextMove[0] != -1) { // 저장된 위치가 있으면(전진할 수 있으면)
					temp.doMove();
					if (temp.Ux == 0 && temp.Uy == N - 1)
						temp.Udone = true;
					if (temp.Rx == 0 && temp.Ry == N - 1)
						temp.Rdone = true;
					if (temp.Udone && temp.Rdone)
						return;
					next = isAvail(0, temp);
					temp.NextMove = next.clone();
					BFSQ.add(temp);
				}
			}
		}
	}

	private static boolean doCheckMap(int[] inputmap, int movedX, int movedY) {
		return isIn(movedX, movedY) && (inputmap[movedX] & 1 << movedY) == 0 && (Maps[movedX] & 1 << movedY) == 0;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	private static int[] isAvail(int order, Player input) { // 0 : 전진, 1 : 우회전, 2 : 좌회전
		int[] answer = new int[5];
		answer[0] = -1;
		int nextx, nexty;
		int tempdir = order == 0 ? input.dir : (order == 1 ? (input.dir + 1) % 4 : (input.dir + 3) % 4);
		if (!input.Udone) {
			nextx = input.Ux + deltas[tempdir][0];
			nexty = input.Uy + deltas[tempdir][1];
			if (doCheckMap(input.Vis[0], nextx, nexty)) {
				answer[1] = deltas[tempdir][0];
				answer[2] = deltas[tempdir][1];
				answer[0] = tempdir;
			}
		}
		if (!input.Rdone) {
			nextx = input.Rx + deltas[(tempdir + 1) % 4][0];
			nexty = input.Ry + deltas[(tempdir + 1) % 4][1];
			if (doCheckMap(input.Vis[1], nextx, nexty)) {
				answer[3] = deltas[(tempdir + 1) % 4][0];
				answer[4] = deltas[(tempdir + 1) % 4][1];
				answer[0] = tempdir;
			}
		}
		return answer;
	}

	private static class Player {
		int Ux;
		int Uy;
		int Rx;
		int Ry;
		int dir;
		int[][] Vis = new int[2][N];;
		int[] NextMove;
		boolean Udone, Rdone;

		public Player() {
			this.Ux = this.Rx = N - 1;
			this.Uy = this.Ry = 0;
			this.dir = 0;
			this.Vis = new int[2][N];
			this.Vis[0][N - 1] = this.Vis[1][N - 1] = 1;
			this.NextMove = new int[] { 0, -1, 0, 0, 1 };
			this.Udone = this.Rdone = false;
		}

		public Player(Player input, int[] Next) { // 현재 위치에서 방향만 바꾸고 다음 위치를 저장한 놈
			this.Ux = input.Ux;
			this.Uy = input.Uy;
			this.Rx = input.Rx;
			this.Ry = input.Ry;
			this.dir = Next[0];
			this.Vis[0] = input.Vis[0].clone();
			this.Vis[1] = input.Vis[1].clone();
			this.NextMove = Next.clone();
			this.Udone = input.Udone;
			this.Rdone = input.Rdone;
		}

		public void doMove() { // 저장된 다음 위치로 이동
			this.Ux += NextMove[1];
			this.Uy += NextMove[2];
			this.Rx += NextMove[3];
			this.Ry += NextMove[4];
			this.Vis[0][this.Ux] |= 1 << this.Uy;
			this.Vis[1][this.Rx] |= 1 << this.Ry;
		}

	}

}
