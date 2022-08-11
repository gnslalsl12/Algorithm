package day0811;

import java.util.Arrays;
import java.util.Scanner;

public class BitMasking {
	
	
	static int N, R, totalCnt;
	static int [] numbers, input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		totalCnt = 0;
		
		input = new int[N];
		numbers = new int[R];
//		isSelected = new boolean[N];	//현재 시도하는 숫자가 이전에 사용됐는가 (현재 숫자와 특정 인덱스 값을 매핑시켜줌)
		
		for(int i = 0; i<N ; i++) {
			input[i] = sc.nextInt();
		}
		
		perm(0,0);
		System.out.println("총 경우의 수 : " + totalCnt);
		
		
		
	}
	//cnt : 직전까지 뽑은 순열에 포함된 수의 개수
	// flag : 선택된 수들의 상태를 비트로 표현하고있는 비트열(정수)
	private static void perm(int cnt, int flag) {
		
		if(cnt==R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		
		for(int i = 0; i<N; i++) {
			if((flag & 1<<i) != 0) continue; //0이 아니란 소리는 사용중이다!
			
			numbers[cnt] = input[i];
			
			perm(cnt+1, flag | i<<N);
		}
	}
}
