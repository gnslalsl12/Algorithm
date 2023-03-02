package LGUPLUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class prob3 {
	static ArrayList<Integer>[] Map;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] GlobalVis;

	public static void main(String[] args) {
		int[][] macaron = { { 1, 1 }, { 2, 1 }, { 1, 2 }, { 3, 3 }, { 6, 4 }, { 3, 1 }, { 3, 3 }, { 3, 3 }, { 3, 4 },
				{ 2, 1 } };
		int[][] macaron1 = { { 1, 1 }, { 1, 2 }, { 1, 4 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 3, 4 }, { 3, 1 }, { 3, 2 },
				{ 3, 3 }, { 3, 4 }, { 4, 4 }, { 4, 3 }, { 5, 4 }, { 6, 1 } };
		int [][] macaron3 = {{1,1},{1,1},{1,1},{1,1},{2,2},{2,3}};
		
		for(String t: solution(macaron3)) {
			System.out.println(t);
		}

	}

	public static String[] solution(int[][] macaron) {
		String[] answer = new String[6];
		Map = new ArrayList[6];
		for (int i = 0; i < 6; i++) {
			Map[i] = new ArrayList<>();
		}
		for (int i = 0; i < 6; i++) {
			for (int j = Map[i].size(); j < 6; j++) {
				Map[i].add(0);
			}
		}
		for (int i = 0; i < macaron.length; i++) {
			int index = macaron[i][0] - 1;
			int color = macaron[i][1];
			for (int j = 0; j < 6; j++) {
				if (Map[index].get(j) == 0) {
					Map[index].set(j, color);
					break;
				}
			}
			solv();
		}
		for (int i = 0; i < 6; i++) {
			answer[5 - i] = "";
			for (int j = 0; j < 6; j++) {
				answer[5 - i] += (char) (Map[j].get(i) + '0');
			}
		}
		return answer;
	}

	public static void solv() {
		while (doSearch()) {
			setStack();
		}
	}

	public static void setStack() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < Map[i].size();) {
				if (Map[i].get(j) == 0) {
					Map[i].remove(j);
				} else {
					j++;
				}
			}
			for (int j = Map[i].size(); j < 6; j++) {
				Map[i].add(0);
			}
		}
	}

	public static boolean doSearch() {
		GlobalVis = new int[6];
		boolean hasmore = false;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if ((GlobalVis[i] & 1 << j) != 0)
					continue;
				if (Map[i].get(j) != 0) {
					if (BFS(i, j, Map[i].get(j)))
						hasmore = true;
				}
			}
		}
		return hasmore;
	}

	public static boolean BFS(int x, int y, int color) {
		Queue<int[]> BFSQ = new LinkedList<>();
		BFSQ.add(new int[] { x, y });
		int[] vis = new int[6];
		vis[x] |= 1 << y;
		Queue<int[]> TempPop = new LinkedList<>();
		TempPop.add(new int[] { x, y });

		while (!BFSQ.isEmpty()) {
			int[] temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp[0] + deltas[dir][0];
				int nexty = temp[1] + deltas[dir][1];
				if (nextx < 0 || nextx >= 6 || nexty < 0 || nexty >= 6 || (vis[nextx] & 1 << nexty) != 0)
					continue;
				vis[nextx] |= 1 << nexty;
				if (Map[nextx].get(nexty) == color) {
					BFSQ.add(new int[] { nextx, nexty });
					TempPop.add(new int[] { nextx, nexty });
				}
			}
		}
		if (TempPop.size() >= 3) { // ≈Õ∂ﬂ∑¡æﬂ«ÿ
			while (!TempPop.isEmpty()) {
				int[] pop = TempPop.poll();
				GlobalVis[pop[0]] |= 1 << pop[1];
				Map[pop[0]].set(pop[1], 0);
			}
			return true;
		} else {
			while (!TempPop.isEmpty()) {
				int[] pop = TempPop.poll();
				GlobalVis[pop[0]] |= 1 << pop[1];
			}
			return false;
		}
	}
}
