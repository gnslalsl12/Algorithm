package BOJ_G4;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1888_∞ı∆Œ¿Ã_Imple {

	static int M, N;
	static int[][] Map;
	static int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		Map = new int[M][N];
		for (int i = 0; i < M; i++) {
			String templine = read.readLine();
			for (int j = 0; j < N; j++) {
				Map[i][j] = templine.charAt(j) - '0';
			}
		}
		
	}

}
