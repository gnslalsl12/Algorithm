package day1116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_2598 {
	static HashSet<Integer> Dictionary = new HashSet<>();
	static int[] Dices = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String templine = read.readLine();
			int tempdice = 0;
			for (int j = 0; j < 6; j++) {
				int tempnum;
				if (templine.charAt(j) == 'R') {
					tempnum = 1;
				} else if (templine.charAt(j) == 'G') {
					tempnum = 2;
				} else if (templine.charAt(j) == 'B') {
					tempnum = 3;
				} else {
					tempnum = 4;
				}
				tempdice += tempnum * Math.pow(10, j);
			}
			Dices[i] = tempdice;
		}
		/*
		 * 0 : 위 1 : 앞 2 : 오른쪽 3 : 뒤 4 : 왼쪽 5 : 아래
		 */
	}

	private static void checkTower(int order, int check) {
		// order는 위에서부터의 주사위 순서
		// check는 앞면 기준으로 가능한 경우
		// hashset으로 확인하자
	}

	private static void setDices(int order) {
		int dice1 = order / 1000;
		order %= 1000;
		int dice2 = order / 100;
		order %= 100;
		int dice3 = order / 10;
		int dice4 = order % 10;
		for (int c1 = 0; c1 < 6; c1++) {
			int set1 = (int) ((dice1 / Math.pow(10, c1)) % 10);
			for (int c2 = 0; c2 < 6; c2++) {
				int set2 = (int) ((dice2 / Math.pow(10, c2)) % 10);
				if (set1 == set2)
					continue;
				for (int c3 = 0; c3 < 6; c3++) {
					int set3 = (int) ((dice3 / Math.pow(10, c3)) % 10);
					if (set1 == set3 || set1 == set3)
						continue;
					for (int c4 = 0; c4 < 6; c4++) {
						int set4 = (int) ((dice4 / Math.pow(10, c4)) % 10);
						if (set1 == set4 || set2 == set4 || set3 == set4)
							continue;
						// 일단 앞면들은 되는 경우임
						int check = c1 + c2 * 10 + c3 * 100 + c4 * 1000; // c1이 맨 위에
					}
				}
			}
		}

	}

	private static void permOrder(int count, int sel, boolean[] vis) {
		if (count == 4) {

			return;
		}
		for (int i = 0; i < 4; i++) {
			if (vis[i])
				continue;
			sel += i * Math.pow(10, count);
			vis[i] = true;
			permOrder(count + 1, sel, vis);
			vis[i] = false;
		}

	}

}
