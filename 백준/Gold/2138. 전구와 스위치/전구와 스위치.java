import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[] BeforeF, BeforeS;
	static boolean[] After;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		init();
		String lineB = read.readLine();
		String lineA = read.readLine();
		for (int i = 0; i < N; i++) {
			BeforeF[i] = lineB.charAt(i) == '1';
			BeforeS[i] = BeforeF[i];
			After[i] = lineA.charAt(i) == '1';
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		BeforeF = new boolean[N];
		BeforeS = new boolean[N];
		After = new boolean[N];
	}

	private static void solv() {
		AscendingSearch();
	}

	private static void AscendingSearch() {
		int countFromFirst = 1; // 첫번째 전구를 toggle
		BeforeF[0] = !BeforeF[0];
		BeforeF[1] = !BeforeF[1]; // 첫번쨰 전구 누른 상태로 세팅
		int countFromSecond = 0; // 첫번째 전구는 스킵

		for (int i = 1; i < N; i++) {
			if (i < N - 1) { // i-1, i, i+1에 적용되는 스위치들
				if (BeforeF[i - 1] != After[i - 1]) {
					countFromFirst++;
					BeforeF[i] = !BeforeF[i];
					BeforeF[i + 1] = !BeforeF[i + 1];
				}
				if (BeforeS[i - 1] != After[i - 1]) {
					countFromSecond++;
					BeforeS[i] = !BeforeS[i];
					BeforeS[i + 1] = !BeforeS[i + 1];
				}
			} else {	//마지막 스위치
				if (BeforeF[N - 2] != After[N - 2]) {
					countFromFirst++;
					BeforeF[N - 1] = !BeforeF[N - 1];
				}
				if (BeforeS[N - 2] != After[N - 2]) {
					countFromSecond++;
					BeforeS[N - 1] = !BeforeS[N - 1];
				}
			}
		}
		if (BeforeF[N - 1] != After[N - 1])
			countFromFirst = Integer.MAX_VALUE;
		if (BeforeS[N - 1] != After[N - 1])
			countFromSecond = Integer.MAX_VALUE;	//완성됐는가 체크
		
		Result = countFromFirst == Integer.MAX_VALUE && countFromSecond == Integer.MAX_VALUE ? -1
				: Math.min(countFromFirst, countFromSecond);
	}

}