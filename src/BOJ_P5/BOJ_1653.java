package BOJ_P5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_1653 {
	static int N;
	static int K;
	static int[] Weights;
	static int DefaultVis;
	static ArrayList<Integer>[] SortedSet;
	static TreeSet<Integer> AbleSum;
	static PriorityQueue<Long> Results;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		init();
		for (int i = 1; i <= N; i++) {
			int w = Integer.parseInt(tokens.nextToken());
			Weights[i] = w;
			DefaultVis |= 1 << w;
		}
		K = Integer.parseInt(read.readLine());
		write.write(solv() + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		Weights = new int[11];
		DefaultVis = 0;
		SortedSet = new ArrayList[500000];
		AbleSum = new TreeSet<>(); // 가능한 무게 목록
		Results = new PriorityQueue<>();
		Results.add(0L);
	}

	private static long solv() {
		setSortByWeights();
		getAbleCase();
		K = Math.min(K, Results.size() - 1);
		while (K-- > 0) {
			Results.poll();
		}
		return Results.peek();
	}

	private static void setSortByWeights() {
		for (int num = 1; num <= 98765; num++) {
			int tempsum = checkAbleSet(num);
			if (tempsum != -1) {
				if (SortedSet[tempsum] == null) {
					SortedSet[tempsum] = new ArrayList<>();
				}
				AbleSum.add(tempsum);
				SortedSet[tempsum].add(num);
			}
		}
	}

	private static int checkAbleSet(int num) { // 해당 세팅이 내가 갖고있는 추로 표현 가능한가
		int vis = 0;
		int pow = 0;
		int temptotal = 0;
		for (int i = 1; i <= 10000; i *= 10) {
			int tempnum = (num / i) % 10; // 사용된 추
			pow++;
			if (tempnum == 0)
				continue;
			temptotal += tempnum * pow;
			if ((vis & 1 << tempnum) != 0)
				return -1; // 중복 사용 추
			boolean has = false;
			for (int j = 0; j <= N; j++) { // 입력 추에 포함된 추인가
				if (Weights[j] == tempnum) { // 있네?
					has = true;
					vis |= 1 << tempnum;
				}
			}
			if (!has)
				return -1;
		}
		if (vis == DefaultVis) // 보유한 추를 모두 다 쓴 경우라면 false
			return -1;
		return temptotal; // 해당 세팅에서의 총 무게
	}

	private static void getAbleCase() {
		for (int totalweight : AbleSum) { // 기록된 무게들 중에서만
			getComb(totalweight);
		}
	}

	private static void getComb(int totalweight) { // 두개씩 뽑아서 확인
		int size = SortedSet[totalweight].size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) { // 두 개씩 뽑아서
				int setA = SortedSet[totalweight].get(i);
				int setB = SortedSet[totalweight].get(j);
				if (checkAble(setA, setB)) { // 중복 추 없이 세팅 가능한가?
					addResult(setA, setB); // 전체 세팅해서 추가
				}
			}
		}
	}

	private static boolean checkAble(int setA, int setB) { // 중복 추 체크
		int vis = 0;
		for (int i = 1; i <= 10000; i *= 10) {
			int tempA = (setA / i) % 10;
			int tempB = (setB / i) % 10;
			if (tempA != 0) {
				if ((vis & 1 << tempA) != 0)
					return false;
				vis |= 1 << tempA;
			}
			if (tempB != 0) {
				if ((vis & 1 << tempB) != 0)
					return false;
				vis |= 1 << tempB;
			}
		}
		return true;
	}

	private static void addResult(int A, int B) { // 저울에 세팅
		long resultA = A;
		long resultB = B;
		for (int i = 1; i <= 10000; i *= 10) {
			resultA *= 10;
			resultA += (B / i) % 10;
			resultB *= 10;
			resultB += (A / i) % 10;
		}
		Results.add(resultA);
		Results.add(resultB);
	}

}
