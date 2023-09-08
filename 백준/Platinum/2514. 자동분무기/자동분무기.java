import java.io.*;

public class Main {
	static int M;
	static int K;
	static int[][] Map;
	static int[] AvailLocs;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		M = readInt();
		K = readInt();
		Map = new int[9][9];
		AvailLocs = new int[K];
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Map[i][j] = readInt() - M;
			}
		}
		read.close();
	}

	private static int readInt() throws IOException {
		int n, c;
		do
			n = System.in.read();
		while (n < 48);
		n &= 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getCumulSumOfRC();
		getAddOrRem();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (Map[i][j] == 1000)
					write.write("+ ");
				else if (Map[i][j] == -1000)
					write.write("- ");
				else
					write.write(". ");
			}
			write.write("\n");
		}
		write.close();
	}

	private static void getCumulSumOfRC() {
		for (int i = 1; i <= 8; i++) {
			int sum = 0;
			for (int j = 1; j <= 8; j++) {
				sum += Map[i][j];
			}
			Map[i][0] = sum;
		}
		for (int j = 1; j <= 8; j++) {
			int sum = 0;
			for (int i = 1; i <= 8; i++) {
				sum += Map[i][j];
			}
			Map[0][j] = sum;
		}
		int k = 0;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Map[i][j] = Map[i][0] + Map[0][j] - Map[i][j];
				if (Map[i][j] % 2 != 0)
					AvailLocs[k++] = i * 9 + j; // 분무기 위치 후보지들
			}
		}
	}

	private static void getAddOrRem() {
		int[][] allAddCaseMap = new int[9][9];
		for (int loc : AvailLocs) {
			int locI = loc / 9;
			int locJ = loc % 9;
			for (int i = 1; i <= 8; i++) {
				allAddCaseMap[locI][i]++;
				allAddCaseMap[i][locJ]++;
			}
			allAddCaseMap[locI][locJ]--;
		}
		for (int i = 1; i <= 8; i++) {
			int sum = 0;
			for (int j = 1; j <= 8; j++) {
				sum += allAddCaseMap[i][j];
			}
			allAddCaseMap[i][0] = sum;
		}
		for (int j = 1; j <= 8; j++) {
			int sum = 0;
			for (int i = 1; i <= 8; i++) {
				sum += allAddCaseMap[i][j];
			}
			allAddCaseMap[0][j] = sum;
		}
		for (int loc : AvailLocs) {
			int locI = loc / 9;
			int locJ = loc % 9;
			int ifAllAddCase = allAddCaseMap[locI][0] + allAddCaseMap[0][locJ] - allAddCaseMap[locI][locJ];
			if ((ifAllAddCase - Map[locI][locJ]) % 4 == 0)
				Map[locI][locJ] = 1000; // 영양제
			else
				Map[locI][locJ] = -1000; // 제초제
		}
	}

}