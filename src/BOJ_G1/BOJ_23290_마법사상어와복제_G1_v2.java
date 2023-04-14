package BOJ_G1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_23290_마법사상어와복제_G1_v2 {
	static int M, S;
	static int[][] Fdir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int[][][] Sdir = new int[64][3][2];
	static ArrayList<Integer>[][] Map, CopyMap;
	static int[][] ScentMap = new int[5][5];
	static int[] Shark;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		Map = new ArrayList[5][5];
		CopyMap = new ArrayList[5][5];
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				Map[r][c] = new ArrayList<>();
				CopyMap[r][c] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken()) - 1;
			Map[r][c].add(d);
		}
		tokens = new StringTokenizer(read.readLine());
		Shark = new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) };
		getSdir();
		write.write(solv() + "");
		write.write("\n");
		write.close();
		read.close();
	}

	private static void getSdir() { // 상어 이동 방향 저장
		int count = 63;
		int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		for (int dir1 = 0; dir1 < 4; dir1++) {
			for (int dir2 = 0; dir2 < 4; dir2++) {
				for (int dir3 = 0; dir3 < 4; dir3++) {
					Sdir[count--] = new int[][] { delta[dir1], delta[dir2], delta[dir3] };
				}
			}
		}
	}

	private static int solv() {
		for (int i = 0; i < S; i++) {
			doCopy();
			doMoveFish();
			doMoveShark();
			removeScent();
			doPaste();
		}
		return getCount();
	}

	private static void doCopy() { // 물고기 복사하기 (그냥 CopyMap에 다 밀어넣고 Map은 비우기)
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				CopyMap[r][c].addAll(Map[r][c]);
				Map[r][c].clear();
			}
		}
	}

	private static void doMoveFish() { // CopyMap에서 하나씩 복사해서 Map에 이동한 상태로 추가하기
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				int size = CopyMap[r][c].size();
				for (int s = 0; s < size; s++) {
					int fishdir = CopyMap[r][c].get(s);
					boolean found = false;
					int nextR = r;
					int nextC = c;
					int tempdir = fishdir;
					for (int dir = 0; dir < 8; dir++) {
						tempdir = (fishdir + 8 - dir) % 8;
						nextR = r + Fdir[tempdir][0];
						nextC = c + Fdir[tempdir][1];
						if (!isIn(nextR, nextC) || ScentMap[nextR][nextC] < 0
								|| (Shark[0] == nextR && Shark[1] == nextC))
							continue;
						found = true; // 움직일 방향 찾음
						break;
					}
					if (found) {
						Map[nextR][nextC].add(tempdir); // 바뀐 위치에 바뀐 방향
					} else {
						Map[r][c].add(fishdir); // 원래 위치에 원래 방향
					}
				}

			}
		}

	}

	private static void doMoveShark() { // 상어 움직이기
		int maxdir = 0;
		int maxcount = -1;
		for (int dir = 0; dir <= 63; dir++) { // 사전순 큰 방향부터
			boolean[][] visited = new boolean[5][5];
			int tempcount = 0;
			int tempR = Shark[0];
			int tempC = Shark[1];
			boolean able = true;
			for (int step = 0; step < 3; step++) {
				tempR += Sdir[dir][step][0];
				tempC += Sdir[dir][step][1];
				if (!isIn(tempR, tempC)) {
					able = false;
					break;
				}
				if (!visited[tempR][tempC]) {
					tempcount += Map[tempR][tempC].size();
					visited[tempR][tempC] = true;
				}
			}
			if (able && maxcount <= tempcount) {
				maxcount = tempcount;
				maxdir = dir;
			}
		} // 상어 최종 방향 정해짐
		for (int[] move : Sdir[maxdir]) {
			Shark[0] += move[0];
			Shark[1] += move[1];
			if (Map[Shark[0]][Shark[1]].size() > 0) {
				Map[Shark[0]][Shark[1]].clear();
				ScentMap[Shark[0]][Shark[1]] = -3;
			}
		}
	}

	private static void removeScent() { // 냄새 하나씩 빼주기
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				if (ScentMap[r][c] < 0)
					ScentMap[r][c]++;
			}
		}
	}

	private static void doPaste() { // CopyMap의 초기 물고기들 Map에 붙여넣기
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				for (int fish : CopyMap[r][c]) {
					Map[r][c].add(fish);
				}
				CopyMap[r][c].clear();
			}
		}
	}

	private static int getCount() { // Map 상 최종 물고기 수
		int count = 0;
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				count += Map[r][c].size();
			}
		}
		return count;
	}

	private static boolean isIn(int r, int c) {
		return r >= 1 && r <= 4 && c >= 1 && c <= 4;
	}

}
