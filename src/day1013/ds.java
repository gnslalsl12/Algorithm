import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3238 {
	public static void main(String args[]) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(read.readLine());
		for (int t = 1; t <= T; t++) {
			long[] fac = new long[(int) Math.pow(10, 5) + 1];
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			long N = Long.parseLong(tokens.nextToken());
			long R = Long.parseLong(tokens.nextToken());
			long P = Long.parseLong(tokens.nextToken());
			fac[0] = 1;
			for (int i = 1; i <= P; i++) {
				fac[i] = (i * fac[i - 1]) % P;
			}
			long ans = 1;
			while ((R | N) != 0) {
				long x = N % P;
				long y = R % P;
				if (y > x) {
					ans = 0;
					break;
				}
				ans = (ans * fac[(int) x]) % P;
				for (int i = 0; i < P - 2; i++) {
					ans = (ans * fac[(int) (x - y)] * fac[(int) y]) % P;
				}
				N /= P;
				R /= P;
			}
			ans %= P;
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.print(sb);
	}
}
