package day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ4179 {
	static int R,C;
	static int [][] maps;
	static ArrayList<Integer> Successes;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		String [][] tempmaps = new String [R][C];
		dirXY JihoonDir = new dirXY();
		maps = new int[R][C];
		ArrayList<dirXY> FireDirArray = new ArrayList<>();	//불 좌표들
		for(int i = 0; i< R ; i++) {
			tempmaps[i] = read.readLine().split("");
			for(int j = 0; j < C ; j++) {
				switch(tempmaps[i][j]) {
				case("#"):{
					maps[i][j] = Integer.MAX_VALUE; //R*C는 거르고 이동하기
					break;
				}
				case("J"):{
					maps[i][j] = -1;
					JihoonDir = new dirXY(i,j);		//지훈이 좌표
					break;
				}
				case("F"):{
					maps[i][j] = 1;
					FireDirArray.add(new dirXY(i,j));
					break;
				}
				case("."):{
					maps[i][j] = 0;
					break;
				}
				}
			}
		} //maps 완료
		Successes = new ArrayList<>();
		for(dirXY fires : FireDirArray) {
			BFSforFire(fires);
		}
		DFSforJihoon(JihoonDir);
		
		
		if(Successes.size() == 0) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		Collections.sort(Successes);
		System.out.println(Successes.get(0));
	}
	
	static void BFSforFire(dirXY firexy) {
		Queue<dirXY> BFSqueueF = new LinkedList<>();
		BFSqueueF.add(firexy);
		maps[firexy.x][firexy.y] = 1;
		int [][] PassedFireway = new int [R][C];
		PassedFireway[firexy.x][firexy.y] = 1;
		while(!BFSqueueF.isEmpty()) {	
			dirXY poppedXY = BFSqueueF.poll();	//지나온 경로를 1로 표시하지 말고 지금보다 낮으면 가지 않는 걸로 하자
			for(int dd = 0; dd < 4; dd++) {
				int nextx = poppedXY.x + deltas[dd][0];
				int nexty = poppedXY.y + deltas[dd][1];
				if(nextx >= R || nexty >= C || nextx < 0 || nexty < 0 || maps[nextx][nexty] == 1 || maps[nextx][nexty] == -1 || maps[nextx][nexty] == Integer.MAX_VALUE) {//범위를 벗어나면 continue + 다음 만나는 게 지훈(-1)이면 pass
					continue;
				}
				if(PassedFireway[nextx][nexty] == 0) {	// 다음 경로의 발자국이 0이 아닌거(안 갔음 아직) == 어차피 BFS라 나보다 낮은지는 체크 불필요
					//내가 안 지나온 길이거나 내가 지나왔는데 더 빠르게 지나갈 수 있는 경우
					
					if(maps[nextx][nexty] == 0 || maps[nextx][nexty] > maps[poppedXY.x][poppedXY.y]+1) { //다음 위치가 길이면 (0) or 다음 예상 경로가 내가 들어갈 값보다 클 떄 = 내가 선수치는 불
//						System.out.println("추가될 자표 : " + nextx + ", "+ nexty);
						PassedFireway[nextx][nexty] = PassedFireway[poppedXY.x][poppedXY.y] +1; //지나왔다는 뜻으로 1 넣어줌
						maps[nextx][nexty] = maps[poppedXY.x][poppedXY.y]+1;
						BFSqueueF.add(new dirXY(nextx, nexty));//다음 경로를 넣어줌
					}
				}//그 외에 -1(F)를 만나거나 R*C(벽)을 만나거나 이미 지나온 경로를 만나면 아무것도 안 하고 continue
			}
		}	
	}
	
	static int[][] PassedJHway;
	static void DFSforJihoon(dirXY tempxy) { //DFS는 Stack구조
		Stack<dirXY> DFSstackJ = new Stack<>();
		DFSstackJ.add(tempxy);
		maps[tempxy.x][tempxy.y] = 1; //초기 시작을 1로 해줌
		PassedJHway = new int [R][C];
		PassedJHway[tempxy.x][tempxy.y] = 1; //초기 시작을 1로 해줌
		
		while(!DFSstackJ.isEmpty()) {	//0인지? , else if 나보다 작은지 => 불에 탐
			
			dirXY poppedXY = DFSstackJ.pop();
			
			for(int dd = 0; dd < 4; dd++) {
				int nextx = poppedXY.x + deltas[dd][0];
				int nexty = poppedXY.y + deltas[dd][1];
				if(nextx >= R || nexty >= C || nextx < 0 || nexty < 0) {//범위를 벗어나면 성공
					Successes.add(maps[poppedXY.x][poppedXY.y]);
					break;	//다음 위치에서 빠져나간다는 건 다음 이동 경우 생각 안 해도 됨
				}
				if(maps[nextx][nexty] == Integer.MAX_VALUE || maps[nextx][nexty] == 1) { //R*C는 벽임
					continue;
				}else if(PassedJHway[nextx][nexty] == 0 || PassedJHway[nextx][nexty] > PassedJHway[poppedXY.x][poppedXY.y]) {
					if(maps[nextx][nexty] == 0 || maps[nextx][nexty] > maps[poppedXY.x][poppedXY.y]+1) {
						//내 경로가 불이 지나간 단계보다 작을 때(빠름) 지나갈 수 있음 || 불이 없을 경우 대비 0 포함
						PassedJHway[nextx][nexty] = PassedJHway[poppedXY.x][poppedXY.y]+1; //지나왔다는 뜻으로 +1 넣어줌
						maps[nextx][nexty] = maps[poppedXY.x][poppedXY.y]+ 1; //움직인 단계를 넣어줌 (이전 단계 +1)
						DFSstackJ.add(new dirXY(nextx, nexty));//다음 경로를 넣어줌
					}
				}
			}
		}	
	}
}

class dirXY{
	int x;
	int y;
	public dirXY() {
		
	}
	public dirXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}