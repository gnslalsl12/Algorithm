package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_CONTEST_C {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		String temp = read.readLine();
		Queue<Character> Rot = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			Rot.add(temp.charAt(i));
		}
		int P = Integer.parseInt(read.readLine());
		int result = 0;
		String Obj = read.readLine();
		for(int i = 0; i < P; i++) {
			int tempchar = Obj.charAt(i);
			for(int j = 0; j < N; j++) {
				result++;
				char pop = Rot.poll();
				Rot.add(pop);
				if(pop == tempchar) {
					break;
				}
				if(j == N-1) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(result);
	}
}