package B1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13552_备客_目府 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int[] x = new int[100001];
		int[] y = new int[100001];
		int[] z = new int[100001];
		int N = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			x[i] = Integer.parseInt(tokens.nextToken());
			y[i] = Integer.parseInt(tokens.nextToken());
			z[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			tokens = new StringTokenizer(read.readLine());
			int Qx = Integer.parseInt(tokens.nextToken());
			int Qy = Integer.parseInt(tokens.nextToken());
			int Qz = Integer.parseInt(tokens.nextToken());
			int R = Integer.parseInt(tokens.nextToken());
			R *= R;
			int count = 0;
			for (int i = 0; i < N; i++) {
				int dx = x[i] - Qx;
				int dy = y[i] - Qy;
				int dz = z[i] - Qz;
				if (dx * dx + dy * dy + dz * dz <= R)
					count++;
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}

}
