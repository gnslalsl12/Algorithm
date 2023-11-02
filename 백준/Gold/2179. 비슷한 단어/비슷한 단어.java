import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static String[] Words;
	static int[] Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Words = new String[N];
		for (int n = 0; n < N; n++) {
			Words[n] = read.readLine();
		}
		Result = new int[2];
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getSubStrings();
		write.write(Words[Result[0]] + "\n" + Words[Result[1]] + "\n");
		write.close();
	}

	private static void getSubStrings() {
		int maxCount = -1;
		for (int i = 0; i < N - 1; i++) {
			String subA = Words[i];
			for (int j = i + 1; j < N; j++) {
				String subB = Words[j];
				int len = Math.min(subA.length(), subB.length());
				int count = 0;
				for (int c = 0; c < len; c++) {
					if (subA.charAt(c) == subB.charAt(c)) {
						count++;
					} else {
						break;
					}
				}
				if (maxCount < count) {
					Result[0] = i;
					Result[1] = j;
					maxCount = count;
				}
			}
		}
	}

}