package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1248_공통조상 {
	static int V, E;
	static int A, B;
	static int[] Parents;
	static int[][] Childs;
	static int ResultCount, ResultParent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			Parents = new int[V + 1];
			Childs = new int[V + 1][2];
			tokens = new StringTokenizer(read.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(tokens.nextToken());
				int child = Integer.parseInt(tokens.nextToken());
				Parents[child] = parent;
				if (Childs[parent][0] == 0)
					Childs[parent][0] = child;
				else
					Childs[parent][1] = child;
			}
			solv();
			write.write("#" + test + " " + ResultParent + " " + ResultCount + "\n");
		}
		write.close();
		read.close();
	}

	private static void solv() {
		ResultParent = getSameParent(A, B);
		ResultCount = 1;
		countWholeChilds(ResultParent);
	}

	private static int getSameParent(int a, int b) {
		boolean[] vis = new boolean[V + 1];
		while (a != 0) {
			vis[a] = true;
			a = Parents[a];
		}
		while (true) {
			if (vis[b])
				break;
			b = Parents[b];
		}
		return b;
	}

	private static void countWholeChilds(int from) {
		if (Childs[from][0] != 0) {
			ResultCount++;
			countWholeChilds(Childs[from][0]);
		}
		if (Childs[from][1] != 0) {
			ResultCount++;
			countWholeChilds(Childs[from][1]);
		}
	}

}
