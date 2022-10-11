package day0929;

public class DailyWS_AptPainting {

	static int N;
	static long[] memo;

	public static void main(String[] args) {

		N = 9;
		memo = new long[N + 1];
		memo[1] = 2;
		memo[2] = 3;
		solveTopDown(N);
		System.out.println(memo[N]);
		System.out.println(solveBottomUp(N));
	}

	// 재귀 형태로 작성해보기!
	
	static long solveTopDown(int n) {
		//기저상황
		if(memo[n] > 0) {
			return memo[n];
		}
		
		return memo[n] = solveTopDown(n-1) + solveTopDown(n-2);
	}
	
	static long solveBottomUp(int n) {
		long [] memo = new long[N+1];
		memo[1]=2;
		memo[2]=3;
		for(int i = 3; i <= n; i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}
		return memo[n];
	}
	

}
