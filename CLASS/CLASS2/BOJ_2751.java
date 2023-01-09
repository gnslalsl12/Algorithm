package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2751 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder write = new StringBuilder();
		int N = Integer.parseInt(read.readLine());
		PriorityQueue<Integer> result = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			result.offer(Integer.parseInt(read.readLine()));
		}
		for (int i = 0; i < N; i++) {
			write.append(result.poll() + "\n");
		}
		System.out.print(write);

	}
}
