import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2072 {
	static int[] WhiteMaps = new int[20], BlackMaps = new int[20];
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };
	static int[][] Bdeltas = { { 0, -1 }, { -1, 0 }, { -1, -1 }, { -1, 1 } };
	static int Phase = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		Queue<int[]> setGo = new LinkedList<>();
		for (int Order = 1; Order <= N; Order++) {
			tokens = new StringTokenizer(read.readLine());
			setGo.add(new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) });
		}
		int result = -1;
		while (!setGo.isEmpty()) {
			Phase++;
			if (solv(setGo.poll())) {
				result = Phase;
				break;
			}
		}
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static boolean solv(int[] input) {
		if (Phase % 2 == 1) {
			setGo(BlackMaps, input[0], input[1]);
			if (doCheck(BlackMaps))
				return true;
		} else {
			setGo(WhiteMaps, input[0], input[1]);
			if (doCheck(WhiteMaps))
				return true;
		}
		return false;
	}

	private static void setGo(int[] Maps, int x, int y) {
		Maps[x] |= 1 << y;
	}

	private static boolean doCheck(int[] Maps) {
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if ((Maps[i] & 1 << j) != 0) {
					for (int dir = 0; dir < 4; dir++) {
						int count = 1;
						boolean way = false;
						boolean Bway = false;
						int nextx, nexty;
						for (int len = 1; len < 19; len++) {
							if (!way) {
								nextx = i + deltas[dir][0] * len;
								nexty = j + deltas[dir][1] * len;
								if (!isIn(nextx, nexty) || (Maps[nextx] & 1 << nexty) == 0)
									way = true;
								else
									count++;
							}
							if (!Bway) {
								nextx = i + Bdeltas[dir][0] * len;
								nexty = j + Bdeltas[dir][1] * len;
								if (!isIn(nextx, nexty) || (Maps[nextx] & 1 << nexty) == 0) {
									Bway = true;
								} else
									count++;
							}
							if ((way && Bway) || count == 6)
								break;
						}
						if (count == 5)
							return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean isIn(int x, int y) {
		return x >= 1 && y >= 1 && x <= 19 && y <= 19;
	}

}
