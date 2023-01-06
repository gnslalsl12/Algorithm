package day0808;

import java.util.Scanner;

public class BJ19939 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int SumK = k*(k+1)/2;
		if(n < SumK) {
			System.out.println(-1);
			return;
		}else {
			n -= SumK;
			n = n%k;
			if(n>0) {
				n = 1;
			}
			System.out.println(k-1+n);
			return;
		}
	}
}
