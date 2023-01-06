package day0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2505 {
	static int N;
	static int FirstRevIndexL, FirstRevIndexR;
	static int SecondRevIndexL, SecondRevIndexR;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		int [] FromLeftLine = new int [N+1];
		tokens = new StringTokenizer(read.readLine());
		for(int i = 1; i <= N; i++) {
			FromLeftLine[i] = Integer.parseInt(tokens.nextToken());
		}
		int [] FromRightLine = FromLeftLine.clone();
		
		//왼쪽부터 찾아보기
		FirstRevIndexL = 0;
		FirstRevIndexR = 0;	//초기화
		SecondRevIndexL = 0;
		SecondRevIndexR = 0;
		Search(FromLeftLine, true);	//FRIL, FRIR 확보
		LetsDoRev(FromLeftLine, FirstRevIndexL, FirstRevIndexR);
		Search(FromLeftLine, true);	//두번째 SRIL, SRIR 확보
		LetsDoRev(FromLeftLine, SecondRevIndexL, SecondRevIndexR);
		
		if(FinalCheck(FromLeftLine)) {
			if(SecondRevIndexL == 0) {
				SecondRevIndexL = 1;
				SecondRevIndexR = 1;
				if(FirstRevIndexL == 0) {
					FirstRevIndexL = 1;
					FirstRevIndexR = 1;
				}
			}
			System.out.println(FirstRevIndexL + " " + FirstRevIndexR);
			System.out.println(SecondRevIndexL + " " + SecondRevIndexR);
			return;
		}
		FirstRevIndexL = 0;
		FirstRevIndexR = 0;	//초기화
		Search(FromRightLine, false);	//FRIL, FRIR 확보
		LetsDoRev(FromRightLine, FirstRevIndexL, FirstRevIndexR);
		Search(FromRightLine, false);	//두번째 SRIL, SRIR 확보
		LetsDoRev(FromRightLine, SecondRevIndexL, SecondRevIndexR);
		System.out.println(FirstRevIndexL + " " + FirstRevIndexR);
		System.out.println(SecondRevIndexL + " " + SecondRevIndexR);
	}
	
	private static void Search(int [] templine, boolean FromLeft) {	//비정상구간 인덱스 찾기
		int FirstFoundRevIndex = 0;
		int SecondFoundRevIndex = 0;	//뒤집을 구간의 왼쪽, 오른쪽 index
		int A, M;
		if(FromLeft) {	//왼쪽부터 찾을 때
			A = 1;
			M = 1;
		}else {			//오른쪽부터 찾을 때
			A = N;
			M = -1;
		}
		for(int i = A; i>=1 && i<=N; i += M) {
			if(FirstFoundRevIndex == 0 && templine[i] != i) {	//왼쪽에서 부터 찾은 첫 비정상값
				FirstFoundRevIndex = i;
			}
			if(templine[i] == FirstFoundRevIndex) {	//비정상 구간의 첫 위치에 들어가있어야할 정상값 발견
				SecondFoundRevIndex = i;
				break;
			}
		}
		if(FirstRevIndexL + FirstRevIndexR == 0) {	//첫번쨰 구간 발견이라면
			if(FromLeft) {
				FirstRevIndexL = FirstFoundRevIndex;
				FirstRevIndexR = SecondFoundRevIndex;
				return;
			}else {		//오른쪽부터 탐색이라면 처음 찾은 Rev구간 인덱스가 오른쪽 인덱스
				FirstRevIndexL = SecondFoundRevIndex;
				FirstRevIndexR = FirstFoundRevIndex;
				return;
			}
		}else {										//두번째라면
			if(FromLeft) {
				SecondRevIndexL = FirstFoundRevIndex;
				SecondRevIndexR = SecondFoundRevIndex;
				return;
			}else {		//오른쪽부터 탐색이라면 처음 찾은 Rev구간 인덱스가 오른쪽 인덱스
				SecondRevIndexL = SecondFoundRevIndex;
				SecondRevIndexR = FirstFoundRevIndex;
				return;
			}
		}
	}
	
	private static void LetsDoRev(int [] templine, int RevLeft, int RevRight) {	//뒤집기
		for(int i = RevLeft; i <= (RevLeft + RevRight)/2 ; i++) {
			int tempnum = templine[i];
			templine[i] = templine[RevRight + RevLeft - i];
			templine[RevRight + RevLeft - i] = tempnum;
		}
	}
	
	private static boolean FinalCheck(int [] templine) {	//정상 라인인지 확인
		for(int i = 1; i <= N; i++) {
			if(templine[i] != i) return false;
		}
		return true;
	}
}