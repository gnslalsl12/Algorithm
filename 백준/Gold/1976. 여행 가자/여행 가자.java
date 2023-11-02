import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		N = readInt();
		M = readInt();
		maps = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (readInt() == 1) {
					maps[i][j] = true;
					maps[j][i] = true;
				}
			}
		}
		FLYD();
		boolean answer = true;
		int start = readInt() - 1;
		int next;
		for (int i = 1; i < M; i++) {
			next = readInt() - 1;
			if (!maps[start][next]) {
				answer = false;
				break;
			}
			start = next;
		}
		if (answer)
			write.write("YES\n");
		else
			write.write("NO\n");
		write.close();
	}
    
    private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void FLYD() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (i == j || maps[i][k] && maps[k][j]) {
						maps[i][j] = true;
						maps[j][i] = true;
					}
				}
			}
		}
	}

}