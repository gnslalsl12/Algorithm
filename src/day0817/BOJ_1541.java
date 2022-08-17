package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String Lines = read.readLine();
		int len = Lines.length();
		String n = "";
		int result = 0;
		boolean minus = false;
		for(int i = 0; i < len+1; i++) {
			if(i == len) {
				result += minus? Integer.parseInt(n)*-1 : Integer.parseInt(n);
				break;
			}
			if(Lines.charAt(i) != '+' && Lines.charAt(i) != '-') { //숫자면
				n+=Lines.charAt(i);
			}else {	//+거나 -일 떄 / 마막일 때 : 
				result += minus? Integer.parseInt(n)*-1 : Integer.parseInt(n);
				n="";
				if(Lines.charAt(i) == '-') minus = true;
				}
			}
		System.out.println(result);
	}
}