package day0910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10875 {
	static int L;
	static int N;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int direction;
	static Queue<Timing> RotateTiming = new LinkedList<>();
	static ArrayList<TraceLine> Traces = new ArrayList<>();
	static boolean Dead;
	static boolean FinalCheck;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(read.readLine());
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			RotateTiming.add(new Timing(Integer.parseInt(tokens.nextToken()), tokens.nextToken().charAt(0)));
		} // 매핑 완료
		int[] Snake = { 0, 0 };
		direction = 0;
		long resultcount = 0;
		while (!RotateTiming.isEmpty()) {
			Dead = false;
			Timing temp = RotateTiming.poll();
			int AbleLen = AvailToGo(Snake, direction, temp.timing); // 갈 수 있는 거리
			if (Dead) { // 물고 죽었거나 벽 밖을 나감
				resultcount += AbleLen;
				break;
			} else {
				int nextx = Snake[0] + deltas[direction][0] * AbleLen;
				int nexty = Snake[1] + deltas[direction][1] * AbleLen;
				Traces.add(new TraceLine(Snake[0], Snake[1], nextx, nexty));
				Snake[0] = nextx;
				Snake[1] = nexty;
				resultcount += AbleLen;
				direction = (direction + temp.rotate + 4) % 4;
			}
		}
		if (!Dead) { // 아직 죽지 않았다면
			int lastAbleLen = AvailToGo(Snake, direction, 3 * L);
			resultcount += lastAbleLen;
		}
		System.out.println(resultcount);
	}

	private static int AvailToGo(int[] nowXY, int delta, int templen) {
		int minlen = Integer.MAX_VALUE;

		for (int i = 0; i < Traces.size() - 2; i++) {
			if (delta == 0 || delta == 2) { // 가로 이동시

				if (Traces.get(i).fromx == Traces.get(i).tox) { // 가로 선분 만났을 때
					if (nowXY[0] == Traces.get(i).fromx) { // 같은 가로 위치에 있을 때
						if (delta == 0 && nowXY[1] < Traces.get(i).fromy) { // 오른쪽 갈 떄 : 해당 흔적 시작위치까지의 거리가 templen보다 짧으면
																			// 안됨
							int tempavaillen = Math.abs(Traces.get(i).fromy - nowXY[1]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						} else if (delta == 2 && nowXY[1] > Traces.get(i).toy) { // 왼쪽 갈 떄 : 해당 흔적 위치의 끝부분 거리가 templen
																					// 보다 짧으며 ㄴ안됨
							int tempavaillen = Math.abs(nowXY[1] - Traces.get(i).toy);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						}
					}
				}

				else { // 세로 선분 만났을 때 //fromy == toy
					if (Traces.get(i).fromx <= nowXY[0] && Traces.get(i).tox >= nowXY[0]) { // x값이 흔적 내부일 때
						if (delta == 0 && nowXY[1] < Traces.get(i).fromy) { // 오른쪽 가다가 세로 만났을 때
							int tempavaillen = Math.abs(Traces.get(i).fromy - nowXY[1]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						} else if (delta == 2 && nowXY[1] > Traces.get(i).fromy) {
							int tempavaillen = Math.abs(Traces.get(i).fromy - nowXY[1]);
							if (templen >= tempavaillen) { // 0 없애도 되나?
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						}
					}
				}

			}

			else if (delta == 1 || delta == 3) { // 세로 이동시

				if (Traces.get(i).fromy == Traces.get(i).toy) { // 세로 선분과 만났을 떄
					if (nowXY[1] == Traces.get(i).fromy) { // 같은 세로 위치에 있을 떄
						if (delta == 1 && nowXY[0] < Traces.get(i).fromx) { // 아래로 갈 떄
							int tempavaillen = Math.abs(Traces.get(i).fromx - nowXY[0]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						} else if (delta == 3 && nowXY[0] > Traces.get(i).toy) { // 위로 갈 떄
							int tempavaillen = Math.abs(Traces.get(i).tox - nowXY[0]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						}
					}
				}

				else { // 가로 선분 만났을 떄 (fromx == tox)
					if (Traces.get(i).fromy <= nowXY[1] && Traces.get(i).toy >= nowXY[1]) {
						if (delta == 1 && nowXY[0] < Traces.get(i).fromx) { // 아래로 가다가 가로선 만났을 때
							int tempavaillen = Math.abs(Traces.get(i).fromx - nowXY[0]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						} else if (delta == 3 && nowXY[0] > Traces.get(i).fromx) { // 위로 가다가
							int tempavaillen = Math.abs(Traces.get(i).fromx - nowXY[0]);
							if (templen >= tempavaillen) {
								Dead = true;
								minlen = Integer.min(minlen, tempavaillen);
								continue;
							}
						}
					}
				}
			}
		} // 해당 움직임에서 가장 가까운 막힘을 찾음 (흔적만 비교)
		int towall;
		if (minlen == Integer.MAX_VALUE) { // 흔적을 못 만남
			if (delta == 0) {
				towall = L - nowXY[1];
			} else if (delta == 2) {
				towall = L + nowXY[1];
			} else if (delta == 1) {
				towall = L - nowXY[0];
			} else {
				towall = L + nowXY[0];
			}
			towall++;
			if (templen >= towall) { // 벽을 넘어가게됨
				Dead = true;
				return towall;
			} else { // 흔적도 안 만나고 벽보다 전임
				Dead = false;
				return templen;
			}

		} else { // 흔적을 만남
			if (Dead) { // 흔적을 만나게됨
				return minlen;
			} else {
				return templen;
			}
		}
	}

	private static class TraceLine {
		int fromx;
		int tox;
		int fromy;
		int toy;

		public TraceLine(int x, int y, int xx, int yy) {
			this.fromx = Integer.min(x, xx);
			this.tox = Integer.max(x, xx);
			this.fromy = Integer.min(y, yy);
			this.toy = Integer.max(y, yy);
		}

	}

	private static class Timing {
		int timing;
		int rotate;

		public Timing(int timing, char rotate) {
			this.timing = timing;
			if (rotate == 'L') {
				this.rotate = -1;
			} else {
				this.rotate = 1;
			}
		}

	}
}