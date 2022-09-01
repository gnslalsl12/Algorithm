package day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684 {
	static int N, M, H;
	static int [][]maps;
	static int Totalcount;;
	static int LineNumber;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		Totalcount = 0;
		LineNumber = 1;	//가로로 세우는 사다리마다 짝을 맞추기 위해 고유한 번호로 연결
		maps = new int [H][N];
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken())-1;
			int b = Integer.parseInt(tokens.nextToken())-1;
			maps[a][b] = LineNumber;
			maps[a][b+1] = LineNumber++;
		}//mapping 완료
		for(int templine = N-1; templine >= 0; templine--) {	//오른쪽부터
			if(!SetCorssLine(templine)) {	//세워야하는데 못 세우는 경우
				System.out.println(-1);
				return;
			}
		}
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
		if(FinalCheck()) {				//i라인이 i로 끝나는지 체크 (0라인으로 테스트)
		System.out.println(Totalcount);
		}else {
			System.out.println("안 돌아오는데");
			System.out.println(-1);
		}
	}
	private static boolean FinalCheck() {
		int checkingline = 0;
		for(int i = 0; i < H; i++) {
			if(maps[i][checkingline] != 0) {	//이동 가능한 층에서 어딘가랑 연결됨
				
				if(checkingline > 0 && checkingline < N-1) {	//맨 끝 라인이 아닐 때
					if(maps[i][checkingline] == maps[i][checkingline+1]) {	//오른쪽 먼저 체크
						System.out.println("오른쪽");
						checkingline++;	//오른쪽으로 이동
						continue;
					}else {		//오른쪽에 길이 없음 = "왼쪽에 길"
						System.out.println("왼쪽");
						checkingline--;	//왼쪽으로 이동
						continue;
					}
				}else if (checkingline == 0) {	//맨 왼쪽 라인인데 연결돼있을 때
					System.out.println("오른쪽");
					checkingline++;
					continue;
				}else if (checkingline == N-1) {	//맨 오른쪽 라인인데 연결돼있을 때
					System.out.println("왼쪽");
					checkingline--;
					continue;
				}
			}
		}
		if(checkingline == 0) {	//시작 라인으로 돌아옴
			return true;
		}else {
			return false;
		}
	}
	
	private static boolean SetCorssLine(int Sline) {	//짝수개가 아니면 가능한 곳에 줄 하나 세우고 끝
		int crosscount = 0;
		for(int i = 0; i < H; i++) {
			if(maps[i][Sline] != 0) {
				crosscount++;
			}
		}//해당 줄에 포함된 가로줄 수
		if(crosscount%2 == 0) return true;	//짝수 가로줄이 있으면 확인 끝
		
		int check = -1;
		int next = -1;
		for(int i = H-1; i >= 0; i--) {	//설치 가능한 라인 찾기(아래부터)
			
			if(Sline > 0 && Sline < N-1) {	//양 끝이 아님
				if(maps[i][Sline] == 0) {	//빈 곳
					if(maps[i][Sline+1] == 0) {	//오른쪽이 비었을 떄
						check = i; 	//설치 가능한 가로줄 i
						next = 1;
						break;
					}else if(maps[i][Sline-1] == 0) {	//왼쪽이 비었을 때
						check = i;
						next = -1;
					}
				}
			}else if(Sline == 0) {	//맨 왼쪽
				if(maps[i][Sline] == 0 && maps[i][Sline+1] == 0) {	//자기자신과 오른쪽이 빔
					check = i;
					next = 1;
				}
			}else if(Sline == N-1) {	//맨 오른쪾
				if(maps[i][Sline] == 0 && maps[i][Sline-1] == 0) {	//자기 자신과 왼쪽이 빔
					check = i;
					next = -1;
				}
			}
		}
		
		if(check == -1) {	//세워야하는데 못 세움
			return false;
		}else {	//세워도 되는 곳 찾음
			maps[check][Sline] = LineNumber;
			maps[check][Sline+next] = LineNumber++;
			Totalcount++;
			return true;
		}
	}
	
}