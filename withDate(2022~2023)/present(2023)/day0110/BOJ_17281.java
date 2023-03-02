package day0110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17281 {
	static int N, result;
	static int[][] hits;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		hits = new int[N][10];
		result = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= 9; j++) {
				hits[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		getPerm(0, new int[9], 0);
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void getPerm(int count, int[] sel, int vis) {
		if (count == 9) {
			getGame(sel);
			return;
		}
		if (count == 3) {
			sel[count] = 1;
			getPerm(count + 1, sel, vis | 1 << 1);
			return;
		}
		for (int i = 2; i <= 9; i++) {
			if ((vis & 1 << i) != 0)
				continue;
			sel[count] = i;
			getPerm(count + 1, sel, vis | 1 << i);
		}

	}

	private static void getGame(int[] orders) {
		int order = 0;
		int out;
		int score = 0;
		boolean[] field;
		for (int g = 0; g < N; g++) {
			out = 0;
			field = new boolean[3];
			while (out < 3) {
				int man = orders[order];
				order = (order + 1) % 9;
				if (hits[g][man] == 0) {
					out++;
				} else {
					for (int h = 0; h < hits[g][man]; h++) {
						if (field[2])
							score++;
						field[2] = field[1];
						field[1] = field[0];
						if (h == 0)
							field[0] = true;
						else
							field[0] = false;
					}
				}
			}
		}
		result = Math.max(score, result);
	}

}
