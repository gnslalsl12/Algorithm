package day1008;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LISTest2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Nlist = new int[N];
		int[] SelectedMin = new int[N]; // [k]는 k길이를 만족하는 해당 자리에 오는 최소값

		for (int i = 0; i < N; i++) {
			Nlist[i] = sc.nextInt();
		}

		int size = 0;

		for (int i = 0; i < N; i++) {
			int position = Arrays.binarySearch(SelectedMin, 0, size, Nlist[i]); // toindex는 excursive라서 
																				// 해당 값 미만까지 탐색 => size 그대로 주면 됨
			if (position >= 0) // 찾았다!
				continue;	//해당 인덱스에 값이 들어가있게됨
			//그게 아니라면 직접 넣어줘야함
			int insertposition = Math.abs(position)-1; //맨 뒤, 기존 원소 대체자리
			SelectedMin[insertposition] = Nlist[i];
			if(insertposition == size) size++;
		}
		
		System.out.println(size);	//LIS 길이!!!
		

	}

}
