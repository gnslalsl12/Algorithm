package day.study0731;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class programmers_42883_new {
	
	public String solution(String number, int k) {
		String answer = "";
		
		ArrayList<Long> AnswerArray = new ArrayList<>(); //[1924,~~]
		
		AnswerArray.add(Long.parseLong(number)); // 1924
//		AnswerArray.add(Integer.parseInt(number)); // 1924
		System.out.println("시작인디?????????????????????????");
		for(int i = 0; i < k ; i++) {
			System.out.println("=================");
			
			ArrayList<Long> tempAnswerArray = new ArrayList<>(); //[1924,~~]
			for(long temp : AnswerArray) { // 현재 어레일 ㅣ스트에 있는 수자들을 하나씩 꺼내서 =>
														//그 숫자들을 한 자리씩 뺸 경우를 모아서 추가해주고 그 숫자는 ㅣ=지워야함
				//String [] removeone = new String [temparray.size()]; //한 자리를 지운 숫자들을 넣어줄 배열
				
				
				//temp는 현재 모든 숫자가 모인 배열에서 하나 뽑아온 숫자
				//이걸 arraylist char화 해서 하나씩 뺴준 값을 어떤 array에 넣어주고 (그 전에 int숫자를 chararray화 시켜서 하나씩 넣어줘야함
				ArrayList<Character> temparray = new ArrayList<>();
				char[] tempchar = Long.toString(temp).toCharArray(); //현재 뽑아온 숫자를 char어레이화 시킴
				for(char t : tempchar) {
					temparray.add(t);
				}//숫자 하나씩을 char화 시켜서 arraylist화 시켜줌 1 9 2 4
				
				//이제 하나씩 뺴준값을 새로운 array에 넣어줘야함
				int [] withoutone = new int [temparray.size()];
				System.out.println("???");
				System.out.println(temparray);
				
				for(int ii = 0; ii<temparray.size();ii++) {
					ArrayList<Character> temptemp = new ArrayList<>();
					for(char ext : temparray) {
						temptemp.add(ext);
					}
					System.out.println("temptemp : " + temptemp);
					temptemp.remove(ii);
					System.out.println("temptemp : " + temptemp);
					String exlong = "";
					for(char exch : temptemp) {
						exlong += exch;
						System.out.println("exlong : " + exlong);
					}
					tempAnswerArray.add(Long.parseLong(exlong));
					System.out.println("현재 tempanswerarray : " + tempAnswerArray);
				}//withoutone = 924
				
				
				
				System.out.println("?");
				

			} //for(temp:answerArray) 끝
			
			AnswerArray = tempAnswerArray;
			System.out.println("수행한 배열 : " + AnswerArray);
			answer = Long.toString(Collections.max(AnswerArray));
		}
		
		
		System.out.println(answer);
		return answer;
		
	}
	public static void main(String[] args) {
		programmers_42883_new tt = new programmers_42883_new();
//		tt.solution("1924", 2);
		tt.solution("1231234", 3);
//		tt.solution("4177252841", 4);
	}

}
