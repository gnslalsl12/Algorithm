package day1219;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PROG_기둥과보설치 {

	public static void main(String[] args) {
		int n = 5;
		int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
				{ 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		int[][] build_frame_r = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
				{ 5, 1, 0, 1 }, { 3, 2, 1, 1 }, { 4, 2, 1, 1 } };
		int[][] build_frame_2 = { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } };
		int[][] build_frame_2_re = { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 }, { 1, 1, 0, 1 },
				{ 1, 1, 0, 0 } };
		int[][] get = solution(n, build_frame_2);
		for (int i = 0; i < get.length; i++) {
			System.out.println(Arrays.toString(get[i]));
		}
	}

	static inmap[][] maps;
	static int nsize;

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		nsize = n;
		maps = new inmap[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				maps[i][j] = new inmap();
			}
		}
		ArrayList<settings> Slist = new ArrayList<>();
		for (int i = 0; i < build_frame.length; i++) {
			int[] pop = build_frame[i];
			int x = pop[1];
			int y = pop[0];
			int type = pop[2];
			int set = pop[3];
			if (set == 1) { // 설치
				if (type == 0) { // 기둥
					if (checkPi(x, y)) {
						maps[x][y].st = true;
						Slist.add(new settings(x, y, type));
						continue;
					}
				} else { // 보
					if (checkVo(x, y)) {
						maps[x][y].vo = true;
						Slist.add(new settings(x, y, type));
						continue;
					}
				}
			} else {
				if (type == 0)
					maps[x][y].st = false;
				else
					maps[x][y].vo = false;
				boolean ok = true;
				boolean found = false;
				int removenum = 0;
				for (int num = 0; num < Slist.size(); num++) {
					settings st = Slist.get(num);
					if (st.type == type && st.x == x && st.y == y) {
						removenum = num;
						found = true;
						continue;
					}
					if (st.type == 0) { // 기둥
						if (checkPi(st.x, st.y)) {
							continue;
						} else {
							ok = false;
							if (found)
								break;
						}
					} else { // 보
						if (checkVo(st.x, st.y)) {
							continue;
						} else {
							ok = false;
							if (found)
								break;
						}
					}
				}
				if (ok) {
					Slist.remove(removenum);
				} else {
					if (type == 0)
						maps[x][y].st = true;
					else
						maps[x][y].vo = true;
				}
			}
		}
		Collections.sort(Slist);
		answer = new int[Slist.size()][3];
		for (int i = 0; i < Slist.size(); i++) {
			answer[i][0] = Slist.get(i).y;
			answer[i][1] = Slist.get(i).x;
			answer[i][2] = Slist.get(i).type;
		}
		return answer;
	}

	public static boolean checkPi(int x, int y) {
		return x == 0 || maps[x - 1][y].st || maps[x][y].vo || (y != 0 && maps[x][y - 1].vo);
	}

	public static boolean checkVo(int x, int y) {
		return maps[x - 1][y].st || maps[x - 1][y + 1].st
				|| ((y != 0 && y != nsize) && (maps[x][y - 1].vo && maps[x][y + 1].vo));
	}

	public static class inmap {
		boolean st;
		boolean vo;

		public inmap() {
			this.st = false;
			this.vo = false;
		}
	}

	public static class settings implements Comparable<settings> {
		int x;
		int y;
		int type;

		public settings(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public int compareTo(settings o) {
			if (this.y == o.y) {
				if (this.x == o.x) {
					return Integer.compare(this.type, o.type);
				}
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.y, o.y);
		}

	}

}
