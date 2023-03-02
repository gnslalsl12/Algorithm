package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1138 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int [] temp = new int [n];
		ArrayList<Integer> lines = new ArrayList<>();
		for(int i = 0; i < n ; i++) {
			temp[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int j = n-1; j>=0; j--) {
			lines.add(temp[j], j+1);
		}
		
		for(int i : lines) {
			System.out.printf("%d ", i);
		}
	}

}