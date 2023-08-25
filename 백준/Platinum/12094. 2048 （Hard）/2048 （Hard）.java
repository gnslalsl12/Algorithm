import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] Map;
	static int Result;
	static int[][] Deltas;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		Result = 0;
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		read.close();
	}

	private static void solv() {
		if (N == 1) {
			Result = Map[0][0];
			return;
		}
		setWholeCases(Map, 0);
	}

	private static void setWholeCases(int[][] map, int count) {
		for (int i = 0; i < 3; i++) {
			int[][] copyMap = getCopyMap(map);
			setMoveDir(i, copyMap, count + 1);
		}
		setMoveDir(3, getCopyMap(map), count + 1);
	}

	private static int[][] getCopyMap(int[][] map) {
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}
		return copyMap;
	}

	private static void setMoveDir(int dir, int[][] map, int count) {
		int startJ, endJ, addJ;
		boolean untilSmallerJ;
		int startIndex, addIndex;
		startJ = startIndex = 0;
		endJ = N;
		addJ = addIndex = 1;
		untilSmallerJ = true;
		boolean convertIJ = dir % 2 == 0;

		int maxNum = -1;
		if (dir == 1 || dir == 2) {
			startJ = N - 1;
			endJ = -1;
			addJ = -1;
			untilSmallerJ = false; // untilBiggerJ
			startIndex = N - 1;
			addIndex = -1;
		} else if (dir == 2) {

		}
		for (int i = 0; i < N; i++) {
			int before = -1;
			int fillIndex = startIndex;
			for (int j = startJ; compareIJ(j, endJ, untilSmallerJ); j += addJ) {
				int num = map[convertIJ ? j : i][convertIJ ? i : j];
				map[convertIJ ? j : i][convertIJ ? i : j] = 0;
				if (num != 0) { // 숫자 발견
					if (before == num) { // 합쳐질 숫자
						before = -1;
						map[convertIJ ? fillIndex : i][convertIJ ? i : fillIndex] = num * 2;
						maxNum = Math.max(maxNum, num * 2);
						fillIndex += addIndex;
					} else { // 다를 숫자
						if (before != -1) {
							map[convertIJ ? fillIndex : i][convertIJ ? i : fillIndex] = before;
							maxNum = Math.max(maxNum, before);
							fillIndex += addIndex;
						}
						before = num;
					}
				}
				if ((j == endJ - 1 || j == endJ + 1) && before != -1) {
					map[convertIJ ? fillIndex : i][convertIJ ? i : fillIndex] = before; // 마지막에 합쳐지지 못한 숫자가 남아있으면 채워주기
					maxNum = Math.max(maxNum, before);
				}
			}
		}
		if (count >= 10) {
			Result = Math.max(Result, maxNum);
		} else {
			setWholeCases(map, count);
		}
	}

	private static boolean compareIJ(int ij, int end, boolean smaller) {
		return smaller ? ij < end : ij > end;
	}

}