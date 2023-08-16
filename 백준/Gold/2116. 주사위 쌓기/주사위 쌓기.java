import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] Dices;
	static int[] OpsIndex;
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
		Dices = new int[N][6];
		for (int n = 0; n < N; n++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int d = 0; d < 6; d++) {
				Dices[n][d] = Integer.parseInt(tokens.nextToken());
			}
		}
		OpsIndex = new int[] { 5, 3, 4, 1, 2, 0 }; // 각 인덱스별 반대편 인덱스
		Result = -1;
		read.close();
	}

	private static void solv() {
		setDices();
	}

	private static void setDices() {
		for (int d = 0; d < 6; d++) { // 1번 주사위 세팅 6가지
			setBase(0, d, 0);
		}
	}

	private static void setBase(int order, int upperIndex, int sum) {
		// order번째 주사위의 index번쨰 면이 윗면, upperValue는 그 면의 값
		if (order == N) {
			Result = Math.max(Result, sum);
			return;
		}
		int max = 0;
		int nextUpperIndex = 0;
		for (int i = 0; i < 6; i++) {
			// 현재 윗쪽 면 값이 다음 주사위의 i번쨰 값과 같다면 그 반대방향 index가 다음 upperIndex
			if (order != N - 1 && Dices[order + 1][i] == Dices[order][upperIndex])
				nextUpperIndex = OpsIndex[i];
			if (i == upperIndex || i == OpsIndex[upperIndex]) // 윗쪽면과 반대방향면은 제외
				continue;
			max = Math.max(max, Dices[order][i]); // 그 중 가장 큰 값
		}
		setBase(order + 1, nextUpperIndex, sum + max);
	}

}