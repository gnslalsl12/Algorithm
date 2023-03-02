package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_visits {
	static int N, X;
	static int[][] maps;
	static int totalresult;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		totalresult = 2 * N;
		for (int i = 0; i < N; i++) {
			CheckHor(i);
			CheckVer(i);
		}
		System.out.print(totalresult);
	}

	private static void CheckHor(int line) {
		int floor = maps[line][0];
		boolean[] tempV = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (floor != maps[line][i]) { // 층이 달라짐
				if (maps[line][i] == floor - 1) { // 층이 낮아짐
					floor = maps[line][i];
					for (int j = 0; j < X; j++) {
						if (!isIn(line, i + j)) {
							totalresult--;
							return; // 앞이 범위 밖이면
						}
						if (tempV[i + j]) {
							totalresult--;
							return;
						}
						tempV[i + j] = true;
						if (maps[line][i + j] != floor) { // 앞으로 평평하지 않으면
							totalresult--;
							return;
						}
					}
				} else if (maps[line][i] == floor + 1) { // 층이 높아짐
					for (int j = 0; j < X; j++) {
						if (!isIn(line, i - 1 - j)) {
							totalresult--;
							return; // 뒤가 범위 밖이면
						}
						if (tempV[i - 1 - j]) {
							totalresult--;
							return;
						}
						tempV[i - 1 - j] = true;
						if (maps[line][i - 1 - j] != floor) { // 뒤가 평평하지 ㅇ낳으면
							totalresult--;
							return;
						}
					}
					floor = maps[line][i];
				} else {
					totalresult--;
					return;
				}
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void CheckVer(int line) {
		int floor = maps[0][line];
		boolean[] tempV = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (floor != maps[i][line]) { // 층이 달라짐
				if (maps[i][line] == floor - 1) { // 층이 낮아짐
					floor = maps[i][line];
					for (int j = 0; j < X; j++) {
						if (!isIn(i + j, line)) {
							totalresult--;
							return; // 앞이 범위 밖이면
						}
						if (tempV[i + j]) {
							totalresult--;
							return;
						}
						tempV[i + j] = true;
						if (maps[i + j][line] != floor) { // 앞으로 평평하지 않으면
							totalresult--;
							return;
						}
					}
				} else if (maps[i][line] == floor + 1) { // 층이 높아짐
					for (int j = 0; j < X; j++) {
						if (!isIn(i - 1 - j, line)) {
							totalresult--;
							return; // 뒤가 범위 밖이면
						}
						if (tempV[i - 1 - j]) {
							totalresult--;
							return;
						}
						tempV[i - 1 - j] = true;
						if (maps[i - 1 - j][line] != floor) { // 뒤가 평평하지 ㅇ낳으면
							totalresult--;
							return;
						}
					}
					floor = maps[i][line];
				} else {
					totalresult--;
					return;
				}
			}
		}
	}
}