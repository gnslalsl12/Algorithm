package day.study0731;

import java.util.ArrayList;

public class programmers_42883_done {
	
	public String solution(String number, int k) {
		String answer = "";
		String [] numberArray = number.split("");
		ArrayList<String> numberAL = new ArrayList<>();
		for(String temp : numberArray) {
			numberAL.add(temp);
		}
		int BigIndex= -1;
		int first = -1;
		int BiggestNum = 0;
		for(int i = 0; i < k ; ) {
			int SmallestNum = Integer.MAX_VALUE;
			int SmallIndex = 0;
			if(BigIndex == first) {
				BiggestNum = Integer.MIN_VALUE;
				first ++;
				for(int j = numberAL.size()-1; j>= first; j--) {
					if(BiggestNum <= Integer.parseInt(numberAL.get(j))) {
						BiggestNum = Integer.parseInt(numberAL.get(j));
						BigIndex = j;
					}
				}
				continue;
			}
			if(first == numberAL.size()) {
				numberAL.remove(first-1);
				BigIndex--;
				first--;
				i++;
				continue;
			}
			for(int jj = BigIndex-1; jj>= first; jj--) {
				if(SmallestNum >= Integer.parseInt(numberAL.get(jj))) {
					SmallestNum = Integer.parseInt(numberAL.get(jj));
					SmallIndex = jj;
				}
			}
			numberAL.remove(SmallIndex);
			BigIndex--;
			i++;
		}
		for(String temp : numberAL) {
			answer+=temp;
		}	
		return answer;
	}

	public static void main(String[] args) {
		programmers_42883_done a = new programmers_42883_done();
		System.out.println(a.solution("222234", 3));
		System.out.println(a.solution("4177252841",4));
		System.out.println(a.solution("129348521",6));
	}
	
}
