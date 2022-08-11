package day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_햄버거 {
	static int N;
	static int[] Taste;
	static int[] Cals;
	static int MaxCal;
	static int MaxTaste;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(read.readLine());
		
		for(int test = 1; test <= t; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			MaxCal = Integer.parseInt(tokens.nextToken());
			Taste = new int [N];
			Cals = new int [N];
			
			for(int i = 0; i < N; i ++) {
				tokens = new StringTokenizer(read.readLine());
				Taste[i] = Integer.parseInt(tokens.nextToken());
				Cals[i] = Integer.parseInt(tokens.nextToken());
			}
			
			Selected = new boolean[N];
			MaxTaste = Integer.MIN_VALUE;
			HamburgerJH(0,Selected);
			System.out.println("#" + test + " " + MaxTaste);
			
			
		}
		

	}
	
	static boolean[] Selected;
	static int tempCal;
	static int tempTaste;
	private static void HamburgerJH(int countnow, boolean[] Selected) {
		if(countnow == N ) {
			tempCal = 0;
			tempTaste = 0;
			for(int i = 0; i < N ; i++) {
				if(Selected[i]) {
					tempCal += Cals[i];
					tempTaste += Taste[i];
				}
				if(tempCal > MaxCal) {
					return;
				}
			}
			if(MaxTaste<tempTaste) {
				MaxTaste = tempTaste;
			}
			return;
		}
		
		Selected[countnow] = true;
		HamburgerJH(countnow+1, Selected);
		Selected[countnow] = false;
		HamburgerJH(countnow+1, Selected);
	}
}
