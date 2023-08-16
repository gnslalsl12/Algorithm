import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] Expressions;
	static long Result;
	static Queue<Integer> Subsets;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Expressions = new int[N];
		String line = read.readLine();
		for (int i = 0; i < N; i++) {
			char c = line.charAt(i);
			if (c == '+')
				Expressions[i] = 11;
			else if (c == '-')
				Expressions[i] = 12;
			else if (c == '*')
				Expressions[i] = 13;
			else
				Expressions[i] = c - '0';
		}
		Subsets = new LinkedList<>();
		Result = Long.MIN_VALUE;
		read.close();
	}

	private static void solv() {
		if (N == 1) {
			Result = Expressions[0];
			return;
		}
		getSubsets(1, 0);
		setPriorCalc();
	}

	private static void getSubsets(int index, int subset) {
		if (index > N) {
			Subsets.add(subset);
			return;
		}
		subset |= 1 << index;
		getSubsets(index + 4, subset);
		subset &= ~(1 << index);
		getSubsets(index + 2, subset);

	}

	private static void setPriorCalc() {
		while (!Subsets.isEmpty()) {
			Queue<Integer> prioredExp = new LinkedList<>();
			int priorOrder = Subsets.poll();
			for (int i = 1; i < N;) {
				if ((priorOrder & (1 << i)) != 0) { // i번째 수식에 괄호처리 돼있음
					// 괄호 안 수식 결과값을 추가
					prioredExp.add(calculated(Expressions[i - 1], Expressions[i], Expressions[i + 1]));
					if (i + 2 < N) // 다음 연산이 존재한다면
						prioredExp.add(Expressions[i + 2]); // 해당 연산
					i += 2;
				} else {
					prioredExp.add(Expressions[i - 1]);
					prioredExp.add(Expressions[i]);
				}
				i += 2;
				if (i == N)
					prioredExp.add(Expressions[i - 1]); // 맨 마지막 값 뺴먹지 않기
			}
			getResult(prioredExp);
		}

	}

	private static int calculated(int left, int calcExp, int right) { // 계산
		if (calcExp == 11)
			return left + right;
		else if (calcExp == 12)
			return left - right;
		else
			return left * right;
	}

	private static void getResult(Queue<Integer> exp) { // 괄호 수식 계산
		int result = exp.poll();
		boolean orderCalc = true;
		int calcExp = 0;
		while (!exp.isEmpty()) {
			if (orderCalc) {
				calcExp = exp.poll();
			} else {
				result = calculated(result, calcExp, exp.poll());
			}
			orderCalc = !orderCalc;
		}
		Result = Math.max(Result, result);
	}

}