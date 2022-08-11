package day0807;

import java.util.ArrayList;

public class BJ4673 {

	
	public static void main(String[] args) {
		int t = 1;
		ArrayList<Integer> b = new ArrayList<>();
		while(true) {
			b.add(SelfN(t));
			if(b.contains(t)) {
				t++;
			}else {
				System.out.println(t++);
			}
			if(t==10000) break;
		}	
	}
	private static int SelfN(int number) {
		int temp = number;
		while(number >= 10) {
			int a = number%10;
			number /=10;
			temp += a;
		}
		temp += number;
		return temp;
	}
	
}
