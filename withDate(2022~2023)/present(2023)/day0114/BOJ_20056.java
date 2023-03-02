package day0114;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_20056 {
	static int N, M, K;
	static Queue<Fball> FireBalls = new LinkedList<>();
	static TreeMap<Integer, Finfo> Maps;
	static int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int[][] Dset = { { 0, 2, 4, 6 }, { 1, 3, 5, 7 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken()) - 1;
			int c = Integer.parseInt(tokens.nextToken()) - 1;
			int m = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			FireBalls.add(new Fball(r, c, m, s, d));
		}
		for (int i = 0; i < K; i++) {
			Maps = new TreeMap<>();
			shotFball();
		}
		int result = 0;
		while (!FireBalls.isEmpty())
			result += FireBalls.poll().m;
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void shotFball() {
		int size = FireBalls.size();
		Queue<Integer> Dup = new LinkedList<>();
		while (size-- > 0) { // 일단 Que에 있는 거 하나씩 발싸
			Fball temp = FireBalls.poll();
			temp.r = (temp.r + deltas[temp.d][0] * temp.s) % N;
			temp.c = (temp.c + deltas[temp.d][1] * temp.s) % N;
			if (temp.r < 0)
				temp.r += N;
			if (temp.c < 0)
				temp.c += N;
			int loc = temp.r * N + temp.c; // 이동한 위치를 int로 변환
			if (Maps.containsKey(loc)) { // 해당 위치에 이미 FB이 있음
				Maps.get(loc).m += temp.m;
				Maps.get(loc).s += temp.s;
				Maps.get(loc).count++;
				int dir = Maps.get(loc).dir;
				if (dir != -1 && temp.d % 2 != dir % 2) // 이미 있는 FB와 방향 타입이 다르면 -1로 저장
					Maps.get(loc).dir = -1;
			} else { // 내가 첨이야
				Maps.put(loc, new Finfo(temp.m, temp.s, 1, temp.d));
				Dup.add(loc);
			}
		}
		for (int loc : Maps.keySet()) {	//FB이 존재하는 위치 하나씩 보면서
			Finfo temp = Maps.get(loc);
			if (temp.count == 1) {	//혼자 왔니
				FireBalls.add(new Fball(loc / N, loc % N, temp.m, temp.s, temp.dir));
			} else {	//싱글 아니야
				int m = temp.m / 5;
				if (m == 0)
					continue;
				int s = temp.s / temp.count;
				if (temp.dir != -1) {
					for (int d : Dset[0]) {
						FireBalls.add(new Fball(loc / N, loc % N, m, s, d));
					}
				} else {
					for (int d : Dset[1]) {
						FireBalls.add(new Fball(loc / N, loc % N, m, s, d));
					}
				}
			}
		}
	}

	private static class Finfo {
		int m;
		int s;
		int count;
		int dir;

		public Finfo(int m, int s, int count, int dir) {
			this.m = m;
			this.s = s;
			this.count = count;
			this.dir = dir;
		}

	}

	private static class Fball {
		int r;
		int c;
		int m;
		int s;
		int d;

		public Fball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}

}
