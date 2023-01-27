import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17114 {
	static int m, n, o, p, q, r, s, t, u, v, w;
	static int[][] deltas = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 } };
	static int[][][][][][][][][][][] Maps;
	static int UnRiped = 0;
	static Queue<int[]> RipedTomatos = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		m = Integer.parseInt(tokens.nextToken());
		n = Integer.parseInt(tokens.nextToken());
		o = Integer.parseInt(tokens.nextToken());
		p = Integer.parseInt(tokens.nextToken());
		q = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		s = Integer.parseInt(tokens.nextToken());
		t = Integer.parseInt(tokens.nextToken());
		u = Integer.parseInt(tokens.nextToken());
		v = Integer.parseInt(tokens.nextToken());
		w = Integer.parseInt(tokens.nextToken());
		Maps = new int[w][v][u][t][s][r][q][p][o][n][m];
		for (int i11 = 0; i11 < w; i11++) {
			for (int i10 = 0; i10 < v; i10++) {
				for (int i9 = 0; i9 < u; i9++) {
					for (int i8 = 0; i8 < t; i8++) {
						for (int i7 = 0; i7 < s; i7++) {
							for (int i6 = 0; i6 < r; i6++) {
								for (int i5 = 0; i5 < q; i5++) {
									for (int i4 = 0; i4 < p; i4++) {
										for (int i3 = 0; i3 < o; i3++) {
											for (int i2 = 0; i2 < n; i2++) {
												tokens = new StringTokenizer(read.readLine());
												for (int i1 = 0; i1 < m; i1++) {
													int temp = Integer.parseInt(tokens.nextToken());
													Maps[i11][i10][i9][i8][i7][i6][i5][i4][i3][i2][i1] = temp;
													if (temp == 0)
														UnRiped++;
													else if (temp == 1)
														RipedTomatos.add(new int[] { i11, i10, i9, i8, i7, i6, i5, i4,
																i3, i2, i1 });
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (UnRiped == 0)
			write.write("0\n");
		else
			write.write(solv() + "\n");
		write.close();
		read.close();
	}

	private static int solv() {
		int day = 0;
		while (!RipedTomatos.isEmpty()) {
			doSpread();
			day++;
			if (UnRiped == 0)
				return day;
		}
		return -1;
	}

	private static void doSpread() {
		int size = RipedTomatos.size();
		for (int s = 0; s < size; s++) {
			int[] RipedOne = RipedTomatos.poll();
			for (int dir = 0; dir < 22; dir++) {
				int i1 = RipedOne[10] + deltas[dir][10];
				int i2 = RipedOne[9] + deltas[dir][9];
				int i3 = RipedOne[8] + deltas[dir][8];
				int i4 = RipedOne[7] + deltas[dir][7];
				int i5 = RipedOne[6] + deltas[dir][6];
				int i6 = RipedOne[5] + deltas[dir][5];
				int i7 = RipedOne[4] + deltas[dir][4];
				int i8 = RipedOne[3] + deltas[dir][3];
				int i9 = RipedOne[2] + deltas[dir][2];
				int i10 = RipedOne[1] + deltas[dir][1];
				int i11 = RipedOne[0] + deltas[dir][0];
				if (!isIn(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11))
					continue;
				int temp = Maps[i11][i10][i9][i8][i7][i6][i5][i4][i3][i2][i1];
				if (temp == -1 || temp == 1)
					continue;
				UnRiped--;
				if (UnRiped == 0)
					return;
				Maps[i11][i10][i9][i8][i7][i6][i5][i4][i3][i2][i1] = 1;
				RipedTomatos.add(new int[] { i11, i10, i9, i8, i7, i6, i5, i4, i3, i2, i1 });
			}
		}
	}

	private static boolean isIn(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10,
			int i11) {
		return i1 >= 0 && i2 >= 0 && i3 >= 0 && i4 >= 0 && i5 >= 0 && i6 >= 0 && i7 >= 0 && i8 >= 0 && i9 >= 0
				&& i10 >= 0 && i11 >= 0 && i1 < m && i2 < n && i3 < o && i4 < p && i5 < q && i6 < r && i7 < s && i8 < t
				&& i9 < u && i10 < v && i11 < w;
	}

}
