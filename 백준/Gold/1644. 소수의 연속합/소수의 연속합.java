import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static boolean[] NonPrimeSets;
	static ArrayList<Integer> PrimeList;
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
		NonPrimeSets = new boolean[N + 1];
		PrimeList = new ArrayList<>();
		Result = 0L;
		read.close();
	}

	private static void solv() {
		if (N == 1)
			return;
		setPrimes();
		getCombPrimes();
	}

	private static void setPrimes() { // N 이하의 소수 구하기
		for (int i = 2; i <= N; i++) {
			if (!NonPrimeSets[i]) { // 소수 발견
				PrimeList.add(i);
				int multi = 2;
				while (i * multi <= N) {
					NonPrimeSets[i * multi++] = true;
				}
			}
		}
	}

	private static void getCombPrimes() { // 투포인터
		int endIndex = PrimeList.size() - 1;
		int startIndex = PrimeList.size() - 1;
		long sum = PrimeList.get(endIndex);
		while (true) {
			if (sum == N) {
				Result++;
				sum -= PrimeList.get(endIndex--);
			} else if (sum > N) {
				sum -= PrimeList.get(endIndex--);
			} else {
				if (startIndex == 0) // 아직 작은데 더이상 더할 게 없다
					return;
				sum += PrimeList.get(--startIndex);
			}
		}
	}

}