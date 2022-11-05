package day1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579 {
	static int N;
	static int[] stairs;
	static int[] DPresult;
	static int [] countarr;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String readLine = read.readLine();
		N = Integer.parseInt(readLine);
		stairs = new int[N+1];
		DPresult = new int[N+1];
		countarr = new int [N+1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(read.readLine());
		}
		// inputing
		System.out.println(Arrays.toString(stairs));
		upstair(0, 0);
		System.out.println(DPresult[N ]);
	}

	private static void upstair(int index, int count) {
		System.out.println("index : " + index + ", count : " + count + Arrays.toString(DPresult));
		if(count < 2 && index < N) {	//한 칸 씩 갔고 범위 안임
			if(DPresult[index+1] >= DPresult[index] + stairs[index+1]) {	//원래 있는 애가 더 좋아
				if(countarr[index+1] < count + 1) {
					
				}
				upstair(index+1, count+1);
			}
		}
		
		
//		if (count < 2 && index < N) {
//			if(DPresult[index+1] >= DPresult[index] + stairs[index+1]) {	//원래 있던 애가 더 조하
//				upstair(index+1, countarr[index+1]);
//			}else {	//새로온 애가 더 높아
//				DPresult[index+1] = DPresult[index] + stairs[index+1];
//				upstair(index+1, count+1);
//			}
//		}
//		if(index < N-1) {//연속 세 개 될 때
//			if(DPresult[index+1] >= DPresult[index] + stairs[index+1]) {	//원래 있던 애가 더 조하
//				upstair(index+1, countarr[index+1]);
//			}else {	//새로온 애가 더 높아
//				DPresult[index+1] = DPresult[index] + stairs[index+1];
//				upstair(index+1, count+1);
//			}
//		}
		
	}
}
