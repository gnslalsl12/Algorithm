package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5525 {
	static int N;
	static int M;
	static String line;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		M = Integer.parseInt(read.readLine());
		line = read.readLine();
		for (int i = 0; i < M;) {
//			System.out.println("체크 : " + i);
			if (line.charAt(i) == 'I') {
				int tempresult = check(i);
				if (tempresult == -1) { // 범위 초과 , 더이상 진행 불가
					break;
				} else if (tempresult == 0) { // 성공
//					System.out.println(i);
					i += 2;
				} else { // 실패(실패한 index값을 반환함
//					System.out.println("실패 : " + i);
					i += tempresult;
				}
			} else {
				i++;
			}
		}
		System.out.println(result);
	}

	private static int check(int index) {
		for (int i = 1; i <= N; i++) {
			if (!isIn(index + i * 2) || !isIn(index + i * 2 - 1))
				return -1;
			if (line.charAt(index + i * 2 - 1) != 'O') { // O여야하는데 I야
				return i * 2 - 1;
			} else if (line.charAt(index + i * 2) != 'I') { // I여야하는데 O야
				return i * 2 + 1;
			}
		}
		result++;
		return 0;
	}

	private static boolean isIn(int index) {
		return index >= M ? false : true;
	}
}