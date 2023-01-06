package day0924;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sol3 {

	public static void main(String[] args) {

	}
	
	
	
	public int[] solution(int[][] users, int[] emoticons) {
		//10 20 30 40
		ArrayList<int []> emoVal = new ArrayList<>();
		for(int i = 0 ; i < emoticons.length; i++) {
			int[] tempval = new int [4];
			tempval[0] = (emoticons[i]/10) *9; 
			tempval[1] = (emoticons[i]/10) *8; 
			tempval[2] = (emoticons[i]/10) *7; 
			tempval[3] = (emoticons[i]/10) *6; 
			emoVal.add(tempval);
		}//이모티콘 할인별 가격 저장
		for(int i = 0; i < users.length; i++) {
			
		}
		
		
		
        int[] answer = {};
        return answer;
    }
	
	
}
