package day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
	static long startpoint;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		long N = Integer.parseInt(tokens.nextToken());
		long r = Integer.parseInt(tokens.nextToken());
		long c = Integer.parseInt(tokens.nextToken());
		long size = (long) Math.pow(2, N);
		startpoint = 0;
		Zmaker(r,c,size);
		System.out.println(startpoint);
	}

	private static void Zmaker(long x, long y, long s) {
		if(s < 2) {
			return;
		}
		if(x >= s/2) {
			if(y >= s/2) {
				startpoint += s*s*3/4;				
				Zmaker(x-s/2,y-s/2,s/2);
			}else{
				startpoint += s*s*1/2;
				Zmaker(x-s/2,y,s/2);
			}
		}else {
			if(y >= s/2) {
				startpoint += s*s*1/4;
				Zmaker(x,y-s/2,s/2);
			}else { 
				Zmaker(x,y,s/2);
			}
		}
	}
}