package day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684 {
	static int N, M, H;
	static int [][] maps;
	static int thisCaseMaxCount;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());	//세로선 개수
		M = Integer.parseInt(tokens.nextToken());	//설치된 가로선 개수
		H = Integer.parseInt(tokens.nextToken());	//가로선 놓을 수 있는 라인 수
		maps = new int [H][N];
		result = -1;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken())-1;	//가로 a줄을
			int b = Integer.parseInt(tokens.nextToken())-1;	//세로 b와 b+1줄에
			maps[a][b] = 1;		//오른쪽으로
			maps[a][b+1] = -1;	//왼쪽으로
		}
		for(thisCaseMaxCount = 0; thisCaseMaxCount <= 3; thisCaseMaxCount++) {	//최대 3개까지 놓을 수 있으므로 0개, 1개, 2개, 3개 놓는 경우 모두 DFS
			DFS(0, 0);	//(시작 가로줄, 새로 세운 가로다리 수)
			if(result != -1) {	//되는 경우를 찾음
				break;
			}
		}
		System.out.println(result);

	}
	
	private static void DFS(int Cross, int tempCrossCount) {
		if(tempCrossCount == thisCaseMaxCount) {	//max만큼 다리 놓음
			if(CheckAllReturn()) {	//모두 자기 자신에게 돌아오는가
				result = tempCrossCount;	//돌아오는 경우 찾음
				return;
			}
			result = -1;
			return;		//돌아오는 경우 없음
		}
		
		for(int tempcross = Cross; tempcross < H; tempcross++) {
			for(int templine = 0; templine < N; templine++) {
				if(templine == N-1) continue;
				if(maps[tempcross][templine] == 0 && maps[tempcross][templine+1] == 0) {
					maps[tempcross][templine] = 1;
					maps[tempcross][templine + 1] = -1;	//선 하나 놓아보기 (같은 x에서 오른쪽으로 하나씩
					DFS(tempcross, tempCrossCount+1);
					if(result != -1) return;	//돌아오는 경우 찾음
					maps[tempcross][templine] = 0;
					maps[tempcross][templine + 1] = 0;//맵 되돌려주기	(백트래킹)
				}
			}
		}
	}
	
	private static boolean CheckAllReturn() {	//모든 선이 자신에게로 돌아오는가
		for(int templine = 0; templine < N; templine++) {
			int endpoint = templine;
			for(int tempcross = 0; tempcross < H; tempcross++) {
				templine += maps[tempcross][templine];	//1이면 오른쪾 이동 -1이면 왼쪽 이동
			}
			if(templine == endpoint) {
				continue;
			}else {
				return false;	//하나라도 자기 자신에게 못 옴
			}
		}
		return true;
	}
}