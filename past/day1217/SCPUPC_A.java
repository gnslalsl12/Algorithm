package day1217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SCPUPC_A {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		String templine = read.readLine();
		int tempS = 0;
		int tempB = 0;
		for(int i = 0; i < templine.length();) {
			if(templine.charAt(i) == 's') {
				tempS++;
				i += 8;
			}else {
				tempB++;
				i += 7;
			}
		}
		if(tempS == tempB) System.out.println("bigdata? security!");
		else if(tempS < tempB) System.out.println("bigdata?");
		else System.out.println("security!");
	}
}