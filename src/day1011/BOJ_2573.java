package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int N, M;
	static int[][] maps;
	static Queue<dirXY> Cls = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean splited = false;
	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(tokens.nextToken());
				maps[i][j] = temp;
				if (temp != 0) {
					Cls.add(new dirXY(i, j));
				}
			}
		}
		time = 0;
		while(!splited) {
			time++;
			melting();
		}
		System.out.println(time);
	}
	
	private static void melting() {
		int size = Cls.size();
		Queue<dirXY> readytomelt = new LinkedList<>();
		while(size-- > 0) {
			dirXY temp = Cls.poll();
			int meltcount = 0;
			for(int dir = 0; dir < 4; dir++) {
				if(maps[temp.x+ deltas[dir][0]][temp.y+deltas[dir][1]] == 0) {
					meltcount++;
				}
			}
			if(maps[temp.x][temp.y] <= meltcount) {
				readytomelt.add(temp);
			}else {
				maps[temp.x][temp.y] -= meltcount;
				Cls.add(temp);
			}
		}
		if(Cls.isEmpty()) {
			splited = true;
			time = 0;
			return;
		}
		while(!readytomelt.isEmpty()) {
			dirXY temp2= readytomelt.poll();
			maps[temp2.x][temp2.y] = 0;
		}
		dirXY start = Cls.peek();
		readytomelt.add(start);
		int count = 1;
		boolean [][] visited = new boolean [N][M];
		visited[start.x][start.y]= true; 
		while(!readytomelt.isEmpty()) {
			dirXY temp3 = readytomelt.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nextx = temp3.x  +deltas[dir][0];
				int nexty = temp3.y + deltas[dir][1];
				if(maps[nextx][nexty] != 0) {
					if(visited[nextx][nexty]) continue;
					visited[nextx][nexty] = true;
					count++;
					readytomelt.add(new dirXY(nextx, nexty));
				}
			}
		}
		if(count != Cls.size()) splited = true;
	}

	private static class dirXY {
		int x;
		int y;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}