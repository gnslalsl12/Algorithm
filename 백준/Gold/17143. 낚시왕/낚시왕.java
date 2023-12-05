import java.util.*;
import java.io.*;

public class Main {
	static int R, C, M;
	static int[][] Map;
	static TreeMap<Integer, Info> SharkList;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static class Info {
		int r, c, s, d;

		public Info(int r, int c, int s, int d) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
		}
	}

	private static void init() throws IOException {
		R = readInt();
		C = readInt();
		M = readInt();
		Map = new int[R][C];
		SharkList = new TreeMap<>();
		for (int m = 0; m < M; m++) {
			int r = readInt() - 1;
			int c = readInt() - 1;
			int s = readInt();
			int d = readInt();
			if (d == 1) {
				d = 0;
			} else if (d == 3) {
				d = 1;
			} else if (d == 4) {
				d = 3;
			}
			int z = readInt();
			SharkList.put(z, new Info(r, c, s, d));
			Map[r][c] = z;
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
	}

	private static int readInt() throws IOException {
		int n, c;
		do {
			n = System.in.read();
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult();
		write.write(Result + "\n");
		write.close();
	}

	private static void getResult() {
		for (int fisher = 0; fisher < C; fisher++) {
			for (int r = 0; r < R; r++) {
				if (Map[r][fisher] != 0) {
					int target = Map[r][fisher];
					Map[r][fisher] = 0;
					SharkList.remove(target);
					Result += target;
					break;
				}
			}
			setSharksMove();
		}
	}

	private static void setSharksMove() {
		Map = new int[R][C];
		Queue<Integer> targets = new LinkedList<>();
		for (Integer key : SharkList.keySet()) {
			Info tempS = SharkList.get((int) key);
			if (tempS.d % 2 == 0) { // 위아래 이동
				int nextR = tempS.r + Deltas[tempS.d][0] * tempS.s;
				int blocked = nextR / (R - 1); // 벽에 부딪힌 횟수
				int remain = nextR % (R - 1); // 벽에서부터터 이동 크기
				if (remain < 0) {
					tempS.d = (tempS.d + 2) % 4;
					remain = -remain;
				}
				if (blocked % 2 == 0) { // 방향 그대로
					tempS.r = remain;
				} else { // 방향 전환
					tempS.r = (R - 1) - remain;
					tempS.d = (tempS.d + 2) % 4;
				}
			} else {
				int nextC = tempS.c + Deltas[tempS.d][1] * tempS.s;
				int blocked = nextC / (C - 1);
				int remain = nextC % (C - 1);
				if (remain < 0) {
					tempS.d = (tempS.d + 2) % 4;
					remain = -remain;
				}
				if (blocked % 2 == 0) {
					tempS.c = remain;
				} else {
					tempS.c = (C - 1) - remain;
					tempS.d = (tempS.d + 2) % 4;
				}
			}
			int target = Map[tempS.r][tempS.c];
			if (target != 0) {
				targets.add(target);
			}
			Map[tempS.r][tempS.c] = key;
		}
		while (!targets.isEmpty()) {
			SharkList.remove(targets.poll());
		}
	}

}