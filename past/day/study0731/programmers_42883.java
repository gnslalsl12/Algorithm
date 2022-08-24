package day.study0731;

import java.util.ArrayList;

public class programmers_42883 {

	public String solution(String number, int k) {
		
		String answer = "";
		String [] charsy = number.split("");
		ArrayList<Integer> lines = new ArrayList<>();
		
		for(int i = 0; i < charsy.length; i++) { // 문자열 number를 Arraylist에 숫자형태로 변환
			lines.add(Integer.parseInt(charsy[i]));
			
		}
		//뒤에서 앞으로 가면서 가장 큰 숫자 ㅏ찾고
		//그 앞에서 최대한 지우고
		//그 다음에 큰 숫자 닷 ㅣ찾고
		//그 앞에서 최대한 치우기
		
		int removeindex = 0;
		for(int i = 0; i< k ; i++) {
			int smallest = Integer.MAX_VALUE;
			for(int j = lines.size()-k+1; j >=0 ; j--) { // 한 번 돌면서 가장 작은 수 찾기;
				if(lines.get(j) <= smallest) {
					smallest = lines.get(j);
					removeindex = j;
					
				}
				if(j == 0) {
					lines.remove(removeindex);
				}
			}//가장 작은 수 중 맨 앞에 있는 놈 찾음.
		}

		for(int temp : lines) {
			answer += Integer.toString(temp);
		}

		return answer;
	}
	/*public static void main(String[] args) {

		programmers_42883 temp = new programmers_42883();
		System.out.println(temp.solution("4177252841",4));
	
	}*/

}
