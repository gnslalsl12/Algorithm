package day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471 {
	static int N;
	static int people[];
	static boolean maps[][];
	static int MIN;
	static int l1;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 1][N + 2];
		people = new int[N + 1];
		tokens = new StringTokenizer(read.readLine());
		for (int n = 1; n <= N; n++) { // N개의 노드
			people[n] = Integer.parseInt(tokens.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int c = Integer.parseInt(tokens.nextToken());
			for (int j = 0; j < c; j++) {
				int t = Integer.parseInt(tokens.nextToken());
				maps[i][t] = true;
				maps[t][i] = true;

			}
		}
		MIN = Integer.MAX_VALUE;
		getP(0, new boolean[N + 1]);
		if (MIN == Integer.MAX_VALUE)
			MIN = -1;
		System.out.println(MIN);
	}

	private static void getP(int count, boolean sel[]) {
		if (count == N) {
			if (Check(sel)) { // 1부터 들어있는 sel
				getPep(sel);
			}
			return;
		}
		sel[count + 1] = true;
		getP(count + 1, sel);
		sel[count + 1] = false;
		getP(count + 1, sel);
	}

	private static boolean Check(boolean sel[]) { // 연결돼있나?
		l1 = 0;
		int[][] found = new int[2][N];
		int selcount1 = 0;
		int selcount2 = 0;
		for (int i = 1; i <= N; i++) {
			if (sel[i]) { // true면 1구역 false면 2구역
				l1++;
				found[0][selcount1++] = i;
			} else {
				found[1][selcount2++] = i;
			}
		}
		if (found[0][0] == 0 || found[1][0] == 0)
			return false; // 하나 이상 포함해야함
		if (checkCon(found[0], 1) && checkCon(found[1], 2))
			return true;
		else
			return false;
	}

	private static void getPep(boolean[] sel) {
		int sum = 0;
		for (int i = 1; i < N + 1; i++) {
			if (sel[i])
				sum += people[i];
			else
				sum -= people[i];
		}
		sum = Math.abs(sum);
		MIN = Math.min(sum, MIN);
	}

	private static boolean checkCon(int[] line, int num) {
		Queue<Integer> BFSQ = new LinkedList<>();
		boolean[] visits =new boolean [line.length];
		visits[0] = true;
		BFSQ.add(line[0]);
		int count = 0;
		while(!BFSQ.isEmpty()) {
			count++;
			int from = BFSQ.poll();
			for(int i = 0; i < line.length; i++) {
				if(line[i] == 0) break;;
				if(from == line[i]) continue;
				if(visits[i]) continue;
				if(maps[from][line[i]]) {
					BFSQ.add(line[i]);
					visits[i] = true;
				}
			}
		}
		if(num == 1 && count == l1) {
			return true;
		}else if(num == 2 && count == N-l1) {
			return true;
		}
		return false;
	}
}
