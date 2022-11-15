package day1115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_보호필름 {
	static int D, W, K;
	static ArrayList<Integer>[] maps;
	static PriorityQueue<availLine> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			D = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			pq = new PriorityQueue<>();
			maps = new ArrayList[W];
			for (int i = 0; i < W; i++) {
				maps[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < D; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < W; j++) {
					int temp = Integer.parseInt(tokens.nextToken());
					maps[j].add(temp);
				}
			} // input
			selLine(0, new int[D]);
			boolean totalavail = false;
			int result = 0;
			while (!pq.isEmpty()) {
				availLine templine = pq.poll();
				int[] tempchange = templine.lines;
				totalavail = true;
				for (int i = 0; i < W; i++) {
					int samecount = 0;
					int beforetemp = -1;
					boolean avail = false;
					for (int deep = 0; deep < D; deep++) {
						int temp = 0;
						if (tempchange[deep] == -1) {
							temp = maps[i].get(deep);
						} else {
							temp = tempchange[deep];
						}

						if (beforetemp == -1) {
							samecount = 1;
							beforetemp = temp;
						} else {
							if (temp == beforetemp) {
								samecount++;
							} else {
								samecount = 1;
								beforetemp = temp;
							}
						}
						if(samecount == K) {
							avail = true;
							break;
						}
					}
					if(!avail) {	//하나라도 불가능
						totalavail = false;
						break;
					}
				}
				if(totalavail) {
					result = templine.changecount;
					break;
				}
			}
			if(totalavail) {
				sb.append("#" + test + " " + result + "\n");
			}
		}
		System.out.println(sb);

	}

	private static void selLine(int count, int[] ChangeLine) {
		if (count == D) {
			int tempbefore = -1; // 초기
			int samecount = 0;
			boolean avail = false;
			int changecount = 0;
			for (int i = 0; i < D; i++) {
				int temp = 0;
				if (ChangeLine[i] == -1) {
					temp = maps[0].get(i);
				} else {
					changecount++;
					temp = ChangeLine[i];
				}
				if (tempbefore == -1) {
					tempbefore = temp;
					samecount = 1;
				} else {
					if (tempbefore == temp) {
						samecount++;
					} else {
						tempbefore = temp;
						samecount = 1;
					}
				}
				if (samecount == K) {
					avail = true;
					break;
				}
			}
			if (avail) {
				pq.add(new availLine(ChangeLine, changecount));
			}
			return;
		}
		ChangeLine[count] = 0;
		selLine(count + 1, ChangeLine.clone());
		ChangeLine[count] = -1;
		selLine(count + 1, ChangeLine.clone());
		ChangeLine[count] = 1;
		selLine(count + 1, ChangeLine.clone());
	}

	private static class availLine implements Comparable<availLine> {
		int[] lines;
		int changecount;

		public availLine(int[] lines, int changecount) {
			super();
			this.lines = lines;
			this.changecount = changecount;
		}

		@Override
		public int compareTo(availLine o) {
			return Integer.compare(this.changecount, o.changecount);
		}

	}

}
