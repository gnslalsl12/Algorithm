package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEACookings {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for(int test = 1; test<=T; test++) {
			
			N = Integer.parseInt(read.readLine());
			int [][] maps = new int [N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				for(int j = 0; j < N ; j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}//maps 완료
			
			Selected = new boolean[N];
			ChoosedBit = new ArrayList<>();
			Comb(0,0,Selected);
			int min = Integer.MAX_VALUE;
			
			for(boolean[] Combtemp : ChoosedBit) { //모든 경우의 수에 대해서
				int Asum = 0;
				int Bsum = 0;
				int Acount = 0;
				int Bcount = 0;
				int [] Axy = new int [N/2];
				int [] Bxy = new int [N/2];
				for(int ing = 0; ing < Combtemp.length; ing++) {
					if(Combtemp[ing]) { //선택된 번호 (0,1,2,3)
						Axy[Acount++] = ing;
					}else { //그 외
						Bxy[Bcount++] = ing;
					}
				}//A와 B 번호 분배 완료
				
				for(int Sel = 0; Sel < N/2 ; Sel++) {
					for(int Sel2 = Sel+1; Sel2 < N/2 ; Sel2++) {
						Asum += maps[Axy[Sel]][Axy[Sel2]] + maps[Axy[Sel2]][Axy[Sel]];
						Bsum += maps[Bxy[Sel]][Bxy[Sel2]] + maps[Bxy[Sel2]][Bxy[Sel]];
					}
				}//A와 B Taste 값 계산 완료
				
				int subs = Math.abs(Asum-Bsum);
				if(subs < min) {
					min = subs;
				}
			}
			System.out.println("#" + test + " " +min);	
		}
	}
	static ArrayList<boolean[]> ChoosedBit;
	static boolean[] Selected;
	public static void Comb(int countnow, int index, boolean[] Selected) {
		if(countnow == N/2) {
			ChoosedBit.add(Selected.clone());
			return;
		}
		for(int i = index; i < N; i ++) {
			Selected[i] = true;
			Comb(countnow+1, i+1, Selected);
			Selected[i] = false;
		}
	}
}