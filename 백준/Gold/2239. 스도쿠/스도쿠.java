import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main { // BitMasking
	static int[][] maps = new int[9][9]; // 맵
	static int[] rowBM = new int[9]; // 행 BM 리스트 (선택된 숫자는 true)
	static int[] colBM = new int[9]; // 열 BM 리스트
	static int[] blockBM = new int[9]; // 블록 BM 리스트
	static boolean done = false;
	static ArrayList<int[]> dirXY = new ArrayList<>();
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
		// BM 매핑 완료
		int len = dirXY.size();
		for (int tc = 0; tc < len; tc++) {// 채워야하는 곳들 하나씩 봤을 때
			int[] temp = dirXY.remove(0);
			int x = temp[0];
			int y = temp[1];
			int k = temp[2];
			int rBM = rowBM[x];
			int cBM = colBM[y];
			int bBM = blockBM[k];
			int count = 0;
			for (int i = 1; i <= 9; i++) {
				if ((rBM & (1 << i)) == 0 && (cBM & (1 << i)) == 0 && (bBM & (1 << i)) == 0) { // 행에도 없고 열에도 없고 블록에도 없는 숫자
					if (count != 0) { // 들어갈 수 있는 숫자가 하나 이상이면 일단 패스
						count = -1;
						break;
					}
					count = i;
				}
			}
			if (count == -1) {
				dirXY.add(temp);
				continue;
			}
			rowBM[x] |= 1 << count;
			colBM[y] |= 1 << count;
			blockBM[k] |= 1 << count;
			maps[x][y] = count;
		}
		DFS(0);
		print();
	}

	private static void DFS(int count) {
		if (count >= dirXY.size()) {
			done = true; // 완성했다!
			return;
		}
		int [] point = dirXY.get(count);
		int x = point[0];
		int y = point[1];
		int k = point[2];
		for (int i = 1; i <= 9; i++) {
			if ((rowBM[x] & (1 << i)) == 0 && (colBM[y] & (1 << i)) == 0 && (blockBM[k] & (1 << i)) == 0) { // 선택되지 않은  숫자야!
				rowBM[x] |= 1 << i;
				colBM[y] |= 1 << i;
				blockBM[k] |= 1 << i;
				maps[x][y] = i;
				DFS(count+1);
				if (done)
					return;
				maps[x][y] = 0;
				rowBM[x] &= ~(1 << i);
				colBM[y] &= ~(1 << i);
				blockBM[k] &= ~(1 << i);
			}
		}
	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(maps[i][j]);
			}
			System.out.println();
		}
	}

	private static int getK(int x, int y) { // 블록 번호 구하기
		return x/3*3 + y / 3;
	}
}