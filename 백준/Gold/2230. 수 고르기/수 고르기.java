import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] Dict;
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
		Dict = new int[N];
		for (int n = 0; n < N; n++)
			Dict[n] = Integer.parseInt(read.readLine());
		Arrays.sort(Dict, 0, N);
		Result = Integer.MAX_VALUE;
		read.close();
	}

	private static void solv() {
		getTwoPointer();
	}

	private static void getTwoPointer() {
		int indexL = 0;
		int indexR = 0;
		while (indexR < N) {
			int gap = Dict[indexR] - Dict[indexL];
			if (gap < M)
				indexR++;
			else {
				Result = Math.min(gap, Result);
				if (gap == M)
					return;
				indexL++;
			}
		}
	}

}