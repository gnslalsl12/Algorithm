package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19235 {
	static int N;
	static int[][] G;
	static int[][] B;
	static int block;
	static boolean[] GBsplit;
	static boolean[] BBsplit;
	static int score;
	static boolean downed;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		GBsplit = new boolean[N + 1];
		BBsplit = new boolean[N + 1];
		StringTokenizer tokens;
		block = 1;
		score = 0;
		G = new int[4][6];
		B = new int[4][6];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int t = Integer.parseInt(tokens.nextToken());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			insert temp = new insert(t, x, y);
			putG(temp);
			putB(temp);
			block++;
			downed = true;
			scanLine();
			topLine();
		}
		System.out.println(score);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (G[i][j] != 0) {
					count++;
				}
				if (B[i][j] != 0) {
					count++;
				}
			}
		}
		System.out.print(count);
	}

	private static void topLine() {
		int Gline = 0;
		int Bline = 0;
		for (int j = 4; j < 6; j++) {
			boolean Gcheck = false;
			boolean Bcheck = false;
			for (int i = 0; i < 4; i++) {
				if (G[i][j] != 0) {
					Gcheck = true;
				}
				if (B[i][j] != 0) {
					Bcheck = true;
				}
			}
			if (Gcheck)
				Gline++;
			if (Bcheck)
				Bline++;
		}

		if (Gline == 1) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= 5; j++) {
					G[i][j - 1] = G[i][j];
				}
			}
		} else if (Gline == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j <= 5; j++) {
					G[i][j - 2] = G[i][j];
				}
			}
		}
		if (Bline == 1) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= 5; j++) {
					B[i][j - 1] = B[i][j];
				}
			}
		} else if (Bline == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j <= 5; j++) {
					B[i][j - 2] = B[i][j];
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j < 6; j++) {
				G[i][j] = 0;
				B[i][j] = 0;
			}
		}
	}

	private static void scanLine() {
		while (true) {
			boolean deleted = false;
			for (int i = 5; i >= 0; i--) {
				if (G[0][i] != 0 && G[1][i] != 0 && G[2][i] != 0 && G[3][i] != 0) { // 지울 라인 발견
					deleted = true;
					score++;
					GBsplit[G[0][i]] = true;
					GBsplit[G[1][i]] = true;
					GBsplit[G[2][i]] = true;
					GBsplit[G[3][i]] = true;
					G[0][i] = 0;
					G[1][i] = 0;
					G[2][i] = 0;
					G[3][i] = 0;
				}
				if (B[0][i] != 0 && B[1][i] != 0 && B[2][i] != 0 && B[3][i] != 0) { // 지울 라인 발견
					deleted = true;
					score++;
					BBsplit[B[0][i]] = true;
					BBsplit[B[1][i]] = true;
					BBsplit[B[2][i]] = true;
					BBsplit[B[3][i]] = true;
					B[0][i] = 0;
					B[1][i] = 0;
					B[2][i] = 0;
					B[3][i] = 0;
				}
			}
			if (deleted == false && downed == false)
				return; // 지운게 없고 내려온 게 없음
			if (deleted) { // 지워진 게 있으니 한 번 더 보자
				continue;
			} else { // 더이상 지울 게 없음
				while (true) {
					downed = false;
					downLine(G, true);
					downLine(B, false);
					if (downed) {
						continue; // 한 칸 밀려 내려왔으니 다시 내려보자
					} else {
						break;
					}
				}
			}
		}
	}

	private static void downLine(int[][] line, boolean Gch) {
		boolean[] check;
		if (Gch) {
			check = GBsplit;
		} else {
			check = BBsplit;
		}
		for (int i = 0; i < 4; i++) { // 한 라인씩 봤을 떄
			for (int j = 1; j < 6; j++) { // 아래에서 위로 보면서
				if (line[i][j] != 0 && line[i][j - 1] == 0 && check[line[i][j]]) { // 블록이 있고 아래가 비어있고 깨진 블록이다 (한칸짜리)
					for (int k = j; k >= 0; k--) { // 그 위치에서 아래로 봤을 때
						if (line[i][k] == 0) { // 빈 공간이 있으면
							line[i][k] = line[i][j];
							line[i][j] = 0; // 블록 아래로 떨어지기
							downed = true;
							break;
						}
					}
				}
				if (!check[line[i][j]] && line[i][j] != 0) { // 안 깨진 가로 두 칸 짜리이면
					int y1, y2;
					if (i == 0) {
						y1 = 0;
						y2 = 1;
					} else if (i == 3) {
						y1 = 2;
						y2 = 3;
					} else {
						y1 = i;
						if (line[i][j] == line[i - 1][j]) {
							y2 = i - 1;
						} else {
							y2 = i + 1;
						}
					}
					if (line[y1][j - 1] == 0 && line[y2][j - 1] == 0) { // 둘 다 아래에 빈공간일 때
						for (int k = j - 1; k >= 0; k--) {
							if (k == 0 || (line[y1][k] == 0 && line[y2][k] == 0)) { // 둘 다 아래에 빈공간
								line[y1][k] = line[y1][j];
								line[y2][k] = line[y2][j];
								line[y1][j] = 0;
								line[y2][j] = 0;
								downed = true;
								break;
							}
						}
					}
				}
			}
		}
	}

	private static void putG(insert input) {
		int y = input.y;
		if (input.t == 1) {
			for (int i = 5; i >= 0; i--) {
				if (i == 0 || G[y][i - 1] != 0) { // 맨 마지막까지 내려와버림 or 위에서부터 봤을 떄 바로 아래가 처음 만난 블록
					if (G[y][i] == 0) {
						G[y][i] = block;
						GBsplit[block] = true;
						return;
					}
				}
			}
		} else if (input.t == 2) {
			for (int i = 5; i >= 0; i--) {
				if (i == 0 || (G[y][i - 1] != 0 || G[y + 1][i - 1] != 0)) {
					G[y][i] = block;
					G[y + 1][i] = block;
					GBsplit[block] = false;
					return;
				}
			}
		} else {
			for (int i = 4; i >= 0; i--) {
				if (i == 0 || G[y][i - 1] != 0) {
					G[y][i] = block;
					G[y][i + 1] = block;
					GBsplit[block] = true;
					return;
				}
			}
		}
	}

	private static void putB(insert input) {
		int x = 3 - input.x;
		if (input.t == 1) {
			for (int i = 5; i >= 0; i--) {
				if (i == 0 || B[x][i - 1] != 0) { // 맨 마지막까지 내려와버림 or 위에서부터 봤을 떄 바로 아래가 처음 만난 블록
					if (B[x][i] == 0) {
						B[x][i] = block;
						BBsplit[block] = true;
						return;
					}
				}
			}
		} else if (input.t == 3) {
			for (int i = 5; i >= 0; i--) {
				if (i == 0 || (B[x][i - 1] != 0 || B[x - 1][i - 1] != 0)) {
					B[x][i] = block;
					B[x - 1][i] = block;
					BBsplit[block] = false;
					return;
				}
			}
		} else {
			for (int i = 4; i >= 0; i--) {
				if (i == 0 || B[x][i - 1] != 0) {
					B[x][i] = block;
					B[x][i + 1] = block;
					BBsplit[block] = true;
					return;
				}
			}
		}
	}

	private static class insert {
		int t;
		int x;
		int y;

		public insert(int t, int x, int y) {
			super();
			this.t = t;
			this.x = x;
			this.y = y;
		}
	}
}