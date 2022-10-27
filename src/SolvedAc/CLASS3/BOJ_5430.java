package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_5430 {
	static String orders;
	static int N;
	static Deque<Integer> sols;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= T; test++) {
			orders = read.readLine();
			N = Integer.parseInt(read.readLine());
			sols = new LinkedList<>();
			String line = read.readLine();
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '[') {
					line.replace("[", "");
				}else if(line.charAt(i) == ']') {
					System.out.println("?HJSKLHJKLA");
					line.replace("]", "");
				}
			}
			System.out.println(line.toString() + "??????");
			String[] templine = line.split(",");
			System.out.println("///////////////////");
			System.out.println(Arrays.toString(templine));
			if(N > 0) {
				for(int i = 1; i < templine.length-1; i++) {
					sols.add(Integer.parseInt(templine[i]));
				}
			}
			boolean out = false;
			boolean reverse = false;
			System.out.println("sols : " + sols);
			for(int i = 0; i < orders.length(); i++) {
				if(orders.charAt(i) == 'R') {
					reverse = !reverse;
					continue;
				}
				else {
					if(sols.isEmpty()) {
						out = true;
						sb.append("error\n");
						break;
					}
					if(reverse) {
						sols.pollLast();
					}else {
						sols.pollFirst();
					}
				}
			}
			if(!out) {
				sb.append("[");
				for(int i = 0; i < sols.size(); i++) {
					sb.append(sols.poll());
					if(i != sols.size()-1) {
						sb.append(",");
					}
				}
				sb.append("]\n");
			}
		}
		System.out.print(sb);
	}
}
