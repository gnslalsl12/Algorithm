package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1231_중위순회 {
	static int N;
	static int[][] Childs;
	static int[] Parents;
	static char[] Library;
	static String result;
	static boolean[] vis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		for (int test = 1; test <= 10; test++) {
			N = Integer.parseInt(read.readLine());
			Library = new char[N + 1];
			Childs = new int[N + 1][2];
			Parents = new int[N + 1];
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				int me = Integer.parseInt(tokens.nextToken());
				char value = tokens.nextToken().charAt(0);
				Library[me] = value;
				if (tokens.hasMoreTokens()) {
					int child1 = Integer.parseInt(tokens.nextToken());
					Parents[child1] = me;
					Childs[me][0] = child1;
				}
				if (tokens.hasMoreTokens()) {
					int child2 = Integer.parseInt(tokens.nextToken());
					Parents[child2] = me;
					Childs[me][1] = child2;
				}
			}
			solv();
			write.write("#" + test + " " + result + "\n");
		}
		write.close();
		read.close();
	}

	private static void solv() {
		result = "";
		int start;
		for (start = 64; start >= 1; start /= 2) {
			if (start <= N)
				break;
		}
		getOrder(start);
	}

	private static void getOrder(int start) {
		vis = new boolean[N + 1];
		vis[0] = true;
		for (int i = 0; i < N; i++) {
			vis[start] = true;
			result += Library[start];
			while (start != 0) {
				start = checkChild(start);
				if (!vis[start])
					break;
			}
		}
	}

	private static int checkChild(int parent) {
		int a = Childs[parent][0];
		int b = Childs[parent][1];
		if (vis[a] && vis[b]) // 더 내려갈 자식이 없으면
			if (vis[parent])
				return Parents[parent]; // 하나 위로
			else
				return parent; // 아니면 나 자신
		if (!vis[a]) // 자식이 방문 전이면
			return checkChild(a); // 계속 내려가라
		return checkChild(b);
	}

}
