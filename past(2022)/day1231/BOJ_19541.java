package day1231;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_19541 { // 오른쪾으로 밀어보고 연속된 애들이 set 되면 뒤집는 구간 정하고
	static int N;
	static ArrayList<Integer> MLine = new ArrayList<>();
	static ArrayList<Integer> OLine = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int ind1 = 0;
		for (int i = 0; i < N; i++) {
			MLine.add(Integer.parseInt(tokens.nextToken()));
			if (MLine.get(i) == 1)
				ind1 = i;
			OLine.add(i + 1);
		}
	}

	private static void solv() {
		setPushR(); // 연속될 떄까지 오른쪽으로 밀기

	}

	private static void findR() {
		int i = -1;
		int j = 0;
		for (; j < N; j++) {

			if (MLine.get(j) == MLine.get(j + 1) - 1) {
				if (i == -1)
					i = j - 1;
				else
					j--;
				break;
			}
		}
	}

	private static void setPushR() {
		while (MLine.get(N - 1) == MLine.get(N - 2) + 1) {
			MLine.add(0, MLine.remove(N - 1));
		}
	}

}
