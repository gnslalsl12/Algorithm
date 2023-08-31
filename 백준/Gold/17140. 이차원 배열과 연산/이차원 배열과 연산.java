import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int r, c, k;
	static int MaxR, MaxC;
	static int[][] Map = new int[101][101];
	static Comparator<int[]> comparator = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[1] == o2[1])
				return Integer.compare(o1[0], o2[0]);
			return Integer.compare(o1[1], o2[1]);
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		MaxR = 3;
		MaxC = 3;
		for (int i = 1; i <= 3; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= 3; j++) {
				Map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		write.write(solv() + "\n");
		write.close();
		read.close();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 1; i <= MaxR; i++) {
			for (int j = 1; j <= MaxC; j++) {
				sb.append(Map[i][j] + " ");
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
	}

	private static int solv() {
		int time = 0;
		while (true) {
			if (Map[r][c] == k)
				break;
			if (time == 100) {
				time = -1;
				break;
			}

			if (MaxR >= MaxC) {
				getRow();
//				System.out.println("getRow : " + MaxC);
			} else {
				getColumn();
//				System.out.println("getColumn : " + MaxR);
			}
			time++;
//			print();
//			if (time == 10)
//				break;
		}

		return time;
	}

	private static void getColumn() {
		int maxlen = 0;
		for (int j = 1; j <= MaxC; j++) {
			maxlen = Math.max(maxlen, setColumn(j));
		}
		MaxR = Math.max(MaxR, maxlen);
	}

	private static int setColumn(int line) {
		int[] count = new int[101];
		for (int i = 1; i <= MaxR; i++) {
			count[Map[i][line]]++;
			Map[i][line] = 0;
		}
		PriorityQueue<int[]> PQ = new PriorityQueue<>(comparator);
		for (int idx = 1; idx <= 100; idx++) {
			if (count[idx] != 0)
				PQ.add(new int[] { idx, count[idx] });
		}
		int start = 1;
		while (!PQ.isEmpty()) {
			int[] temp = PQ.poll();
			Map[start++][line] = temp[0];
			if (start == 100)
				break;
			Map[start++][line] = temp[1];
			if (start == 100)
				break;
		}
		return start - 1;
	}

	private static void getRow() {
		int maxlen = 0;
		for (int i = 1; i <= MaxR; i++) {
			maxlen = Math.max(maxlen, setRow(i));
		}
		MaxC = Math.max(MaxC, maxlen);
	}

	private static int setRow(int line) {
		int[] count = new int[101];
		for (int j = 1; j <= MaxC; j++) {
			count[Map[line][j]]++;
			Map[line][j] = 0;
		}
		PriorityQueue<int[]> PQ = new PriorityQueue<>(comparator);
		for (int idx = 1; idx <= 100; idx++) {
			if (count[idx] != 0)
				PQ.add(new int[] { idx, count[idx] });
		}
		int start = 1;
		while (!PQ.isEmpty()) {
			int[] temp = PQ.poll();
			Map[line][start++] = temp[0];
			if (start == 101)
				break;
			Map[line][start++] = temp[1];
			if (start == 101)
				break;
		}
		return start - 1;
	}

}