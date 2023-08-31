import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] Coaches;
	static int MaxCount;
	static int[] CumSums;
	static int[][] MaxCase;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		Coaches = new int[N];
		CumSums = new int[N];
		for (int n = 0; n < N; n++) {
			Coaches[n] = Integer.parseInt(tokens.nextToken());
			CumSums[n] = Coaches[n] + (n == 0 ? 0 : CumSums[n - 1]);
		}
		MaxCount = Integer.parseInt(read.readLine());
		MaxCase = new int[3][N];
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getWholeMaxCase();
		write.write(Result + "\n");
		write.close();
	}

	private static void getWholeMaxCase() {
		Result = 0;
		for (int t = 0; t < 3; t++) {
			getMaxCase(t);
		}
	}

	private static void getMaxCase(int train) { // train번째 소형 기관차가 끌고가는 경우의 Max
		for (int i = MaxCount - 1; i < N; i++) {
			MaxCase[train][i] = Math.max(MaxCase[train][i - 1],	//i-1번까지 열차를 고려했을 때의 값
					(CumSums[i] - (i == MaxCount - 1 ? 0 : CumSums[i - MaxCount]))	//i번까지의 열차를 고려했을 때의 값
							+ ((train > 0 && i >= MaxCount) ? MaxCase[train - 1][i - MaxCount] : 0));	//train-1번쨰 i-MaCount 번째 열차를 고려했을 때의 최대값
			if (train == 2)
				Result = Math.max(Result, MaxCase[2][i]);
		}
	}

}