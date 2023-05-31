import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> PosNums;
	static PriorityQueue<Integer> NegNums;
	static int Zeros;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		init();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(read.readLine());
			if (input == 0)
				Zeros++;
			else if (input > 0)
				PosNums.add(input);
			else
				NegNums.add(input);
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		Zeros = 0;
		PosNums = new PriorityQueue<>(Collections.reverseOrder());
		NegNums = new PriorityQueue<>();
	}

	private static void solv() {
		while (PosNums.size() > 1) {
			int A = PosNums.poll();
			int B = PosNums.poll();
			Result += Math.max(A * B, A + B);
		}
		while (NegNums.size() > 1) {
			int A = NegNums.poll();
			int B = NegNums.poll();
			Result += A * B;
		}
		if (Zeros > 0) {
			Result += PosNums.isEmpty() ? 0 : PosNums.poll();
		} else {
			int p = PosNums.isEmpty() ? 0 : PosNums.poll();
			int n = NegNums.isEmpty() ? 0 : NegNums.poll();
			Result += p + n;
		}
	}

}