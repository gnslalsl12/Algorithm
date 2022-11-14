package day1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13459 {
	static int N, M;
	static int[] maps;
	static dirXY Hole, firstR, firstB;
	static Queue<DirInfo> BFSQ;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N];
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				if (templine.charAt(j) != '#') {
					maps[i] |= 1 << j;
				}
				if (templine.charAt(j) == 'O') {
					Hole = new dirXY(i, j);
				} else if (templine.charAt(j) == 'R') {
					firstR = new dirXY(i, j);
				} else if (templine.charAt(j) == 'B') {
					firstB = new dirXY(i, j);
				}
			}
		}
		if (rollBfs()) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}

	private static boolean rollBfs() throws CloneNotSupportedException {
		BFSQ = new LinkedList<>();
		BFSQ.add(new DirInfo('S', 0, firstR, firstB));
		boolean Result = false;
		int Rhole = 2;
		int Bhole = 2;
		while (!BFSQ.isEmpty()) {
			DirInfo temp = BFSQ.poll();
			dirXY R = temp.tempR;
			dirXY B = temp.tempB;
			if (temp.count > 10) {
				Result = false;
				break;
			}

			if (temp.dir == 'U') {
				if (R.x < B.x) {
					Rhole = rolling(temp.dir, R, R, B);
					Bhole = rolling(temp.dir, B, R, B);
				} else {
					Bhole = rolling(temp.dir, B, R, B);
					Rhole = rolling(temp.dir, R, R, B);
				}
			} else if (temp.dir == 'R') {
				if (R.y < B.y) {
					Bhole = rolling(temp.dir, B, R, B);
					Rhole = rolling(temp.dir, R, R, B);
				} else {
					Rhole = rolling(temp.dir, R, R, B);
					Bhole = rolling(temp.dir, B, R, B);
				}
			} else if (temp.dir == 'D') {
				if (R.x < B.x) {
					Bhole = rolling(temp.dir, B, R, B);
					Rhole = rolling(temp.dir, R, R, B);
				} else {
					Rhole = rolling(temp.dir, R, R, B);
					Bhole = rolling(temp.dir, B, R, B);
				}
			} else if (temp.dir == 'L') {
				if (R.y < B.y) {
					Rhole = rolling(temp.dir, R, R, B);
					Bhole = rolling(temp.dir, B, R, B);
				} else {
					Bhole = rolling(temp.dir, B, R, B);
					Rhole = rolling(temp.dir, R, R, B);
				}
			}
			if (Rhole == 0) {
				if (Bhole == 0) {
					continue;
				} else if (Bhole == 1) {
					continue;
				} else if (Bhole == 2) {
					addMoreDir(temp);
				}
			} else if (Rhole == 1) {
				if (Bhole == 0) {
					Result = true;
					break;
				} else if (Bhole == 1) {
					continue;
				} else if (Bhole == 2) {
					Result = true;
					break;
				}
			} else if (Rhole == 2) {
				if (Bhole == 0) {
					addMoreDir(temp);
				} else if (Bhole == 1) {
					continue;
				} else if (Bhole == 2) {
					addMoreDir(temp);
				}
			}
		}

		return Result;
	}

	private static void addMoreDir(DirInfo tempinfo) throws CloneNotSupportedException {
		if (tempinfo.dir != 'U')
			BFSQ.add(new DirInfo('U', tempinfo.count + 1, tempinfo.tempR, tempinfo.tempB));
		if (tempinfo.dir != 'R')
			BFSQ.add(new DirInfo('R', tempinfo.count + 1, tempinfo.tempR, tempinfo.tempB));
		if (tempinfo.dir != 'D')
			BFSQ.add(new DirInfo('D', tempinfo.count + 1, tempinfo.tempR, tempinfo.tempB));
		if (tempinfo.dir != 'L')
			BFSQ.add(new DirInfo('L', tempinfo.count + 1, tempinfo.tempR, tempinfo.tempB));
	}

	private static int rolling(char dir, dirXY ball, dirXY R, dirXY B) {
		boolean IntoHole = false;
		int beforex = ball.x;
		int beforey = ball.y;
		switch (dir) {
		case ('U'): {
			for (int i = ball.x - 1; i >= 0; i--) {
				if (i == Hole.x && ball.y == Hole.y) { // 구멍에 빠졌나?
					ball.x = i;
					IntoHole = true;
					break;
				}
				if ((maps[i] & (1 << ball.y)) == 0) { // 벽 만나면 그 이전 위치로
					ball.x = i + 1;
					break;
				}
				if (i == R.x && ball.y == R.y || i == B.x && ball.y == B.y) { // 공 만나면 그 전 위치로
					ball.x = i + 1;
					break;
				}
			}
			break;
		}
		case ('R'): {
			for (int i = ball.y + 1; i < M; i++) {
				if (ball.x == Hole.x && i == Hole.y) {
					ball.y = i;
					IntoHole = true;
					break;
				}
				if ((maps[ball.x] & (1 << i)) == 0) {
					ball.y = i - 1;
					break;
				}
				if (ball.x == R.x && i == R.y || ball.x == B.x && i == B.y) { // 공 만나면 그 전 위치로
					ball.y = i - 1;
					break;
				}
			}
			break;
		}
		case ('D'): {
			for (int i = ball.x + 1; i < N; i++) {
				if (i == Hole.x && ball.y == Hole.y) { // 구멍에 빠졌나?
					ball.x = i;
					IntoHole = true;
					break;
				}
				if ((maps[i] & (1 << ball.y)) == 0) { // 벽 만나면 그 이전 위치로
					ball.x = i - 1;
					break;
				}
				if (i == R.x && ball.y == R.y || i == B.x && ball.y == B.y) { // 공 만나면 그 전 위치로
					ball.x = i - 1;
					break;
				}
			}
			break;
		}
		case ('L'): {
			for (int i = ball.y - 1; i >= 0; i--) {
				if (ball.x == Hole.x && i == Hole.y) {
					ball.y = i;
					IntoHole = true;
					break;
				}
				if ((maps[ball.x] & (1 << i)) == 0) {
					ball.y = i + 1;
					break;
				}
				if (ball.x == R.x && i == R.y || ball.x == B.x && i == B.y) { // 공 만나면 그 전 위치로
					ball.y = i + 1;
					break;
				}
			}
			break;
		}
		}
		if (IntoHole) {
			return 1; // 구멍에 빠짐
		} else if (beforex == ball.x && beforey == ball.y) {
			return 0; // 움직이지 않음
		} else {
			return 2; // 일단 움직이긴 함
		}
	}

	private static class DirInfo {
		char dir;
		int count;
		dirXY tempR;
		dirXY tempB;

		public DirInfo(char dir, int count, dirXY tempR, dirXY tempB) throws CloneNotSupportedException {
			this.dir = dir;
			this.count = count;
			this.tempR = tempR.clone();
			this.tempB = tempB.clone();
		}

	}

	private static class dirXY implements Cloneable {
		int x;
		int y;

		@Override
		protected dirXY clone() throws CloneNotSupportedException {
			return (dirXY) super.clone();
		}

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
