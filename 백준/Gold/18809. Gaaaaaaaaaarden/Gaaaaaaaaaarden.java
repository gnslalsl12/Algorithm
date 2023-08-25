import java.util.*;
import java.io.*;

public class Main {
	static int N, M, G, R;
	static char[][] Map;
	static int[] Spreadable;
	static int SpreadableCount;
	static Queue<int[]> GRCases;
	static Queue<Integer> RSpread;
	static Queue<Integer> GSpread;
	static int[][] Deltas;
	static int FlowerCount;
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
		G = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		Map = new char[N][M];
		Spreadable = new int[10]; // 배양액을 뿌릴 수 있는 위치값 배열
		SpreadableCount = 0;
		GRCases = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			tokens = new StringTokenizer(read.readLine());
			for (int m = 0; m < M; m++) {
				int input = Integer.parseInt(tokens.nextToken());
				if (input == 0) {
					Map[n][m] = 'W';
				} else if (input == 1) {
					Map[n][m] = 'X';
				} else {
					Map[n][m] = 'O';
					Spreadable[SpreadableCount++] = n * M + m;
				}
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
		read.close();
	}

	private static void solv() {
		getCombGRCases(0, 0, 0); // 배양액을 각각 뿌릴 수 있는 모든 케이스 구하기 (조합)
		while (!GRCases.isEmpty()) {
			setElixir(GRCases.poll());
		}
	}

	private static void getCombGRCases(int count, int current, int selR) {
		if (count == R) {
			getCombG(selR, 0, 0, 0); // R케이스 기반으로 G케이스 구하기
			return;
		}

		for (int i = current; i < SpreadableCount; i++) {
			selR |= 1 << i;
			getCombGRCases(count + 1, i + 1, selR);
			selR &= ~(1 << i);
		}
	}

	private static void getCombG(int selR, int count, int current, int selG) {
		if (count == G) {
			GRCases.add(new int[] { selR, selG }); // 해당 케이스 추가
			return;
		}

		for (int i = current; i < SpreadableCount; i++) {
			if ((selR & (1 << i)) != 0)
				continue;
			selG |= 1 << i;
			getCombG(selR, count + 1, i + 1, selG);
			selG &= ~(1 << i);
		}
	}

	private static void setElixir(int[] currentCase) { // GR케이스 기반으로 필드에 배양액 뿌리기
		FlowerCount = 0;
		char[][] copyMap = new char[N][M];
		for (int n = 0; n < N; n++) {
			copyMap[n] = Map[n].clone();
		}
		RSpread = new LinkedList<>(); // R배양액 뿌린 위치값 기록
		GSpread = new LinkedList<>(); // G배양액 뿌린 위치값 기록
		int Rcase = currentCase[0];
		int Gcase = currentCase[1];
		for (int i = 0; i < SpreadableCount; i++) {
			int loc = Spreadable[i];
			if ((Rcase & (1 << i)) != 0) { // R배양액을 세팅할 위치배열의 index
				copyMap[loc / M][loc % M] = 'r';
				RSpread.add(loc);
			} else if ((Gcase & (1 << i)) != 0) { // G배양액을 세팅할 위치배열의 index
				copyMap[loc / M][loc % M] = 'g';
				GSpread.add(loc);
			}
		} // 초기 배양액 세팅 완료
		setSpread(copyMap);
	}

	private static void setSpread(char[][] map) { // 배양액 퍼뜨리기
		if (RSpread.isEmpty() && GSpread.isEmpty()) { // 더이상 퍼져나갈 배양액이 없으면 끝
			Result = Math.max(Result, FlowerCount); // 결과값 기록
			return;
		}
		int size = RSpread.size(); // R배양액 퍼뜨리기
		while (size-- > 0) {
			int locR = RSpread.poll();
			int currentI = locR / M;
			int currentJ = locR % M;
			if (map[currentI][currentJ] != 'r') // 이전 배양액 경우에만 가능 (꽃이 된 경우는 X)
				continue;
			map[currentI][currentJ] = 'R'; // 이전 배양액은 이번 케이스에서는 더이상 퍼지지 않음을 기록 (R : 이전, r : 현재 케이스)
			for (int dir = 0; dir < 4; dir++) {
				int nextI = currentI + Deltas[dir][0];
				int nextJ = currentJ + Deltas[dir][1];
				if (!isIn(nextI, nextJ) || (map[nextI][nextJ] != 'O' && map[nextI][nextJ] != 'X')) // 범위 밖이거나 빈 공간이 아니면
					continue;
				map[nextI][nextJ] = 'r'; // 현재 케이스에 퍼뜨려진 배양액
				RSpread.add(nextI * M + nextJ); // 위치 기록
			}
		}

		size = GSpread.size();
		while (size-- > 0) {
			int locG = GSpread.poll();
			int currentI = locG / M;
			int currentJ = locG % M; // g배양액은 이전, 현재 구분 없이 가능 (뿌려놓고나서 뒤늦게 꽃이 되는 경우가 없기 때문)
			for (int dir = 0; dir < 4; dir++) {
				int nextI = currentI + Deltas[dir][0];
				int nextJ = currentJ + Deltas[dir][1];
				if (!isIn(nextI, nextJ)
						|| (map[nextI][nextJ] != 'O' && map[nextI][nextJ] != 'X' && map[nextI][nextJ] != 'r')) {
					continue;
				}
				if (map[nextI][nextJ] == 'r') { // 현재 케이스에서 뿌려진 R배양액이라면
					map[nextI][nextJ] = 'F'; // 꽃 생성
					FlowerCount++;
					continue; // 위치 기록 X
				}
				map[nextI][nextJ] = 'G'; // 꽃이 되지 못한 R배양액
				GSpread.add(nextI * M + nextJ); // 위치 기록
			}
		}
		setSpread(map); // 재귀
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M;
	}

}