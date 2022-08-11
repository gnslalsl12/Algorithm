
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	
	public String solution(String number, int k) {
		String answer = "";
		ArrayList<Long> AnswerArray = new ArrayList<>();
		AnswerArray.add(Long.parseLong(number));
		for(int i = 0; i < k ; i++) {
			ArrayList<Long> tempAnswerArray = new ArrayList<>();
			for(long temp : AnswerArray) {
				ArrayList<Character> temparray = new ArrayList<>();
				char[] tempchar = Long.toString(temp).toCharArray();
				for(char t : tempchar) {
					temparray.add(t);
				}
				int [] withoutone = new int [temparray.size()];
				for(int ii = 0; ii<temparray.size();ii++) {
					ArrayList<Character> temptemp = new ArrayList<>();
					for(char ext : temparray) {
						temptemp.add(ext);
					}
					temptemp.remove(ii);
					String exlong = "";
					for(char exch : temptemp) {
						exlong += exch;
					}
					tempAnswerArray.add(Long.parseLong(exlong));
				}
			}
			AnswerArray = tempAnswerArray;
			answer = Long.toString(Collections.max(AnswerArray));
		}
		return answer;
	}
	public static void main(String[] args) {
		Solution a = new Solution();
		System.out.println(a.solution("1924", 2));
	}
}
