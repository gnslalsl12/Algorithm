import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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
//		System.out.println("초기 : " + Arrays.toString(FromLeftLine));
		Search(FromLeftLine, true);	//FRIL, FRIR 확보
//		System.out.println(FirstRevIndexL + " " + FirstRevIndexR);
		LetsDoRev(FromLeftLine, FirstRevIndexL, FirstRevIndexR);
//		System.out.println("한 번 : " + Arrays.toString(FromLeftLine));
		Search(FromLeftLine, true);	//두번째 SRIL, SRIR 확보
		LetsDoRev(FromLeftLine, SecondRevIndexL, SecondRevIndexR);
//		System.out.println(SecondRevIndexL + " " + SecondRevIndexR);
//		System.out.println("두 번 : " + Arrays.toString(FromLeftLine));
		
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
		if(FirstRevIndexL + FirstRevIndexR == 0) {
			if(FromLeft) {
				FirstRevIndexL = FirstFoundRevIndex;
				FirstRevIndexR = SecondFoundRevIndex;
				return;
			}else {
				FirstRevIndexL = SecondFoundRevIndex;
				FirstRevIndexR = FirstFoundRevIndex;
				return;
			}
		}else {
			if(FromLeft) {
				SecondRevIndexL = FirstFoundRevIndex;
				SecondRevIndexR = SecondFoundRevIndex;
				return;
			}else {
				SecondRevIndexL = SecondFoundRevIndex;
				SecondRevIndexR = FirstFoundRevIndex;
				return;
			}
		}
	}
	
	private static void LetsDoRev(int [] templine, int RevLeft, int RevRight) {	//뒤집기
//		System.out.println("뒤집기 실행 : " + RevLeft + " " + RevRight);
		for(int i = RevLeft; i <= (RevLeft + RevRight)/2 ; i++) {
			int tempnum = templine[i];
			templine[i] = templine[RevRight + RevLeft - i];
			templine[RevRight + RevLeft - i] = tempnum;
		}
	}
	
	private static boolean FinalCheck(int [] templine) {
		for(int i = 1; i <= N; i++) {
			if(templine[i] != i) return false;
		}
		return true;
	}
	
}