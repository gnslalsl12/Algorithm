import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] stairs;
	static int[] dpcount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		stairs = new int[N + 1];
		dpcount = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(read.readLine());
		}
		if (N == 1) {
			System.out.println(stairs[N]);
			return;
		}
		if (N == 2) {
			System.out.println(stairs[1] + stairs[2]);
			return;
		}
		dpcount[1] = stairs[1];
		dpcount[2] = stairs[2] + stairs[1];
		for (int i = 3; i <= N; i++) {
			dpcount[i] = Math.max(dpcount[i - 2], stairs[i - 1] + dpcount[i - 3]) + stairs[i];
		}
		System.out.println(dpcount[N]);
	}
}