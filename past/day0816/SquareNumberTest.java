package day0816;

import java.util.Scanner;

public class SquareNumberTest {

	static int callCnt1, callCnt2;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int n = sc.nextInt();
		
		System.out.println(exp1(x,n));
		System.out.println(callCnt1);
		System.out.println();
		System.out.println(exp2(x,n));
		System.out.println(callCnt2);
		
		
	}
	
	static long exp1(long x, int n) { //지수 x, 밑수 n
		callCnt1++;
		if(n == 1) return x;
		return x*exp1(x,n-1);
	}
	
	
	static long exp2(long x, int n) {
		callCnt2++;
		if(n == 1) return x;
		
		long y = exp2(x,n/2); //홀수여도 n/2는 몫만 남으니까 구분 안 하고 그냥 n/2
		
		return n%2 == 0? y*y : y*y*x;
		
	}

}
