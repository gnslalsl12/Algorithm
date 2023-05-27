import java.io.*;
import java.util.*;

public class Main {

	static long N;
	static int[] DiceSides = new int[6];
	static int[][] DoubleEdgeCombs = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 1, 2 }, { 1, 3 }, { 1, 5 }, { 2, 4 },
			{ 2, 5 }, { 3, 4 }, { 3, 5 }, { 4, 5 } };
	static int[][] TripleEdgeCombs = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 4 }, { 0, 3, 4 }, { 5, 1, 2 }, { 5, 1, 3 },
			{ 5, 2, 4 }, { 5, 3, 4 } };
	static long MinSide = Integer.MAX_VALUE;
	static long MinDoubleEdgeComb;
	static long MinTripleEdgeComb;
	static long Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < 6; i++) {
			DiceSides[i] = Integer.parseInt(tokens.nextToken());
			MinSide = Math.min(DiceSides[i], MinSide);
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void solv() {
		if (N == 1) { // 하나만 쌓을 때는 예외
			doOnlyOne();
			return;
		}
		getMinDoubleEdge();
		getMinTripleEdge();
		getCalculatedSum();
	}

	private static void doOnlyOne() {
		int max = -1;
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			sum += DiceSides[i];
			max = Math.max(max, DiceSides[i]);
		}
		Result = sum - max;
	}

	private static void getMinDoubleEdge() { // 3면이 나오는 주사위의 숫자 합 최소값 구하기
		long min = Integer.MAX_VALUE;
		for (int[] edgeComb : DoubleEdgeCombs) {
			min = Math.min(min, DiceSides[edgeComb[0]] + DiceSides[edgeComb[1]]);
		}
		MinDoubleEdgeComb = min;
	}

	private static void getMinTripleEdge() { // 2면이 나오는 주사위의 숫자 합 최소값 구하기
		long min = Integer.MAX_VALUE;
		for (int[] edgeComb : TripleEdgeCombs) {
			min = Math.min(min, DiceSides[edgeComb[0]] + DiceSides[edgeComb[1]] + DiceSides[edgeComb[2]]);
		}
		MinTripleEdgeComb = min;
	}

	private static void getCalculatedSum() { // 주사위 노출 면에 따라 총합 구하기
		long oneDirSidesCount = (N - 2) * (N - 2) * 5 + (N - 2) * 4;
		long doubleDirSidesCount = (N - 1) * 4 + (N - 2) * 4;
		long tripleDirSidesCount = 4;
		Result = oneDirSidesCount * MinSide + doubleDirSidesCount * MinDoubleEdgeComb
				+ tripleDirSidesCount * MinTripleEdgeComb;
	}

}