package day0816;

import java.util.Scanner;

public class Ex_CoinMachine_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int targetMoney = sc.nextInt();
		
		int[] units = {500,100,50,10,5,1};
		int[] counts = {sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()};
		
		int totalMoney = 0;
		for(int i = 0, size = units.length ; i < size; i++) {
			totalMoney += units[i]*counts[i];
		}
		
		int remainMoney = totalMoney - targetMoney;
		
		//음료수값 지불을 최대 동전수로 하려면, 지불하고 남은 값을 최소 동전으로 해주면 됨
		
		int sum = 0, maxCnt, availCnt;
		for(int i = 0, size = units.length ; i < size; i++) {
			maxCnt = remainMoney/units[i];	//해당 금액을 i번째 화폐단위로 가장 많이 쓸 떄의 개수
			availCnt = maxCnt<= counts[i]? maxCnt : counts[i];
			
			counts[i] -= availCnt;
			remainMoney -= availCnt*units[i];
			sum += counts[i]; //사용되고 남은 동전이 처음에 지불해야할 값을 위해 사용할 동전 수
		}
		System.out.println(sum);//음료수값을 지불하기 위해 사용된 동전 수
		System.out.println();
		for(int i = 0, size = units.length ; i < size; i++) {
			System.out.println(counts[i] + " ");
		}
	}

}
