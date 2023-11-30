import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] Con;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		Con = new int[N];
		for (int n = 0; n < N; n++) {
			Con[n] = readInt();
		}
	}

	private static int readInt() throws IOException {
		int n, c;
		do {
			n = System.in.read();
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static int getResult() {
		ArrayList<Integer> minSubs = new ArrayList<>();
		minSubs.add(Con[0]);
		for (int n = 1; n < N; n++) {
			int next = Con[n];
			int len = minSubs.size();
			if (minSubs.get(len - 1) < next) { // next가 가장 큰 숫자로 바로 추가
				minSubs.add(next);
			} else {
				for (int i = 0; i < len; i++) {
					if (minSubs.get(i) > next) { // next보다 큰 첫번째 수 대체
						minSubs.set(i, next);
						break;
					}
				}
			}
		}
		return minSubs.size();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(getResult() + "\n");
		write.close();
	}

}