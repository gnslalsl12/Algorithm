package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//길 따라 탐색하다가
//끊긴 곳 발견하면
//상하좌우 탐색 후 다음 위치에 따라 블록 추가해주기
//다음 위치가 한 개가 아닌 3개일 경우 7

//실패 이유 : 파이프가 있어도 어느 방향으로 흘러야하는 지 파악할 수 없기 떄문에 새로운 파이프를 놓는 경우가 너무 길어진다
//수정 아이디어 : 시작 위치와 끝 위치에서 각자 출발해보고 끊긴 지점을 만났을 때 두 좌표를 비교해서 파이프를 놓자
public class BOJ_2931 {
	static int R, C;
	static int [][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static dirXY CutM;
	static dirXY endpoint;
	static boolean setP = false;	//지워진 파이프를 세운 후 true (새로 들어온 파이프가 시작점에 붙어있을 떄 시작 경로를 헷갈리지 않기 위해
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int pipecount = 1;
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		maps = new int [R][C];
		String templine;
		char temp;
		dirXY startpoint = new dirXY(0,0);
		for(int i = 0; i < R; i++) {
			templine = read.readLine();
			for(int j = 0; j < C; j++) {
				temp = templine.charAt(j);
				switch(temp) {
				case('Z'):{
					maps[i][j] = -1;
					endpoint = new dirXY(i,j);
					break;
				}
				case('M'):{
					maps[i][j] = -2;
					startpoint = new dirXY(i,j);
					break;
				}
				case('|'):{
					maps[i][j] = 5;
					pipecount++;
					break;
				}
				case('-'):{
					maps[i][j] = 6;
					pipecount++;
					break;
				}
				case('+'):{
					maps[i][j] = 7;
					pipecount += 2;
					break;
				}
				case('.'):{
					maps[i][j] = 0;
					break;
				}
				default:{
					pipecount++;
					maps[i][j] = (int)(temp - '0');
				}
				}
			}
		}
		CutM = new dirXY(0,0);	
		//mapping 완료
		dirXY CutS = GasGasGas(startpoint);	//시작지에서 파이프 따라 갔을 떄 끊어진 지점
		dirXY CutE = GasGasGas(endpoint);	//도착지에서 파이프 따라 갔을 때 끊어진 지점	
		//dirXY CutM은 끊어진 그 좌표
		//두개 연결하는 경우로 맵 설정해보고, 도착지로 가지 않으면 답은 +
		
		int movex = CutS.x - CutE.x;		//일자 줄만 있을 경우 확인용
		int movey = CutS.y - CutE.y;
		
		if(Math.abs(movex) == 2) {		//위아래로만 이동
			maps[CutM.x][CutM.y]= 5; 
		}else if(Math.abs(movey) == 2) {
			maps[CutM.x][CutM.y]= 6;	//좌우로만 이동 
		}
		
		else {
			int startmovex = CutM.x - CutS.x;	//시작에서 끊긴 지점으로 이동하는 x값
			int startmovey = CutM.y - CutS.y;	//시작에서 끊긴 지점으로 이동하는 y값
			int endmovex = CutE.x - CutM.x;		//끊긴 지점에서 이동해야하는 x값
			int endmovey = CutE.y - CutM.y;		//끊긴 지점에서 이동해야하는 y값
			
			if(startmovey == 1) {	//왼쪽에서 오른쪾 이동 => 위 또는 아래
				if(endmovex == -1) {	//오른쪽 위로 이동 : 3번파이프
					maps[CutM.x][CutM.y] = 3;
				}else {					//오른쪽 아래로 이동 : 4번 파이프
					maps[CutM.x][CutM.y] = 4;
				}
			}else if(startmovey == -1) {	//오른쪽에서 왼쪽 이동 = > 위 또는 아래
				if(endmovex == -1) {			//왼쪽위로 이동 : 2번 파이프
					maps[CutM.x][CutM.y] = 2; 	
				}else {						//왼쪽아래로 이동 : 1번 파이프
					maps[CutM.x][CutM.y] = 1;
				}
			}else if(startmovex == 1) {	//위에서 아래로 이동 => 왼쪽 또는 아래
				if(endmovey == 1) {	//아래 오른쪽 : 2번파이프
					maps[CutM.x][CutM.y] = 2;
				}else {					//아래 왼쪽 : 3번파이프
					maps[CutM.x][CutM.y] = 3;
				}
			}else if(startmovex == -1) {	//아래에서 위로 이동 => 왼쪽 또는 아래
				if(endmovey == 1) {	//위 오른쪽 : 1번 파이프 
					maps[CutM.x][CutM.y] = 1; 
				}else {				//위 왼쪽 : 4번 파이프
					maps[CutM.x][CutM.y]= 4; 
				}
			}
		}
		setP = true;	//사라진 파이프를 설치했음
		
		//경우에 맞게 일단 파이프 설치
		dirXY tempend = GasGasGas(startpoint);	//시작지에서 임시 맵으로 파이프를 따라갔을 때 도착지에 도달했는가
		if(endpoint.x == tempend.x && endpoint.y == tempend.y && pipecount == tempend.count) {	//도착 좌표가 같고 모든 파이프를 다 거쳐서 갔는가?
			if(maps[CutM.x][CutM.y] ==5) {
				System.out.println((CutM.x+1) + " " + (CutM.y+1) + " " + "|");
			}else if(maps[CutM.x][CutM.y] == 6) {
				System.out.println((CutM.x+1) + " " + (CutM.y+1) + " " + "-");
			}else {
				System.out.println((CutM.x+1) + " " + (CutM.y+1) + " " + maps[CutM.x][CutM.y]);
			}
		}else {	//도착지에 가지 못했거나 파이프를 생략한 곳이 있다
			System.out.println((CutM.x+1) + " " + (CutM.y+1) + " " + "+");
		}
	}
	
