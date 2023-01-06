package day0803;

import java.util.ArrayList;
import java.util.Collections;

public class Prg_GMBT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prg_GMBT ex = new Prg_GMBT();
		int [] people = {40, 50, 80, 90, 100, 70, 90, 140, 200, 50, 100, 120, 150, 90, 70, 60};
		System.out.println(ex.solution(people, 180));
	}
	
	
    public int solution(int[] people, int limit) {
        int answer = 0;
        ArrayList<Integer> lowerp = new ArrayList<>();
        ArrayList<Integer> higherp = new ArrayList<>();
        ArrayList<Integer> halfp = new ArrayList<>();
        for(int k : people) {
        	if(k > limit/2) {
        		higherp.add(k);
        	}else if(k == limit/2) {
        		halfp.add(k);
        	}
        	else lowerp.add(k);
        }										//limit 중간보다 큰놈 작은 놈 구분
        
        answer += halfp.size()/2 + halfp.size()%2;
//        System.out.println(halfp.size());
//        System.out.println("반짜리 애들 더한 수 : " + answer);
        Collections.sort(lowerp);
//        Collections.sort(higherp);
        Collections.sort(higherp, Collections.reverseOrder());

        for(int i = 0; i < lowerp.size(); i++) {
        	for(int j = 0; j < higherp.size(); j++) {
        		if(lowerp.get(i) + higherp.get(j) <= limit) {
        			lowerp.remove(i);
        			higherp.remove(j);
        			answer++;
        		}
        	}
        }
        answer += lowerp.size() + higherp.size();
        return answer;
    }

}
