import java.io.*;

public class Main {
	static int N;
	static String Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		Result = "";
	}

	private static int readInt() throws IOException {
		int n, c;
		n = (System.in.read() & 15);
		if ((c = System.in.read()) > 45) {
			return (n << 3) + (n << 1) + (c & 15);
		} else {
			return n;
		}

	}

	private static void getResult(String input) {
		if (input.length() == N) { // 원하는 길이인가?
			Result = input;
			return;
		}

		for (int num = 1; num < 4; num++) { // 맨 뒤에 1~3 하나씩 붙여보고
			if (isNice(input + num))
				getResult(input + num); // 좋은 수열이라면 다음 체크
			if (Result.length() == N)
				return;
			// 좋은 수열이 아니라면 다른 숫자를 붙여보기
		}
	}

	private static boolean isNice(String input) {
		int inputLen = input.length();
		for (int subLen = 1; subLen <= inputLen / 2; subLen++) { // 새로 추가된 숫자를 기준으로 부분 수열을 만들어서 비교해보기(앞에 숫자들은 이전의 경우에서
																	// 체크된 좋은 수열인 상황)
			String subRight = input.substring(inputLen - subLen, inputLen);
			String subLeft = input.substring(inputLen - subLen * 2, inputLen - subLen);
			if (subRight.equals(subLeft))
				return false;
		}
		return true;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult("");
		write.write(Result + "\n");
		write.close();
	}

}