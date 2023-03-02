package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2961_new_byMemoryOver {


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> S1 = new LinkedList<>();
		Queue<Integer> SHH = new LinkedList<>();
		Queue<Integer> B1 = new LinkedList<>();
		Queue<Integer> BHH = new LinkedList<>();
		ArrayList<Integer> Taste = new ArrayList<>();
		
		int n = Integer.parseInt(read.readLine());
		int temps, tempb, subs;
		int found0 = 0;
		for(int y = 0; y < n; y++) {
			StringTokenizer tokens= new StringTokenizer(read.readLine());
			temps = Integer.parseInt(tokens.nextToken());
			tempb = Integer.parseInt(tokens.nextToken());
			subs = Math.abs(temps-tempb);
			Taste.add(Math.abs(subs));
			S1.add(temps);
			B1.add(tempb);
			if(subs == 0) {
				found0 = 1;
			}
			
		}
		while(S1.size() == 1) {

			
			int now = S1.size();
			for(int i = 1; i < now; i++) {
				temps = S1.poll();
				tempb = B1.poll();
				for(int j = i+1; j<now; j++) {
					subs = Math.abs(temps*S1.peek() - (tempb + B1.peek()));
					System.out.println(temps+ "와 " + S1.peek());
					System.out.println(tempb+ "와 " + B1.peek());
					System.out.println(subs);
					System.out.println("=================");
					if(subs == 0) {
						System.out.println(0);
						return;
					}
					SHH.add(temps);
					BHH.add(tempb);
					Taste.add(subs);
					S1.add(S1.poll());
					B1.add(B1.poll());
				}
			}
			int then = SHH.size();
			for(int j = 0; j < then; j++) {
				S1.add(SHH.poll());
				B1.add(BHH.poll());
			}
		}
		if(found0 == 1) {
			System.out.println(0);
			return;
		}
		Collections.sort(Taste);
		System.out.println(Taste.get(0));
	}

}
