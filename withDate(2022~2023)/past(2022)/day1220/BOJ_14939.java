package day1220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class BOJ_14939 {
	private static int[] maps = new int[10];
	private static ArrayList<dirXY> buttons = new ArrayList<>();
	private static TreeSet<Integer> TS = new TreeSet<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int buttonsize;
	static int result;
	
	//맵에서 O마다 눌렀을 떄 자기가 꺼지는 버튼 위치에 +1 해주고 큰 순서대로?

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		result = -1;
		buttonsize = 0;
		for (int i = 0; i < 10; i++) {
			String templine = read.readLine();
			for (int j = 0; j < 10; j++) {
				if (templine.charAt(j) == 'O') {
					maps[i] |= 1 << j;
					buttons.add(new dirXY(i, j));
					for (int dir = 0; dir < 4; dir++) {
						int nextx = i + deltas[dir][0];
						int nexty = j + deltas[dir][1];
						if (!isIn(nextx, nexty))
							continue;
						if (TS.contains(i * 10 + j))
							continue;
						TS.add(i * 10 + j);
						buttons.add(new dirXY(nextx, nexty));
					}
				}
			}
		}
		buttonsize = buttons.size();
		subset(0, new boolean[buttonsize]);
		System.out.println(result);
	}

	private static void subset(int count, boolean[] sel) {
		if (result != -1)
			return;
		if (count == buttonsize) {
			check(sel);
			return;
		}
		sel[count] = false;
		subset(count + 1, sel);
		sel[count] = true;
		subset(count + 1, sel);
	}

	private static void check(boolean[] sel) {
		result = 0;
		Queue<dirXY> returnlist = new LinkedList<>();
		for (int i = 0; i < buttonsize; i++) {
			if (sel[i]) {
				result++;
				returnlist.add(buttons.get(i));
				pushit(buttons.get(i));
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((maps[i] & 1 << j) != 0) {
					result = -1;
					while (!returnlist.isEmpty()) {
						pushit(returnlist.poll());
					}
					return;
				}
			}
		}
	}

	private static void pushit(dirXY temp) {
		maps[temp.x] ^= 1 << temp.y;
		for (int dir = 0; dir < 4; dir++) {
			int nextx = temp.x + deltas[dir][0];
			int nexty = temp.y + deltas[dir][1];
			if (!isIn(nextx, nexty))
				continue;
			maps[nextx] ^= 1 << nexty;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 10 && y < 10;
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
