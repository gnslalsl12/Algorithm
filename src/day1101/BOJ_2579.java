package day1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579 {
	static int N;
	static int[] stairs;
	static int[] DPresult;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String readLine = read.readLine();
		N = Integer.parseInt(readLine);
		stairs = new int[N];
		DPresult = new int[N];
		for (int i = 0; i < N; i++) {
			stairs[i] = Integer.parseInt(read.readLine());
		}
		// inputing
		DPresult[0] = stairs[0];
		upstair(0, 0);
		System.out.println(DPresult[N - 1]);
	}

	private static void upstair(int index, int count) {
		if (count < 3 && index < N-1) {
			DPresult[index + 1] = Math.max(DPresult[index + 1], DPresult[index] + stairs[index + 1]);
			upstair(index + 1, count + 1);
		}
		if(index < N-2) {
			DPresult[index + 2] = Math.max(DPresult[index + 2], DPresult[index] + stairs[index + 2]);
			upstair(index + 2, 1);
		}
		System.out.println(Arrays.toString(DPresult));
		
	}
}
