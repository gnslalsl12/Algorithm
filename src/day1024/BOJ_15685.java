package day1024;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15685 {
	static int N;
	static Queue<dragon> DRG = new LinkedList<>();
	static int[][] deltas = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static boolean[][] maps = new boolean[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int y = Integer.parseInt(tokens.nextToken());
			int x = Integer.parseInt(tokens.nextToken());
			int dir = Integer.parseInt(tokens.nextToken());
			int age = Integer.parseInt(tokens.nextToken());
			DRG.add(new dragon(x, y, dir, age));
		}
		while (!DRG.isEmpty()) {
			rotate(DRG.poll());
		}
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (maps[i][j] && maps[i + 1][j + 1] && maps[i + 1][j] && maps[i][j + 1])
					count++;
			}
		}
		System.out.println(count);
	}

	private static int spin(int dir) {
		if (dir == 3)
			return 0;
		return dir + 1;
	}

	private static void rotate(dragon temp) {

		int size = temp.dirhistory.size();
		Deque<Integer> tempsave = new LinkedList<>();
		if (temp.first) {
			temp.first = false;
			temp.move(temp.dir);
			DRG.add(temp);
			return;
		}
		if (temp.age == 0)
			return;
		for (int i = 0; i < size; i++) {
			int popdir = temp.dirhistory.poll();
			tempsave.addLast(popdir);
			int nextdir = spin(popdir); // 돌려
			temp.move(nextdir); // 움직여
			tempsave.addFirst(nextdir);
		}
		temp.dirhistory = tempsave;
		temp.age--;
		if (temp.age == 0)
			return;
		DRG.add(temp);
	}

	private static class dragon {
		int x;
		int y;
		int dir;
		boolean first;
		int age;
		Queue<Integer> dirhistory = new LinkedList<>();

		public dragon(int x, int y, int dir, int age) {
			super();
			this.x = x;
			this.y = y;
			this.first = true;
			maps[this.x][this.y] = true;
			this.dir = dir;
			this.age = age;
			this.dirhistory.add(dir);
		}

		public void move(int dir) {
			this.x += deltas[dir][0];
			this.y += deltas[dir][1];
			maps[this.x][this.y] = true;
		}
	}
}