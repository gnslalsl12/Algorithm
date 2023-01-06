package day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_2382 {
	static int N;	//정사각형 변
	static int K;	//최초 미생물 군집 개수
	static int[][] deltas = {{}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
	static ArrayList<dirXY> MSM;
	static int [][] maps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(tokens.nextToken());
		for(int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());	//격리시간
			K = Integer.parseInt(tokens.nextToken());
			MSM = new ArrayList<>();
			maps = new int [N][N];
			for(int i = 0; i < K; i++) {
				tokens = new StringTokenizer(read.readLine());
				MSM.add(new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),
									Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}//매핑 완료
			
			for(int time = 0; time < M; time++) {
				Collections.sort(MSM);	//큰놈부터
				int size = MSM.size();
				for(int i = 0; i < size; i++) {	//먼저 자기 위치의 먹이를 먹어버림
					dirXY temppop = MSM.remove(0);
					if(maps[temppop.x][temppop.y] != 0) {	//내 위치에 먹을 게 있네?
						temppop.LeaveonMap();				//와앙
						MSM.add(temppop);					//나는 살아남았어
					}
					//뒤늦게 온 놈은 먹을 게 없어서 죽음
				}
				
				size = MSM.size();	//먹고 살아남은 애들만
				for(int i = 0; i < size; i++) {
					dirXY pop = MSM.get(i);
					Moves(pop);			//한 칸 이동하기
					pop.SetonMap();		//도착해서 뿌리내림
				}//하나씩
			}//시간만큼
			
			int result = 0;
			for(dirXY finalMSM : MSM) {
				result += maps[finalMSM.x][finalMSM.y];
				maps[finalMSM.x][finalMSM.y]= 0; 
			}
			sb.append("#" + test + " " + result + "\n");
		}
		System.out.println(sb);
		
		
	}
	
	private static void Moves(dirXY input) {
		int nextx = input.x + deltas[input.direction][0]*input.rev;
		int nexty = input.y + deltas[input.direction][1]*input.rev;
		if(!isIn(nextx,nexty)) {	//벽에 도착시
			input.rev *= -1;
			input.amount /= 2;
		}
		input.x = nextx;
		input.y = nexty;
	}
	
	private static boolean isIn(int x, int y) {
		return x > 0 && x < N-1 && y > 0 && y < N-1;
	}
	
	private static class dirXY implements Comparable<dirXY>{
		int x;
		int y;
		int amount;
		int direction;
		int rev = 1;
		
		public void LeaveonMap() {	//이동 전의 좌표를 토대로 떠날 준비
			this.amount = maps[this.x][this.y];
			maps[this.x][this.y] = 0;
			
		}
		
		public void SetonMap() {		//이동 후의 좌표를 토대로 움직임
			maps[this.x][this.y] += this.amount;	//나를 도착시켜
		}
		
		public dirXY(int x, int y, int amount, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.amount = amount;
			this.direction = direction;
			maps[x][y] = this.amount;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(o.amount, this.amount);	//양이 많은 놈부터 시작
		}
	}
}