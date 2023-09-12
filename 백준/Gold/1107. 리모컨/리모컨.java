import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int dest = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		boolean[] button = new boolean[10];
		Arrays.fill(button, true);
		if (M != 0) {
			tokens = new StringTokenizer(read.readLine());
			for (int i = 0; i < M; i++) {
				int temp = Integer.parseInt(tokens.nextToken());
				button[temp] = false;
			}
		}
		if (dest == 100) {
			System.out.println(Math.abs(dest - 100));
			return;
		}
		if (M == 0) {
			int len = Integer.toString(dest).length();
			int vis = Math.abs(dest-100);
			System.out.println(Math.min(len, vis));
			return;
		}
		int vis = Math.abs(dest - 100);
		int count = 0;
		int pop = 0;
		int result = 0;
		boolean foundp = false;
		boolean foundm = false;
		while (true) {
			if (count >= vis) {
				System.out.println(vis);
				return;
			}
			int plusone = dest + count;
			int minusone = dest - count;
			if (plusone > 5000000 && minusone < 0) {
				pop = 100;
				break;
			}
			String pst = Integer.toString(plusone);
			String mst = Integer.toString(minusone);
			if (plusone <= 5000000) {
				for (int i = 0; i < pst.length(); i++) {
					char c = pst.charAt(i);
					if (!button[c - '0'])
						break;
					if (i == pst.length() - 1) {
						pop = plusone;
						result = pst.length();
						foundp = true;
						break;
					}
				}
			}
			if (minusone >= 0) {
				for (int i = 0; i < mst.length(); i++) {
					char c = mst.charAt(i);
					if (!button[c - '0'])
						break;
					if (i == mst.length() - 1) {
						pop = minusone;
						result = mst.length();
						foundm = true;
						break;
					}
				}
			}
			if (foundp || foundm)
				break;
			count++;
		}
		result += Math.abs(dest - pop);
		result = Math.min(result, vis);
		System.out.println(result);
	}
}