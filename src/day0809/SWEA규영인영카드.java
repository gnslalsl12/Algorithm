package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA규영인영카드 {
	static ArrayList<Integer> Cards = new ArrayList<>();
	static ArrayList<Integer> OffCards = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(read.readLine());
		for(int test = 1; test <= t; test++) {
			Cards = new ArrayList<>();
			OffCards = new ArrayList<>();
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			while(tokens.hasMoreTokens()) {
				Cards.add(Integer.parseInt(tokens.nextToken()));
			}
			for(int i = 1; i < 19; i++) {
				if(!Cards.contains(i)) OffCards.add(i);
			}
			
			//카드 분배 완료
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i = 0; i < 9 ; i++) {
				temp.add(0);
			}
			Win = 0;
			Lose = 0;
			Games(0,new int [9],temp);
			System.out.printf("#%d %d %d\n", test, Win, Lose);
			
			
			
			
		}

	}
	
	static int Win=0;
	static int Lose=0;
	
	private static void Games(int countnow, int[] Selected, ArrayList<Integer> CardPattern) {
		
		if(countnow == 9) {
			//마지막 수행 동작
			WoL(CardPattern);
			return;
		}
		
		//기본 수행 (OffCard 의 카드 내는 순서 조합 로직)
		for(int i = 0; i < 9; i ++) {
			if(Selected[i] == 0){// Off Cards의 고르지 않은 카드 index
				Selected[i] = 1; //OffCard의 i번째 카드를 골랐다.
				CardPattern.set(countnow, OffCards.get(i));	//카드 패턴의 countnow번째 : offcard순서상 i번째 (countnow 0일동안 9개 다 돌ㅇ가ㅏㅁ)
				Games(countnow+1, Selected, CardPattern);
				Selected[i] = 0;
			}
		}
	}
	
	private static void WoL(ArrayList<Integer> thispattern) {
		int MeWin = 0;
		int HeWin = 0;
		for(int i = 0; i < 9; i++) {
			int my = Cards.get(i);
			int his = thispattern.get(i);
			if(my > his) {		//카드 수 비교
				MeWin += my + his;
			}else {
				HeWin += my + his;
			}
		}
		if(MeWin>HeWin) {
			Win++;
		}else {
			Lose++;
		}
	}
	
	

}
