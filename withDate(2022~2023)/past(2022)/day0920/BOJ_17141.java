package day0920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141 {
	static int N;
	static int M;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] maps;
	static int resultcount;	//결과값
	static int toFill;	//채워야하는 공간 수
	static ArrayList<dirXY> AvailSet = new ArrayList<>();	//바이러스를 배치할 수 있는 좌표
	static ArrayList<int[]> AvailSetAfterComb = new ArrayList<>();	//바이러스 배치 조합을 뽑아올 AvailSet의 Index값 배열

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		toFill = 0;
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if (maps[i][j] == 2) {
					AvailSet.add(new dirXY(i, j, 0));
				}
				if (maps[i][j] == 0) {
					toFill++;
				}

			}
		} // mapping 완료
		resultcount = Integer.MAX_VALUE;
		
		Comb(0, 0, new int[M]);

		for (int[] OnebyOne : AvailSetAfterComb) BFS(OnebyOne);
		
		if (resultcount == Integer.MAX_VALUE)	//전부 실패했으면
			System.out.println(-1);
		else
			System.out.println(resultcount);
	}

	private static void BFS(int[] selectedIndex) {
		boolean[][] visits = new boolean[N][N];
		Queue<dirXY> BFSQ = new LinkedList<>();
		int tempFill = 0;
		for (int i = 0; i < M; i++) {
			dirXY starttemp = AvailSet.get(selectedIndex[i]);
			BFSQ.add(starttemp);
			visits[starttemp.x][starttemp.y] = true;
		}
		int result = Integer.MIN_VALUE;
		while (!BFSQ.isEmpty()) {
			dirXY tempnow = BFSQ.poll();
			result = Math.max(result, tempnow.count);

			for (int dir = 0; dir < 4; dir++) {
				int nextx = tempnow.x + deltas[dir][0];
				int nexty = tempnow.y + deltas[dir][1];

				if (!isIn(nextx, nexty))
					continue; // 범위 밖
				if (visits[nextx][nexty])
					continue;
				if (maps[nextx][nexty] == 1)
					continue; // 벽이면

				visits[nextx][nexty] = true;
				if (maps[nextx][nexty] == 0)	//한 곳 채웠다!
					tempFill++;
				BFSQ.add(new dirXY(nextx, nexty, tempnow.count + 1));
			}
		}
		if (tempFill != toFill)	//맵을 다 채우지 않았는데 끝나버리면 실패처리
			return;
		resultcount = Math.min(resultcount, result);
	}

	private static void Comb(int countnow, int startpoint, int[] sel) {
		if (countnow == M) {
			AvailSetAfterComb.add(sel.clone());
			return;
		}

		for (int i = startpoint; i < AvailSet.size(); i++) {
			sel[countnow] = i;
			Comb(countnow + 1, i + 1, sel);
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class dirXY {
		int x;
		int y;
		int count;

		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}