package day.study0731;

import java.util.ArrayList;

public class programmers_42883_re {
	
	public String solution(String number, int k) {
		String answer = "";
		//뒤에서 앞으로 가면서 가장 큰 숫자 ㅏ찾고
		//그 앞에서 최대한 지우고
		//그 다음에 큰 숫자 닷 ㅣ찾고
		//그 앞에서 최대한 치우기
		
		String [] numberArray = number.split("");
		
		ArrayList<String> numberAL = new ArrayList<>();
		
		for(String temp : numberArray) {
			numberAL.add(temp);
			
		}
		System.out.println(numberAL); // numberAL = {1,2,3,1,2,3,4}

		System.out.println("+++++++++++++++시작+++++++++++++++");
		
		int BigIndex= -1;
		int first = -1;
		int BiggestNum = 0;
		
		for(int i = 0; i < k ; ) {
			int SmallestNum = Integer.MAX_VALUE;
			int SmallIndex = 0;
			System.out.println("i,k : " + i + ", " + k);
			
			//만약 가장 큰숫자 인덱스 앞에 숫자가 없으면 (가장 큰 숫자 인덱스가 first라면)
			//first 숫자값 하나 추가해서 가장 큰 숫자를 제외하고
			//가장 큰 숫자 재검색
			
			//가장 큰 숫자 인덱스 앞에 숫자 있으면
			//작은 수부터 자르기
			
			//가장 큰 숫자 뒤에서 앞으로 찾기
			
			// 큰 숫자 앞에서부터 작은 수부터 자르기
			
			if(BigIndex == first) {
				BiggestNum = Integer.MIN_VALUE;
				first ++;
				for(int j = numberAL.size()-1; j>= first; j--) {
					if(BiggestNum <= Integer.parseInt(numberAL.get(j))) {
						BiggestNum = Integer.parseInt(numberAL.get(j));
						BigIndex = j;
					}//가장 큰 숫자 값과 인덱스 탐색
				}
				System.out.println("큰 숫자가 맨 앞에 있어서 재검색");
				continue; //다시 이것도 가장 큰 숫자인지
			}
			if(first == numberAL.size()) { //숫자 가장 뒤에놈이 가장 자근 수일때 : 맨 뒷놈을 지ㅜ어야함
				System.out.println("얘는 맨 뒤에가 가장 작아서 맨 뒤 지움");
				System.out.println("첫 탐색 숫자 first : " + first);
				System.out.println("first와 비교하는 size : " + numberAL.size());
				
				numberAL.remove(first-1);
				BigIndex--;
				first--;
				i++;
				System.out.println("지우고 난 후 첫 탐색 숫자 first : " + first);
				System.out.println("지우고 난 후 first와 비교하는 size : " + numberAL.size());
				System.out.println("가장 큰 숫자의 인덱스 : " + BigIndex);
				System.out.println(numberAL);
				System.out.println("===================================");
				continue;
				
			}
			System.out.println("큰 숫자 앞에서 중 맨 앞 탐색할 값 frst : " + first + ", " + numberAL.size());
			System.out.println("가장 큰 숫자 값, 인덱스 : " + BiggestNum + ", " + BigIndex);
			
			//if문 빠져나오면 가장 큰 숫자 앞에 다른 숫자가 있음
			
			for(int jj = BigIndex-1; jj>= first; jj--) {
				if(SmallestNum >= Integer.parseInt(numberAL.get(jj))) {//맨 앞과 큰 숫자 사이에 가장 작은 숫자 탐색
					SmallestNum = Integer.parseInt(numberAL.get(jj));
					SmallIndex = jj;
				}
			}
			
			//가장 작은 숫자 찾았음
			
			System.out.println("찾은 작은 숫자와 그 인덱스 : "+ SmallestNum + ", " + SmallIndex);
			
			numberAL.remove(SmallIndex);
			BigIndex--;
			i++;
			System.out.println("한 번 끝남");
			System.out.println("남은 배열 : " + numberAL);
			System.out.println("==========================================");
		}
		
		for(String temp : numberAL) {
			answer+=temp;
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		return answer;
	}
	
	public static void main(String[] args) {
		programmers_42883_re a = new programmers_42883_re();
		System.out.println(a.solution("1231234", 3));
		System.out.println(a.solution("4177252841",4));
		System.out.println(a.solution("129348521",6));
	}
	
	
	
}
