package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18870 {
	static int N;
	static PriorityQueue<Long> nums = new PriorityQueue<>();
	static Map<Long, Integer> countdict = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		ArrayList<Long> input = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			long temp = Long.parseLong(tokens.nextToken());
			input.add(temp);
			nums.add(temp);
			countdict.put(temp, 0);
		}
		// 작은 거부터 nums에 넣고, 중복은 뺼 거고 countdict에 작은 개수마다 추가해줌
		long min = Long.MIN_VALUE;
		int tempcount = -1;
		while (!nums.isEmpty()) {
			long temp = nums.poll();
			if (min == temp)
				continue;
			min = temp;
			tempcount++;
			countdict.put(temp, tempcount);
		}
		for (int i = 0; i < N; i++) {
			sb.append(countdict.get(input.get(i)));
			if (i != N - 1) {
				sb.append(" ");
			}
		}
		System.out.print(sb);

	}

}
