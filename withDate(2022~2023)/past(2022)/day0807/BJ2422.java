package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2422 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		ArrayList<Integer> nope1 = new ArrayList<>();
		int[] nope2 = new int [m];
		
		for(int i = m-1; i >=0; i--) {
			tokens = new StringTokenizer(read.readLine());
			nope2[i] = Integer.parseInt(tokens.nextToken());
			nope1.add(Integer.parseInt(tokens.nextToken()));
		}
		int count = 0;
		for(int i = 1; i <= n-2; i++) {
			for(int j = i+1; j <= n-1; j++) {
				for(int k = j+1; k <= n; k++) {
					if(nope1.contains(i)) {
						System.out.println("noep1에 " + i);
						System.out.println("뒷자리 값 : " + nope2[nope1.indexOf(i)]);
						if(nope2[nope1.indexOf(i)] == j || nope2[nope1.indexOf(i)] == k) {
							continue;
						}
					}
					if(nope1.contains(j)) {
						if(nope2[nope1.indexOf(j)] == i || nope2[nope1.indexOf(j)] == k) {
							continue;
						}
					}
					System.out.println(i + ", " + j + ", " + k);
					count++;
					
				}
			}
		}
		System.out.println(count);
	
	}

}
