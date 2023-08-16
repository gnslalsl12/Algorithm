import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	static int[][][] Map;
	static Coin[] Coins;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Map = new int[N][N][6];
		Coins = new Coin[K + 1];
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = -1;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				Map[i][j][0] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int k = 1; k <= K; k++) {
			tokens = new StringTokenizer(read.readLine());
			int i = Integer.parseInt(tokens.nextToken()) - 1;
			int j = Integer.parseInt(tokens.nextToken()) - 1;
			int dir = Integer.parseInt(tokens.nextToken());
			if (dir == 2)
				dir = 3;
			else if (dir == 3)
				dir = 0;
			else if (dir == 4)
				dir = 2;
			Coins[k] = new Coin(i * N + j, dir);
			Map[i][j][1] = k;
		}
		read.close();
	}

	private static void solv() {
		setWholeGame();
	}

	private static void setWholeGame() {
		for (int turn = 1; turn <= 1000; turn++) {
			for (int k = 1; k <= K; k++) {
				moveCoin(k, true);
				if (Result == 0) {
					Result = turn;
					return;
				}
			}
		}
	}

	private static void moveCoin(int k, boolean firstTime) {
		Coin current = Coins[k];
		int i = current.loc / N;
		int j = current.loc % N;
		int nextI = i + Deltas[current.dir][0];
		int nextJ = j + Deltas[current.dir][1];
		if (!isIn(nextI, nextJ) || Map[nextI][nextJ][0] == 2) { // 범위 밖 / 파란블록이면
			if (!firstTime) // 방향전환 후에도 범위 밖이거나 파란블록이면 움직임 X
				return;
			current.backwards(); // 방향전환
			moveCoin(k, false); // 방향전환 했다 표시 후 다시 move
		} else if (Map[nextI][nextJ][0] == 1) {
			moveTo(i, j, nextI, nextJ, k, true); // 빨간 블록
		} else {
			moveTo(i, j, nextI, nextJ, k, false); // 하얀 블록
		}
	}

	private static void moveTo(int i, int j, int nextI, int nextJ, int k, boolean red) {
		int originIndex = 0;
		int setIndex = -1;
		int originRemainCount = 0;
		for (int index = 1; index <= 5; index++) {
			if (Map[i][j][index] == k)
				originIndex = index; // 원래 위치에서 근간 코인의 높이 인덱스
			if (Map[nextI][nextJ][index] == 0 && setIndex == -1)
				setIndex = index; // 쌓일 위치에서 처음으로 쌓아질 높이 인덱스
			if (originIndex != 0 && Map[i][j][index] != 0)
				originRemainCount++; // 근간 코인이 업고있는 코인 수
		}
		if (setIndex + originRemainCount > 4) { // 4개 이상 쌓이는 경우 바로 끝
			Result = 0;
			return;
		}
		while (originRemainCount > 0) {
			originRemainCount--;
			int originCoin = Map[i][j][originIndex]; // 근간코인부터 위방향으로 하나씩
			Map[i][j][originIndex++] = 0; // 원래 위치에서 코인 삭제
			Map[nextI][nextJ][(red ? setIndex + originRemainCount : setIndex++)] = originCoin; // 다음 위치에 쌓기
			// (red면 위에서부터, white면 아래에서부터 쌓기)
			Coins[originCoin].moveTo(nextI, nextJ); // 움직인 코인 위치 업데이트
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

	private static class Coin {
		int loc;
		int dir;

		public Coin(int loc, int dir) {
			this.loc = loc;
			this.dir = dir;
		}

		public void moveTo(int nextI, int nextJ) {
			this.loc = nextI * N + nextJ;
		}

		public void backwards() {
			this.dir = (this.dir + 2) % 4;
		}

	}

}