	private static dirXY GasGasGas(dirXY input) {	//파이프 따라 가기
		int tempcount = 0;
		int pastx = input.x;
		int pasty = input.y;
		int presentx = input.x;
		int presenty = input.y;
		for(int dir = 0; dir < 4; dir++) {
			if(!isIn(pastx + deltas[dir][0], pasty + deltas[dir][1])) continue;	//범위 밖
			if(maps[pastx + deltas[dir][0]][pasty + deltas[dir][1]] > 0) {		//파이프 찾음
				if(setP == true && pastx + deltas[dir][0] == CutM.x && pasty + deltas[dir][1] == CutM.y) continue;	//사라진 파이프를 세운 후 그 파이프가 탐색되면 continue;
				presentx = pastx + deltas[dir][0];
				presenty = pasty + deltas[dir][1];
			}
		}//처음 이동 경로 찾음
		
		while(true) {
			tempcount++;	//파이프를 몇 개 거쳐갔는가
			
			switch(maps[presentx][presenty]){
			case(-1):{	//도착함
				return new dirXY(endpoint.x, endpoint.y, tempcount-1);
			}
			case(1):{
				if(pastx == presentx) {	//오른쪽에서 들어오는 경우
					pasty--;			//과거 y -1 (왼쪽으로)
					presentx++;			//현재 x값은 +1(아래로)
				}else {	//아래에서 위로 올라가는 경우
					pastx--;			//과거 x -1 (위로)
					presenty++;			//현재 y값은 +1(오른쪽으로)
				}
				break;
			}
			case(2):{
				if(pastx != presentx) {	//위에서 아래로 오는 경우 (오른쪽 이동)
					pastx++;
					presenty++;
				}else {					//오른쪽에서 들어오는 경우
					pasty--;
					presentx--;			//위로 올라감
				}
				break;
			}
			case(3):{
				if(pastx == presentx) {	//왼쪽에서 들어오는 경우
					pasty++;
					presentx--;			//위로 올라감
				}else {					//위세어 내려오는 경우
					pastx++;
					presenty--;			//왼쪽으로 감
				}
				break;
			}
			case(4):{
				if(pastx == presentx) {	//왼쪽에서 들어오는 경우
					pasty++;
					presentx++;
				}else {					//아래에서 올라오는 경우
					pastx--;
					presenty--;
				}
				break;
			}
			case(7):{
				if(pastx < presentx) {	//위에서 내려오는 경우
					pastx++;
					presentx++;
					break;
				}else if(pastx > presentx){		//아래에서 올라오는 경우
					pastx--;
					presentx--;
					break;
				}else if(pasty < presenty) {	//왼쪽에서 오는 경우
					pasty++;
					presenty++;
					break;
				}else if(pasty > presenty){		//오른쪽에서 오는 경우
					pasty--;
					presenty--;
					break;
				}
			}
			case(5):{
				if(pastx < presentx) {	//위에서 내려오는 경우
					pastx++;
					presentx++;
					break;
				}else if(pastx > presentx){					//아래에서 올라오는 경우
					pastx--;
					presentx--;
					break;
				}
			}
			case(6):{
				if(pasty < presenty) {	//왼쪽에서 오는 경우
					pasty++;
					presenty++;
					break;
				}else if(pasty > presenty){					//오른쪽에서 오는 경우
					pasty--;
					presenty--;
					break;
				}
			}
			case(0):{		//끊긴 지점 발견
				CutM = new dirXY(presentx, presenty);	//끊긴 지점
				return new dirXY(pastx, pasty); 		//끊긴 지점을 만나기 전의 좌표
			}
			
			}
		}
		
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < R && y < C;
	}
	
	private static class dirXY {
		int x;
		int y;
		int count;	//파이프 통과 수
		
		public dirXY(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}