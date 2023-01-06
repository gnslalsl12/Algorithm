package day1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {
	static long StackedOverSands;
	static int N;
	static int[][] maps;
	static int RotDir; // 상어 회전 머리 방향
	static int[][] deltas = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int x, y;
	static int minussand;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new int[N][N];
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		RotDir = 0;
		boolean[][] visits = new boolean[N][N];
		
		//시작점 세팅
		x = N / 2;
		y = N / 2;
		visits[x][y] = true;
		
		while (true) {
			x += deltas[RotDir][0];
			y += deltas[RotDir][1];

			SpreadSands();
			if (x == 0 && y == 0)
				break;

			visits[x][y] = true;
			if (!visits[x + deltas[RU()][0]][y + deltas[RU()][1]]) {
				if (RotDir == 3)
					RotDir = 0;
				else
					RotDir++; // 왼쪽이 안 간 곳이면 회전
			}
		}
		System.out.println(StackedOverSands);
	}

	private static int RL() {
		if (RotDir == 0)
			return 3;
		return RotDir - 1;
	}

	private static int R2() {
		if (RotDir == 0)
			return 2;
		if (RotDir == 1)
			return 3;
		if (RotDir == 2)
			return 0;
		return 1;
	}

	private static int RU() {
		if (RotDir == 3)
			return 0;
		return RotDir + 1;
	}

	private static void SpreadSands() {
		if (maps[x][y] == 0)
			return;
		minussand = 0;	//감소한 모래 값
		int tempsand = maps[x][y];

		int tempx = 0;
		int tempy = 0;
		// 왼쪽 7프로
		tempx = x + deltas[RU()][0];
		tempy = y + deltas[RU()][1];
		setSand(tempx, tempy, tempsand * (0.07));

		// 오른쪾 8프로
		tempx = x + deltas[RL()][0];
		tempy = y + deltas[RL()][1];
		setSand(tempx, tempy, tempsand * (0.07));

		// 왼쪽 2프로
		tempx = x + deltas[RU()][0] * 2;
		tempy = y + deltas[RU()][1] * 2;
		setSand(tempx, tempy, tempsand * (0.02));

		// 오른쪽 2프로
		tempx = x + deltas[RL()][0] * 2;
		tempy = y + deltas[RL()][1] * 2;
		setSand(tempx, tempy, tempsand * (0.02));

		// 오른쪽 뒤 1프로
		tempx = x + deltas[RL()][0] + deltas[R2()][0];
		tempy = y + deltas[RL()][1] + deltas[R2()][1];
		setSand(tempx, tempy, tempsand * (0.01));

		// 왼쪽 뒤 1프로
		tempx = x + deltas[RU()][0] + deltas[R2()][0];
		tempy = y + deltas[RU()][1] + deltas[R2()][1];
		setSand(tempx, tempy, tempsand * (0.01));

		// 오른쪽 앞 10프로
		tempx = x + deltas[RotDir][0] + deltas[RL()][0];
		tempy = y + deltas[RotDir][1] + deltas[RL()][1];
		setSand(tempx, tempy, tempsand * (0.1));

		// 왼쪽 앞 10프로
		tempx = x + deltas[RotDir][0] + deltas[RU()][0];
		tempy = y + deltas[RotDir][1] + deltas[RU()][1];
		setSand(tempx, tempy, tempsand * (0.1));

		// 진항방향 앞앞 5프로
		tempx = x + deltas[RotDir][0] * 2;
		tempy = y + deltas[RotDir][1] * 2;
		setSand(tempx, tempy, tempsand * (0.05));

		// 뺸 나머지 모래만큼 a로 이동
		tempx = x + deltas[RotDir][0];
		tempy = y + deltas[RotDir][1];
		setSand(tempx, tempy, tempsand - minussand);

		maps[x][y] = 0;
	}

	private static void setSand(int x, int y, double persand) {
		int ps = (int) persand;
		minussand += ps;
		if (isin(x, y))
			maps[x][y] += ps;
		else
			StackedOverSands += ps;
	}

	private static boolean isin(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}