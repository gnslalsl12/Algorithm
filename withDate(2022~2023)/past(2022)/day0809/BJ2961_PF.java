package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2961_PF {
	static int n;
	static StringBuilder output = new StringBuilder();
	static Material[] materials;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(read.readLine());
		materials = new Material[n];

		for(int i = 0; i < n ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int sour = Integer.parseInt(tokens.nextToken());
			int bitter = Integer.parseInt(tokens.nextToken());
			materials[i] = new Material(sour,bitter);
		}
		
		
//		powerset(0,new boolean[n]);
		powerset(0, 1, 0); // sour는 곱해야하니까 초기값을 1, bitter는 더하니까 초기값 0
		System.out.println(min);
		
	}
	static int min = Integer.MAX_VALUE;
	//부분집합을 구하면서 필요한 값을 바로 계산해보자
	static void powerset(int toCheck, int sour, int bitter) {
		if(toCheck == n) {//부분집합 완료했다 = > 최소값 찾자
			
			//모두 다 비선택이면 안돼!! 최소 하나는 선택돼야함 (공집합 제외)
			if(bitter != 0) {
				min = Math.min(min, Math.abs(sour-bitter));
			}
			
			
			return;
		}
		
		//재료를 선택했을 때와 하지 않았을 때의 맛을 계산해서 다음 재귀 부르자
		Material m = materials[toCheck]; // 재료선택
		
		//이 재료 선택 O : 
		powerset(toCheck+1,sour * m.sour, bitter + m.bitter); //tocheck+1 : 다음 재료
		//selectCnt 는 공집합 방지 (끝까지 0이면 공집합임)
		//이 재료선택 X :
		powerset(toCheck+1, sour, bitter);
	}
	
	
	
	static void powerset(int toCheck, boolean[] checked) { //어디깢 ㅣ체크했따 (부분집합)
		if(toCheck == n) {
			System.out.println(Arrays.toString(checked));
			return;
		}
		
		checked[toCheck] = true;
		powerset(toCheck+1, checked);
		checked[toCheck] = false;
		powerset(toCheck+1, checked);
		
	}

	static class Material{
		int sour;
		int bitter;
		
		public Material(int sour, int bitter) {
			super();
			this.sour = sour;
			this.bitter = bitter;
		}
		
		
		
	}
}
