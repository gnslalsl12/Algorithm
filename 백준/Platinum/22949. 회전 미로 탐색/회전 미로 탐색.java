import java.util.*;
import java.io.*;

public class Main {
	static int Max; // Map의 길이
	static int K; // 입력 K
	static boolean[][][] Map = new boolean[505][505][4]; // 맵
	static int Start; // 시작 위치
	static int[] End = new int[4]; // End 위치의 각 회전된 위치값

	static int[][] Deltas = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(read.readLine());
		Max = K * 4;
		for (int r = 0; r < Max; r++) {
			String inputLine = read.readLine();
			for (int c = 0; c < Max; c++) {
				char tempChar = inputLine.charAt(c);
				if (tempChar == 'S') {
					Start = r * Max + c;
					Map[r][c][0] = true;
				} else if (tempChar == 'E') {
					End[0] = r * Max + c;
					Map[r][c][0] = true;
				} else {
					Map[r][c][0] = tempChar == '.';
				}
			}
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setRotateEnd(1); // End위치의 회전값 구하기
		setRotatedSec(); // 각 섹션의 회전된 케이스 구하기
		write.write(getResult() + "\n");
		write.close();
	}

	private static void setRotateEnd(int rotate) {
		// End가 포함된 구역이 rotate만큼 회전했을 때의 End 위치를 End[rotate]에 기록
		End[rotate] = getRotatedLoc(End[rotate - 1] / Max, End[rotate - 1] % Max);
		if (rotate == 3)
			return;
		setRotateEnd(rotate + 1);
	}

	private static void setRotatedSec() {
		for (int rK = 0; rK < K; rK++) {
			for (int cK = 0; cK < K; cK++) {
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						Map[rK * 4 + c][(cK + 1) * 4 - 1 - r][1] = Map[rK * 4 + r][cK * 4 + c][0];
					}
				}
			}
		}
		for (int rK = 0; rK < K; rK++) {
			for (int cK = 0; cK < K; cK++) {
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						Map[(rK + 1) * 4 - 1 - r][(cK + 1) * 4 - 1 - c][2] = Map[rK * 4 + r][cK * 4 + c][0];
					}
				}
			}
		}
		for (int rK = 0; rK < K; rK++) {
			for (int cK = 0; cK < K; cK++) {
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						Map[(rK + 1) * 4 - 1 - c][cK * 4 + r][3] = Map[rK * 4 + r][cK * 4 + c][0];
					}
				}
			}
		}
	}

	private static int getResult() { // BFS
		// [회전된 구역의 번호, 회전된 구역의 회전 수, 현재 위치, 이동 시간]
		int[] start = { getSecNum(Start / Max, Start % Max), 0, Start, 0 };
		Queue<int[]> caseQ = new LinkedList<>();
		caseQ.add(start);
		boolean[][][] vis = new boolean[Max][Max][4]; // 각 회전된 섹션에서의 방문 배열
		int result = -1;

		breakWhile: while (!caseQ.isEmpty()) {
			int[] caseInfo = caseQ.poll();
			int thisSecNum = caseInfo[0]; // 현재 구역 번호
			int thisSecRotate = caseInfo[1]; // 현재 구역의 회전 횟수
			int thisLoc = caseInfo[2]; // 현재 위치
			int thisTime = caseInfo[3]; // 이동 시간

			for (int[] dir : Deltas) {
				int nextR = thisLoc / Max + dir[0];
				int nextC = thisLoc % Max + dir[1];
				if (!isIn(nextR, nextC)) // 범위 밖
					continue;

				int nextSecNum = getSecNum(nextR, nextC); // 이동한 위치의 구역 번호
				int nextSecRotate = thisSecNum == nextSecNum ? thisSecRotate : 0; // 이동한 구역의 회전 정보
				// (현재와 같은 구역이면 회전 유지, 다른 구역이면 0회전)
				if (!Map[nextR][nextC][nextSecRotate] || vis[nextR][nextC][nextSecRotate]) { // 벽에 막힘 / 이미 방문
					continue;
				}
				vis[nextR][nextC][nextSecRotate] = true; // 방문처리

				if (End[nextSecRotate] == nextR * Max + nextC) { // End에 도착
					result = thisTime + 1;
					break breakWhile;
				}
				// 케이스 추가 [다음 구역, 다음 구역을 한 번 회전, 이동한 위치가 회전된 위치, 이동시간 + 1]
				caseQ.add(new int[] { nextSecNum, (nextSecRotate + 1) % 4, getRotatedLoc(nextR, nextC), thisTime + 1 });

			}
		}
		return result;
	}

	private static int getSecNum(int r, int c) { // r,c위치의 섹션 번호
		return (r / 4) * K + c / 4;
	}

	private static int getRotatedLoc(int r, int c) { // r,c위치가 한 번 회전된 위치
		int rK = r / 4;
		int cK = c / 4;
		r %= 4;
		c %= 4;
		return (rK * 4 + c) * Max + (cK * 4 + (3 - r));
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < Max && c >= 0 && c < Max;
	}

}