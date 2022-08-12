package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA요리사 {
	static int N;
	static ArrayList<int[]> temping;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(read.readLine());
	
		for(int test = 1; test <= t; test++) {
			int totalcount  =0;
			N = Integer.parseInt(read.readLine());
			int [][] maps = new int [N][N];
			for(int i = 0; i < N ; i++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				for(int j = 0 ; j < N ; j++) {
					maps[i][j] += Integer.parseInt(tokens.nextToken());
					totalcount += maps[i][j];
				}
			}
			ArrayList<Integer> linetemp = new ArrayList<>();
			for(int i = 0; i < N ; i++) {
				linetemp.add(i);
			}
			System.out.println(linetemp.toString());
			temping = new ArrayList<>();
			tempi = new int[N/2];
			JH1(0,0);
			int min = Integer.MAX_VALUE;
			for(int[] tempp : temping) {
				System.out.println(Arrays.toString(tempp));
				int Asum = 0;
				int Bsum = 0;
				
				for(int i = 0; i< tempp.length; i++) {
					
					for(int j = i+1; j< tempp.length; j++) {
						Asum +=maps[tempp[i]][tempp[j]]+maps[tempp[j]][tempp[i]];
					}
				
				}
				for(int i = 0; i < N ; i++) {
					for(int j = 0; j < N ; j++) {
						
					}
				}
									
					
				System.out.println(Asum + " : " + Bsum);
				Asum = Math.abs(Asum/2-Bsum/2);
				System.out.println(Asum);
				if(Asum<min) {
					min = Asum;
				}
				
				
				/*for(int i = 0; i < tempp.length ; i++) {
					for(int j = i+1 ; j< tempp.length; j++) {
						sum += maps[tempp[0]][tempp[1]] + maps[tempp[1]][tempp[0]];
					}
				}
				//요리의 sum
				System.out.println(sum);
				totalsubs = Math.abs(totalcount - sum*2);
				if(min > totalsubs) {
					min = totalsubs;
				}*/
				
				
				
			}
			System.out.println("#" + test + " " + min);
			
			
			
			
			
			
			
			
			
		}
			
		

	}
	
	static int[] tempi;
	public static void JH1(int countnow, int index) {
		if(countnow == N/2) {
			temping.add(tempi.clone());
			return;
			
		}
		
		for(int i = index; i < N; i++) {
			tempi[countnow] = i;
			JH1(countnow+1,i+1);
		}
	}
	
	
	
	
}




