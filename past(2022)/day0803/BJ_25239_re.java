package day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25239_re {

	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		String [] temptime = read.readLine().split(":");
		int h = Integer.parseInt(temptime[0]);
		int m = Integer.parseInt(temptime[1]);
		StringTokenizer token = new StringTokenizer(read.readLine());
		int [] sep = new int [6];
		for(int i = 0; i < 6 ; i++) {
			sep[i] = Integer.parseInt(token.nextToken());
		}
		double sec = 0;
		int test = Integer.parseInt(read.readLine());
		
		int result = 0;
		for(int t = 0; t < test; t++) {
			
			token = new StringTokenizer(read.readLine());
			sec += Double.parseDouble(token.nextToken());
			if(sec >= 60) {
				break;
			}
			
			
			String mv = token.nextToken();
			
			switch(mv) {
			case("10MIN") : {
				m += 10;
				break;
			}
			case("30MIN") : {
				m += 30;
				break;
			}
			case("50MIN") : {
				m += 50;
				break;
			}
			case("2HOUR") : {
				h += 2;
				break;
			}
			case("4HOUR") : {
				h += 4;
				break;
			}
			case("9HOUR") : {
				h += 2;
				break;
			}
			case("^") : {
				
				switch(h) {
				case(0):
				case(1):{
					sep[0] = 0;
					break;
				}
				case(2):
				case(3):{
					sep[1] = 0;
					break;
				}
				case(4):
				case(5):{
					sep[2] = 0;
					break;
				}
				case(6):
				case(7):{
					sep[3] = 0;
					break;
				}
				case(8):
				case(9):{
					sep[4] = 0;
					break;
				}
				case(10):
				case(11):{
					sep[5] = 0;
					break;
				}
				}
			}
			}
			if(m >=60) {
				m -= 60;
				h++;
			}
			if(h>=12) {
				h -= 12;
			}
			
			result = 0;
			for(int k : sep) {
				result += k;
			}
			if(result == 0) {
				System.out.println(result);
				return;
			}
		
		}
		result = 0;
		for(int k : sep) {
			result += k;
		}
		
		if(result >= 100) {
			result = 100;
		}
		System.out.println(result);
	}

}
