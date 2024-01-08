import java.util.*;
import java.io.*;

public class Main {
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	static int R, C;
	static boolean[][] Map = new boolean[101][101];
	static Queue<Integer> Throws = new LinkedList<>();

	static int[][] Deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		for (int r = 0; r < R; r++) {
			String inputLine = read.readLine();
			for (int c = 0; c < C; c++) {
				Map[r][c] = inputLine.charAt(c) == 'x';
			}
		}
		read.readLine();
		tokens = new StringTokenizer(read.readLine());
		while (tokens.hasMoreTokens()) {
			Throws.add(Integer.parseInt(tokens.nextToken()));
		}
		read.close();
	}

	private static void solv() throws IOException {
		boolean left = false;
		while (!Throws.isEmpty()) { // 주어진 입력만큼 던지기
			setThrow(R - Throws.poll(), left = !left); // 왼쪽 / 오른쪽 번갈아 던지기
		}
		printMap();
	}

	private static void printMap() throws IOException {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				write.write(Map[r][c] ? 'x' : '.');
			}
			write.write("\n");
		}
		write.close();
	}

	private static void setThrow(int i, boolean left) {
		int startJ = left ? 0 : C - 1;
		int endJ = left ? C - 1 : 0;
		int addJ = left ? 1 : -1;
		for (int j = startJ; left ? j <= endJ : j >= endJ; j += addJ) {
			if (Map[i][j]) { // 막대기가 만난 첫 미네랄 위치
				Map[i][j] = false; // 해당 미네랄 부수기
				boolean[][] vis = new boolean[R][C]; // BFS 탐색 방문배열
				vis[i][j] = true; // 부순 위치 방문처리
				for (int[] dir : Deltas) {
					int nextI = i + dir[0];
					int nextJ = j + dir[1];
					if (!isIn(nextI, nextJ) || vis[nextI][nextJ] || !Map[nextI][nextJ]) // 범위 밖 / 이미 방문 / 빈공간
						continue; // 패스
					if (isSeperated(nextI, nextJ, vis)) // 붙어있던 미네랄의 위치에서부터 탐색해서 공중에 떠있는 클러스터인지 확인
						return; // 공중에 떠있다면 해당 케이스 종료(한 번에 하나씩 떨어지는 조건)
				}
				return;
			}
		}
	}

	private static boolean isSeperated(int i, int j, boolean[][] vis) {
		Queue<Integer> tempQ = new LinkedList<>();
		tempQ.add(i * C + j);
		vis[i][j] = true;

		int[][] dropList = new int[2][C]; // 열마다 마닥/윗쪽 미네랄 위치 배열
		Arrays.fill(dropList[1], -1);
		Arrays.fill(dropList[0], 101);
		dropList[0][j] = dropList[1][j] = i; // 첫 미네랄 위치의 바닥/윗쪽 미네랄 위치값 지정
		boolean floated = true; // 공중에 뜬 클러스터인가 (default true)

		while (!tempQ.isEmpty()) { // BFS
			int loc = tempQ.poll();
			int tempI = loc / C;
			int tempJ = loc % C;
			for (int[] dir : Deltas) {
				int nextI = tempI + dir[0];
				int nextJ = tempJ + dir[1];
				if (!isIn(nextI, nextJ) || vis[nextI][nextJ] || !Map[nextI][nextJ]) // 범위 밖 / 방문 / 빈공간
					continue;// 패스
				if (nextI == R - 1) { // 땅에 붙어있는 미네랄 있음
					floated = false; // 공중에 떠있지 않음
				}
				if (floated) { // 공중에 뜬 클러스터라면
					dropList[0][nextJ] = Math.min(dropList[0][nextJ], nextI); // 해당 열의 가장 아래 미네랄 위치
					dropList[1][nextJ] = Math.max(dropList[1][nextJ], nextI); // 해당 열의 가장 위 미네랄 위치
				}
				vis[nextI][nextJ] = true;
				tempQ.add(nextI * C + nextJ);
			}
		}

		if (floated) { // 공중에 떠있다면
			setDrop(dropList); // 바닥으로 내리기
			return true; // 분리됨
		}
		return false; // 분리되지 않음
	}

	private static void setDrop(int[][] dropList) {
		int minGap = 101; // 해당 클러스터가 떨어지기까지의 가장 가까운 갭
		for (int j = 0; j < C; j++) {
			if (dropList[1][j] == -1)
				continue;
			int i = dropList[1][j];
			for (int gap = 1; i + gap < R; gap++) { // 바닥 미네랄에서부터 내려봤을 때
				if (Map[i + gap][j]) { // 다른 미네랄에 닿음
					minGap = Math.min(minGap, gap - 1);
					break;
				} else if (i + gap == R - 1) { // 바닥에 닿음
					minGap = Math.min(minGap, gap);
					break;
				}
			}
		}
		for (int j = 0; j < C; j++) { // j열에서
			if (dropList[1][j] == -1)
				continue;
			int highI = dropList[0][j]; // 가장 위 미네랄 위치
			int lowI = dropList[1][j]; // 가장 아래 미네랄 위치
			for (int i = lowI; i >= highI; i--) { // minGap만큼 클러스터 내리기
				Map[i + minGap][j] = Map[i][j];
				Map[i][j] = false;
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}

}