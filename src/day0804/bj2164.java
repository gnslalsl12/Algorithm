package day0804;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj2164 {

	
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Queue<Integer> cards = new LinkedList<>();
		
		for(int i = 1; i <= n ; i++) {
			cards.add(i);
		}
		
		while(cards.size() != 1) {
			cards.remove();
			cards.add(cards.remove());
		}
		System.out.println(cards.peek());
		
		
	}
	
	
}
