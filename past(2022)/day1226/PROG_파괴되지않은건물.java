package day1226;

public class PROG_파괴되지않은건물 {
	static int N, M;
	static int[][] maps;
	static int collapse, total;

	public static void main(String[] args) {
		int[][] inputB1 = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] inputS1 = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };
		System.out.println(solution(inputB1, inputS1));

		int[][] inputB2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] inputS2 = { { 1, 1, 1, 2, 2, 4 }, { 1, 0, 0, 1, 1, 2 }, { 2, 2, 0, 2, 0, 100 } };
		System.out.println(solution(inputB2, inputS2));

	}

	public static int solution(int[][] board, int[][] skill) {
		N = board.length;
		M = board[0].length;
		maps = board;
		collapse = 0;
		for (int i = 0; i < skill.length; i++) {
			if (skill[i][0] == 1) {
				damagearr(skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
			} else {
				healingarr(skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] > 0)
					collapse++;
			}
		}

		return collapse;
	}

	private static void damagearr(int r1, int c1, int r2, int c2, int degree) {
		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				maps[i][j] -= degree;
			}
		}
	}

	private static void healingarr(int r1, int c1, int r2, int c2, int degree) {
		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				maps[i][j] += degree;
			}
		}
	}

}
