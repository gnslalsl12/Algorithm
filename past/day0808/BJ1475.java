package day0808;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ1475 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String temp0 = sc.nextLine();
		long lines = Long.parseLong(temp0);
		int count0 = 0;
		ArrayList<Integer> count = new ArrayList<>();
		if(lines != 0) {
			while(lines >= 10) {
				int temp = (int)lines%10;
				lines /= 10;
				if(temp<0) temp = 0;
				if(temp == 0) {
					count0++;
					continue;
				}
				if(temp == 9) {
					count.add(6);
				}else {
					count.add(temp);				
				}
				
				
			}
		}else {
			System.out.println(temp0.length());
			return;
		}
		count.add((int)lines);

		int [] counts = new int[10];
		Collections.sort(count);
		int siz = count.size();
		for(int tt = 0; tt < siz; tt++) {
//			System.out.println(count.get(tt));
			counts[count.get(tt)] += 1;
		}
		if(counts[6] != 0) counts[6] = counts[6]/2 + counts[6]%2;
		
		int max = Integer.MIN_VALUE;
		for(int result : counts) {
			if(result > max) {
				max = result;
			}
		}
		if(max<count0) {
			max = count0;
		}
		System.out.println(max);
	}
}