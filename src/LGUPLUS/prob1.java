package LGUPLUS;

import java.util.Arrays;
import java.util.TreeSet;

public class prob1 {
	static int[] W;
	static int Max;
	static int MaxWeight;
	static int Count;

	static TreeSet<Integer> Already;

	public static void main(String[] args) {
		int[] weight = { 100, 60, 40, 20, 35, 45 };
		int[] weight2 = { 100, 50, 50, 80, 40, 40 };
		int[] weight3 = { 90, 40, 50, 90 };
		System.out.println(Arrays.toString(solution(weight)));
		System.out.println(Arrays.toString(solution(weight2)));
		System.out.println(Arrays.toString(solution(weight3)));
	}

	public static int[] solution(int[] weight) {
		int[] answer = {};
		W = weight;
		Max = weight.length;
		Count = 0;
		MaxWeight = 0;
		getSubset(0, 0, 0, 0);
		answer = new int[] { Count, MaxWeight };
		return answer;
	}

	public static void getSubset(int sel, int count, int sumW, int participate) {
		if (count == Max) {
			if (sumW % 2 != 0 || participate <= 1)
				return;
			Already = new TreeSet<>();
			getComb(0, 0, 0, sel, sumW, participate);
			return;
		}

		getSubset(sel |= 1 << count, count + 1, sumW + W[count], participate + 1); // count 애 출전
		getSubset(sel &= ~(1 << count), count + 1, sumW, participate); // count 미출전

	}

	public static void getComb(int count, int start, int sel, int inputs, int sumW, int participate) {
		if (count == participate) {
			if (Already.contains(sel))
				return;
			Already.add(sel);
			int totalW = 0;
			for (int i = 0; i < Max; i++) {
				if ((sel & 1 << i) != 0) {
					totalW += W[i];
					sel &= ~(1 << i);
				} else {
					sel |= 1 << i;
				}

			}
			Already.add(sel);
			if (totalW == sumW / 2 && totalW >= MaxWeight) {
				if (participate > Count) {
					MaxWeight = totalW;
					Count = participate;
				}
			}
			return;
		}
		for (int i = start; i < Max; i++) {
			if ((inputs & (1 << i)) == 0)
				continue; // 참여 안 하는 사람
			getComb(count + 1, i + 1, sel |= 1 << i, inputs, sumW, participate);
			getComb(count + 1, i + 1, sel &= ~(1 << i), inputs, sumW, participate);
		}

	}
}
