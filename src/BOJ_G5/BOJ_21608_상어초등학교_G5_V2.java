package BOJ_G5;

import java.util.*;
import java.io.*;

public class BOJ_21608_상어초등학교_G5_V2 {
	static int N;
	static Queue<Integer> StudentOrders;
	static int[][] StudentsInfos;
	static int[][][] Map; // [0] : 세팅된 사람 , [1] : 카운트 포인트
	static int[][] Deltas;
	static long Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		init();
		for (int i = 0; i < N * N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			StudentOrders.add(num);
			StudentsInfos[num] = new int[] { a, b, c, d, -1 };
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		StudentsInfos = new int[N * N + 1][5];
		Map = new int[N][N][2];
		StudentOrders = new LinkedList<>();
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
	}

	private static void solv() {
		while (!StudentOrders.isEmpty()) {
			int st = StudentOrders.poll();
			addSideLikes(st);
			getMaxCountPointLoc(st);
		}
		getTotalCount();
	}

	// st가 선호하는 학생들이 배치된 인접 빈 공간 중 인접 학생이 가장 많은 공간 기록하기 (특정 공간의 인접 선호 학생 수 구하기)
	private static void addSideLikes(int st) {
		for (int i = 0; i < 4; i++) {
			int like = StudentsInfos[st][i];
			if (StudentsInfos[like][4] != -1) { // 선호 학생 중 이미 배치된 학생이라면
				int likesLoc = StudentsInfos[like][4]; // 해당 학생의 위치 (int화)
				for (int dir = 0; dir < 4; dir++) {
					int nextR = likesLoc / 100 + Deltas[dir][0];
					int nextC = likesLoc % 100 + Deltas[dir][1];
					if (isIn(nextR, nextC) && Map[nextR][nextC][0] == 0) { // 해당 학생 인접 공간이 빈 자리라면
						Map[nextR][nextC][1]++;
					}
				}
			}
		} // 모든 선호 학생의 인접 공간에 대해 빈 공간 수 기록
	}

	// 모든 맵에서 원하는 조건의 자리 찾기
	private static void getMaxCountPointLoc(int st) {
		int[] maxCount = new int[] { -1, -1 };
		int loc = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Map[i][j][0] == 0) { // 앉을 수 있는 곳 중
					if (Map[i][j][1] >= maxCount[0]) { // 해당 자리가 가진 인접 선호 학생 수
						int emptyCount = 0; // 주변 빈 공간 emptycount
						for (int dir = 0; dir < 4; dir++) {
							int nextI = i + Deltas[dir][0];
							int nextJ = j + Deltas[dir][1];
							if (isIn(nextI, nextJ) && Map[nextI][nextJ][0] == 0) {
								emptyCount++;
							}
						}
						if (Map[i][j][1] > maxCount[0]) { // 선호 학생 수가 가장 많은 공간이라면
							maxCount = new int[] { Map[i][j][1], emptyCount };
							loc = i * 100 + j;
						} else if (Map[i][j][1] == maxCount[0]) { // 같은 수라면
							if (emptyCount > maxCount[1]) { // 빈공간이 많은 공간
								maxCount = new int[] { Map[i][j][1], emptyCount };
								loc = i * 100 + j;
							}
						}
					}
					if (Map[i][j][1] != 0) { // 맵 선호 학생 수 초기화
						Map[i][j][1] = 0;
					}
				}
			}
		}
		Map[loc / 100][loc % 100][0] = st;
		StudentsInfos[st][4] = loc;
	}

	// 조건에 맞는 점수 카운트
	private static void getTotalCount() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int st = Map[i][j][0];
				int countLikes = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nextI = i + Deltas[dir][0];
					int nextJ = j + Deltas[dir][1];
					if (isIn(nextI, nextJ)) {
						for (int like = 0; like < 4; like++) {
							if (Map[nextI][nextJ][0] == StudentsInfos[st][like]) {
								countLikes++;
							}
						}
					}
				}
				if (countLikes != 0)
					Result += Math.pow(10, countLikes - 1);
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}
