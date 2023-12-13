import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int INF = Integer.MAX_VALUE;
	static int[][] Map = new int[105][105];
	static int NodeA, NodeB;
	static int Result = 0;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		for (int ni = 1; ni <= N; ni++) {
			for (int nj = 1; nj <= N; nj++) {
				if (ni != nj) {
					Map[ni][nj] = INF;
				}
			}
		}
		while (M-- > 0) {
			int nA = readInt();
			int nB = readInt();
			Map[nA][nB] = Map[nB][nA] = 1;
		}
	}

	private static int readInt() throws IOException {
		int n, c;
		do {
			n = System.in.read();
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setFloyd();
		setSpecificNodes();
		getResult();
		write.write(Math.min(NodeA, NodeB) + " " + Math.max(NodeA, NodeB) + " " + Result + "\n");
		write.close();
	}

	private static void setFloyd() { // 플로이드 워셜로 각 from에서 to까지 최소 경로 구하기
		for (int from = 1; from <= N; from++) {
			for (int to = from + 1; to <= N; to++) {
				for (int via = 1; via <= N; via++) {
					if (from == via || to == via)
						continue;
					if (Map[from][via] != INF && Map[via][to] != INF) {
						Map[from][to] = Math.min(Map[from][to], Map[from][via] + Map[via][to]);
						Map[to][from] = Map[from][to];
					}
				}
			}
		}
	}

	private static void setSpecificNodes() {
		if (N == 2) {
			NodeA = 1;
			NodeB = 2;
			Result = 0;
			return;
		}
		int[] min = new int[] { -1, INF };
		for (int i = 1; i <= N; i++) { // 모든 노드까지의 경로 최소합을 가진 곳이 첫번째 치킨집 위치
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += Map[i][j];
			}
			if (min[1] > sum) {
				min[0] = i;
				min[1] = sum;
			}
		}
		NodeA = min[0]; // 첫번쨰 치킨집 위치

		min = new int[] { -1, INF };
		for (int i = 1; i <= N; i++) { // 모든 노드들까지의 경로 중 NodeA보다 가깝게 닿는 지점의 총 합산 수가 가장 큰 곳이 두번쨰 치킨집 위치
			if (i == NodeA)
				continue;
			for (int j = 1; j <= N; j++) {
				if (Map[i][j] <= Map[NodeA][j]) {
					Map[i][N + 1] += Map[i][j];
				}
			}
			if (Map[i][N + 1] != 0 && min[1] > Map[i][N + 1]) {
				min[0] = i;
				min[1] = Map[i][N + 1];
			}
		}
		NodeB = min[0]; // 두번쨰 치킨집 위치

		for (int j = 1; j <= N; j++) {
			if (j == NodeA || j == NodeB)
				continue;
			Result += Math.min(Map[NodeA][j], Map[NodeB][j]); // 최소 왕복 합산 수 구하기
		}
	}

	private static void getResult() {
		for (int j = 1; j <= N; j++) {
			if (j == NodeA || j == NodeB)
				continue;
			Result += Math.min(Map[NodeA][j], Map[NodeB][j]);
		}
	}

}