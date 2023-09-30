import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main { // BitMasking
	static int[][] maps = new int[9][9];
	static int[] rowBM = new int[9];
	static int[] colBM = new int[9];
	static int[] blockBM = new int[9];
	static int[] toFill = new int[9];
	static boolean done = false;
	static int[] visits = new int[9];
	static Queue<int[]> dirXY = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			int tempBM = 0;
			String templine = read.readLine();
			for (int j = 0; j < 9; j++) {
				int a = templine.charAt(j) - '0';
				maps[i][j] = a;
				if (a == 0) {
					dirXY.add(new int[] { i, j, getK(i, j) });
					toFill[i] |= 1 << j;
				} else {
					tempBM |= 1 << a;
				}
			}
			rowBM[i] = tempBM;
		}
		for (int j = 0; j < 9; j++) {
			int tempBM = 0;
			for (int i = 0; i < 9; i++) {
				int a = maps[i][j];
				tempBM |= 1 << a;
			}
			colBM[j] = tempBM;
		}
		for (int k = 1; k <= 9; k++) {
			int startx = (k - 1) / 3 * 3;
			int starty = (k - 1) % 3 * 3;
			int tempBM = 0;
			for (int i = startx; i < startx + 3; i++) {
				for (int j = starty; j < starty + 3; j++) {
					tempBM |= 1 << maps[i][j];
				}
			}
			blockBM[k - 1] = tempBM;
		}
		while (!dirXY.isEmpty()) {
			int[] temp = dirXY.poll();
			int x = temp[0];
			int y = temp[1];
			int k = temp[2];
			int rBM = rowBM[x];
			int cBM = colBM[y];
			int bBM = blockBM[k];
			int count = 0;
			for (int i = 1; i <= 9; i++) {
				if ((rBM & (1<<i)) == 0 && (cBM & (1<<i)) == 0 && (bBM & (1<<i)) == 0) { // 행에도 없고 열에도 없고 블록에도 없는 숫자
					if (count != 0) {
						count = -1;
						break;
					}
					count = i;
				}
			}
			if(count == -1) {
				continue;
			}
			rowBM[x] |= 1 << count;
			colBM[y] |= 1 << count;
			blockBM[k] |= 1 << count;
			toFill[x] &= ~(1<<y);
			maps[x][y] = count;
		}

		done = false;
		DFS(0);
		print();
	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(maps[i][j]);
			}
			System.out.println();
		}
	}

	private static void DFS(int count) {
		if (count == 81) {
			done = true;
			return;
		}
		int[] temp = getXY(count);
		int x = temp[0];
		int y = temp[1];
		if (maps[x][y] != 0) {
			DFS(count + 1);
		} else {
			for (int i = 1; i <= 9; i++) {

				if ((rowBM[x] & (1 << i)) == 0 && (colBM[y] & (1 << i)) == 0 && (blockBM[getK(x, y)] & (1 << i)) == 0) {
					rowBM[x] |= 1 << i;
					colBM[y] |= 1 << i;
					blockBM[getK(x, y)] |= 1 << i;
					maps[x][y] = i;
					DFS(count + 1);
					if(done) return;
					maps[x][y] = 0;
					rowBM[x] &= ~(1 << i);
					colBM[y] &= ~(1 << i);
					blockBM[getK(x, y)] &= ~(1 << i);
				}
			}
		}

	}

	private static int[] getXY(int count) {
		int x = count / 9;
		int y = count % 9;
		return new int[] { x, y };
	}

	private static int getK(int x, int y) { // 블록 번호 구하기
		int k = 0;
		if (x < 3) {
			if (y < 3) {
				k = 0;
			} else if (y < 6) {
				k = 1;
			} else {
				k = 2;
			}
		} else if (x < 6) {
			if (y < 3) {
				k = 3;
			} else if (y < 6) {
				k = 4;
			} else {
				k = 5;
			}
		} else {
			if (y < 3) {
				k = 6;
			} else if (y < 6) {
				k = 7;
			} else {
				k = 8;
			}
		}
		return k;
	}
}