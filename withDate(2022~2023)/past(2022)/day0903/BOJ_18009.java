package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18009 {
	static int N, M, G, R;
	static int[][] maps;
	static int[][] tempmaps;
	static ArrayList<dirXY> AvailSeed = new ArrayList<>(); // 배양 가능 위치
	static ArrayList<int[]> SelectedRG = new ArrayList<>(); // 배양 케이스 조합
	static int BloomCount;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		G = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		tempmaps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if (maps[i][j] == 2) {
					AvailSeed.add(new dirXY(i, j));
				}
			}
		}
		CombG(0, 0, new int[R + G]);
		int len = SelectedRG.size();
		for (int i = 0; i < len; i++) {
			CombR(0, 0, SelectedRG.remove(0));
		} // R과 G 배양 위치 경우의 수 완료
		int maxv = Integer.MIN_VALUE;
		for (int[] tempcase : SelectedRG) {
			BloomCount = 0;
			ArrayList<dirXY> PushCase = new ArrayList<>();
			for (int i = 0; i < tempcase.length; i++) {
				if (i < G) {
					dirXY tempGXY = AvailSeed.get(tempcase[i]);
					tempGXY.setCount(1); // 초록색은 양수
					PushCase.add(tempGXY);
				} else {
					dirXY tempRXY = AvailSeed.get(tempcase[i]);
					tempRXY.setCount(-1);
					PushCase.add(tempRXY);
				}
			}
			BFS(PushCase);
			for (int ml = 0; ml < N; ml++) {
				Arrays.fill(tempmaps[ml], 0);
			} // 맵 초기화
			maxv = Integer.max(maxv, BloomCount);
		}
		System.out.println(maxv);
	}

	private static void BFS(ArrayList<dirXY> input) {
		Queue<dirXY> BFSQ = new LinkedList<>();

		for (dirXY put : input) {
			BFSQ.add(put);
			tempmaps[put.x][put.y] = put.count;
		}

		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();

			if (tempmaps[temp.x][temp.y] == 3000)
				continue; // 현재 자리에 꽃이 피어버림 => 상하좌우 탐색 진행 X

			int nextcount = temp.count + (temp.count / Math.abs(temp.count)); // R은 -1, G는 +1
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty))
					continue; // 범위 밖
				if (maps[nextx][nexty] == 0)
					continue; // 벽임
				if (tempmaps[nextx][nexty] == 3000)
					continue; // 이미 꽃이 핀 곳

				if (tempmaps[nextx][nexty] == 0) { // 빈 곳
					tempmaps[nextx][nexty] = nextcount;
					BFSQ.add(new dirXY(nextx, nexty, nextcount)); // 이전 좌표를 기억하는 새로운 좌표
					continue;
				}

				if (tempmaps[nextx][nexty] * temp.count > 0) { // 같은 배양액을 만남
					if (Math.abs(tempmaps[nextx][nexty]) > Math.abs(nextcount)) { // 방금 움직인 카운트가 더 빨라
						tempmaps[nextx][nexty] = nextcount;
						BFSQ.add(new dirXY(nextx, nexty, nextcount));
						continue;
					} else { // 더 느리게 오거나 동시 도착
						continue;
					}
				}

				else { // 다른 배양액을 만남
					if (Math.abs(tempmaps[nextx][nexty]) == Math.abs(nextcount)) { // 동시에 도착한 곳
						tempmaps[nextx][nexty] = 3000; // 꽃 피우기
						BloomCount++;
						continue;
					} else if (Math.abs(tempmaps[nextx][nexty]) > Math.abs(nextcount)) { // 얘가 더 빨리 도착함
						tempmaps[nextx][nexty] = nextcount;
						BFSQ.add(new dirXY(nextx, nexty, nextcount));
						continue;
					} else {// 더 느리게 옴
						continue;
					}
				}
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static void CombG(int countnow, int startpoint, int sel[]) { // 초록 배양 위치 조합
		if (countnow == G) {
			SelectedRG.add(sel.clone());
			return;
		}
		for (int i = startpoint; i < AvailSeed.size(); i++) {
			sel[countnow] = i;
			CombG(countnow + 1, i + 1, sel);
		}
	}

	private static void CombR(int countnow, int startpoint, int sel[]) { // 빨강 배양 위치 조합
		if (countnow == R) {
			SelectedRG.add(sel.clone());
			return;
		}
		for (int i = startpoint; i < AvailSeed.size(); i++) {
			boolean dup = false;
			for (int j = 0; j < G; j++) {
				if (sel[j] == i) {
					dup = true; // 중복값G에 포함된 중복값은 생략
					break;
				}
			} // 포함되지 않은 것
			if (dup == true)
				continue;
			sel[G + countnow] = i;
			CombR(countnow + 1, i + 1, sel);
		}
	}

	private static class dirXY {
		int x;
		int y;
		int count;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}