package BOJ_G5;

import java.util.*;
import java.io.*;

public class BOJ_1756_피자굽기_G5 {

	static int D, N;
	static Stack<Integer> Braizer;
	static Queue<Integer> Doughs;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		D = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		init();
		tokens = new StringTokenizer(read.readLine());
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < D; i++) {
			int diameter = Integer.parseInt(tokens.nextToken());
			if (min > diameter) {
				min = diameter;
			}
			Braizer.add(min);
		}
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			Doughs.add(Integer.parseInt(tokens.nextToken()));
		}
		write.write(solv() + "\n");
		write.close();
		read.close();

	}

	private static void init() {
		Braizer = new Stack<>();
		Doughs = new LinkedList<>();
	}

	private static int solv() {
		int result = 0;
		putDoughs();
		if (Braizer.isEmpty()) { // 화덕이 다 채워짐
			if (Doughs.isEmpty()) {
				result = 1; // 피자도 다 씀
			} else
				result = 0;
		} else // 화덕이 덜 채워짐
			result = Braizer.size();
		return result;
	}

	private static void putDoughs() { // false : 입구에서 다 못넣고 막힘 / true : 일단 들어감
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Map[i][j] = Map[j][N-1-i];
			}
		}
		
		while (!Doughs.isEmpty()) {
			if (Braizer.isEmpty())
				return;
			int dough = Doughs.poll();
			while (!Braizer.isEmpty()) {
				int diameter = Braizer.peek();
				if (dough > diameter) { // 배치 불가능
					Braizer.pop();
					continue;
				}
				break;
			}
		}
		return;
	}

}
