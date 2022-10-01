package day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_7573_trash {
	static int N;	//2 ~ 10,000	//맵 크기
	static int I;	//4 ~ 100		//그물 길이
	static int M;	//1 ~ 100		//물고기 수
	static ArrayList<dirXY> Fishes = new ArrayList<>();
	static boolean [] FishPop;
	static int MaxFish;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		I = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		FishPop = new boolean[(N)*(N)];	//물고기가 있는 위치
		MaxFish = Integer.MIN_VALUE;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			Fishes.add(new dirXY(x-1, y-1));
			FishPop[XYtoInt(x-1, y-1)] = true;
		}
		for(dirXY tempfish : Fishes) {
			for(int w = 1; w < I/2; w++) {
				getSquareEdgeRU(tempfish, w);
				getSquareEdgeRD(tempfish, w);
				getSquareEdgeLD(tempfish, w);
				getSquareEdgeLU(tempfish, w);
			}
		}
		System.out.println(MaxFish);
		
		
	}
	
	private static void getSquareEdgeRU(dirXY input, int W) {		//변환된 xy input
		dirXY[] SquareEdge = new dirXY[2];
		int L = I/2 - W;
		SquareEdge[0] = new dirXY(input.x - L, input.y);		//왼쪽 위
		SquareEdge[1] = new dirXY(input.x, input.y + W);		//오른쪽 아래
		
		if(SquareEdge[0].x < 0) {
			SquareEdge[0].x = 0;
			SquareEdge[1].x = L;
		}
		if(SquareEdge[1].y >= N) {
			SquareEdge[1].y = N-1;
			SquareEdge[0].y = N-1-W;
		}
		
		ThrowHash(SquareEdge);
	}
	private static void getSquareEdgeRD(dirXY input, int W) {
		dirXY[] SquareEdge = new dirXY[2];
		int L = I/2 - W;
		SquareEdge[0] = input;									//왼쪽 위
		SquareEdge[1] = new dirXY(input.x + L, input.y + W);	//오른쪽 아래
		
		if(SquareEdge[1].x >= N) {
			SquareEdge[1].x = N-1;
			SquareEdge[0].x = N-1-L;
		}
		if(SquareEdge[1].y >= N) {
			SquareEdge[1].y = N-1;
			SquareEdge[0].y = N-1-W;
		}
		ThrowHash(SquareEdge);
	}
	private static void getSquareEdgeLD(dirXY input, int W) {
		dirXY[] SquareEdge = new dirXY[2];
		int L = I/2 - W;
		SquareEdge[0] = new dirXY(input.x, input.y - W);		//왼쪽 위
		SquareEdge[1] = new dirXY(input.x + L, input.y);		//오른쪽 아래
		
		if(SquareEdge[1].x >= N) {
			SquareEdge[1].x = N-1;
			SquareEdge[0].x = N-1-L;
		}
		if(SquareEdge[0].y < 0) {
			SquareEdge[0].y = 0;
			SquareEdge[1].y = W;
		}
		ThrowHash(SquareEdge);
	}
	private static void getSquareEdgeLU(dirXY input, int W) {
		dirXY[] SquareEdge = new dirXY[2];
		int L = I/2 - W;
		SquareEdge[0] = new dirXY(input.x - L, input.y - W);	//왼쪽 위
		SquareEdge[1] = input;									//오른쪽 아래
		
		if(SquareEdge[0].x < 0) {
			SquareEdge[0].x = 0;
			SquareEdge[1].x = L;
		}
		if(SquareEdge[0].y < 0) {
			SquareEdge[0].y = 0;
			SquareEdge[1].y = W;
		}
		
		ThrowHash(SquareEdge);
	}
	
	private static void ThrowHash(dirXY [] tempHash) {	//가장 큰 값으로 자동 갱신
		int CapFishCount = 0;
		for(int x = tempHash[0].x; x <= tempHash[1].x; x++) {
			for(int y = tempHash[0].y; y <= tempHash[1].y; y++) {
//				if(!isIn(x,y)) continue;
				if(FishPop[XYtoInt(x, y)]) {	//해당 위치에 물고기가 있으면
					CapFishCount++;
				}
			}
		}
		MaxFish = Math.max(MaxFish, CapFishCount);
	}
	
	private static int XYtoInt(int x, int y) {
		return x*N + y;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
//	private static int DrownX(int x) {
//		if(x < 0) {
//			return 0;
//		}
//		return x;
//	}
//	private static int DrownY(int y) {
//		if(y < 0) {
//			return 0;
//		}
//		return y;
//	}
//	private static int OverX(int x) {
//		if(x >= N) {
//			return N-1;
//		}
//		return x;
//	}
//	private static int OverY(int y) {
//		if(y >= N) {
//			return N-1;
//		}
//		return y;
//	}
	
	
	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "dirXY [x=" + x + ", y=" + y + "]";
		}
		
	}
}
