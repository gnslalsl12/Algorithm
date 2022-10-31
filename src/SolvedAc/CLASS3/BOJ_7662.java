package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(read.readLine());
			StringTokenizer tokens;
			TreeMap<Integer, Integer> tm = new TreeMap<>();
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				char order = tokens.nextToken().charAt(0);
				int num = Integer.parseInt(tokens.nextToken());
				if (order == 'I') {
					tm.put(num, tm.getOrDefault(num, 0) + 1);
				} else {
					if (tm.isEmpty())
						continue;
					if (num == -1) {
						if (tm.get(tm.firstKey()) == 1) {
							tm.remove(tm.firstKey());
						} else {
							tm.put(tm.firstKey(), tm.get(tm.firstKey()) - 1);
						}
					} else {
						if (tm.get(tm.lastKey()) == 1) {
							tm.remove(tm.lastKey());
						} else {
							tm.put(tm.lastKey(), tm.get(tm.lastKey()) - 1);
						}
					}
				}
			}
			if (tm.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(String.format("%d %d\n", tm.lastKey(), tm.firstKey()));
			}
		}
		System.out.print(sb);
	}
}