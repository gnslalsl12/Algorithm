import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int INF = 10000000;
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
		write.write(NodeA + " " + NodeB + " " + Result + "\n");
		write.close();
	}

	private static void setFloyd() { // 플로이드 워셜로 각 from에서 to까지 최소 경로 구하기
		for (int from = 1; from <= N; from++) {
			for (int to = from + 1; to <= N; to++) {
				for (int via = 1; via <= N; via++) {
					if (from == via || to == via)
						continue;
					Map[to][from] = Map[from][to] = Math.min(Map[from][to], Map[from][via] + Map[via][to]);
				}
			}
		}
	}

	private static void setSpecificNodes() {
		Result = INF;
		for (int nA = 1; nA <= N; nA++) {
			for (int nB = nA + 1; nB <= N; nB++) {
				int tempResult = getResult(nA, nB);
				if (Result > tempResult) {
					NodeA = nA;
					NodeB = nB;
					Result = tempResult;
				}
			}
		}
	}

	private static int getResult(int nA, int nB) {
		int sum = 0;
		for (int j = 1; j <= N; j++) {
			if (j == nA || j == nB)
				continue;
			sum += Math.min(Map[nA][j], Map[nB][j]);
		}
		return sum * 2;
	}

}