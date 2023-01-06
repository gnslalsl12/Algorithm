package day1016;

import java.util.Scanner;

public class BOJ_9655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean [] games = new boolean[N+1];
		games[1] = true;
		if(N>=2) {
			games[2] = false;
			if(N >= 3) {
				games[3] = true;
				for(int i = 4; i <= N; i++) {
					games[i] = !games[i-3];
				}
			}
		}
		if(!games[N]) System.out.println("CY");
		else System.out.println("SK");
	}
}