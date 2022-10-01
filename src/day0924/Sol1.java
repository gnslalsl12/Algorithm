package day0924;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Sol1 {

	public static void main(String[] args) throws IOException {
		String today = "2022.05.19";
		String [] terms = {"A 6", "B 12", "C 3"};
		String [] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
		String todayre = "2019.05.28";
		String [] termsre = {"Z 3", "D 5"};
		String [] privaciesre = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

//		int [] rr = test(today, terms, privacies);
//		for(int i = 0; i < rr.length; i++) {
//			System.out.println(rr[i]);
//		}

		int [] rrre = test(todayre, termsre, privaciesre);
		for(int i = 0; i < rrre.length; i++) {
			System.out.println(rrre[i]);
		}
		
		
	}
	
	public static int[] test(String today, String[] terms, String[] privacies) {
		StringTokenizer tokens = new StringTokenizer(today, ".");
		int thisyear = Integer.parseInt(tokens.nextToken());
		int thismonth = Integer.parseInt(tokens.nextToken());
		int thisday = Integer.parseInt(tokens.nextToken());
		int thistotal = thisyear * 10000 + thismonth * 100 + thisday;
		
		Map<Character, Integer> termsmap = new HashMap<Character, Integer>();
		for(int i = 0; i < terms.length; i++) {
			tokens = new StringTokenizer(terms[i]);
			char type = tokens.nextToken().charAt(0);
			int plusterm = Integer.parseInt(tokens.nextToken());
			
			termsmap.put(type, plusterm);
			System.out.println("type : " + type + ", day : " + termsmap.get(type));
		}
		//약관 맵 완성
		ArrayList<Integer> result = new ArrayList<>();
		for(int j = 0; j < privacies.length; j++) {
			String temp = privacies[j];
			String tempday = temp.substring(0, 10);
			char temptype = temp.charAt(temp.length()-1);
			tokens = new StringTokenizer(tempday, ".");
			int privyear = Integer.parseInt(tokens.nextToken());
			int privmonth= Integer.parseInt(tokens.nextToken());
			int privday = Integer.parseInt(tokens.nextToken());
			int tempplus = termsmap.get(temptype);
			
			int plusyear = tempplus/12;
			int plusmonth = tempplus%12;
			privyear += plusyear;
			privmonth += plusmonth;
			if(privmonth > 12) {
				privyear+= privmonth/12;
				privmonth %= 12;
			}
			privday--;
			if(privday == 0) {
				privmonth--;
				privday = 28;
			}
			if(privmonth == 0) {
				privyear --;
				privmonth = 12;
			}
			int privtotal = privyear*10000 + privmonth * 100 + privday;
			System.out.println("하나씩 : " + temptype + " , : " + privtotal);
			if(thistotal > privtotal) {
				result.add(j+1);
			}
			
			
		}
		int [] answer = new int [result.size()];
		for(int r = 0; r < result.size(); r++) {
			answer[r] = result.get(r);
		}
		
        return answer;
        
    }
	
	/*public static int[] test(String today, String[] terms, String[] privacies) {
		StringTokenizer tokens = new StringTokenizer(today, ".");
		int thisyear = Integer.parseInt(tokens.nextToken());
		int thismonth = Integer.parseInt(tokens.nextToken());
		int thisday = Integer.parseInt(tokens.nextToken());
		
		
		Map<Character, Integer> termsmap = new HashMap<Character, Integer>();
		for(int i = 0; i < terms.length; i++) {
			tokens = new StringTokenizer(terms[i]);
			char type = tokens.nextToken().charAt(0);
			int plusterm = Integer.parseInt(tokens.nextToken());
			int termyear = thisyear - plusterm/12;
			int termmonth = thismonth - plusterm%12;
			if(termmonth < 1) {
				termyear--;
				termmonth += 12;
			}
			int termday = thisday + 1;
			if(termday == 28) {
				termmonth++;
				termday = 1;
			}
			termsmap.put(type, termyear*10000 + termmonth*100 + termday);
			System.out.println("type : " + type + ", day : " + termsmap.get(type));
		}
		//약관 맵 완성
		ArrayList<Integer> result = new ArrayList<>();
		for(int j = 0; j < privacies.length; j++) {
			String temp = privacies[j];
			String tempday = temp.substring(0, 10);
			char temptype = temp.charAt(temp.length()-1);
			tokens = new StringTokenizer(tempday, ".");
			int privday = Integer.parseInt(tokens.nextToken())*10000 + Integer.parseInt(tokens.nextToken())*100 + Integer.parseInt(tokens.nextToken());
			System.out.println("하나씪 : " +temptype + " , " + privday);
			if(termsmap.get(temptype) > privday) {
				result.add(j+1);
			}
		}
		int [] answer = new int [result.size()];
		for(int r = 0; r < result.size(); r++) {
			answer[r] = result.get(r);
		}
		
        return answer;
        
    }*/

}
