import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int D;
	static int[] Map;
	static int Result;
	static int Enemies;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		D = readInt();
		Map = new int[N];
		Result = 0;
		Enemies = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (readInt() == 1) {
					Map[n] |= 1 << m;
					Enemies++;
				}
			}
		}
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
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setArchersWithComb(0, 0, new int[3]); // 조합으로 궁수 위치M 정하기
		write.write(Result + "\n");
		write.close();
	}

	private static void setArchersWithComb(int count, int start, int[] sel) {
		if (count == 3) {
			setArchersOnMap(sel); // 궁수 위치 조합 케이스
			return;
		}

		for (int m = start; m < M; m++) {
			sel[count] = m;
			setArchersWithComb(count + 1, m + 1, sel);
		}

	}

	private static void setArchersOnMap(int[] sel) { // 궁수 위치 기반으로 진행
		int[] tempMap = Map.clone();
		int remain = Enemies;
		int tempResult = 0;
		while (remain > 0) {
			int killed = setShootEnemies(sel, tempMap); // 한 턴에 죽인 적 수
			tempResult += killed; // 총 죽인 적 수 +
			remain -= killed; // 남은 적 수 -
			if (remain == 0)
				break;
			remain -= setEnemyMove(tempMap); // 적 한 칸씩 이동
		}
		Result = Math.max(Result, tempResult);
	}

	private static int setShootEnemies(int[] sel, int[] tempMap) { // 적 쏘기
		int removedCount = 0;
		int[] targeted = new int[3];

		for (int s = 0; s < 3; s++) {
			int archerM = sel[s]; // M위치에 있는 궁수
			int targetN = N - 1; // 목표 위치 N
			int targetM = archerM; // 목표 위치 M

			breakAll: while (true) {
				if ((tempMap[targetN] & (1 << targetM)) != 0) { // 우선순위의 표적 발견
					targeted[s] = targetN * M + targetM; // 해당 표적의 위치를 기록(중복 가능)
					break;
				}

				while (true) { // 목표지점 이동
					if (targetM < archerM) { // 우선순위(가장 가까이, 그리고 왼쪽) 순으로 목표 지점 이동
						targetN--;
					} else {
						targetN++;
					}
					targetM++;

					if (!isIn(targetN, targetM)) { // 이동한 목표 지점이 영역 밖이라면
						if (targetM == archerM + D) { // 마지막 좌표까지 확인한 경우라면
							targeted[s] = -1; // 가능한 목표가 없음
							break breakAll;
						}

						if (targetN == N) { // 가장 왼쪽부터 다시 탐색 (피라미드 형태 이동)
							targetN = N;
							targetM = archerM - (targetM - archerM + 1);
						}
					} else { // 영역 내 목표지점임을 확인
						break;
					}
				}
			}
		}

		for (int targetLoc : targeted) { // 모든 목표 대상
			if (targetLoc == -1)
				continue;
			int locN = targetLoc / M;
			int locM = targetLoc % M;
			if ((tempMap[locN] & (1 << locM)) != 0) { // 맵에서 지우기
				tempMap[locN] &= ~(1 << locM);
				removedCount++;
			}
		}
		return removedCount;
	}

	private static int setEnemyMove(int[] tempMap) { // 적 한 칸씩 내려오기
		int excepted = 0;
		for (int m = 0; m < M; m++) {
			boolean before = false;
			for (int n = 0; n < N; n++) {
				boolean present = (tempMap[n] & (1 << m)) != 0;
				if (before) {
					tempMap[n] |= 1 << m;
				} else {
					tempMap[n] &= ~(1 << m);
				}
				before = present;
			}
			if (before)
				excepted++;
		}
		return excepted;
	}

	private static boolean isIn(int n, int m) {
		return n >= 0 && n < N && m >= 0 && m < M;
	}

}