package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String templine = read.readLine();
		int size = templine.length();
		char[] LinetoChar = templine.toCharArray();
		for(int i = 0; i < size; i++) {
			if(LinetoChar[i] == '(') {
				if(LinetoChar[i+1] == ')') {
					LinetoChar[i] = LinetoChar[i+1] = 'X';	//레이저는 연속된 X로 표현해줌
					i++;
					continue;
				}
			}
		}
		int Totalcount = 0;
		int count = 0;
		for(int i = 0; i < size ; i++) {
			if(LinetoChar[i] == '(') {	//쇠막대기 하나 발견했음
				Totalcount++;			//발견한 쇠막대기
				count++;				//레이저 만나기 전까지 count
			}else if(LinetoChar[i] == ')') {
				count--;				//하나의 쇠막대기가 끝남
			}else {
				Totalcount+=count;		//지금 레이저로 자르면지금까지 만난 쇠막대기 개수만큼 잘라짐
				i++;					//연속된 XX이므로 두번째 X는 건너뜀
			}
		}
		System.out.println(Totalcount);
	}
}