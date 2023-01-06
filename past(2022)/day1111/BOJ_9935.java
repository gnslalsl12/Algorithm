package day1111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_9935 {
	static String tempread;
	public static void main(String[] args) {
		BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader btempread = new BufferedReader(new InputStreamReader(System.in));
		String read= bread.toString();
		tempread = btempread.toString();
		while (true) {
			boolean tempresult = check(read, tempread);
			if(!tempresult) break;
		}
		System.out.println();

	}

	private static boolean check(String read, String tempread) {
		String removed = "";
		Queue<Integer> remindex = new LinkedList<Integer>();
		breakall: for (int i = 0; i < read.length(); i++) {
			if (read.charAt(i) == tempread.charAt(0)) {
				for (int j = 1; j < tempread.length(); j++) {
					if (i + j >= read.length())
						break breakall;
					if (read.charAt(i + j) != tempread.charAt(j)) {
						break;
					}
					if (j == tempread.length()) {
						remindex.add(i);
					}
				}
			}
		}
		if (!remindex.isEmpty()) {
			for (int i = 0; i < read.length(); i++) {
				if (!remindex.isEmpty() && i == remindex.peek()) {
					i = remindex.poll();
				}
				removed += read.charAt(i);
			}
			read = removed;
			return true;
		} else {
			return false;
		}
	}

}
