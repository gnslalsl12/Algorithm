package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder write = new StringBuilder();
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			Queue<hoons> q = new LinkedList<>();
			q.add(new hoons(A, ""));
			//q사이즈 넣기
			boolean [] visits = new boolean [10000];
			while (!q.isEmpty()) {
				hoons temp = q.poll();
				int tempnum = temp.num;
				if(visits[tempnum])continue;
				visits[tempnum] = true;
				int solvnum = compD(tempnum);
				if (solvnum == B) {
					write.append(temp.history + "D\n");
					break;
				} else {
					q.add(new hoons(solvnum, temp.history + 'D'));
				}
				solvnum = compS(tempnum);
				if (solvnum == B) {
					write.append(temp.history + "S\n");
					break;
				} else {
					q.add(new hoons(solvnum, temp.history + 'S'));
				}
				solvnum = compL(tempnum);
				if (solvnum == B) {
					write.append(temp.history + "L\n");
					break;
				} else {
					q.add(new hoons(solvnum, temp.history + 'L'));
				}
				solvnum = compR(tempnum);
				if (solvnum == B) {
					write.append(temp.history + "R\n");
					break;
				} else {
					q.add(new hoons(solvnum, temp.history + 'R'));
				}

			}

		}
		System.out.println(write);
	}

	private static int compD(int num) {
		return (num * 2) % 10000;
	}

	private static int compS(int num) {
		return num == 0 ? 9999 : num - 1;
	}

	private static int compL(int num) {
		return (num * 10 + (num / 1000)) % 10000;
	}

	private static int compR(int num) {
		return (num + (num % 10) * 10000) / 10;
	}

	private static class hoons {
		int num;
		String history;

		public hoons(int num, String history) {
			super();
			this.num = num;
			this.history = history;
		}
	}
}
