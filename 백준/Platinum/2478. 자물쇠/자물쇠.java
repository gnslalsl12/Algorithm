import java.util.*;
import java.io.*;

/* 9 8 7 6 5 4 3 2 1 10 같은 경우도 가능
 * 1
 * 1 9
 * 1
*/
public class Main {

	static int N;
	static int[] Locks;
	static int FR, SRL, SRR, TR;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(FR + "\n" + SRL + ' ' + SRR + "\n" + TR + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Locks = new int[N];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int n = 0; n < N; n++) {
			Locks[n] = Integer.parseInt(tokens.nextToken());
		}
		read.close();
	}

	private static void solv() {
		getDescendingSection();
		getTR();
	}

	private static void getDescendingSection() {
		SRL = -1;
		SRR = -1;
		boolean[] ascArray = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (Locks[(i + 1) % N] - Locks[i] == 1 || (Locks[(i + 1) % N] == 1 && Locks[i] == N)) { // 상승구간
				ascArray[i] = true;
				ascArray[(i + 1) % N] = true;
			}
		}
		for (int i = 0; i < N; i++) {
			if (!ascArray[i] && ascArray[(i + 1) % N])
				SRR = i;
			if (ascArray[i] && !ascArray[(i + 1) % N])
				SRL = (i + 1) % N;
		}
		if (SRL == -1 || SRR == -1) { // 끝나지 않는 하강
			if (Locks[N - 2] == 1)
				SRL = N - 2;
			else
				SRL = N - 1;
			SRR = SRL - 1;
		}
	}

	private static void getTR() {
		TR = 1;
		int originL = SRL;
		int originR = SRR;
		for (; TR < N; TR++) {
			SRL = (originL + TR) % N;
			SRR = (originR + TR) % N; // 마지막 밀기를 역으로 돌린만큼 두 인덱스가 오른쪽으로 이동
			if (SRL < SRR) { // p < q 를 만족할 때
				getFR();
				if (FR >= 1 && FR < N) {
					SRL++;
					SRR++;
					return;
				}
			}
		}
	}

	private static void getFR() {
		int targetI = -1;
		for (int i = 0; i < N; i++) {
			if (Locks[i] == 1) {
				targetI = i;
				break;
			}
		}
		targetI = (targetI + TR) % N;
		if (targetI >= SRL && targetI <= SRR) { // 1이 뒤집힌 구간 내에 존재함
			int gapL = targetI - SRL;
			int gapR = SRR - targetI;
			if (gapL < gapR) { // 왼쪽에 위치
				targetI = SRR - gapL;
			} else if (gapL > gapR) { // 오른쪽에 위치
				targetI = SRL + gapR;
			}
		}
		FR = N - targetI;
	}

}