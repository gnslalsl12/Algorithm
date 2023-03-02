package day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_20164 {
	static ArrayList<base> list;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		base start = new base(read.readLine(), 0);
		start.countOdd();
		list = new ArrayList<>();
		list.add(start);
		int min = Integer.MAX_VALUE;
		int max = -1;
		for (int i = 0; i < list.size(); i++) {
			base temp = list.get(i);
			if (temp.st.length() >= 2) {
				list.remove(i);
				i--;
				if (temp.st.length() == 2)
					cut2(temp);
				else
					cut3(temp);
				continue;
			}
			max = Math.max(list.get(i).sum, max);
			min = Math.min(list.get(i).sum, min);
		}
		System.out.println(min + " " + max);
	}

	private static void cut2(base input) {
		int a = input.st.charAt(0) - '0';
		int b = input.st.charAt(1) - '0';
		base pop = new base(Integer.toString(a + b), input.sum);
		pop.countOdd();
		list.add(pop);
	}

	private static void cut3(base input) {
		String temp = input.st;
		for (int i = 1; i < temp.length() - 1; i++) {
			for (int j = i + 1; j < temp.length(); j++) {
				String temp1 = temp.substring(0, i);
				String temp2 = temp.substring(i, j);
				String temp3 = temp.substring(j, temp.length());
				int tempsum = Integer.parseInt(temp1) + Integer.parseInt(temp2) + Integer.parseInt(temp3);
				base pop = new base(Integer.toString(tempsum), input.sum);
				pop.countOdd();
				list.add(pop);
			}
		}
	}

	private static class base {
		String st;
		int sum;

		public base(String st, int sum) {
			this.st = st;
			this.sum = sum;
		}

		public void countOdd() {
			int count = 0;
			for (int i = 0; i < this.st.length(); i++) {
				if ((this.st.charAt(i) - '0') % 2 == 1) {
					count++;
				}
			}
			this.sum += count;
		}
	}
}
