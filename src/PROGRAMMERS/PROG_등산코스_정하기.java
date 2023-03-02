package PROGRAMMERS;

import java.util.Arrays;
import java.util.TreeSet;

public class PROG_등산코스_정하기 {
	static int N, Gates[], Summits[];
	static int TotalMinDist;
	static int[][] Map;
	static TreeSet<Integer> Starting, Ending;
	static boolean[] Vis;
	static int[] Dists;
	static int[] result;

	public static void main(String[] args) {
		int n = 6;
		int[][] paths = { { 1, 2, 3 }, { 2, 3, 5 }, { 2, 4, 2 }, { 2, 5, 4 }, { 3, 4, 4, }, { 4, 5, 3 }, { 4, 6, 1 },
				{ 5, 6, 1 } };
		int[] gates = { 1, 3 };
		int[] summits = { 5 };

		int n2 = 7;
		int[][] paths2 = { { 1, 4, 4 }, { 1, 6, 1 }, { 1, 7, 3 }, { 2, 5, 2 }, { 3, 7, 4 }, { 5, 6, 6 } };
		int[] gates2 = { 1 };
		int[] summits2 = { 2, 3, 4 };

		int n3 = 7;
		int[][] paths3 = { { 1, 2, 5 }, { 1, 4, 1 }, { 2, 3, 1 }, { 2, 6, 7 }, { 4, 5, 1 }, { 5, 6, 1 }, { 6, 7, 1 } };
		int[] gates3 = { 3, 7 };
		int[] summits3 = { 1, 5 };
		System.out.println(Arrays.toString(solution(n, paths, gates, summits)));

		System.out.println(Arrays.toString(solution(n2, paths2, gates2, summits2)));
		System.out.println(Arrays.toString(solution(n3, paths3, gates3, summits3)));

		int n4 = 5;
		int[][] paths4 = { { 1, 3, 10 }, { 1, 4, 20 }, { 2, 3, 4 }, { 2, 4, 6 }, { 3, 5, 20 }, { 4, 5, 6 } };
		int[] gates4 = { 1, 2 };
		int[] summits4 = { 5 };
		System.out.println(Arrays.toString(solution(n4, paths4, gates4, summits4)));
	}

	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		result = new int[] { 0, Integer.MAX_VALUE };
		N = n;
		Gates = gates;
		Summits = summits;
		Starting = new TreeSet<>();
		Ending = new TreeSet<>();
		for (int g : gates) {
			Starting.add(g);
		}
		for (int s : summits) {
			Ending.add(s);
		}
		Map = new int[N + 1][N + 1];
		for (int[] p : paths) {
			Map[p[0]][p[1]] = Map[p[1]][p[0]] = p[2];
		}
		Vis = new boolean[N + 1];
		Dists = new int[N + 1];
		Arrays.fill(Dists, Integer.MAX_VALUE);
		solv();
		return result;

	}

	private static void solv() {
		getDistLowToHigh();
	}

	private static void getDistLowToHigh() {
		for (int from : Gates) {
			for (int to : Summits) {
				TotalMinDist = Integer.MAX_VALUE;
				Vis[from] = true;
				setDists(from, to, -1);
				Vis[from] = false;
				if (result[1] >= TotalMinDist) {
					if (result[1] == TotalMinDist) {
						result[0] = Math.min(result[0], to);
					} else {
						result[1] = TotalMinDist;
						result[0] = to;
					}
				}
			}
		}
	}

	private static void setDists(int from, int to, int tempmaxdist) {
		if (result[1] < tempmaxdist)
			return;
		for (int next = 1; next <= N; next++) {
			if (Map[from][next] != 0) { // 연결돼있다면
				if (Dists[next] < tempmaxdist || Starting.contains(next) || Vis[next]
						|| (next != to && Ending.contains(next))) {
					continue; // 출발점 // 이미 지나온 곳 //목적지가 아닌 산봉우리
				} else if (next == to) {
					TotalMinDist = Math.min(Math.max(tempmaxdist, Map[from][next]), TotalMinDist);
				} else {
					if (Dists[next] < tempmaxdist)
						return;
					Dists[next] = Math.min(Dists[next], tempmaxdist); // next까지의 최대비용 중 최소비
					Vis[next] = true;
					setDists(next, to, Math.max(Map[from][next], tempmaxdist));
					Vis[next] = false;
				}
			}
		}
	}

}
