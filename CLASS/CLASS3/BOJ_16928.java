package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
	static int[] maps = new int[101];
	static boolean[] visits = new boolean[101];
	static int[] count = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N + M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			maps[from] = to;
		}
		count[1] = 0;
		visits[1] = true;
		System.out.println(bfs());
	}

	private static int bfs() {
		int result = 0;
		Queue<Integer> BFSQ = new LinkedList<Integer>();
		BFSQ.add(1); // 시작점
		while (!BFSQ.isEmpty()) {
			int now = BFSQ.poll();
			for (int roll = 1; roll <= 6; roll++) {
				int next = now + roll;
				if (next > 100)
					break;
				if (visits[next])
					continue; // 이미 방문한 자리
				// 되돌아서 온다고 해도 방문처리 됐다는 뜻은 되돌아오는 타이밍보다 먼저 해당 자리를 지나갔기 때문에 count가 더 작음이 확보됨
				visits[next] = true;
				if (maps[next] != 0) { // 사다리나 뱀 발견
					int jump = maps[next];
					if (!visits[jump]) { // 점프할 곳이 아직 방문 전임(점프하는게 더 빨리 오는 거야)
						BFSQ.add(jump);
						visits[jump] = true;
						count[jump] = count[now] + 1; // 현재 위치에서 주사위 한 번 굴리고 jump바로 했으니 + 1
					} // 방문한 곳이면 그냥 지나쳐
				} else {
					BFSQ.add(next);
					count[next] = count[now] + 1;
				}
			}
			if (now == 100) { // 가장 먼저 100이 확인될 떄가 가장 빠른 count
				result = count[100];
			}
		}
		return result;
	}

}
