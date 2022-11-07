package day1107;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9375 {
	static long [] factorial = new long [32];
	public static void main(String[] args) throws NumberFormatException, IOException {
		factorial[1] = 1;
		fact(20);
		System.out.println(Arrays.toString(factorial));
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			Map<String, Integer> closet = new HashMap<>();
			int N = Integer.parseInt(read.readLine());
			StringTokenizer tokens;
			Queue<String> q = new LinkedList<String>();
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				tokens.nextToken();
				String kinds = tokens.nextToken();
				if (closet.containsKey(kinds)) {
					closet.replace(kinds, closet.get(kinds) + 1);
				} else {
					closet.put(kinds, 1);
					q.add(kinds);
				}
			}
			Queue<Integer> qq = new LinkedList<>();
			while (!q.isEmpty()) {
				String temp = q.poll();
				qq.add(closet.get(temp));
			}
			int tempsum = 0;
			long tempfact = 1;
			while(!qq.isEmpty()) {
				int tempnum = qq.poll();
				tempsum += tempnum+1;
				tempfact *= fact(tempnum+1);
			}
			long result = (fact(tempsum)/tempfact)-1;
			
			write.write(result + "\n");
		}
		write.close();
	}
	
	private static long fact(int num) {
		if(factorial[num] != 0) {
			return factorial[num];
		}
		return factorial[num] = factorial[num-1] * num;
	}
}
