package day1119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14927 {
	static int N;
	static int[] maps;
	static Queue<MapWithCount> Q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		N = Integer.parseInt(read.readLine());
		maps = new int[N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				if (tokens.nextToken().equals("1"))
					maps[i] |= 1 << j;
			}
		}
		subsetFirst(0, maps.clone(), 0); // 부분집합으로 첫라인 세팅
		int result = Integer.MAX_VALUE;
		int line = 1;
		while (true) {
			int size = Q.size();
			System.out.println(size);
			for (int p = 0; p < size; p++) {
				MapWithCount pop = Q.poll();
				int[] tempmap = pop.map;
				int tempcount = pop.count;
				for (int j = 0; j < N; j++) {
					if ((tempmap[line - 1] & (1 << j)) != 0) { // 위에가 켜져있어서 해당 위치를 눌러야함
						toggle(line, j, tempmap);
						tempcount++;
					}
				}
				// 해당 라인의 윗라인까지는 모두 꺼짐
				Q.add(new MapWithCount(tempmap, tempcount));
			}
			line += 1;
			if (line == N)
				break;
		}
		while (!Q.isEmpty()) {
			MapWithCount last = Q.poll();
			int[] lastpopmap = last.map;
			for (int j = 0; j < N; j++) {
				if ((lastpopmap[N - 1] & (1 << j)) != 0)
					break;
				if (j == N - 1) {
					result = Math.min(result, last.count);
				}
			}
		}
		if (result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);

	}

	private static void subsetFirst(int j, int[] inputmap, int togcount) {
		System.out.println("j : " + j);
		System.out.println(inputmap[0]);
		if (j == N)
			return;
		int[] beforetog = inputmap.clone();
		Q.add(new MapWithCount(beforetog.clone(), togcount)); // 토글하지 않고 그대로
		subsetFirst(j + 1, beforetog.clone(), togcount);

		toggle(0, j, beforetog);
		Q.add(new MapWithCount(beforetog.clone(), togcount + 1)); // 토글하고
		subsetFirst(j + 1, beforetog, togcount + 1);
	}

	private static void toggle(int i, int j, int[] togmap) {
		togmap[i] ^= 1 << j; // 해당 자리를 토글하기
		if (isIn(i - 1, j))
			togmap[i - 1] ^= 1 << j;
		if (isIn(i, j - 1))
			togmap[i] ^= 1 << (j - 1);
		if (isIn(i, j + 1))
			togmap[i] ^= 1 << (j + 1);
		if (isIn(i + 1, j))
			togmap[i + 1] ^= 1 << j;
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}

	private static class MapWithCount {
		int[] map;
		int count;

		public MapWithCount(int[] map, int count) {
			this.map = map;
			this.count = count;
		}

	}
}
