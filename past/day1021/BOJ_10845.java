package day1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10845 {
	static ArrayList<Integer> q = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		while(N-- > 0) {
			tokens = new StringTokenizer(read.readLine());
			String temp = tokens.nextToken();
			if(temp.equals("push")) {
				push(Integer.parseInt(tokens.nextToken()));
			}else if(temp.equals("pop")) {
				pop();
			}else if(temp.equals("size")) {
				size();
			}else if(temp.equals("empty")) {
				empty();
			}else if(temp.equals("front")) {
				front();
			}else {
				back();
			}
		}
		

	}
	private static void push(int n) {
		q.add(n);
	}
	private static void pop() {
		if(q.isEmpty()) {
			System.out.println(-1);
		}else {
			System.out.println(q.remove(0));
		}
	}
	private static void size() {
		System.out.println(q.size());
	}
	private static void empty() {
		if(q.isEmpty()) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	private static void front() {
		if(q.isEmpty()) {
			System.out.println(-1);
		}else {
			System.out.println(q.get(0));
		}
	}
	private static void back() {
		if(q.isEmpty()) {
			System.out.println(-1);
		}else {
			System.out.println(q.get(q.size()-1));
		}
	}
}
