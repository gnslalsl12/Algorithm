package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//C개 중 L개 조합
//bitmasking으로 모음인지 판별
//모음만 true시킨 bitmasking = 1065233
public class BOJ_1759 {
	static int L,C;
	static ArrayList<Integer> AlphabetFromLow = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < C; i++) {
			AlphabetFromLow.add(tokens.nextToken().charAt(0) - 'a');	//알파벳을 숫자로 전환해
		}
		Collections.sort(AlphabetFromLow); //정렬
		Comb(0,0,0);
		System.out.println(sb);
		
		
	}
	
	private static void Comb(int countnow, int startpoint, int SelBM) {
		if(CheckVowelFirst(SelBM)) return;
		
		if(countnow == L) {
			if(CheckVowel(SelBM)) {
				MakeAlphaString(SelBM);
			}
			return;
		}
		
		for(int i = startpoint; i<AlphabetFromLow.size(); i++) {
				SelBM |= 1<<AlphabetFromLow.get(i);
				Comb(countnow+1, startpoint+1, SelBM);
				SelBM &= ~(1<<AlphabetFromLow.get(i));
		}
	}
	
	private static boolean CheckVowelFirst(int BM) {
		int countV = 0;
		for(int i = 0; i<5; i++) {
			if((BM & VowelIndex[i]) != 0) {
				countV++;
			}
		}
		return (countV > L-2);
	}
		
	static int [] VowelIndex = {1, 16, 256, 16384, 1048576};
	private static boolean CheckVowel(int BM) {
		int countV = 0;
		for(int i = 0; i<5; i++) {
			if((BM & VowelIndex[i]) != 0) {
				countV++;
			}
		}
		return countV >= 1 && countV <= L-2;
	}
	static StringBuilder sb = new StringBuilder();
	private static void MakeAlphaString(int BM) {
		StringBuilder tempsb = new StringBuilder();
		int count = 0;
		for(int i = 0; i<26; i++) {
			if( (BM & 1<<i) != 0) {	//선택된 알파벳임
				count++;
				tempsb.append((char)(i + 'a'));
			}
			if(count == L) {
				tempsb.append("\n");
				sb.append(tempsb);
				return;
			}
			
		}
	}
	
}