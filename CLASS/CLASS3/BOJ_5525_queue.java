package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_5525_queue {
	static int N, M;
	static Queue<Character> Q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		M = Integer.parseInt(read.readLine());
		String templine = read.readLine();
		for (int i = 0; i < M; i++) {
			Q.add(templine.charAt(i));
		}
		int result = 0;
		boolean foundI = false;
		int count = 0;
		boolean end = false;
		while (true) {
			if (Q.isEmpty()) {
				if (foundI)
					end = true;
				break;
			}
			char temp = Q.poll();
//			System.out.println("보는 애 : " + temp + ",  count : " + count);
			if (!foundI) {
				if (temp == 'I') {
//					System.out.println("첫 시작!");
					foundI = true;
				}
				continue;
			} else { // I 시작됨
				if (count % 2 == 0) { // O여야함
					if (temp == 'O') {
						count++;
						continue;
					} else {
//						System.out.println("끊김");
						foundI = false;
					}
				} else {
					if (temp == 'I') {
						count++;
						continue;
					} else {
//						System.out.println("끊김");
						foundI = false;
					}
				}
			}
//			System.out.println("끊긴 상태까지의 count : " + count);
			if (!foundI) { // 끊김
				count -= count % 2;
				if (count / 2 >= N) {
//					System.out.println(count + ", " + N);
//					System.out.println("계싼");
					result += (count / 2 - N + 1);
				}
				if (temp == 'I') { // 끊긴 게 I부터
					foundI = true;
				}
				count = 0;
			}
		}
//		System.out.println("마지막에서의 상태 : ");
//		System.out.println("result  : " + result);
//		System.out.println("count : " + count);
//		System.out.println(foundI);
//		System.out.println(end);
		if (end) {
//			System.out.println("마지막에 계싼해야해!");
			count -= count % 2;
//			System.out.println(count);
			if (count / 2 >= N) {
				result += (count / 2 - N + 1);
				count = 0;
			}
		}
		System.out.println(result);
	}
}