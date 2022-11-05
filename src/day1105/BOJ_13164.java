package day1105;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13164 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Integer> diff = new PriorityQueue<>(Collections.reverseOrder());
		tokens = new StringTokenizer(read.readLine());
		int result = 0;
		int temp = Integer.parseInt(tokens.nextToken());
		for (int i = 1; i < N; i++) {
			int next = Integer.parseInt(tokens.nextToken());
			diff.offer(next - temp);
			result += next-temp;
			temp = next;
		}
		for (int i = 0; i < K-1; i++) {
			result -= diff.poll();
		}
		write.write(result + "\n");
		read.close();
		write.close();
	}
}