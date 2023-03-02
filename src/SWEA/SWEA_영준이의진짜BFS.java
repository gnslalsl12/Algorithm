package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_영준이의진짜BFS {
	static int N;
	static int[] Parents;
	static ArrayList<Integer>[] Childs;
	static Queue<Integer> Q;
	static long TotalResult;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(read.readLine());
			Parents = new int[N + 1];
			Childs = new ArrayList[N + 1];
			Parents[1] = 1;
			TotalResult = 0;
			for (int i = 1; i <= N; i++) {
				Childs[i] = new ArrayList<>();
			}
			tokens = new StringTokenizer(read.readLine());
			for (int i = 2; i <= N; i++) {
				int temp = Integer.parseInt(tokens.nextToken());
				Parents[i] = temp;
				Childs[temp].add(i);
			}
			solv();
			write.write("#" + test + " " + TotalResult + "\n");
		}
		write.close();
		read.close();
	}

	private static void solv() {
		Q = new LinkedList<>();
		int from = 1;
		Q.add(from);
		while (!Q.isEmpty()) {
			int temp = Q.poll();
			addChilds(temp);
			TotalResult += getDist(from, temp);
			from = temp;
		}
	}

	private static void addChilds(int from) {
		for (int to : Childs[from]) {
			Q.add(to);
		}
	}

	private static int getDist(int from, int to) {
		boolean[] vis = new boolean[N + 1];
		int[] fromDist = new int[N + 1];
		int dist = 0;
		while (true) {
			vis[from] = true;
			fromDist[from] = dist;
			if (from == 1)
				break;
			dist++;
			from = Parents[from];
		}
		dist = 0;
		while (true) {
			if (vis[to])
				break;
			to = Parents[to];
			dist++;
		}
		dist += fromDist[to];
		return dist;
	}

}
