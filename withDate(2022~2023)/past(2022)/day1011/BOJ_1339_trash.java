package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1339_trash {
	static int N;
	static String[] LineArray;
	static int[][] AlphaCount;
	static int[] FinalCount = new int[26];

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		LineArray = new String[N];
		AlphaCount = new int[8][26];
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			LineArray[i] = templine;
			for (int j = 0; j < templine.length(); j++) {
				char tempc = templine.charAt(j);
				int tempi = tempc - 'A';
				AlphaCount[templine.length() - 1 - j][tempi]++; // j번째 자리에 쓰인 알파벳(tempi)마다의 카운트 수
			}
		}
		PriorityQueue<counts> orderAlpha = new PriorityQueue<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 26; j++) {
				if (AlphaCount[i][j] != 0) {
					orderAlpha.add(new counts(j, AlphaCount[i][j], i));	//큰 자리수에 쓰인 알파벳 순, 그리고 그 자리에서 많이 쓰인 알파벳 순
				}
			}
		}
		int Nmber = 9;
		while (!orderAlpha.isEmpty()) {
			counts temp = orderAlpha.poll();
			if(!orderAlpha.isEmpty() && orderAlpha.peek().count*orderAlpha.peek().orders > temp.count*temp.orders) {	//뒤에가 더 클 때
				counts temp2 = orderAlpha.poll();
				orderAlpha.add((counts)temp.clone());
				temp = (counts)temp2.clone();
			}
			if (Nmber != 0 && FinalCount[temp.alpha] != 0)
				continue;
			FinalCount[temp.alpha] = Nmber--;	//점수 매겨줌
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			String resultline = LineArray[i];
			for (int j = 0; j < resultline.length(); j++) {
				char tempc = resultline.charAt(j);
				result += FinalCount[tempc-'A']*((int)Math.pow(10, resultline.length()-1-j));
			}
		}
		System.out.println(result);
	}

	private static class counts implements Comparable<counts> , Cloneable{
		int alpha;
		int count;
		int orders;

		public counts(int alpha, int count, int orders) {
			super();
			this.alpha = alpha;
			this.count = count;
			this.orders = orders;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		@Override
		public int compareTo(counts o) {
			if (this.orders == o.orders) {
				return Integer.compare(o.count, this.count); // 같은 자리에 쓰인 애들이면 count가 많은 순대로
			}
			return Integer.compare(o.orders, this.orders);
		}
	}
}
