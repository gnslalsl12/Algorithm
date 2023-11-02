import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		N = Integer.parseInt(read.readLine());
		M = Integer.parseInt(read.readLine());
		maps = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				if (tokens.nextToken().equals("1")) {
					maps[i][j] = true;
					maps[j][i] = true;
				}
			}
		}
		FLYD();
		boolean answer = true;
		tokens = new StringTokenizer(read.readLine());
		int start = Integer.parseInt(tokens.nextToken()) - 1;
		int next;
		for (int i = 1; i < M; i++) {
			next = Integer.parseInt(tokens.nextToken()) - 1;
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
		read.close();
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