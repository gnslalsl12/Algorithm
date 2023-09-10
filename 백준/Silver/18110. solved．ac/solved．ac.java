import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> PQ;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		PQ = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			PQ.add(readInt());
		}
	}

	private static int readInt() throws IOException {
		int n, c;
		do
			n = System.in.read();
		while (n < 45);
		n &= 15;
		while ((c = System.in.read()) > 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(getResult() + "\n");
		write.close();
	}

	private static int getResult() {
		int result = 0;
		int cut = (int) Math.round((float) N * 0.15);
		for (int c = 0; c < cut; c++) {
			PQ.poll();
		}
		int sum = 0;
		int remained = PQ.size() - cut;
		while (PQ.size() > cut) {
			sum += PQ.poll();
		}
		result = (int) Math.round((float) sum / remained);
		return result;
	}

}