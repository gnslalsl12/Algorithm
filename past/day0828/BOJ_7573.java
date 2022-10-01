package day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_7573 {
	static int N;	//2 ~ 10,000	//맵 크기
	static int I;	//4 ~ 100		//그물 길이
	static int M;	//1 ~ 100		//물고기 수
	static boolean [] FishPop;
	static ArrayList<dirXY> Fishes = new ArrayList<>();
	static int MaxFish;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		I = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		FishPop = new boolean[N*N];	//물고기가 있는 위치
		MaxFish = Integer.MIN_VALUE;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken())-1;
			int y = Integer.parseInt(tokens.nextToken())-1;
			Fishes.add(new dirXY(x,y));
			FishPop[XYtoInt(x, y)] = true;
		}
		MaxFish = 0;
		for(dirXY tempFish : Fishes) {
			for(int w = 1; w < I/2; w++) {
				ThrowMove(tempFish, w);
			}
		}
		System.out.print(MaxFish);
	}
	
	private static void ThrowMove(dirXY input, int width) {	//물고기 하나 기준으로 그물 좌표를 전부 이동해 던져보기
		int fishcount = 0;
		int high = I/2 - width;
		for(int tempx = input.x - high; tempx <= input.x; tempx++) {
			for(int tempy = input.y - width; tempy <= input.y; tempy++) {
				fishcount = 0;
				if(!isIn(tempx, tempy)) continue;
				if(!isIn(tempx + high, tempy + width)) continue;
				for(int temphigh = 0; temphigh <= high; temphigh++) {
					for(int tempwidth = 0; tempwidth <= width; tempwidth++) {
						if(FishPop[XYtoInt(tempx + temphigh, tempy + tempwidth)]) fishcount++;
					}
				}
				MaxFish = Math.max(MaxFish, fishcount);
			}
		}
	}
	
	private static int XYtoInt(int x, int y) {
		return x*N + y;
	}
	
	private static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
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