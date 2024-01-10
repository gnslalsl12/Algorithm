import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] Map = new char[55][55];
	static int Start;

	static int[][] Deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		for (int n = 0; n < N; n++) {
			String inputLine = read.readLine();
			for (int m = 0; m < M; m++) {
				char tempChar = inputLine.charAt(m);
				if (tempChar == '0') { // 시작위치 ('\0')
					Start = n * M + m;
				} else if (tempChar != '.') { // 벽('#'), 문('A' ~ 'F'), 열쇠('a'~'f'), 도착 ('1')
					Map[n][m] = tempChar;
				}
			}
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(getResult() + "\n");
		write.close();
	}

	private static int getResult() {
		int result = -1;
		int[][][] countMap = new int[N][M][66]; // 열쇠 소지 케이스에 따른 이동 횟수 기록 맵
		countMap[Start / M][Start % M][6] = 1;
		Queue<int[]> bfsQ = new LinkedList<>();
		bfsQ.add(new int[] { Start, 1, 0 }); // 위치, 움직임 카운트(default 1), 열쇠 소지(비트마스킹)

		while (!bfsQ.isEmpty()) {
			int[] info = bfsQ.poll();
			int loc = info[0];
			int count = info[1];
			int keys = info[2];
			for (int[] dir : Deltas) {
				int nextN = loc / M + dir[0];
				int nextM = loc % M + dir[1];
				if (!isIn(nextN, nextM) || Map[nextN][nextM] == '#') {
					continue; // 범위 밖 / 벽
				}

				char mapValue = Map[nextN][nextM];
				if (mapValue == '1') { // 탈출
					if (result == -1) {
						result = count + 1;
					} else {
						result = Math.min(result, count + 1);
					}
					continue;
				}

				if ('A' <= mapValue && mapValue <= 'F' && (keys & 1 << (mapValue - 'A')) == 0) {
					continue; // 문을 만났지만 아직 그 키가 없을 때
				}

				int newKeys = keys;
				if ('a' <= mapValue && mapValue <= 'f') {
					newKeys |= 1 << (mapValue - 'a'); // 키 획득
				}

				if (countMap[nextN][nextM][newKeys] == 0 || countMap[nextN][nextM][newKeys] > count + 1) {
					// 현재 소지한 키 세팅의 카운트맵 상 이동 위치가 처음 || 더 짧게 이동 가능할 때
					countMap[nextN][nextM][newKeys] = count + 1;
					bfsQ.add(new int[] { nextN * M + nextM, count + 1, newKeys });
				}
			}
		}
		return result == -1 ? -1 : result - 1;
	}

	private static boolean isIn(int n, int m) {
		return n >= 0 && n < N && m >= 0 && m < M;
	}

}