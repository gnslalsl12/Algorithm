package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_최적경로 {
	static int N;
	static ArrayList<dirXY> Location;
	static ArrayList<Integer> CDist;
	static ArrayList<Integer> HDist;
	static ArrayList<ArrayList<Integer>> Distances;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for(int test = 1; test <= T; test++) {
			N = Integer.parseInt(read.readLine());
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			Location = new ArrayList<>();
			dirXY Campus = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			dirXY Home = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			for(int i = 0; i < N ; i++) {
				dirXY temp = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
				Location.add(temp);
			}
			Distances = new ArrayList<>();
			CDist = new ArrayList<>();
			HDist = new ArrayList<>();
			//회사부터의 거리 array
			ArrayList<Integer> tempA = new ArrayList<>();
			for(int i = 0; i < N ; i ++) {
				int tempD = CalDist(Campus, Location.get(i));
				CDist.add(tempD);
			}
			
			//각 건물에서 건물까지 거리	(0은 자기 자신까지 거리)
			for(int c = 0; c < N ; c++) {
				tempA = new ArrayList<>();
				for(int i = 0; i < N ; i ++) {
					int tempD = CalDist(Location.get(c), Location.get(i));
					tempA.add(tempD);
				}
				Distances.add(tempA);	
			}
			
			//각 건물에서 집까지 거리
			for(int i = 0; i < N ; i ++) {
				int tempD = CalDist(Home, Location.get(i));
				HDist.add(tempD);
			}
			min = Integer.MAX_VALUE;
			boolean[] flags = new boolean[N];
			SUM(0,0,0,flags);
			sb.append("#" + test + " " + min + "\n");
		}
		System.out.println(sb);
	}
	static int min;
	private static void SUM(int sum,int from, int count, boolean[] flag) {
		if(count == N) {
			sum += HDist.get(from);
			min = Math.min(min, sum);
			return;
		}
		if(count == 0) {//초기는 회사에서 출발
			for(int i = 0; i < N ; i++) {
				sum = CDist.get(i); //i번째 건물까지의 거리
				flag[i] = true;
				SUM(sum, i, count+1,flag);
				flag[i] = false;
			}
		}else { //from 건물에서 B 건물까지
			for(int B = 0; B < N ; B++) {
				int tempN = Distances.get(from).get(B);
				if(tempN != 0 && flag[B] == false) {
					flag[B] = true;
					SUM(sum + tempN, B, count+1,flag);
					flag[B] = false;
				}
			}
		}
	}
	private static int CalDist(dirXY from, dirXY to) {
		return Math.abs(from.x-to.x) + Math.abs(from.y - to.y);
	}
	private static class dirXY{
		int x;
		int y;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}