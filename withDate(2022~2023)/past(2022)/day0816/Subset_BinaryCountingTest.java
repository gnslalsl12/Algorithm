package day0816;

import java.util.Scanner;

public class Subset_BinaryCountingTest {

	static int [] numbers;
	static int N;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		
		for(int i = 0; i < N ; i++) {
			numbers[i] = sc.nextInt();
			
			
			
		}
		generateSubset();
		
	}
	
	private static void generateSubset() {
		for(int flag = 0 , caseCnt = 1<<N ; flag < caseCnt; flag ++) {	
										//모든 가능한 비트열의 상황에 대해 처리
			//현 비트열의 상태에 대해 각 원소의 부분집합에 포함 유/무 확인
			for(int i = 0; i < N ; i++) {
				if((flag&(1<<i)) != 0) { //i원소가 부분집합에 포함되어 &했을 때 0이 아닌거
					System.out.print(numbers[i] + " ");
				}
			}
			System.out.println();
			
			
		}
	}

}
