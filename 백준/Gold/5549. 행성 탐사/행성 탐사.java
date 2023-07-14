import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int K;
	static char[][] Map;
	static int[][][] CountMap;
	static Queue<int[]> Areas;
	static HashMap<Character, Integer> Dict;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		while (!Areas.isEmpty()) {
			int[] output = Areas.poll();
			write.write(output[0] + " " + output[1] + " " + output[2] + "\n");
		}
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(read.readLine());
		Map = new char[N][M];
		CountMap = new int[N][M][3];
		Areas = new LinkedList<>();
		Dict = new HashMap<>();
		Dict.put('J', 0);
		Dict.put('O', 1);
		Dict.put('I', 2);
		for (int n = 0; n < N; n++) {
			String line = read.readLine();
			for (int m = 0; m < M; m++) {
				Map[n][m] = line.charAt(m);
			}
		}
		for (int k = 0; k < K; k++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken()) - 1;
			int b = Integer.parseInt(tokens.nextToken()) - 1;
			int c = Integer.parseInt(tokens.nextToken()) - 1;
			int d = Integer.parseInt(tokens.nextToken()) - 1;
			Areas.add(new int[] { a * M + b, c * M + d });
		}
		read.close();
	}

	private static void solv() {
		setMapCounted();
		getAreasCount();
	}

	private static void setMapCounted() { // 왼쪽위부터 누적합
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int type = 0; type < 3; type++) {
					CountMap[i][j][type] = (i == 0 ? 0 : CountMap[i - 1][j][type])
							+ (j == 0 ? 0 : CountMap[i][j - 1][type])
							- (i > 0 && j > 0 ? CountMap[i - 1][j - 1][type] : 0);
				}
				CountMap[i][j][Dict.get(Map[i][j])]++;
			}
		}
	}

	private static void getAreasCount() {
		int size = Areas.size();
		while (size-- > 0) {
			int[] pop = Areas.poll();
			int a = pop[0] / M;
			int b = pop[0] % M;
			int c = pop[1] / M;
			int d = pop[1] % M;
			int[] counted = CountMap[c][d].clone();
			for (int type = 0; type < 3; type++) {
				counted[type] -= (a == 0 ? 0 : CountMap[a - 1][d][type]) + (b == 0 ? 0 : CountMap[c][b - 1][type]);
				if (a != 0 && b != 0)
					counted[type] += CountMap[a - 1][b - 1][type];
			}
			Areas.add(counted);
		}
	}

}