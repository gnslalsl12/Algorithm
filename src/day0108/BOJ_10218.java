package day0108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10218 {
	static int N, M, Hx, Hy;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[] dirsC = { 'U', 'R', 'D', 'L' };
	static int[] Maps;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			Maps = new int[N];
			for (int i = 0; i < N; i++) {
				String templine = read.readLine();
				for (int j = 0; j < M; j++) {
					char temp = templine.charAt(j);
					if (temp == '.') {
						Maps[i] |= 1 << j;
					} else if (temp == 'O') {
						Hx = i;
						Hy = j;
					}
				}
			}
			write.write(getPushBFS());
		}
		write.close();
		read.close();
	}

	private static String getPushBFS() {
		Queue<PushCase> Q = new LinkedList<>();
		String result = "XHAE\n";
		Q.add(new PushCase(Maps, ""));
		breakall: while (!Q.isEmpty()) {
			PushCase temp = Q.poll();
			int len = temp.dirstack.length();
			for (int dir = 0; dir < 4; dir++) {
				if (len != 0 && temp.dirstack.charAt(len - 1) == dirsC[dir])
					continue;
				int[] moved = pushD(temp.tempmap.clone(), dir);
				boolean allZ = true;
				for (int i = 0; i < moved.length; i++) {
					if (moved[i] != 0) {
						allZ = false;
						break;
					}
				}
				if (allZ) {
					result = temp.dirstack + dirsC[dir] + "\n";
					break breakall;
				}
				if (len < 9)
					Q.add(new PushCase(moved, temp.dirstack + dirsC[dir]));
			}
		}
		return result;
	}

	private static int[] pushD(int[] inputmap, int dir) {
		if (dir == 0)
			return UP(inputmap);
		else if (dir == 1)
			return RIGHT(inputmap);
		else if (dir == 2)
			return DOWN(inputmap);
		else
			return LEFT(inputmap);
	}

	private static int[] UP(int[] input) {
		for (int j = 1; j < M - 1; j++) {
			boolean newpoint = false;
			for (int i = N - 1; i >= 0; i--) {
				if ((input[i] & 1 << j) != 0) { // 빈공간
					newpoint = true;
					input[i] &= ~(1 << j);
				} else if ((Maps[i] & 1 << j) == 0) { // 막힘
					if (i == Hx && j == Hy)
						continue;
					if (newpoint) {
						input[i + 1] |= 1 << j;
						newpoint = false;
					}
				}
			}
		}
		return input;
	}

	private static int[] RIGHT(int[] input) {
		for (int i = 1; i < N - 1; i++) {
			boolean newpoint = false;
			for (int j = 0; j < M; j++) {
				if ((input[i] & 1 << j) != 0) { // 빈공간
					newpoint = true;
					input[i] &= ~(1 << j);
				} else if ((Maps[i] & 1 << j) == 0) { // 막힘
					if (i == Hx && j == Hy)
						continue;
					if (newpoint) {
						input[i] |= 1 << (j - 1);
						newpoint = false;
					}
				}
			}
		}
		return input;
	}

	private static int[] DOWN(int[] input) {
		for (int j = 1; j < M - 1; j++) {
			boolean newpoint = false;
			for (int i = 0; i < N; i++) {
				if ((input[i] & 1 << j) != 0) { // 빈공간
					newpoint = true;
					input[i] &= ~(1 << j);
				} else if ((Maps[i] & 1 << j) == 0) { // 막힘
					if (i == Hx && j == Hy)
						continue;
					if (newpoint) {
						input[i - 1] |= 1 << j;
						newpoint = false;
					}
				}
			}
		}
		return input;
	}

	private static int[] LEFT(int[] input) {
		for (int i = 1; i < N - 1; i++) {
			boolean newpoint = false;
			for (int j = M - 1; j >= 0; j--) {
				if ((input[i] & 1 << j) != 0) { // 빈공간
					newpoint = true;
					input[i] &= ~(1 << j);
				} else if ((Maps[i] & 1 << j) == 0) { // 막힘
					if (i == Hx && j == Hy)
						continue;
					if (newpoint) {
						input[i] |= 1 << (j + 1);
						newpoint = false;
					}
				}
			}
		}
		return input;
	}

	private static class PushCase {
		int[] tempmap;
		String dirstack;

		public PushCase(int[] tempmap, String dirstack) {
			this.tempmap = tempmap;
			this.dirstack = dirstack;
		}

	}

}
