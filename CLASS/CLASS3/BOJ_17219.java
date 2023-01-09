package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		Map<String, String> temp = new HashMap<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			temp.put(tokens.nextToken(), tokens.nextToken());
		}
		for (int j = 0; j < M; j++) {
			tokens = new StringTokenizer(read.readLine());
			write.write(temp.get(tokens.nextToken()) + "\n");
		}
		write.close();
	}
}