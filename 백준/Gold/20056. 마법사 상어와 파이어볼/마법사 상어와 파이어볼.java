import java.util.*;
import java.io.*;

public class Main {

	static int N, M, K;
	static int[][][] Map;
	static int[][][] NextMap;
	static Queue<Integer> MapLocs;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Map = new int[N][N][4];
		NextMap = new int[N][N][4];
		MapLocs = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken()) - 1;
			int c = Integer.parseInt(tokens.nextToken()) - 1;
			int m = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			Map[r][c] = new int[] { 1, m, s, d };
			MapLocs.add(r * N + c);
		}
		Deltas = new int[][] { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
		Result = 0;
		read.close();
	}

	private static void solv() {
		while (K-- > 0) {
			setMoveWholeFB();
		}
		getSumM();

	}

	private static void getSumM() {
		while (!MapLocs.isEmpty()) {
			int loc = MapLocs.poll();
			int[] info = Map[loc / N][loc % N];
			if (info[0] == 1)
				Result += info[1];
			else if (info[1] >= 5) {
				Result += (info[1] / 5) * 4;
			}
		}
	}

	private static void setMoveWholeFB() {
		int size = MapLocs.size();
		while (size-- > 0) {
			int loc = MapLocs.poll();
			int[] info = Map[loc / N][loc % N].clone();
			if (info[0] > 1) { // 하나 이상일 때
				setSpreadFB(loc, info);
			} else {
				setMoveFB(loc, info);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Map[i][j] = NextMap[i][j].clone();
			}
		}
		NextMap = new int[N][N][4];
	}

	private static void setSpreadFB(int loc, int[] info) {
		int divM = info[1] / 5;
		if (divM == 0)
			return;
		int divS = info[2] / info[0];
		int d = info[3] > 0 ? 0 : 1; // 모인 볼들 방향성이 다 같으면 0, 다르면 1
		for (; d <= 7; d += 2) {
			int nextI = (loc / N + Deltas[d][0] * divS + divS * N) % N;
			int nextJ = (loc % N + Deltas[d][1] * divS + divS * N) % N;
			int[] nextInfo = NextMap[nextI][nextJ]; // 도착한 곳의 정보
			nextInfo[0]++;
			nextInfo[1] += divM;
			nextInfo[2] += divS;
			if (nextInfo[0] == 1) // 방금 처음 도착했으면
				MapLocs.add(nextI * N + nextJ);
			setDirVar(nextInfo, d);
		}
	}

	private static void setMoveFB(int loc, int[] info) {
		int nextI = (loc / N + Deltas[info[3]][0] * info[2] + info[2] * N) % N;
		int nextJ = (loc % N + Deltas[info[3]][1] * info[2] + info[2] * N) % N;
		int[] nextInfo = NextMap[nextI][nextJ];
		nextInfo[0]++;
		if (nextInfo[0] == 1)
			MapLocs.add(nextI * N + nextJ);
		nextInfo[1] += info[1];
		nextInfo[2] += info[2];
		setDirVar(nextInfo, info[3]);
	}

	private static void setDirVar(int[] info, int newDir) { // 도착한 곳에 방향 기입하기
		if (info[0] == 1) // 첫 도착이라면
			info[3] = newDir;
		else if (info[0] == 2) { // 앞에 하나가 있다면 (방향이 기록돼있음)
			if (info[3] % 2 == newDir % 2) // 둘 다 방향성이 같으면 방향성으로 기록
				info[3] = info[3] % 2 + 1;
			else // 둘 다 방향성이 다르면 -1
				info[3] = -1;
		} else { // 둘 이상이 있다면(방향'성'이 기록돼있음)
			if (info[3] > 0 && info[3] != newDir % 2 + 1) // 이전까지 방향성이 같았는데 새로운 방향성이 다르면
				info[3] = -1;// -1
		}
	}

}