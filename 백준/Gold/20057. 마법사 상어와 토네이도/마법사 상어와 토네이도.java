import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] Map;
	static int Result;
	static int[][] SpreadDirs;
	static int[][] nextDir;

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
		Map = new int[N][N];
		Result = 0;
		SpreadDirs = new int[][] { { -2, 0, 5 }, { -1, 1, 10 }, { -1, -1, 10 }, { 0, 1, 7 }, { 0, -1, 7 }, { 0, 2, 2 },
				{ 0, -2, 2 }, { 1, 1, 1 }, { 1, -1, 1 }, { -1, 0, 0 } };
		nextDir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		read.close();
	}

	private static void solv() {
		setAdvanceTorn();
	}

	private static void setAdvanceTorn() { // 토네이도 이동
		int[] current = new int[] { N / 2, N / 2 }; // 현재 토네이도 위치
		int[] dirMaxStacks = new int[] { 2, 2, 1, 1 }; // 토네이도 방향을 바꿔야하는 시기
		int dirTempStack = 0; // 현재 토네이도 방향으로 이동한 스택
		int dir = 3; // 현재 토네이도 방향
		while (current[0] != 0 || current[1] != 0) {
			current[0] += nextDir[dir][0]; // 토네이도 위치 이동
			current[1] += nextDir[dir][1];
			setSpreadSands(current, dir); // 해당 위치로 이동해 모래 흩뿌리기
			dirTempStack++; // 해당 방향으로 이동한 스택 쌓기
			if (dirTempStack == dirMaxStacks[dir]) { // 해당 방향 스택이 모두 찼으면 방향 전환 및 스택 초기화
				dirMaxStacks[dir] += 2; // 다음에 같은 방향으로 이동해야할 때 최대 스택 갱신
				dirTempStack = 0;
				dir = (dir + 3) % 4;
			}
		}
	}

	private static void setSpreadSands(int[] loc, int dir) { // 모래 흩뿌리기
		int sand = Map[loc[0]][loc[1]]; // 알파값
		if (sand < 10) { // 뿌려야할 모래가 10 미만이라면 (10퍼센트 미만 모두 1 보다 작을 떄)
			int nextI = loc[0] + nextDir[dir][0];
			int nextJ = loc[1] + nextDir[dir][1];
			if (isIn(nextI, nextJ))
				Map[nextI][nextJ] += sand;
			else
				Result += sand;
			Map[loc[0]][loc[1]] = 0;
			return; // 그대로 그 모래만큼을 알파위치로 이동하고 끝
		}
		for (int[] d : SpreadDirs) { // 흩뿌려질 위치,비율 값 배열
			int dI = d[0];
			int dJ = d[1];
			int tempSand = d[2] == 0 ? sand : Map[loc[0]][loc[1]] * d[2] / 100; // 알파 위치로 이동해야한다면 남은 모래만큼 /아니라면 비율만큼
			if (dir == 1) { // 토네이도 이동 방향에 맞게 흩뿌려질 위치 갱신
				dI = d[1];
				dJ = d[0] * -1;
			} else if (dir == 2) {
				dI = d[0] * -1;
			} else if (dir == 3) {
				dI = d[1];
				dJ = d[0];
			}
			int nextI = loc[0] + dI; // 흩뿌려질 위치겂
			int nextJ = loc[1] + dJ;
			if (isIn(nextI, nextJ)) { // 범위 안이라면 해당 위치에 모래 쌓기
				Map[nextI][nextJ] += tempSand;
			} else { // 밖이라면 결과값에 추가
				Result += tempSand;
			}
			sand -= tempSand;
		}
		Map[loc[0]][loc[1]] = 0; // 원래 모래 없애기
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}