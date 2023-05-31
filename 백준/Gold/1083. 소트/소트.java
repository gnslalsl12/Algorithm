import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<Integer> Array;
	static int S;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		while (tokens.hasMoreTokens()) {
			Array.add(Integer.parseInt(tokens.nextToken()));
		}
		S = Integer.parseInt(read.readLine());
		solv();
		for (int i = 0; i < N; i++) {
			write.write(Array.get(i) + (i == N - 1 ? "\n" : " "));
		}
		write.close();
		read.close();
	}

	private static void init() {
		Array = new ArrayList<>();
	}

	private static void solv() {
		searchFromLeft();
	}

	private static void searchFromLeft() {
		int startIndex = 0;
		while (S > 0 && startIndex < N - 1) {
			int max = Array.get(startIndex);
			int maxIndex = startIndex;
			for (int i = startIndex + 1; i < Math.min(startIndex + S + 1, N); i++) {// 왼쪽 아이부터 오른쪽으로 탐색하고, 가져올 수 있는 아이 중
																					// 가장 큰 아이를 내 자리로
				if (max < Array.get(i)) {
					max = Array.get(i);
					maxIndex = i;
				}
			}
			// 현재 max는 startIndex 이후에 있는, 가져올 수 있는 숫자들 중 가장 큰 숫자, maxIndex는 그 위치
			if (maxIndex != startIndex) {

				S -= (maxIndex - startIndex);
				Array.add(startIndex, Array.remove(maxIndex));
			} // 더 큰 아이를 못 찾았다면 패스
			startIndex++; // 그 다음 오른쪽 아이를 바꾸도록 탐색 시작
		}
	}

}