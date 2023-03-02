package day1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17404 {
	static int N;
	static int[][] color;
	static int[][] cost;
	static int MAX = 1000 * 1000 + 100;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		color = new int[N][3];
		cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < 3; j++) {
				color[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		int result = MAX;
		for (int startcolor = 0; startcolor < 3; startcolor++) {
			// 첫집의 시작 색(red / green / blue) 외에 다른 첫집 비용은 무시하기 위해 MAX처리
			for (int i = 0; i < 3; i++) {
				if (i == startcolor)
					cost[0][i] = color[0][i];
				else
					cost[0][i] = MAX;
			}
			for (int i = 1; i < N; i++) {
				cost[i][0] = color[i][0] + Math.min(cost[i - 1][1], cost[i - 1][2]);
				cost[i][1] = color[i][1] + Math.min(cost[i - 1][0], cost[i - 1][2]);
				cost[i][2] = color[i][2] + Math.min(cost[i - 1][1], cost[i - 1][0]);
			}
			for (int last = 0; last < 3; last++) {
				if (last == startcolor)
					continue;
				result = Math.min(result, cost[N - 1][last]);
			}
		}
		write.write(result + "\n");
		read.close();
		write.close();

	}

}
