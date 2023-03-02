package day1105;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14499 {
	static int N, M, K;
	static dirXY temp;
	static int[][] maps;
	static int[][] deltas = { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Queue<Integer> movedir = new LinkedList<>();
	static int[] dice = new int[7];
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int x = Integer.parseInt(tokens.nextToken());
		int y = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		temp = new dirXY(x, y);
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // mapping
		tokens = new StringTokenizer(read.readLine());
		movedir = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(tokens.nextToken());
			moveDice(dir);
		}
		read.close();
		write.close();
	}

	private static void moveDice(int dir) throws IOException { // 움직일 수 있으면 굴리자
		if (!isIn(temp.x + deltas[dir][0], temp.y + deltas[dir][1]))
			return;
		rollDice(dir); // 주사위 위치 갱신, 값 갱신
		if (maps[temp.x][temp.y] != 0) { // 맵에 숫자가 있으면 주사위 업데이트
			dice[6] = maps[temp.x][temp.y];
			maps[temp.x][temp.y] = 0;
		} else { // 숫자 없으면 맵 업데이트
			maps[temp.x][temp.y] = dice[6];
		}
		write.write(dice[1] + "\n");
		return;
	}

	private static void rollDice(int dir) { // 바닥면에 따라 주사위 설정
		temp.move(dir);
		int[] newdice = new int[7];
		switch (dir) {
		case (1): { // 동쪽
			newdice[6] = dice[3];
			newdice[5] = dice[5];
			newdice[4] = dice[6];
			newdice[3] = dice[1];
			newdice[2] = dice[2];
			newdice[1] = dice[4];
			break;
		}
		case (2): { // 서쪽
			newdice[6] = dice[4];
			newdice[5] = dice[5];
			newdice[4] = dice[1];
			newdice[3] = dice[6];
			newdice[2] = dice[2];
			newdice[1] = dice[3];
			break;
		}
		case (3): { // 북쪽
			newdice[6] = dice[2];
			newdice[5] = dice[6];
			newdice[4] = dice[4];
			newdice[3] = dice[3];
			newdice[2] = dice[1];
			newdice[1] = dice[5];
			break;
		}
		case (4): { // 남쪽
			newdice[6] = dice[5];
			newdice[5] = dice[1];
			newdice[4] = dice[4];
			newdice[3] = dice[3];
			newdice[2] = dice[6];
			newdice[1] = dice[2];
			break;
		}
		}
		dice = newdice.clone();
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static class dirXY {
		int x;
		int y;

		public void move(int dir) {
			this.x += deltas[dir][0];
			this.y += deltas[dir][1];
		}

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
