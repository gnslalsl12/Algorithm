package day0818;

import java.util.Scanner;

public class NQueenTest {
	static int N , cols[], ans;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		
		cols = new int [N+1] ; //1행부터 사용
		ans = 0;
		
		setQueen(1); //1행부터 시작
		System.out.println(ans);
		
		
		
		
	}
	
	
	private static void setQueen(int rowNo) {	//하나의 퀸만 가능한 모든 곳에 놓아보기
		
		if(!isAvailable(rowNo-1)) return; //가지치기 (직전까지 상황이 유망하지 않으면 백트랙킹
		
		if(rowNo > N) {	// 퀸을 다 놓음
			ans++;
			return;
		}
		
		
		for(int i = 1; i <= N; i++) {
			cols[rowNo] = i;
			setQueen(rowNo+1);
			//또는
			// if(isAvailable(rowNo)) setQueen(rowNo+1); //일단 놓아보고 가능하면 다음으로 넘어가기(백트래킹이 아님)
			
		}
			
	}
	
	public static boolean isAvailable(int rowNo) {
		for(int j = 1; j < rowNo; j++) {
			if(cols[j] == cols[rowNo] || rowNo-j == Math.abs(cols[rowNo] - cols[j]) ) { //열의 위치가 같거나
											//행 차이가 같을 때
				return false;
				
			}
			
		}
		return true;
	}
	
	
}
