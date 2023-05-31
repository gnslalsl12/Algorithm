import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long Result;
	static PriorityQueue<Long> AscendingQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		init();
		for (int i = 0; i < N; i++) {
			AscendingQueue.add(Long.parseLong(read.readLine()));
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		AscendingQueue = new PriorityQueue<>();
		Result = 0;
	}

	private static void solv() {
		sumFromLow();
	}

	private static void sumFromLow() {
		while (AscendingQueue.size() > 1) {
			long A = AscendingQueue.poll();
			long B = AscendingQueue.poll();
			AscendingQueue.add(A + B);
			Result += A + B;
		}
	}

}