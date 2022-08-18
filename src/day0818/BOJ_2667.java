package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667 {
	static int N, maps[][];
	static ArrayList<Integer> homes;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new int [N][N];
		for(int i = 0; i  < N ; i++) {
			String[] temp = read.readLine().split("");
			for(int j = 0; j < N ; j++) {
				maps[i][j] = Integer.parseInt(temp[j]);
			}
		}// 매핑 완료
		homes = new ArrayList<>();
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j < N ; j++) {
				if(maps[i][j] == 1) {
					BFS(new dirXY(i,j),1);
				}
			}
		}
		Collections.sort(homes);
		System.out.println(homes.size());
		for(int i : homes) {
			System.out.println(i);
		}
	}
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}}; 
	static void BFS(dirXY temp, int count) {
		Queue<dirXY> tempQ = new LinkedList<>();
		maps[temp.x][temp.y]= 0; 
		tempQ.add(temp);
		while(!tempQ.isEmpty()) {
			dirXY tempH = tempQ.poll();
			for(int i = 0; i < 4 ; i++) {
				if(isIn(tempH.x + deltas[i][0],tempH.y + deltas[i][1])) {
					if(maps[tempH.x + deltas[i][0]][tempH.y + deltas[i][1]] == 1) {
						count++;
						tempQ.add(new dirXY(tempH.x + deltas[i][0],tempH.y + deltas[i][1]));
						maps[tempH.x + deltas[i][0]][tempH.y + deltas[i][1]] = 0;
					}	
				}
			}
		}
		homes.add(count);
	}
	static boolean isIn(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N)? true : false;
	}
	static class dirXY{
		int x;
		int y;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}