package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2108 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		double result1 = 0;
		ArrayList<Integer> result2 = new ArrayList<>();
		int[] check = new int[4001];
		int[] Mcheck = new int[40001];
		int max = -1;
		ArrayList<Integer> frames = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (temp < 0) {
				Mcheck[temp * -1]++;
				if (max < Mcheck[temp * -1]) {
					frames.clear();
					max = Mcheck[temp * -1];
					frames.add(temp);
				} else if (max == Mcheck[temp * -1]) {
					frames.add(temp);
				}
			} else {
				check[temp]++;
				if (max < check[temp]) {
					frames.clear();
					max = check[temp];
					frames.add(temp);
				} else if (max == check[temp]) {
					frames.add(temp);
				}
			}
			result2.add(temp);
			result1 += temp;
		}
		write.write(Math.round(result1 / N) + "\n");
		Collections.sort(result2);
		write.write(result2.get((N - 1) / 2) + "\n");
		if (frames.size() == 1) {
			write.write(frames.get(0) + "\n");
		} else {
			Collections.sort(frames);
			write.write(frames.get(1) + "\n");
		}
		write.write(result2.get(N - 1) - result2.get(0) + "\n");
		write.close();
	}
}