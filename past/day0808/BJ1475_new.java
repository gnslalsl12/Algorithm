package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1475_new {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		String number = read.readLine();
		
		char [] numchar = number.toCharArray();
		
		int [] counts = {0,0,0,0,0,0,0,0,0};
		for(char temp : numchar) {
			if(temp == '9') {
				temp = '6';
			}
			counts[temp-'0'] += 1;
		}
		
		counts[6] = counts[6]/2 + counts[6]%2;
		
		int max = Integer.MIN_VALUE;
		for(int cnt : counts) {
			if(cnt>max) {
				max = cnt;
			}
		}
		System.out.println(max);

	}

}
