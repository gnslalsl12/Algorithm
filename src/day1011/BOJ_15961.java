package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15961 {
	static int N, d, k, c;
	static ArrayList<Integer> Sushis = new ArrayList<>();
	static int[] selected;
	static Queue<Integer> tempSushi = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 벨트 위 초밥 수
		d = Integer.parseInt(tokens.nextToken()); // 초밥 종류 수
		k = Integer.parseInt(tokens.nextToken()); // 연속해서 먹을 수 있는 접시 수
		c = Integer.parseInt(tokens.nextToken()); // 쿠폰 초밥 번호
		boolean[] ObjIndex = new boolean[N];
		selected = new int[d + 1];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (i < k) {
				tempSushi.add(temp);
			}
			Sushis.add(temp);
			if (temp == c) {
				ObjIndex[i] = true;
			}
		}
		int totalresult = 0;
		int result = 0;
		for (int i = 0; i < k; i++) {
			int temps = tempSushi.poll();
			if (selected[temps] == 0) {
				selected[temps]++;
				result++;
			} else {
				selected[temps]++;
			}
			tempSushi.add(temps);
		}
		if (selected[c] == 0) {
			totalresult = Math.max(totalresult, result + 1);
		} else {
			totalresult = Math.max(totalresult, result);
		}
		for (int i = k; i < N; i++) {
			selected[tempSushi.peek()]--; // 하나 뺴고
			if (selected[tempSushi.poll()] == 0)
				result--;
			tempSushi.add(Sushis.get(i));
			if (selected[Sushis.get(i)] == 0) {
				result++;
			}
			selected[Sushis.get(i)]++;

			if (selected[c] == 0) {
				totalresult = Math.max(totalresult, result + 1);
			} else {
				totalresult = Math.max(totalresult, result);
			}
		}
		for (int i = 0; i < k-1; i++) {
			selected[tempSushi.peek()]--; // 하나 뺴고
			if (selected[tempSushi.poll()] == 0)
				result--;
			tempSushi.add(Sushis.get(i));
			if (selected[Sushis.get(i)] == 0) {
				result++;
			}
			selected[Sushis.get(i)]++;

			if (selected[c] == 0) {
				totalresult = Math.max(totalresult, result + 1);
			} else {
				totalresult = Math.max(totalresult, result);
			}
		}
		System.out.println(totalresult);
	}

}
