package day0114;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2140 {
	static int N, result;
	static ArrayList<dirXY> Lines = new ArrayList<>();
	static int[][] maps;
	static int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		maps = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < N; j++) {
				if (templine.charAt(j) != '#') {
					maps[i][j] = templine.charAt(j) - '0';
				} else if (i == 1 || i == N - 2 || j == 1 || j == N - 2) {
					dirXY temp = new dirXY(i, j);
					temp.outlines = new ArrayList<>();
					for (int dir = 0; dir < 8; dir++) {
						int nextx = i + deltas[dir][0];
						int nexty = j + deltas[dir][1];
						if (nextx == 0 || nextx == N - 1 || nexty == 0 || nexty == N - 1) {
							temp.outlines.add(new dirXY(nextx, nexty));
						}
					}
					Lines.add(temp);
				} else
					result++;
			}
		}
		while (!Lines.isEmpty()) {
			dirXY pop = Collections.max(Lines);
			getSearch(pop);
			Lines.remove(pop);
		}
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void getSearch(dirXY input) {
		for (dirXY outs : input.outlines) {
			if (maps[outs.x][outs.y] == 0)
				return;
		}
		for (dirXY outs : input.outlines) {
			maps[outs.x][outs.y]--;
		}
		result++;
	}

	private static int getCount(ArrayList<dirXY> input) {
		int tempcount = 0;
		for (dirXY pop : input) {
			tempcount += maps[pop.x][pop.y];
		}
		return tempcount;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		ArrayList<dirXY> outlines;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(dirXY o) {
			int org = getCount(this.outlines);
			int dev = getCount(o.outlines);
			return Integer.compare(dev, org);
		}

	}

}
