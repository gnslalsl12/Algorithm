package day0811;

import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation { // nPn (몇 개 골라서 쓸 수는 없음)

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int [] input = new int[N];
		
		for(int i = 0; i < N ; i++) {
			input[i] = sc.nextInt();
		}
		
		//1. 오름차순 정렬
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
			
		}while(NextPerm(input)); //순열이 있다면 true, 더이상 만들 순열이 없으면 false (마지막에 ㅏㄴ온 게 가장 큰 수)
		
		
		
		
		
		
		
	}

	
	public static boolean NextPerm(int[] numbers) {/// numbers 배열의 상태를 근거로 다음 순열생성,
													//다음 순열 존재하면 ture 아니면 false
		
		int N = numbers.length;
		//1. 꼭대기 찾기
		int i = N-1;
		while( i > 0 && numbers[i-1] >= numbers[i]) { // 내 앞에있는 애가 더 크다
			--i;
		}
			
		if(i == 0) {	//맨 앞으로 와버려서 내 앞에 숫자가 없음
			return false;
		}
			
		
		//2. 꼭대기 바로 앞자리(i-1)값을 크게 만들 교환값을 그 뒤에서 찾기
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) {
			--j;
		}
		
		
		//3. i-1위치값ㄱ과 j위치값을 교환하자
		swap(numbers, i-1, j);
		
		//4. i위치부터 맨 뒤까지 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N-1;
		while(i<k) {//i는 꼭대기지점, k는 꼭대기 이전 값들 이니까
			swap(numbers,i++,k--); //여기까지 왔을 떄는 i 뒤의 값들이 최대값인 상태이니까 자리 바꿔주면 최소값이됨
		}
		return true;
		
		
		
	}
	
	public static void swap(int[]numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		
	}
}
