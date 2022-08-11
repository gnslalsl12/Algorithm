package day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25239 {
	static int h;
	static int m;
	
	public static void Roop(String add) {
		switch(add) {
		case("10MIN") :{
			m += 10;
			break;
		}
		case("30MIN") :{
			m += 30;
			break;
		}
		case("50MIN") :{
			m += 50;
			break;
		}
		case("2HOUR") :{
			h += 2;;
			break;
		}
		case("4HOUR") :{
			h += 4;
			break;
		}
		case("9HOUR") :{
			h += 9;
			break;
		}
		}
		if(m >= 60) {
			m -= 60;
			h += 1;
		}
		if(h >= 12) {
			h -= 12;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String [] time = read.readLine().split(":");
		h = Integer.parseInt(time[0]);
		m = Integer.parseInt(time[1]);
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int [] sep = new int [6];
		for(int i = 0; i < 6; i++) {
			sep[i] = Integer.parseInt(tokens.nextToken());
		}
		int test = Integer.parseInt(read.readLine());
		double a = 0;
		
		int answer = 0;
		
		
		
		
		for(int t = 0; t < test; t++) {

			tokens = new StringTokenizer(read.readLine());
			a += Double.parseDouble(tokens.nextToken());
//			String tmp = tokens.nextToken(read.readLine());
//			String [] st = tmp.split(".");
//			double a1 = Double.parseDouble(st[0]) + Double.parseDouble(st[1])*0.001;
			if(a > 60.0) {
				break;
			}
			String move = tokens.nextToken();
			if(m == 0) {
				continue;
			}
			if(move.equals("^")) {
				switch(h) {
				case(0) :
				case(1) :
					sep[0] = 0;
					break;
				case(2) :
				case(3) :
					sep[1] = 0;
					break;
				case(4) :
				case(5) :
					sep[2] = 0;
					break;
				case(6) :
				case(7) :
					sep[3] = 0;
					break;
				case(8) :
				case(9) :
					sep[4] = 0;
					break;
				case(10) :
				case(11) :
					sep[5] = 0;
					break;
				}
				
			}else {
				Roop(move);
			}
			
			
			answer = 0;
			for(int k : sep) {
				answer += k;
			}
			if(answer == 0) {
				break;
			}
			
		}
		answer = 0;
		for(int k : sep) {
			System.out.println(k);
			answer += k;
		}

		if(answer > 100) answer = 100;
		System.out.println(answer);
	}

}
