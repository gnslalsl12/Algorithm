package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2961_re {
	static int n;
	
	static ArrayList<Integer> S = new ArrayList<>();
	static ArrayList<Integer> B = new ArrayList<>();
	static ArrayList<Integer> Taste = new ArrayList<>(); 
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader read= new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(read.readLine());
		int sub = 0;
		for(int i = 0; i < n ; i ++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			S.add(Integer.parseInt(tokens.nextToken()));
			B.add(Integer.parseInt(tokens.nextToken()));
			sub = Math.abs(S.get(i)  - B.get(i));
			if(sub == 0) {
				found0 = 1;
			}
			Taste.add(sub);
		}
		
		Cook(n);	//n개까지 뽑아서 tatse
		Collections.sort(Taste);
		if(found0 == 1) {
			System.out.println(0);
			return;
		}
		System.out.println(Taste.get(0));
	}
	static int found0  = 0;
	private static void Cook(int things) {
		ArrayList<Integer> tempS = new ArrayList<>();
		ArrayList<Integer> tempB = new ArrayList<>();
		for(int i = 0; i < S.size(); i++) {
			tempS.add(S.get(0));
			tempB.add(B.get(i));
		}
		if(things == 0) {
			return;
		}
		
		int ts;
		int tb;
		int subs;
		int size= tempS.size();
		for(int i = 0; i < size; i++) {
			ts = tempS.remove(0);
			tb = tempB.remove(0);
			for(int j = 0; j < n; j++) {
				subs = Math.abs((ts*S.get(j) - (tb+B.get(j))));
				if(subs == 0) {
					found0 = 1;
					break;
				}
				Taste.add(Math.abs(ts-tb));
				tempS.add(ts*S.get(j));
				tempB.add(tb+B.get(j));
			}
		}
		Cook(things-1);	
	}
}