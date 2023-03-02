package day1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12100 {
	static int N;
	static char[] dir = { 'U', 'D', 'R', 'L' };
	static int result;
	static int[][] floor1, floor2, floor3, floor4, floor5;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		int[][] originmaps = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				originmaps[i][j] = Integer.parseInt(tokens.nextToken());
				result = Math.max(originmaps[i][j], result);
			}
		}
		for (int d1 = 0; d1 < 4; d1++) {
			floor1 = copymaps(originmaps);
			setUDRL(dir[d1], floor1);
			for (int d2 = 0; d2 < 4; d2++) {
				floor2 = copymaps(floor1);
				setUDRL(dir[d2], floor2);
				for (int d3 = 0; d3 < 4; d3++) {
					floor3 = copymaps(floor2);
					setUDRL(dir[d3], floor3);
					for (int d4 = 0; d4 < 4; d4++) {
						floor4 = copymaps(floor3);
						setUDRL(dir[d4], floor4);
						for (int d5 = 0; d5 < 4; d5++) {
							floor5 = copymaps(floor4);
							setUDRL(dir[d5], floor5);
							for (int i = 0; i < N; i++) {
								for (int j = 0; j < N; j++) {
									result = Math.max(result, floor5[i][j]);
								}
							}
						}
					}
				}
			}
		}
		write.write(result + "\n");
		read.close();
		write.close();
	}

	private static int[][] copymaps(int[][] from) {
		int[][] to = new int[N][N];
		for (int i = 0; i < N; i++) {
			to[i] = from[i].clone();
		}
		return to;
	}

	private static void setUDRL(char dir, int[][] tempmaps) {
		boolean re = false;
		if (dir == 'D' || dir == 'R') {
			re = true;
		}
		int Start = 0;
		int End = N - 1;
		int add = 1;
		if (re) {
			Start = N - 1;
			End = 0;
			add = -1;
		}
		for (int i = Start; Reverse(re, i, End); i += add) {
			Queue<TempNum> tempQ = new LinkedList<>();
			for (int j = Start; Reverse(re, j, End); j += add) {
				if (tempmaps[RLorUD(dir, i, j)[0]][RLorUD(dir, i, j)[1]] == 0) {
					continue;
				}
				tempQ.add(new TempNum(tempmaps[RLorUD(dir, i, j)[0]][RLorUD(dir, i, j)[1]], false));
			}
			while (true) {
				if (tempQ.isEmpty() || tempQ.peek().stack || tempQ.size() <= 1)
					break;
				TempNum pop = tempQ.poll();
				if (pop.num == tempQ.peek().num && !tempQ.peek().stack) {
					tempQ.poll();
					tempQ.add(new TempNum(pop.num * 2, true));
				} else {
					tempQ.add(new TempNum(pop.num, true));
				}
			}
			for (int j = Start; Reverse(re, j, End); j += add) {
				if (tempQ.isEmpty()) {
					tempmaps[RLorUD(dir, i, j)[0]][RLorUD(dir, i, j)[1]] = 0;
				} else {
					tempmaps[RLorUD(dir, i, j)[0]][RLorUD(dir, i, j)[1]] = tempQ.poll().num;
				}
			}
		}
	}

	private static boolean Reverse(boolean re, int iorj, int End) {
		if (!re) {
			return iorj <= End;
		} else {
			return iorj >= End;
		}
	}

	private static int[] RLorUD(char dir, int i, int j) {
		if (dir == 'R' || dir == 'L') {
			return new int[] { i, j };
		} else {
			return new int[] { j, i };
		}
	}

	private static class TempNum {
		int num;
		boolean stack;

		public TempNum(int num, boolean stack) {
			this.num = num;
			this.stack = stack;
		}

	}

}