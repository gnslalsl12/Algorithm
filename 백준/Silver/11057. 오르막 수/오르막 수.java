import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] Stacks;
	static long Result;

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
		Stacks = new int[10];
		Arrays.fill(Stacks, 1);
		Result = 0;
		read.close();
	}

	private static void solv() {
		getCount();
	}

	private static void getCount() {	//DP
		for (int i = 0; i < N; i++) {
			Stacks[9] = 1;
			for (int j = 8; j >= 0; j--) {
				Stacks[j] = (Stacks[j] + Stacks[j + 1]) % 10007;
			}
		}
		Result = Stacks[0];
	}

}