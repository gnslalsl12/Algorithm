package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2920 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int start = Integer.parseInt(tokens.nextToken());
		int next = Integer.parseInt(tokens.nextToken());
		boolean asc = true;
		if (start > next)
			asc = false;
		for (int i = 2; i < 8; i++) {
			int temp = Integer.parseInt(tokens.nextToken());
			if (next < temp) {
				if (asc) {
					if (i == 7) {
						write.write("ascending\n");
						break;
					}
					next = temp;
					continue;
				} else {
					write.write("mixed\n");
					break;
				}
			} else {
				if (!asc) {
					if (i == 7) {
						write.write("descending\n");
						break;
					}
					next = temp;
					continue;
				} else {
					write.write("mixed\n");
					break;
				}
			}
		}
		write.close();
	}
}