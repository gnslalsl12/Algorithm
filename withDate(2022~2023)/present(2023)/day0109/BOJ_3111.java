package day0109;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_3111 {
	static String A;
	static Deque<String> DQ;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		A = read.readLine();
		String[] T = read.readLine().split("");
		DQ = new LinkedList<>(Arrays.asList(T));
		System.out.println(DQ);

	}

	private static boolean fromFirst() {
		boolean ans = false;
		
		return ans;
	}

}
