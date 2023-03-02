package day0110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16434 {
	static int N;
	static long Hatk, Hhp, Hmax;
	static Queue<int[]> Rooms = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Hatk = Integer.parseInt(tokens.nextToken());
		Hmax = 1;
		Hhp = 1;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int t = Integer.parseInt(tokens.nextToken());
			int a = Integer.parseInt(tokens.nextToken());
			int h = Integer.parseInt(tokens.nextToken());
			if (t == 1) {
				Rooms.add(new int[] { a, h });
			} else {
				Rooms.add(new int[] { -1 * a, h });
			}
		}
		while (!Rooms.isEmpty())
			enterRoom();
		write.write(Hmax + "\n");
		write.close();
		read.close();
	}

	private static void enterRoom() {
		int[] temp = Rooms.poll();
		if (temp[0] < 0) {
			Hatk -= temp[0];
			Hhp += temp[1];
			Hmax = Math.max(Hhp, Hmax);
		} else {
			fightWith(temp[0], temp[1]);
		}
	}

	private static void fightWith(int atk, int hp) {
		while (true) {
			hp -= Hatk;
			if (hp <= 0)
				break;
			Hhp -= atk;
			if (Hhp <= 0) {
				Hmax -= Hhp;
				Hhp = 0;
			}
		}
		if (Hhp <= 0) {
			Hmax++;
			Hhp = 1;
		}
	}

}
