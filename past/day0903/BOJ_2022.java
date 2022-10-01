package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2022 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		double x = Double.parseDouble(tokens.nextToken());
		double y = Double.parseDouble(tokens.nextToken());
		double c = Double.parseDouble(tokens.nextToken());
		
		double leftpoint = 0;				//임시 가로 길이 범위의 최소값
		double rightpoint = Math.min(x, y);	//임시 가로 길이 범위의 최대값 (어쨋든 가로 길이는 x와 y 보다 작을테니 임시 최대 범위는 x y 둘 중 작은 걸로 지정해줌)
		
		while(true) {
			double tempwidth = (rightpoint + leftpoint)/2;	//tempwidth 는 임시 가로 길이	(rp와 lp를 줄여가면서 tempwidth의 값을 업데이트하고 실제 가로길이와의 오차범위를 좁힐 예정
																//이를 위해 임시 가로길이를 두 범위의 중간값으로 지정해줌 (Up & Down)
			double Hx = Math.sqrt(Math.pow(x, 2) - Math.pow(tempwidth, 2));		//(왼쪽 높이) Hx = (x^2 - W^2)^1/2
			double Hy = Math.sqrt(Math.pow(y, 2) - Math.pow(tempwidth, 2));		//(오른쪽 높이) Hy = (y^2 - W^2)^1/2
			
			double tempc = (Hx*Hy)/(Hx+Hy);		//임시 width로 구해본 임시 c값
			//Hx와 Hy로 c 구하는 법은 아래 참조
			
			if(rightpoint - leftpoint < 0.001) break;	//구해본 가로 길이의 오차 범위가 0.001 보다 작을 때 그 높이를 정답이라 판별;
			
			if(tempc > c) {				//임시로 구한 높이가 더 높다 = "임시 가로길이가 실제 가로길이보다 좁다" = "임시 가로길이를 현재(lp와 rp의 중간값)보다 늘려야함"
				leftpoint = tempwidth;	// = "왼쪽의 포인터를 tempwidth 지점(중간값)으로 당겨서 현재의 임시 가로길이(lp와 rp의 중간값)보다 높은 곳으로 범위를 줄임"
			}else {
				rightpoint = tempwidth;
			}
		}
		
		//while문을 빠져나온 rightpoint는 구하는 상황의 width의 어슷값
		System.out.println(String.format("%.3f", rightpoint));
	}
}
/*c가 찍힌 곳의 x좌표 기준 왼쪽은 r1, 오른쪽은 r2라 할 때
 * Hy/r = c/r1
 * Hx/r = c/r2
 * 
 * r1 = r*c/Hy
 * r2 = r*c/Hx
 * 
 * r = r1 + r2 = r*c/Hy + r*c/Hx
 * 
 * 1/c = 1/Hy + 1/Hx
 * 
 * c = (Hx*Hy)/(Hx+Hy)
 */