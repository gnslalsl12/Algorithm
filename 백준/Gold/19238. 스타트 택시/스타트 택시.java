import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int Fuel;
	static int Current;
	static char[][] Map;
	static HashMap<Integer, Integer> GuestInfos;
	static int[][] Deltas;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Fuel = readInt();
		Map = new char[N][N];
		GuestInfos = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (readInt() == 1)
					Map[i][j] = 'X';
			}
		}
		Current = (readInt() - 1) * N + readInt() - 1;
		for (int m = 0; m < M; m++) {
			int fromX = readInt() - 1;
			int fromY = readInt() - 1;
			Map[fromX][fromY] = 'G';
			GuestInfos.put(fromX * N + fromY, (readInt() - 1) * N + readInt() - 1);
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			neg = n == 45;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult();
		write.write(GuestInfos.isEmpty() ? Fuel + "\n" : "-1\n");
		write.close();
	}

	private static void getResult() {
		while (true) {
			if (!setTaxiMove())
				break;
			if (GuestInfos.isEmpty())
				break;
		}
	}

	private static boolean setTaxiMove() {
		int fixedUsedFuel = -1; // 최종적으로 목적지까지 필요한 연료 값
		int dest = GuestInfos.containsKey(Current) ? GuestInfos.remove(Current) : -1; // 목적지 (손님이 있으면 내릴 곳, 아니면 -1)
		if (dest != -1) // 태운 손님 위치 초기화
			Map[Current / N][Current % N] = '\0';
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.add(Current);
		int[] vis = new int[N];
		vis[Current / N] |= 1 << (Current % N);

		while (!bfsQ.isEmpty()) {
			int loc = bfsQ.poll();
			int needFuel = loc / 1000;
			if (fixedUsedFuel != -1 && needFuel + 1 != fixedUsedFuel) // 목적지를 찾았다면 같은 연료값으로 갈 수 있는 나머지 탐색
				break;
			loc %= 1000;
			int x = loc / N;
			int y = loc % N;
			for (int[] dir : Deltas) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				if (!isIn(nextX, nextY) || Map[nextX][nextY] == 'X')
					continue; // 갈 수 없음
				if ((vis[nextX] & (1 << nextY)) != 0)
					continue; // 방문
				if ((dest == -1 && Map[nextX][nextY] == 'G') || (nextX * N + nextY == dest)) { // 손님을 찾았거나 목적지까지 도착
					if (fixedUsedFuel == -1) { // 첫 도착
						Current = nextX * N + nextY; // 위치값 갱신
						fixedUsedFuel = needFuel + 1; // 최종 필요 연료값
					} else { // 같은 연료값의 다른 위치 도착
						Current = Math.min(Current, nextX * N + nextY); // 위치값 작은 걸로 갱신
					}
				}
				if (fixedUsedFuel == -1) {
					vis[nextX] |= 1 << nextY;
					bfsQ.add(nextX * N + nextY + (needFuel + 1) * 1000);
				}
			}
		}
		Fuel -= fixedUsedFuel; // 필요 연료만큼 차감
		if (Fuel < 0 || fixedUsedFuel == -1) { // 연료가 부족하거나 어디에도 도착할 곳이 없음
			Fuel = -1;
			return false;
		} else { // 연료 충분히 도착
			if (dest != -1) // 손님을 모신 거라면 연료 충전
				Fuel += fixedUsedFuel * 2;
			return true;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}