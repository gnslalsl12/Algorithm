import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] Map;
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
		N = Integer.parseInt(read.readLine());
		Result = 0;
		Map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(tokens.nextToken());
				Map[i][j] = value;
				Result = Math.max(Result, value);
			}
		}
	}

	private static void solv() {
		setGame(Map, 0);
	}

	private static void setGame(int[][] map, int turn) {
		if (turn == 5) {
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			int[][] newMap = new int[N][N];
			for (int n = 0; n < N; n++) {
				newMap[n] = map[n].clone();
			}
			setPushMap(dir, newMap, turn);
		}
	}

	private static void setPushMap(int dir, int[][] newMap, int turn) {
		for (int i = 0; i < N; i++) {
			int index = dir % 2 == 0 ? 0 : N - 1; // 채우게 될 위치
			boolean stacked = false; // 방금 앞에서 합쳐졌는가
			for (int j = 0; j < N; j++) {
				int realI = dir < 2 ? i : dir == 2 ? j : N - 1 - j;
				int realJ = dir == 0 ? j : dir == 1 ? N - 1 - j : i;
				int value = newMap[realI][realJ];
				newMap[realI][realJ] = 0;
				if (value != 0) {
					int beforeIndex = dir % 2 == 0 ? index - 1 : index + 1;
					if (!stacked && beforeIndex >= 0 && beforeIndex < N) {
						if (dir < 2 ? newMap[realI][beforeIndex] == value : newMap[beforeIndex][realJ] == value) {
							stacked = true;
							if (dir < 2)
								newMap[realI][beforeIndex] = value * 2;
							else
								newMap[beforeIndex][realJ] = value * 2;
							Result = Math.max(Result, value * 2);
							continue;
						}
					}
					stacked = false;
					if (dir < 2)
						newMap[realI][index] = value;
					else
						newMap[index][realJ] = value;
					index += dir % 2 == 0 ? 1 : -1;
				}
			}
		}
		setGame(newMap, turn + 1);
	}

}