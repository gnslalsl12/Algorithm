package day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1461 {
	static int N;
	static int M;
	static int totaldistance;
	static ArrayList<Integer> Plusbooks;
	static ArrayList<Integer> Minusbooks;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		Plusbooks = new ArrayList<>();
		Minusbooks = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			int n = Integer.parseInt(tokens.nextToken());
			if(n>0) Plusbooks.add(n);
			else Minusbooks.add(n);
		}
		
		
		Collections.sort(Plusbooks,Collections.reverseOrder());
		Collections.sort(Minusbooks); //절대값 내림차순이니까 Minus는 그냥 정렬
		int tempM = 0;
		totaldistance = 0;
		while(true) {
			if(tempM>=Plusbooks.size()) {
				break;
			}
			totaldistance += 2*Plusbooks.get(tempM);
			tempM += M;
		}
		tempM = 0; //tempM 초기화
		while(true) {
			if(tempM>=Minusbooks.size()) {
				break;
			}
			totaldistance -= 2*Minusbooks.get(tempM);
			tempM += M;
		}
		if(Plusbooks.size() == 0) {
			totaldistance += Minusbooks.get(0);
		}else if(Minusbooks.size() == 0) {
			totaldistance -= Plusbooks.get(0);
		}else {
			if(Plusbooks.get(0) > Minusbooks.get(0)*-1) {
				totaldistance -= Plusbooks.get(0);
			}else {
				totaldistance += Minusbooks.get(0);
			}
		}		
		System.out.println(totaldistance);
	}
}