package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683 {
	static int N,M;
	static int [][] maps;
	static int[][] deltas1 = {{0,-1},{1,0},{0,1},{-1,0}};
	static int[][] deltas2 = {{0,-1},{1,0},{0,1},{-1,0}};
	static int[][] deltas3 = {{0,-1},{1,0},{0,1},{-1,0}};
	static int[][] deltas4 = {{0,-1},{1,0},{0,1},{-1,0}};
	static int[][] deltas5 = {{0,-1},{1,0},{0,1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		int count0 = 0;
		PriorityQueue<CCTVinfo> CCTV = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] >0 && maps[i][j] < 6) {
					CCTV.add(new CCTVinfo(i,j,maps[i][j]));
				}else if(maps[i][j] == 0) {
					count0++;
				}
			}
		}
		
		System.out.println();
		while(!CCTV.isEmpty()) {
			CheckArea(CCTV.poll());
		}
		for(int [] t : maps) {
			for(int tt : t) {
				System.out.printf("%d ",tt);
			}
			System.out.println();
		}
		System.out.println(count0-totalcount);

	}
	static boolean isIn(CCTVinfo tem) {
		if(tem.x >= 0 && tem.x < N && tem.y >= 0 && tem.y < M) return true;
		return false;
	}
	static int[][] mapsU, mapsR, mapsD, mapsL;
	static int GoforU(CCTVinfo tempG) {
		int count = 0;
		int tempx = tempG.x;
		int tempy = tempG.y;
		mapsU = new int [N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				mapsU[i][j] = maps[i][j];
			}
		}
		mapsU[tempG.x][tempG.y]= -2; 
		while(true) {
			tempG.x--;
			if(!isIn(tempG)) {
				break;
			}
			if(mapsU[tempG.x][tempG.y]== 6) {
				break;
			}else if(mapsU[tempG.x][tempG.y]!= 0) {
				continue;
			}
			mapsU[tempG.x][tempG.y]= -1; 
			count++;
		}
		tempG.x = tempx;
		tempG.y = tempy;
		
		for(int [] tm : mapsU) {
			for(int tt : tm) {
				System.out.printf("%d ", tt);
			}
			System.out.println();
		}
		return count;
	}
	static int GoforR(CCTVinfo tempG) {
		int count = 0;
		int tempx = tempG.x;
		int tempy = tempG.y;
		mapsR = new int [N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				mapsR[i][j] = maps[i][j];
			}
		}
		mapsR[tempG.x][tempG.y]= -2; 
		while(true) {
			tempG.y++;
			if(!isIn(tempG)) {
				break;
			}
			if(mapsR[tempG.x][tempG.y]== 6) {
				break;
			}else if(mapsR[tempG.x][tempG.y]!= 0) {
				continue;
			}
			mapsR[tempG.x][tempG.y]= -1; 
			count++;
		}
		tempG.x = tempx;
		tempG.y = tempy;
		return count;
	}
	static int GoforD(CCTVinfo tempG) {
		int count = 0;
		int tempx = tempG.x;
		int tempy = tempG.y;
		mapsD = new int [N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				mapsD[i][j] = maps[i][j];
			}
		}
		mapsD[tempG.x][tempG.y]= -2; 
		while(true) {
			tempG.x++;
			if(!isIn(tempG)) {
				break;
			}
			if(mapsD[tempG.x][tempG.y]== 6) {
				break;
			}else if(mapsD[tempG.x][tempG.y]!= 0) {
				continue;
			}
			mapsD[tempG.x][tempG.y]= -1; 
			count++;
		}
		tempG.x = tempx;
		tempG.y = tempy;
		return count;
	}
	static int GoforL(CCTVinfo tempG) {
		int count = 0;
		int tempx = tempG.x;
		int tempy = tempG.y;
		mapsL = new int [N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				mapsL[i][j] = maps[i][j];
			}
		}
		mapsL[tempG.x][tempG.y]= -2; 
		while(true) {
			tempG.y--;
			if(!isIn(tempG)) {
				break;
			}
			if(mapsL[tempG.x][tempG.y]== 6) {
				break;
			}else if(mapsL[tempG.x][tempG.y]!= 0) {
				continue;
			}
			mapsL[tempG.x][tempG.y]= -1; 
			count++;
		}
		tempG.x = tempx;
		tempG.y = tempy;
		return count;
	}
	
	static int totalcount;
	static void CheckArea(CCTVinfo loc) {
		switch(loc.n) {
		case(1):{
			int cU = GoforU(loc);
			int cR = GoforR(loc);
			int cD = GoforD(loc);
			int cL = GoforL(loc);
			
			int [] dirtemp = {cU,cR,cD,cL};
			int tt = Integer.MIN_VALUE;
			int foundindex = 0;
			for(int i = 0; i < 4; i++) {
				if(tt<dirtemp[i]) {
					foundindex = i;
					tt = dirtemp[i];
				}
			}
			int [][][] maptemp = {mapsU,mapsR,mapsD,mapsL};
			for(int k = 0; k < 4; k++) {
				if(k == foundindex) continue;
				for(int i = 0; i < N ;i++) {
					for(int j = 0; j < M ; j++) {
						maps[i][j] -= maptemp[k][i][j];
					}
				}
				
			}
			totalcount += dirtemp[foundindex];
			System.out.println("1번");
			break;
		}
		case(2):{
			int cU = GoforU(loc);
			int cR = GoforR(loc);
			int cD = GoforD(loc);
			int cL = GoforL(loc);
			int UD = cU+cD;
			int RL = cR+cL;
			if(UD > RL) {
				for(int i = 0; i < N ;i++) {
					for(int j = 0; j < M ; j++) {
						maps[i][j] -= mapsR[i][j];
						maps[i][j] -= mapsL[i][j];
					}
				}
			}else {
				for(int i = 0; i < N ;i++) {
					for(int j = 0; j < M ; j++) {
						maps[i][j] -= mapsU[i][j];
						maps[i][j] -= mapsD[i][j];
					}
				}
			}
			totalcount += Math.max(UD, RL);
			System.out.println("2번");
			break;
		}
		case(3):{
			int cU = GoforU(loc);
			int cR = GoforR(loc);
			int cD = GoforD(loc);
			int cL = GoforL(loc);
			int UR = cU+cR;
			int RD = cR+cD;
			int DL = cD+cL;
			int LU = cL+cU;
			int [] dirtemp = {UR,RD,DL,LU};
			int [][][] maptemp = {mapsU,mapsR,mapsD,mapsL};
			int ff = dirtemp[0];
			int foundf = 0;
			int ss = Integer.MIN_VALUE;
			int founds = 0;
			for(int i = 1; i < N; i++) {
				if(dirtemp[i] > ff) {
					ss = ff;
					founds = foundf;
					ff = dirtemp[i];
					foundf = i;
				}else if(dirtemp[i] > ss){
					ss = dirtemp[i];
					founds = i;
				}
			}
			for(int i = 0; i < N ;i++) {
				for(int j = 0; j < M ; j++) {
					maps[i][j] -= maptemp[foundf][i][j];
					maps[i][j] -= maptemp[founds][i][j];
				}
			}
			System.out.println("3번");
			break;
		}
		case(4):{
			int cU = GoforU(loc);
			int cR = GoforR(loc);
			int cD = GoforD(loc);
			int cL = GoforL(loc);
			int [] dirtemp = {cU,cR,cD,cL};
			int tt = Integer.MAX_VALUE;
			int foundindex = 0;
			for(int i = 0; i < 4; i++) {
				if(tt>dirtemp[i]) {
					foundindex = i;
					tt = dirtemp[i];
				}
			}
			int [][][] maptemp = {mapsU,mapsR,mapsD,mapsL};
			for(int i = 0; i < N ;i++) {
				for(int j = 0; j < M ; j++) {
					maps[i][j] -= maptemp[foundindex][i][j];
				}
			}
			totalcount += cU + cR + cD + cL - dirtemp[foundindex];
			System.out.println("4번");
			break;
		}
		case(5):{
			totalcount += GoforU(loc) + GoforR(loc) + GoforD(loc) + GoforL(loc);
			System.out.println("5번");
			break;
		}
		}
	}

}

class CCTVinfo implements Comparable<CCTVinfo>{
	int x;
	int y;
	int n;
	CCTVinfo(){
		super();
	}
	
	CCTVinfo(int x, int y, int n) {
		super();
		this.x = x;
		this.y = y;
		this.n = n;
	}

	@Override
	public int compareTo(CCTVinfo o) {
		// TODO Auto-generated method stub
		return o.n - this.n;
	}
}