package day1107;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_11286 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (temp == 0) {
				if (pq.isEmpty()) {
					write.write("0\n");
				} else {
					write.write(pq.poll() + "\n");
				}
			} else {
				pq.add(temp);
			}
		}
		write.close();
	}

}
