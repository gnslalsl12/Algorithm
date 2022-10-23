package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10814 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(tokens.nextToken());
		PriorityQueue<temp> ns = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			ns.add(new temp(Integer.parseInt(tokens.nextToken()), i, tokens.nextToken()));
		}
		while (!ns.isEmpty()) {
			sb.append(ns.poll());
		}
		System.out.print(sb);
	}

	private static class temp implements Comparable<temp> {
		int age;
		int index;
		String name;

		public temp(int age, int index, String name) {
			super();
			this.age = age;
			this.index = index;
			this.name = name;
		}

		@Override
		public int compareTo(temp o) {
			if (this.age == o.age)
				return Integer.compare(this.index, o.index);
			return Integer.compare(this.age, o.age);
		}

		@Override
		public String toString() {
			return this.age + " " + this.name + "\n";
		}
	}
}