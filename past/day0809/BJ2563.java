package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ2563 {
	static int []xline;
	static int []yline;
	static HashSet<Integer> lines = new HashSet<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		for(int i = 0 ; i < n; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			xytoIntandPut(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
			
		}
		System.out.println(lines.size());
	}
	
	private static void xytoIntandPut(int x, int y) {
		int tmp = x*100+y;
		for(int i = 0; i<10; i++) {
			for(int j = 0; j < 10; j++) {
				lines.add(tmp+(100*i)+j);
			}
		}
	}

}
