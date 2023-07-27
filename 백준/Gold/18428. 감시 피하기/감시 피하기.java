import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] Map;
	static ArrayList<int[]> TList;
	static int[][] Deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static String Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Map = new int[N][N];
		TList = new ArrayList<>();
		Result = "NO";
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				char ij = tokens.nextToken().charAt(0);
				if (ij == 'S') {
					Map[i][j] = -1;
				} else if (ij == 'T') {
					Map[i][j] = -2;
					TList.add(new int[] { i, j });
				}
			}
		}
		read.close();
	}

	private static void solv() {
		getCombOLocs();
	}

	private static void getCombOLocs() { // O 위치 조합
		getComb(0, 0, new int[3]);
	}

	private static void getComb(int count, int start, int[] sel) {
		if (Result == "YES")
			return;
		else if (count == 3) {
			setObjAtLocs(sel);
			return;
		}

		for (int current = start; current < N * N; current++) {
			int i = current / N;
			int j = current % N;
			if (Map[i][j] < 0)
				continue;
			sel[count] = current;
			getComb(count + 1, current + 1, sel);
		}
	}

	private static void setObjAtLocs(int[] sel) { // 조합된 위치에 O 세팅하고 T 시선 처리
		for (int loc : sel) {
			int i = loc / N;
			int j = loc % N;
			Map[i][j] = 1;
		}
		boolean founded = false;
		breakAll: for (int[] tLoc : TList) {
			for (int[] delta : Deltas) {
				for (int len = 1; len < N * N; len++) {
					int nextI = tLoc[0] + delta[0] * len;
					int nextJ = tLoc[1] + delta[1] * len;
					if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 1) // 범위 밖 / 장애물로 시선 차단됨
						break; // 학생 발견 X
					else if (Map[nextI][nextJ] == -1) { // 학생 발견
						founded = true;
						break breakAll;
					}
				}
			}
		}
		if (!founded)
			Result = "YES";
		else {
			for (int loc : sel) {
				int i = loc / N;
				int j = loc % N;
				Map[i][j] = 0;
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}