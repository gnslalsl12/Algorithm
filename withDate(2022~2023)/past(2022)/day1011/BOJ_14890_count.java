package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_count {
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
		totalresult = 0;
		for (int i = 0; i < N; i++) {
			if (CheckHor(i))
				totalresult++;
			if (CheckVer(i))
				totalresult++;
		}
		System.out.print(totalresult);
	}

	private static boolean CheckHor(int line) {
		int floor = maps[line][0];
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (floor == maps[line][i]) {
				count++;
			} else { // 층이 달라짐
				if (floor + 1 == maps[line][i]) { // 층이 높아짐
					if (count < X) // 놓을 수 없음
						return false;
					count = 1;
					floor = maps[line][i];
				} else if (floor - 1 == maps[line][i]) { // 층이 낮아짐
					if (count < 0)// 갚지 못하고 낮아짐
						return false;
					count = -X + 1;// X개만큼 갚아야하고 해당 위치에서 하나를 갚음
					floor = maps[line][i];
				} else {
					return false;
				}
			}
		}
		if (count < 0)
			return false;
		return true;
	}

	private static boolean CheckVer(int line) {
		int floor = maps[0][line];
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (floor == maps[i][line]) {
				count++;
			} else { // 층이 달라짐
				if (floor + 1 == maps[i][line]) { // 층이 높아짐
					if (count < X) // 놓을 수 없음
						return false;
					count = 1;
					floor = maps[i][line];
				} else if (floor - 1 == maps[i][line]) { // 층이 낮아짐
					if (count < 0)
						return false;
					count = -X + 1; // X개만큼 갚아야하고 해당 위치에서 하나를 갚음
					floor = maps[i][line];
				} else {
					return false;
				}
			}
		}
		if (count < 0)
			return false; // 마지막에서 갚지 못함
		return true;
	}
}