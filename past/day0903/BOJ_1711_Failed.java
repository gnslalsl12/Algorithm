package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1711_Failed {
	static int N;
	static ArrayList<int []> Points = new ArrayList<>();
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int [] temp = {Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())};
			Points.add(temp);
		}
		
		result = 0;
		int [] t1, t2, t3;
		int len12, len13, len23;
		for(int i = 0; i < N; i++) {
			t1 = Points.get(i);
			for(int j = i+1; j < N; j++) {
				t2 = Points.get(j);
				len12 = (int)Math.pow(t1[0] - t2[0], 2) + (int)Math.pow(t1[1] - t2[1], 2);
				for(int k = j+1; k < N; k++) {
					t3 = Points.get(k);
					if(t1[0] == t2[0] && t1[0] == t3[0]) break;
					if(t1[1] == t2[1] && t1[1] == t3[1]) break;
					len23 = (int)Math.pow(t2[0] - t3[0], 2) + (int)Math.pow(t2[1] - t3[1], 2);
					len13 = (int)Math.pow(t1[0] - t3[0], 2) + (int)Math.pow(t1[1] - t3[1], 2);
					if(len12 + len23 == len13 || len12 + len13 == len23 || len23 + len13 == len12) result++;
				}
			}
		}
		System.out.println(result);
	}
}