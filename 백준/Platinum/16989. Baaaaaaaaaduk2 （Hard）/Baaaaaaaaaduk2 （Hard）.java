import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] Map;
	static int[][] Deltas;
	static boolean[][] VisBlack;
	static HashMap<Integer, ArrayList<int[]>> Points;
	static int PointA;
	static int PointB;
	static int Result;
	static boolean[] SameAnotherPoint;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Map = new int[N][M];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				Map[n][m] = readInt();
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		VisBlack = new boolean[N][M];
		SameAnotherPoint = new boolean[N * M + 1];
		Points = new HashMap<>();
		// loc자리에 arraylist는
		// [0]은 그 곳에 놓을 경우 얻을 하나짜리 케이스의 검은 돌 수
		// 이후는 loc자리와 조합하면 두개짜리로 얻을 케이스의 위치와 그 두개짜리의 검은 돌 수
		PointA = 0;
		PointB = 0;
		Result = 0;
	}

	private static int readInt() throws IOException {
		int c, n;
		do
			n = System.in.read();
		while (n < 45);
		n &= 15;
		while ((c = System.in.read()) > 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getLocsValue();
		getResult();
		write.write(Result + "\n");
		write.close();
	}

	// 빈 공간이 채울 가치가 있을 때 그 위치에서의 잡아먹을 검은 돌 수
	private static void getLocsValue() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (Map[i][j] == 0)
					getBfsDirs(i, j);
			}
		}
	}

	private static void getBfsDirs(int i, int j) {
		int pointSum = 0;
		for (int[] dir : Deltas) {
			int sideI = i + dir[0];
			int sideJ = j + dir[1];
			// 범위 밖이거나 검은 돌이 아니거나 방문한 검은 돌
			if (!isIn(sideI, sideJ) || Map[sideI][sideJ] != 2 || VisBlack[sideI][sideJ])
				continue;
			int[] output = getBfs(sideI, sideJ, i, j); // {검은 돌 수, 추가로 막아야하는 정보}
			if (output[1] == -1) { // 하나짜리
				if (!Points.containsKey(i * M + j)) { // 첫 획득
					Points.put(i * M + j, new ArrayList<>());
					Points.get(i * M + j).add(output.clone());
				} else {
					Points.get(i * M + j).get(0)[0] += output[0]; // 하나짜리 개수 카운트
				}
				pointSum = Points.get(i * M + j).get(0)[0]; // i,j에서 먹을 수 있는 하나짜리 카운트값
			} else if (output[1] != -2) { // 막아야할 곳이 하나 더 있는 곳의 위치
				if (!Points.containsKey(i * M + j)) { // 첫 획득
					Points.put(i * M + j, new ArrayList<>());
					Points.get(i * M + j).add(new int[2]);
				}
				if (SameAnotherPoint[output[1]]) { // 중복 투케이스 경우
					for (int[] info : Points.get(i * M + j)) { // 어차피 최대 4개라 for문 돌려도 괜찮음
						if (info[1] == output[1]) { // 중복 위치 찾음
							info[0] += output[0];
							break;
						}
					}
				} else { // output[1]에도 놓으면 투케이스를 먹을 수 있다!
					Points.get(i * M + j).add(output.clone()); // output[1]에 또 놓으면 output[0]만큼 추가로 얻음
					SameAnotherPoint[output[1]] = true;
				}
			} // -2면 고려하지 않을 위치들
		}
		if (PointA <= pointSum) { // 하나짜리 최대값 A
			PointB = PointA;
			PointA = pointSum;
		} else if (PointB < pointSum) { // 하나짜리 최대값 B
			PointB = pointSum;
		}
	}

	private static int[] getBfs(int i, int j, int baseI, int baseJ) { // 검은 돌 i,j에서부터 탐색해서 검은 돌 세기
		int[] output = new int[] { -1, -1 };
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.add(i * M + j);
		int countBlack = 1;
		VisBlack[i][j] = true;
		while (!bfsQ.isEmpty()) {
			int loc = bfsQ.poll();
			for (int[] dir : Deltas) {
				int nextI = loc / M + dir[0];
				int nextJ = loc % M + dir[1];
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 1 || VisBlack[nextI][nextJ])
					continue;
				if (Map[nextI][nextJ] == 2) {
					VisBlack[nextI][nextJ] = true;
					countBlack++;
					bfsQ.add(nextI * M + nextJ);
				} else if (Map[nextI][nextJ] == 0) { // 새로 방문한 빈 곳 발견
					if (nextI == baseI && nextJ == baseJ) // 중심지 빈곳은 제외
						continue;
					if (output[1] == -1) { // 막아야할 곳 하나 발견
						output[1] = nextI * M + nextJ;
					} else { // 막아야할 곳이 둘 이상
						if (output[1] != nextI * M + nextJ) // 아까 봤던 빈 곳이 아님
							output[1] = -2;
					}
				}
			}
		}
		output[0] = countBlack;
		// output[1]이 -2이면 막아야할 곳이 둘 이상이라 고려 X
		// -1이면 추가로 막아야할 곳이 없으므로 하나짜리 케이스
		// 그 이외에는 추가로 막아야할 자리 한 곳의 위치값
		return output;
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M;
	}

	private static void getResult() {
		Result = PointA + PointB; // 하나짜리 두 개의 최대값 케이스가 초기값
		for (ArrayList<int[]> list : Points.values()) {
			int caseOneBlockSum = list.get(0)[0];
			int size = list.size();
			for (int index = 1; index < size; index++) { // 두 자리를 막는 경우
				int[] anotherBlockInfo = list.get(index); // 추가로 막을 자리
				int sum = caseOneBlockSum + anotherBlockInfo[0]; // 현재 위치의 하나짜리 개수에 추가로 막은 경우의 두개짜리 개수 더하기
				int loc = anotherBlockInfo[1]; // 추가로 막은 위치
				if (Points.containsKey(loc))
					sum += Points.get(loc).get(0)[0]; // 추가로 막은 위치의 하나짜리 개수
				Result = Math.max(Result, sum);
			}
		}
	}
}