package G3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로계란치기 {
	static int N;
	static int[][] EggsWeight;
	static int MaxCount;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		EggsWeight = new int[N][2];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			EggsWeight[i] = new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) };
		}
		MaxCount = 0;
		solv();
		write.write(MaxCount + "\n");
		write.close();
		read.close();
	}

	private static void solv() {
		setHit(0, 0);
	}

	private static void setHit(int index, int count) {
		MaxCount = Math.max(MaxCount, count);
		if (MaxCount == N)
			return;
		if (index == N) {
			MaxCount = Math.max(MaxCount, count);
			return;
		}
		if (EggsWeight[index][0] <= 0) {
			setHit(index + 1, count);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (i == index || EggsWeight[i][0] <= 0)
				continue;
			int tempcount = HitorRecovery(true, index, i);
			setHit(index + 1, count + tempcount);
			HitorRecovery(false, index, i);
		}
	}

	private static int HitorRecovery(boolean Hit, int from, int to) {
		int result = 0;
		if (Hit) {
			EggsWeight[from][0] -= EggsWeight[to][1];
			EggsWeight[to][0] -= EggsWeight[from][1];
			result = EggsWeight[from][0] <= 0 ? EggsWeight[to][0] <= 0 ? 2 : 1 : EggsWeight[to][0] <= 0 ? 1 : 0;
		} else {
			EggsWeight[from][0] += EggsWeight[to][1];
			EggsWeight[to][0] += EggsWeight[from][1];
		}
		return result;
	}

}
