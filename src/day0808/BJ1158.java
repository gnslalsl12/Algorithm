package day0808;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ1158 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		Queue<Integer> lines = new LinkedList<>();

		for(int i = 1; i <=n ; i++) {
			lines.add(i);
		}
		sb.append("<");
		for(int i = 0; i < n-1 ; i++) {
			for(int j = 0; j < k-1; j++) {
			lines.add(lines.poll());
			}
			sb.append(lines.poll() + ", ");
		}
		sb.append(lines.poll()+">");
		System.out.println(sb);
	}
}