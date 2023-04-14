package BOJ_G3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_23288_주사위굴리기2 {
	static int N, M, K, Dir;
	static int[][] Map;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] Dice;
	static int[] Loc = { 1, 1 };
	static long Result = 0;
	static int[] Visited;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Dir = 1;
		Map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			tokens = new StringTokenizer(read.readLine());
			for (int c = 1; c <= M; c++) {
				Map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		makeDice();
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void makeDice() { // 주사위 초기 설정
		Dice = new int[4][3];
		Dice[0][1] = 2;
		Dice[1][0] = 4;
		Dice[1][1] = 1;
		Dice[1][2] = 3;
		Dice[2][1] = 5;
		Dice[3][1] = 6;
	}

	private static void solv() {
		for (int time = 0; time < K; time++) {
			doMove();
			getPoints();
			getDir();
		}
	}

	private static void doMove() { // 방향대로 움직이기 (Loc 위치 이동) (필요시 Dir 변경)
		int tempLocR = Loc[0] + deltas[Dir][0];
		int tempLocC = Loc[1] + deltas[Dir][1];
		if (!isIn(tempLocR, tempLocC)) {
			Dir = (Dir + 2) % 4;
			tempLocR = Loc[0] + deltas[Dir][0];
			tempLocC = Loc[1] + deltas[Dir][1];
		}
		doRollDice(Dir);
		Loc[0] = tempLocR;
		Loc[1] = tempLocC;
	}

	private static void doRollDice(int dir) { // 방향대로 굴렸을 때 주사위 전개도 변경
		switch (dir) {
		case 0: {
			int temp = Dice[0][1];
			for (int i = 0; i < 3; i++) {
				Dice[i][1] = Dice[i + 1][1];
			}
			Dice[3][1] = temp;
			break;
		}
		case 1: {
			int temp = Dice[3][1];
			Dice[3][1] = Dice[1][2];
			Dice[1][2] = Dice[1][1];
			Dice[1][1] = Dice[1][0];
			Dice[1][0] = temp;
			break;
		}
		case 2: {
			int temp = Dice[3][1];
			for (int i = 3; i >= 1; i--) {
				Dice[i][1] = Dice[i - 1][1];
			}
			Dice[0][1] = temp;
			break;
		}
		case 3: {
			int temp = Dice[3][1];
			Dice[3][1] = Dice[1][0];
			Dice[1][0] = Dice[1][1];
			Dice[1][1] = Dice[1][2];
			Dice[1][2] = temp;
			break;
		}
		}
	}

	private static void getPoints() { // DFS로 맞닿은 숫자 모두 찾기
		int r = Loc[0];
		int c = Loc[1];
		Visited = new int[N + 1];
		Visited[r] |= 1 << c;
		int num = Map[r][c];
		Result += num;
		DFS(r, c, num);
	}

	private static void DFS(int r, int c, int num) {
		for (int dir = 0; dir < 4; dir++) {
			int nextR = r + deltas[dir][0];
			int nextC = c + deltas[dir][1];
			if (!isIn(nextR, nextC) || Map[nextR][nextC] != num || (Visited[nextR] & (1 << nextC)) != 0)
				continue;
			Visited[nextR] |= 1 << nextC;
			Result += num;
			DFS(nextR, nextC, num);
		}
	}

	private static void getDir() { // 방향 바꾸기
		int A = Dice[3][1];
		int B = Map[Loc[0]][Loc[1]];
		if (A > B)
			Dir = (Dir + 1) % 4;
		else if (A < B)
			Dir = (Dir + 3) % 4;
	}

	private static boolean isIn(int r, int c) {
		return r >= 1 && c >= 1 && r <= N && c <= M;
	}

}
