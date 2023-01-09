package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_6064 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			int M = Integer.parseInt(tokens.nextToken());
			int N = Integer.parseInt(tokens.nextToken());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			Map<Integer, boolean[]> check = new HashMap<>();
			int xt = 1;
			int yt = 1;
			int count = 1;
			while (true) {
				if(x == xt && y == yt) break;
				if (!check.containsKey(xt)) {
					boolean[] tempcheck = new boolean[N + 1];
					tempcheck[yt] = true;
					check.put(xt, tempcheck);
				} else {
					if (check.get(xt)[yt]) {
						count = -1;
						break;
					} else {
						check.get(xt)[yt] = true;
					}
				}
				xt++;
				yt++;
				count++;
				if (xt == M+1)
					xt = 1;
				if (yt == N+1)
					yt = 1;
			}
			write.write(count + "\n");
		}
		write.close();
	}
}