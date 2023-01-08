package day0108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_1938 {
	static int N;
	static boolean[][] maps;
	static log BBB;
	static int Ex, Ey;
	static boolean Ev;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		maps = new boolean[N][N];
		int[][] firstB = new int[2][2];
		int[][] firstE = new int[2][2];
		int Bcount = 0;
		int Ecount = 0;
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < N; j++) {
				char temp = templine.charAt(j); 
				if (temp == 'B') {
					if (Bcount == 0) {
						firstB[0][0] = i;
						firstB[0][1] = j;
					} else if (Bcount == 1) {
						firstB[1][0] = i;
						firstB[1][1] = j;
					}
					Bcount++;
				} else if (temp == 'E') {
					if (Ecount == 0) {
						firstE[0][0] = i;
						firstE[0][1] = j;
					} else if (Ecount == 1) {
						firstE[1][0] = i;
						firstE[1][1] = j;
					}
					Ecount++;
				} else if (temp == '1') {
					maps[i][j] = true;
				}
			}
		} // input
		result = 0;
		if (firstB[0][0] == firstB[1][0]) {
			BBB = new log(firstB[1][0], firstB[1][1], false, 0);
		} else {
			BBB = new log(firstB[1][0], firstB[1][1], true, 0);
		}
		if (firstE[0][0] == firstE[1][0])
			Ev = false;
		else
			Ev = true;
		Ex = firstE[1][0];
		Ey = firstE[1][1];
		getBFS();
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void getBFS() {
		PriorityQueue<log> PQ = new PriorityQueue<>();
		PQ.add(BBB);
		boolean[][] visV = new boolean[N][N];
		boolean[][] visH = new boolean[N][N];
		while (!PQ.isEmpty()) {
			log temp = PQ.poll();
			if (temp.x == Ex && temp.y == Ey && temp.ver == Ev) {
				result = temp.count;
				return;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!availMoveSpot(nextx, nexty, temp.ver))
					continue;
				if ((temp.ver && visV[nextx][nexty]) || (!temp.ver && visH[nextx][nexty])) {
					continue;
				}
				PQ.add(new log(nextx, nexty, temp.ver, temp.count + 1));
				if (temp.ver)
					visV[nextx][nexty] = true;
				else
					visH[nextx][nexty] = true;
			}
			if ((temp.ver && !visH[temp.x][temp.y]) || (!temp.ver && !visV[temp.x][temp.y])) {
				if (availRotate(temp.x, temp.y)) {
					PQ.add(new log(temp.x, temp.y, !temp.ver, temp.count + 1));
					if (temp.ver)
						visH[temp.x][temp.y] = true;
					else
						visV[temp.x][temp.y] = true;
				}
			}
			if (temp.ver)
				visV[temp.x][temp.y] = true;
			else
				visH[temp.x][temp.y] = true;
		}

	}

	private static boolean availRotate(int x, int y) {
		if (x == 0 || x == N - 1 || y == 0 || y == N - 1)
			return false;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (maps[i][j])
					return false;
			}
		}
		return true;
	}

	private static boolean availMoveSpot(int inputx, int inputy, boolean inputver) {
		int ULx = inputver ? inputx - 1 : inputx;
		int ULy = inputver ? inputy : inputy - 1;
		int RDx = inputver ? inputx + 1 : inputx;
		int RDy = inputver ? inputy : inputy + 1;
		return (ULx >= 0 && ULy >= 0 && RDx >= 0 && RDy >= 0 && ULx < N && ULy < N && RDx < N && RDy < N)
				&& (!theresWood(ULx, ULy) && !theresWood(RDx, RDy) && !theresWood(inputx, inputy));
	}

	private static boolean theresWood(int x, int y) {
		if (maps[x][y])
			return true;
		else
			return false;
	}

	private static class log implements Comparable<log> {
		int x;
		int y;
		boolean ver;
		int count;

		public log(int x, int y, boolean ver, int count) {
			this.x = x;
			this.y = y;
			this.ver = ver;
			this.count = count;
		}

		@Override
		public int compareTo(log o) {
			return Integer.compare(this.count, o.count);
		}

	}

}
