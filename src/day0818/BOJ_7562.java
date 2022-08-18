package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562 {
	static dirXY Dest;
	static int M;
	static int [] MoveAmount = {1,-1,2,-2};
	static int [][] MoveDir = {{3,0},{1,2},{0,2},{2,0},{2,1},{0,3},{1,3},{3,1}};
	static StringTokenizer tokens;
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(read.readLine());
		for(int test = 1; test <= T; test++) {
			M = Integer.parseInt(read.readLine());
			tokens = new StringTokenizer(read.readLine());
			dirXY Night = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			tokens = new StringTokenizer(read.readLine());
			Dest = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			BFS_Nights(Night,0);
		}
		System.out.println(sb);
	}
	static boolean isIn(int x, int y) {
		return (x >= 0 && x < M*M && y >= 0 && y < M*M)? true : false;
	}
	static ArrayList<dirXY> Dup;
	static void BFS_Nights(dirXY N, int count) {
		Dup = new ArrayList<>();
		Queue<dirXY> NightMoveQ = new LinkedList<>();
		NightMoveQ.add(N);
		while(!NightMoveQ.isEmpty()) {
			dirXY tempN = NightMoveQ.poll();
			int dist = Math.abs(tempN.x-Dest.x) + Math.abs(tempN.y-Dest.y);
			if(tempN.equals(Dest)) {
				sb.append(tempN.c + "\n");
				NightMoveQ = new LinkedList<>();
				return;
			}
			for(int i = 0; i < 8 ; i++) {
				int tempx = tempN.x + MoveAmount[MoveDir[i][0]];
				int tempy = tempN.y + MoveAmount[MoveDir[i][1]];
				if(isIn(tempx,tempy)) {
					if(Math.abs(tempx - Dest.x) + Math.abs(tempy - Dest.y) > dist) continue;
					dirXY tempXY = new dirXY(tempx,tempy,tempN.c+1);
					if(NightMoveQ.contains(tempXY)) continue;
					Dup.add(tempXY);
					NightMoveQ.add(tempXY);
				}
			}
		}
	}
	static class dirXY{
		int x;
		int y;
		int c = 0;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public dirXY(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			dirXY other = (dirXY) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
}