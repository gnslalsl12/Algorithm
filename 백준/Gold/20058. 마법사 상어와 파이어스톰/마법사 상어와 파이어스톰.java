import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int NSize;
	static int Q;
	static Queue<Integer> QList;
	static int[][] Map;
	static int[][] Deltas;
	static long[] Vis;
	static int[] Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		NSize = (int) Math.pow(2, N);
		Q = readInt();
		QList = new LinkedList<>();
		Map = new int[NSize][NSize];
		for (int i = 0; i < NSize; i++) {
			for (int j = 0; j < NSize; j++) {
				Map[i][j] = readInt();
			}
		}
		while (Q-- > 0)
			QList.add(readInt());
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	}

	private static int readInt() throws IOException {
		int n, c;
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
		while (!QList.isEmpty()) {
			Result = new int[2];
			setRotateMap();
			getRemainedIceCount();
			getBiggestSection();
		}
		write.write(Result[0] + "\n" + Result[1] + "\n");
		write.close();
	}

	private static void setRotateMap() { // 구간 맵 회전시키기
		int[][] tempMap = new int[NSize][NSize];
		int LSize = (int) Math.pow(2, QList.poll());
		for (int i = 0; i < NSize; i += LSize) {
			for (int j = 0; j < NSize; j += LSize) {
				setRotateSection(i, j, LSize, tempMap);
			}
		}
		for (int i = 0; i < NSize; i++) { // 회전된 맵으로 갱신
			Map[i] = tempMap[i];
		}
	}

	private static void setRotateSection(int startI, int startJ, int len, int[][] tempMap) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				tempMap[startI + j][startJ + len - 1 - i] = Map[startI + i][startJ + j];
			}
		}
	}

	private static void getRemainedIceCount() { // 남은 얼음 수 세기
		Queue<Integer> meltedLocs = new LinkedList<>(); // 녹을 위치
		for (int i = 0; i < NSize; i++) {
			for (int j = 0; j < NSize; j++) {
				if (Map[i][j] == 0)
					continue;
				if (!isLinked(i, j)) { // 셋 이상 연결돼있지 않다면
					meltedLocs.add(i * NSize + j);
				}
				Result[0] += Map[i][j];
			}
		}
		Result[0] -= meltedLocs.size();
		while (!meltedLocs.isEmpty()) {
			int loc = meltedLocs.poll();
			Map[loc / NSize][loc % NSize]--;
		}
	}

	private static boolean isLinked(int i, int j) { // 연결 된 얼음이 3개 이상인가
		if ((i == 0 && j == 0) || (i == NSize - 1 && j == 0) || (i == 0 && j == NSize - 1)
				|| (i == NSize - 1 && j == NSize - 1))
			return false;
		int count = 0;
		for (int[] dir : Deltas) {
			int nextI = i + dir[0];
			int nextJ = j + dir[1];
			if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 0)
				continue;
			count++;
		}
		if (count < 3)
			return false;
		return true;
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < NSize && j < NSize;
	}

	private static void getBiggestSection() { // 가장 큰 덩어리 크기 구하기
		Vis = new long[NSize];
		for (int i = 0; i < NSize; i++) {
			for (int j = 0; j < NSize; j++) {
				if (Map[i][j] == 0 || (Vis[i] & (1L << j)) != 0L)
					continue;
				getBfsCount(i, j);
			}
		}
	}

	private static void getBfsCount(int i, int j) {
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.add(i * NSize + j);
		Vis[i] |= 1L << j;
		int blockCount = 1;
		while (!bfsQ.isEmpty()) {
			int loc = bfsQ.poll();
			for (int[] dir : Deltas) {
				int nextI = loc / NSize + dir[0];
				int nextJ = loc % NSize + dir[1];
				if (!isIn(nextI, nextJ) || (Vis[nextI] & (1L << nextJ)) != 0L || Map[nextI][nextJ] == 0)
					continue;
				blockCount++;
				Vis[nextI] |= 1L << nextJ;
				bfsQ.add(nextI * NSize + nextJ);
			}
		}
		Result[1] = Math.max(Result[1], blockCount);
	}

}