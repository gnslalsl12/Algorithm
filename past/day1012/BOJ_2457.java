package day1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2457 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int LenF = dateToNum(3, 1);
		int LenE = dateToNum(11, 30);
		ArrayList<flowers> Flist = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int startm = Integer.parseInt(tokens.nextToken());
			int startd = Integer.parseInt(tokens.nextToken());
			int endm = Integer.parseInt(tokens.nextToken());
			int endd = Integer.parseInt(tokens.nextToken());
			int start = dateToNum(startm, startd);
			int end = dateToNum(endm, endd);
			if (start > LenE || end <= LenF)
				continue;
			Flist.add(new flowers(start, end));
		}
		Collections.sort(Flist);

		int endday = LenF;
		int tempindex = -1;
		int result = 0;
		int size = Flist.size();
		breakall: while (true) {
			int temp = endday; // 시작지점
			int i = tempindex;
			boolean found = false;
			while (true) {
				i++;
				if (i >= size) { // 볼 만큼 다 봤다
					if (!found) {	//끝까지 봤는데 갱신될 게 없어
						result = 0;
						break breakall;
					}
					break;
				}
				if (Flist.get(i).start > temp) { // 초과해버림
					if (!found) {
						result = 0;
						break breakall;
					}
					break;
				}
				if (Flist.get(i).end > endday) { // 시작 초과하지 않고 endday가 갱신됨
					tempindex = i; // i다음부터 봐라
					endday = Flist.get(i).end;
					found = true;
				}
			}
			result++;
			if (endday > LenE)
				break;
		}
		System.out.println(result);
	}

	private static int dateToNum(int m, int d) {
		int result = d;
		for (int i = 1; i < m; i++) {
			if (i == 4 || i == 6 || i == 9 || i == 11) {
				result += 30;
			} else if (i == 2) {
				result += 28;
			} else {
				result += 31;
			}
		}
		return result;
	}

	static class flowers implements Comparable<flowers> {
		int start;
		int end;

		public flowers(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(flowers o) {
			if (this.start == o.start)
				return Integer.compare(o.end, this.end);
			return Integer.compare(this.start, o.start);
		}
	}
}