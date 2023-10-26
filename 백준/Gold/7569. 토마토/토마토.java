import java.util.*;
import java.io.*;

public class Main {
	static int M;
	static int N;
	static int H;
	static char[][][] Box;
	static int[][] Deltas;
	static int RemainCount;
	static Queue<Integer> BfsQ;
	static int Result;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		M = readInt();
		N = readInt();
		H = readInt();
		Box = new char[N][M][H];
		RemainCount = 0; // 익어야하는 토마토 수
		BfsQ = new LinkedList<>();
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					int input = readInt();
					Box[n][m][h] = input == 1 ? 'T' : input == 0 ? 't' : 'X';
					if (input == 0)
						RemainCount++;
					else if (input == 1)
						BfsQ.add(h * N * M + n * M + m);
				}
			}
		}
		Deltas = new int[][] { { -1, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
		Result = 0;
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setDayOver();
		write.write(Result + "\n");
		write.close();
	}

	private static void setDayOver() {
		while (true) {
			int size = BfsQ.size();
			if (size == 0 || RemainCount == 0)
				break; // 퍼질 토마토가 없거나 모두 익었을 경우
			setRipeOver(size); // 현재 시점에서 익은 토마토만 퍼뜨리기
			Result++; // 하루 경과
		}
		if (RemainCount != 0)
			Result = -1; // 토마토가 모두 익지 못했다면
	}

	private static void setRipeOver(int size) {
		while (size-- > 0) {
			int loc = BfsQ.poll();
			int m = loc % M;
			int n = (loc / M) % N;
			int h = (loc / M) / N;
			for (int[] dir : Deltas) {
				int nextN = n + dir[0];
				int nextM = m + dir[1];
				int nextH = h + dir[2];
				if (!isIn(nextN, nextM, nextH))
					continue; // 박스 범위 밖
				if (Box[nextN][nextM][nextH] == 't') { // 익지 않은 토마토인 경우
					Box[nextN][nextM][nextH] = 'T'; // 익히기
					BfsQ.add(nextH * N * M + nextN * M + nextM); // 현재 시점에서 익은 토마토로 기록
					RemainCount--; // 익어야하는 토마토 수 -1
				}
			}
		}
	}

	private static boolean isIn(int n, int m, int h) {
		return n >= 0 && m >= 0 && h >= 0 && n < N && m < M && h < H;
	}

}