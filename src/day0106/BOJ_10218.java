package day0106;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10218 {
	static int N, M;
	static int[] Maps;
	static int Hx, Hy;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int resultdir;
	static PriorityQueue<Integer> answers;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			resultdir = 0;
			Maps = new int[N];
			Hx = 0;
			Hy = 0;
			answers = new PriorityQueue<>(Collections.reverseOrder());
			ArrayList<dirXY> StartList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String templine = read.readLine();
				for (int j = 0; j < M; j++) {
					char temp = templine.charAt(j);
					if (temp == '.') {
						Maps[i] |= 1 << j;
						StartList.add(new dirXY(i, j, 0, 0));
					} else if (temp == 'O') {
						Hx = i;
						Hy = j;
					}
				}
			}
			boolean totalresult = true;
			for (int i = 0; i < StartList.size(); i++) {
				dirXY temp = StartList.get(i);
				int before = answers.size();
				search(temp);
				if (before == answers.size()) {
					totalresult = false;
					break;
				}
			}
			boolean lastresult = false;
			if (totalresult) {
				while (!answers.isEmpty()) {
					lastresult = true;
					int tempdirst = answers.poll();
					if(tempdirst < 0) break;
					for (int i = 0; i < StartList.size(); i++) {
						if (!lastSearch(StartList.get(i), tempdirst)) {
							lastresult = false;
							break;
						}
					}
					if (lastresult) {
						resultdir = tempdirst;
						break;
					}
				}
			}
			if (!totalresult || !lastresult) {
				write.write("XHAE\n");
			} else {
				String dirresult = Integer.toString(resultdir);
				for (int i = 0; i < dirresult.length(); i++) {
					if (dirresult.charAt(i) == '0')
						write.write('U');
					else if (dirresult.charAt(i) == '1')
						write.write('R');
					else if (dirresult.charAt(i) == '2')
						write.write('D');
					else
						write.write('L');
				}
				write.write("\n");
			}
		}
		write.close();
		read.close();

	}

	private static boolean lastSearch(dirXY input, int dirstack) {
		String tempdirstack = Integer.toString(dirstack);
		int tempx = input.x;
		int tempy = input.y;
		for (int i = 0; i < tempdirstack.length(); i++) {
			int tempdir = tempdirstack.charAt(i) - '0';
			for (int len = 1; len <= 10; len++) {
				tempx += deltas[tempdir][0];
				tempy += deltas[tempdir][1];
				if ((Maps[tempx] & (1 << tempy)) == 0)
					break;
			}
			if (tempx == Hx && tempy == Hy)
				return true;
			tempx -= deltas[tempdir][0];
			tempy -= deltas[tempdir][1];
		}
		return false;
	}

	private static void search(dirXY input) {
		Stack<dirXY> ST = new Stack<>();
		ST.add(input);
		while (!ST.isEmpty()) {
			dirXY temp = ST.pop();
			if (temp.count == 10)
				continue;
			int lastdir = temp.lastdirstacked % 10;
			for (int dir = 0; dir < 4; dir++) {
				if (temp.count != 0 && ((lastdir + 2) % 4 == dir || lastdir == dir))
					continue;
				int nextx = temp.x;
				int nexty = temp.y;
				boolean holed = false;
				if ((Maps[temp.x + deltas[dir][0]] & 1 << (temp.y + deltas[dir][1])) == 0
						&& (temp.x + deltas[dir][0] != Hx && temp.y + deltas[dir][1] != Hy))
					continue;
				for (int len = 0; len <= 10; len++) {
					nextx += deltas[dir][0];
					nexty += deltas[dir][1];
					if ((Maps[nextx] & (1 << nexty)) == 0) {
						if (nextx == Hx && nexty == Hy)
							holed = true;
						nextx -= deltas[dir][0];
						nexty -= deltas[dir][1];
						break;
					}
				}
				int madedirstack = temp.lastdirstacked * 10 + dir;
				if (holed) {
					answers.add(madedirstack);
				} else {
					if (temp.count == 9)
						continue;
					ST.add(new dirXY(nextx, nexty, temp.count + 1, madedirstack));
				}
			}
		}
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int count;
		int lastdirstacked;

		public dirXY(int x, int y, int count, int lastdir) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.lastdirstacked = lastdir;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(Math.abs(Hx - o.x) + Math.abs(Hy - o.y),
					Math.abs(Hx - this.x) + Math.abs(Hy - this.y));
		}

	}

}
