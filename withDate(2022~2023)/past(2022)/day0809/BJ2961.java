package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2961 {

	static ArrayList<Integer> S = new ArrayList<>();
	static ArrayList<Integer> B = new ArrayList<>();
	static ArrayList<Integer> Taste = new ArrayList<>();
	static int countN;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		for(int i = 0; i < n ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			S.add(Integer.parseInt(tokens.nextToken()));
			B.add(Integer.parseInt(tokens.nextToken()));
			Taste.add(Math.abs(S.get(i) - B.get(i)));
			
		}
		Cook(n);
		if(subs == -1) {
			System.out.println(0);
			return;
		}
		Collections.sort(Taste);
		System.out.println(Taste.get(0));

	}
	
	static int subs;
	private static void Cook(int count) {
		int temps, tempb;
		for(int c = 0; c < count; c++) {
			int n = S.size();
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j<n; j++) {
					temps =S.get(i)*S.get(j);
					tempb =B.get(i) + B.get(j); 
					S.add(temps);
					B.add(tempb);
					subs= Math.abs(temps-tempb);
					if(subs == 0) {
						subs = -1;
						return;
					}
					Taste.add(subs);
				}
				S.remove(i);
				B.remove(i);
			}
		}
	}
}