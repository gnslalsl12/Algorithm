package day0816;

import java.util.Scanner;

public class BOJ_2839 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M5=N/5;
		int totalmin = Integer.MAX_VALUE;
		if(N < 3) {
			System.out.println(-1);
			return;
		}else {
			while(M5>=0) {
				if((N-M5*5)%3 != 0) {
					M5--;
					continue;
				}
				if(totalmin>(M5+((N-M5*5)/3))) {
					totalmin = (M5+((N-M5*5)/3));
				}
				M5--;
			}
		}
		
		if(totalmin == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(totalmin);
	}
}
	
