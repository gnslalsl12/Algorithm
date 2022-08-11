package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.print.attribute.IntegerSyntax;


public class SWEA9229 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(read.readLine());
		for(int test = 1; test <= t ; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			ArrayList<Integer> GB = new ArrayList<>();
			int n = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			
			for(int i = 0; i < n ; i++) {
				GB.add(Integer.parseInt(tokens.nextToken()));
			}
			
			Collections.sort(GB);
			
			int sum = 0;
			ArrayList<Integer> Sums = new ArrayList<>();
			for(int i = 0; i < n ; i++) {
				for(int j = i+1; j < n; j++) {
					sum = GB.get(i) + GB.get(j);
					if(sum <= m) {
						Sums.add(sum);
					}else {
						break;
					}
				}
			}
			int temp = 0;
			Collections.sort(Sums);
			if(Sums.size() != 0) {
				temp = Sums.get(Sums.size()-1);
			}else {
				temp = -1;
			}
			System.out.printf("#%d %d\n", test, temp);
			
			
			
			
			
		}
	}

}
