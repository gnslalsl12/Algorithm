package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		Schedule[] list = new Schedule[N + 1];
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int day = Integer.parseInt(tokens.nextToken());
			int money = Integer.parseInt(tokens.nextToken());
			if (day + i > N)
				continue;
			list[i] = new Schedule(day, money);
		}
		int [] templist = new int[N + 1];
		int result = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (list[i] == null) // 계산할 수 없는 스케줄이면 점프
				continue;
			int tempmoney = 0; // 현재 스케줄이 끝나면 받는 일정에서 받을 총 머니
			for (int j = i + list[i].day; j <= N - 1; j++) {
				tempmoney = Math.max(tempmoney, templist[j]);
			}
			tempmoney += list[i].money;
			templist[i] = tempmoney;
			result = Math.max(result, tempmoney);
		}
		
		System.out.println(result);
		
	}

	private static class Schedule {
		int day;
		int money;
		public Schedule(int day, int money) {
			super();
			this.day = day;
			this.money = money;
		}
	}
}