package day1019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1654 {
	static int N, K;
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		K = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < K; i++) {
			list.add(Integer.parseInt(read.readLine()));
		}
		Collections.sort(list);
		long set = 1;
		while(true) {
			long count = 0;
			for(int i = 0; i < K; i++) {
				count += list.get(i)/set;
			}
			if(count < N) break;
			set++;
		}
		write.write((set-1) + "\n");
		write.close();
	}
}