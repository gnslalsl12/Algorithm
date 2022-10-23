package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1654 {
	static int K, N;
	static ArrayList<Long> Lines = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		K = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		long max = -1;
		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(read.readLine());
			long temp = Integer.parseInt(tokens.nextToken());
			Lines.add(temp);
			max = Math.max(max, temp);
		}
		long min = 1;
		long templen = (min + max) / 2;
		while (true) {
			long count = 0;
			for (Long pop : Lines) {
				count += pop / templen;
			}
			if (max - min <= 1)
				break;
			if (count >= N) { // 더 많이 만들 수 있을 떄 : 길이를 늘려야함
				min = templen;
			} else if (count < N) { // 더 적게 만들어질 때 : 길이를 줄여야함
				max = templen;
			}
			templen = (min + max) / 2;
		}
		long count = 0;
		for (Long pop : Lines) {
			count += pop / max;
		}
		if (count == N) {
			write.write(max + "\n");
		} else {
			write.write(templen + "\n");
		}
		write.close();
	}
}