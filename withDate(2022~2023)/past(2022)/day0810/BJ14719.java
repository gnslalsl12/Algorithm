package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14719 {
	
	static int [] Buildings;
	static int maxH;
	static int W;
	static int countAir;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		maxH = Integer.parseInt(tokens.nextToken());
		W = Integer.parseInt(tokens.nextToken());
		int AllBlocks = maxH*W;
		tokens = new StringTokenizer(read.readLine());
		Buildings = new int[W];
		for(int i = 0; i < W ; i++) {
			Buildings[i] = Integer.parseInt(tokens.nextToken());
			AllBlocks -= Buildings[i];
		}
		countAir = 0;
		for(int i = maxH; i >=1 ; i--) {
			FloorSearch(i);
		}
		System.out.println(AllBlocks-countAir);
		
	}
	
	private static void FloorSearch(int height) {
		int CountOfThisHeight=0;
		int maxW = Integer.MIN_VALUE;
		int minW = Integer.MAX_VALUE;
		
		for(int i = 0; i < W; i++) {
			if(Buildings[i]>=height) {
				CountOfThisHeight++;
				if(i > maxW) maxW = i;
				if(i < minW) minW = i;
			}
		}
		if(CountOfThisHeight <= 1) {
			countAir += W-CountOfThisHeight;
//			System.out.println(height + "층일 때1 공기 : " + countAir);
		}else if(CountOfThisHeight > 1) {
			countAir += (W-1)-maxW+minW;
//			System.out.println(height + "층일 때2 공기 : " + countAir);
		}
	}
}
