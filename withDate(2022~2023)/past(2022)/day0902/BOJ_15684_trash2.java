package day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684_trash2 {
	static int N, M, H;
	static int CrossLineCount;
	static int [][] maps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());	//세로선 개수
		M = Integer.parseInt(tokens.nextToken());	//설치된 가로선 개수
		H = Integer.parseInt(tokens.nextToken());	//가로선 놓을 수 있는 라인 수
		maps = new int [H][N];
		CrossLineCount = 0;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken())-1;	//가로 a줄을
			int b = Integer.parseInt(tokens.nextToken())-1;	//세로 b와 b+1줄에
			maps[a][b] = ++CrossLineCount;
			maps[a][b+1] = CrossLineCount;
		}
		
		
		for(int line = 0; line < N; line++) {
			for(int [] t : maps) {
				System.out.println(Arrays.toString(t));
			}
			int setC = CrossLineCount;
			if(SetLinetoReturn(line, 0, setC)) {
				continue;
			}else {
				System.out.println("돌아오지 못한 라인 : " + line);
				System.out.println(-1);	//돌아오지 못하는 경우가 발생
				return;
			}
		}
//		for(int [] t : maps) {
//			System.out.println(Arrays.toString(t));
//		}
		System.out.println(CrossLineCount - M);
	}

	private static boolean SetLinetoReturn(int StartLine, int nowCross, int setcount) {
		int tempLine = StartLine;
		for(int Wline = nowCross; Wline < H; Wline++) {
			if(maps[Wline][tempLine] != 0) {	//어딘가로 연결된 선이 있음
				
				//왼쪾이 범위 안일 때
				if(isIn(tempLine-1)) {
					if(maps[Wline][tempLine] == maps[Wline][tempLine-1]) {//왼쪽으로 연결됨
						tempLine--;
						continue;
					}else {	//오른쪽으로 연결됨	(범위는 체크 할 필요 없음)
						tempLine++;
						continue;
					}
				}
				//왼쪽이 범위 안이 밖임
				if(isIn(tempLine+1)) { //오른쪽이 범위 안임 (체크 할 필요 없지만 가독성 위해)
					if(maps[Wline][tempLine] == maps[Wline][tempLine+1]) {
						tempLine++;
						continue;
					}else {	//오른쪽이 범위 안인데 오른쪽으로 연결된 게 아님 => 왼쪽
						tempLine--;
						continue;
					}
					
				}
				//현재 위치가 0이 아니면 왼쪽 오른족 둘 중 하나에 무조건 연결돼있음
			}
			//선이 연결된 경우 끝
			
			//선이 비어있는 경우 시작
			else {	//0임 => 자기 자리 찾으러 가야함
				if(tempLine == StartLine) {	//현재 위치가 시작 선임 => continue;
					continue;
				}else {	//자기 자리로 가야함
					
					if(tempLine < StartLine && isIn(tempLine+1)) {	//오른쪽이 범위 안임
						if(maps[Wline][tempLine+1] == 0) {	//오른쪽이 빔
							maps[Wline][tempLine] = ++CrossLineCount;
							maps[Wline][tempLine+1] = CrossLineCount;
							System.out.println(tempLine + "에서 오른쪽으로 길 만들어서 감");
							System.out.println(CrossLineCount);
							tempLine++;
							continue;
						}
					}
					//오른쪽이 범위 밖이거나 범위 안인데 오른쪽 빈칸이 아님
					
					if(tempLine > StartLine && isIn(tempLine-1)) {	//왼쪽이 범위 안임
						if(maps[Wline][tempLine-1] == 0) {	//왼쪽이 비어있음
							maps[Wline][tempLine] = ++CrossLineCount;
							maps[Wline][tempLine-1] = CrossLineCount;
							System.out.println(tempLine + "에서 왼쪽으로 길 만들어서 감, 그 층 : " + Wline);
							System.out.println(CrossLineCount);
							tempLine--;
							continue;
						}
					}
					//왼쪽이 범위 밖이거나 범위 안인데 왼쪽이 차있음
					
					continue;	//양쪽이 차있음
				}
			}
			
		}
		System.out.println("도착지 : " + tempLine);
		if(tempLine != StartLine) {	//돌아오지 못함
			return false;
		}else {	//돌아옴
			return true;	
		}
	}
	
	private static boolean isIn(int line) {
		return line >= 0 && line < N;
	}
}
