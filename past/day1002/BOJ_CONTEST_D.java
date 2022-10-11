package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_CONTEST_D {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		String[] templine = read.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		long[] way = new long[N+1];
		way[N-1] = 1;
		if(N == 1) {
			System.out.print(1);
			return;
		}
		sb.append("1 ");
		for(int i = N-2; i >= 0; i--) {
			int temp = Integer.parseInt(templine[i]);
			if(temp == 0) {
				way[i] = way[i+1] + 1;
				sb.append(way[i]);
				if(i != 0) sb.append(" ");
				continue;
			}
			if(temp + i + 1>= N) {
				way[i] = 1;
				sb.append(way[i]);
				if(i != 0) sb.append(" ");
				continue;
			}
			way[i] = way[i + temp + 1] + 1;
			sb.append(way[i]);
			if(i != 0) sb.append(" ");
		}
		System.out.print(sb.reverse().toString());
	}
}