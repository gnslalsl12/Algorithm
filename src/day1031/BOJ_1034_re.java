package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1034_re {
	static int N, M, K;
	static boolean[][] maps;
	static ArrayList<nums> onCount;
	static int max;
	static boolean found;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		found = false;
		max = -1;
		onCount = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1') {
					maps[i][j] = true;
				}
			}
		}
		for (int j = 0; j < M; j++) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (maps[i][j]) {
					count++;
				}
			}
//			System.out.println("count : " +count);
//			if (count > N/2)
//				continue;
			onCount.add(new nums(j, count));
		}
		Collections.sort(onCount); // 켜진 게 작은 라인 순서대로 들어와있음
//		System.out.println(onCount);
		K = Integer.parseInt(read.readLine());
		if(onCount.size()==0) {
			System.out.println(0);
			return;
		}
		Comb(0, new boolean[M], 0);
		System.out.println(max);
	} // K - count 가 짝수이면 가능

	private static void Comb(int count, boolean[] sel, int index) {
		if (found)
			return;
		if (count > K)
			return;
		if (index == M) {
			if ((K - count) % 2 == 1)
				return;
//			System.out.println("가능한 경우 : " + Arrays.toString(sel));
			// 가능한 경우임
			int tempresult = setMap(sel);
//			System.out.println(tempresult);
			if (max == -1) { // 찾기 전
				max = tempresult;
			} else {
				if (max > tempresult) {
					found = true;
					return;
				} else {
					max = tempresult;
				}
			}
			return;
		}
		sel[index] = true;
		Comb(count + 1, sel, index + 1);
		sel[index] = false;
		Comb(count, sel, index + 1);

	}
	
	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N;i ++) {
			for(int j = 0; j< M; j++) {
				if(maps[i][j]) {
					sb.append("1 ");
				}else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int setMap(boolean[] sel) {
		for (int i = 0; i < M; i++) {
			if (sel[i]) {
				switching(onCount.get(i).index); // 켜진 수가 작은 애들 부터 바꿔봄
			}
		} // 맵을 전환함
//		System.out.println("전환된 맵 : ");
//		print();
		int tempresult = count(); // 바꾼 맵에서 개수 셈
		for (int i = 0; i < M; i++) {
			if (sel[i]) {
				switching(onCount.get(i).index);
			}
		} // 맵을 돌려놓음
		return tempresult;
	}

	private static int count() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			boolean con = true;
			for (int j = 0; j < M; j++) {
				if (!maps[i][j]) {
					con = false;
					break;
				}
			}
			if (con)
				result++;
		}
//		System.out.println("센 개수 : " + result);
		return result;
	}

	private static void switching(int line) {
		for (int i = 0; i < N; i++) {
			maps[i][line] = !maps[i][line];
		}
	}

	private static class nums implements Comparable<nums> {
		int index;
		int count;

		public nums(int index, int count) {
			super();
			this.index = index;
			this.count = count;
		}

		@Override
		public int compareTo(nums o) {
			return Integer.compare(this.count, o.count);
		}

		@Override
		public String toString() {
			return "nums [index=" + index + ", count=" + count + "]";
		}

		
	}

}
