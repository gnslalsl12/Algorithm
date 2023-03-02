package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1759_re {
	static ArrayList<char[]> ResultArray = new ArrayList<>();
	static int L, C;
	static ArrayList<Character> AlphaArray = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(read.readLine());
		
		for(int i = 0; i<C; i++) {
			AlphaArray.add(tokens.nextToken().charAt(0));
		}
		Collections.sort(AlphaArray);
		Comb(0, 0, new char[L]);
		
		for(char[] temp : ResultArray) {	//출력
			for(char out : temp) {
				sb.append(out);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void Comb(int countnow, int startpoint, char[] result) {	//조합 메서드
		if(countnow == L) {
			if(CheckVowel(result)) {		//모음 개수가 적합하면
				ResultArray.add(result.clone());	//추가
			}
			return;
		}
		
		for(int i = startpoint; i < AlphaArray.size(); i++) {
			result[countnow] = AlphaArray.get(i);
			Comb(countnow + 1, i + 1, result.clone());
		}
		
	}
	
	private static boolean CheckVowel(char[] temp) {						//모음 개수 세기 메서드
		int countV = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == 'a' || temp[i] == 'e' || temp[i] == 'i' || temp[i] == 'o' || temp[i] == 'u') {
				countV++;
			}
		}
		return countV >= 1 && countV <= L-2;
		
	}
}