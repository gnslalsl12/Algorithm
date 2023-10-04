import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int K;
	static int MaxN;
	static int[] Durabilities;
	static boolean[] HasRobot;
	static int Current;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		K = readInt();
		MaxN = 2 * N;
		Durabilities = new int[2 * N];
		for (int n = 0; n < MaxN; n++) {
			Durabilities[n] = readInt();
		}
		HasRobot = new boolean[N];
		Current = 0;
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		long timeCount = 0L;
		while (K > 0) {
			timeCount++;
			setRotate();
			setProceed();
			if (K == 0)
				break;
			setNewRobot();
		}
		write.write(timeCount + "\n");
		write.close();
	}

	private static void setRotate() { // 벨트 회전
		for (int i = N - 2; i >= 0; i--) { // 한 칸씩 전부 이동
			HasRobot[i + 1] = HasRobot[i];
		}
		HasRobot[0] = false;// 올리는 위치의 공간상태
		Current = (Current + MaxN - 1) % MaxN; // 올리는 위치의 번호값(Durabilities의 인덱스)
	}

	private static void setProceed() { // 조건에 맞는 아이 이동
		boolean next = false; // 내리는 위치의 공간상태
		for (int i = N - 2; i >= 1; i--) { // i==0 인 올리는 위치는 어차피 false이니 확인X
			boolean present = HasRobot[i]; // 옮기려는 벨트의 위치
			// 현재 물체가 있음 & 다음 위치가 비어있음 & 다음 위치의 번호값으로 확인한 내구도 1 이상임
			if (present && !next && Durabilities[(Current + i + 1) % MaxN] > 0) {
				HasRobot[i] = !(HasRobot[i + 1] = true);
				if (--Durabilities[(Current + i + 1) % MaxN] == 0)
					K--;
				if (K == 0)
					return;
				next = false; // 옮겨진 아이의 원 위치는 비어있음
			} else
				next = present;
		}
	}

	private static void setNewRobot() { // 새로 올리기
		if (Durabilities[Current] > 0) {
			if (--Durabilities[Current] == 0)
				K--;
			HasRobot[0] = true;
		}
	}

}