package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190 {
	
	static ArrayList<Integer> Apples = new ArrayList<>();
	static int N, K, L;
	static int x, y;
	static Queue<Integer> RotateTiming = new LinkedList<>();	//회전해야하는 타이밍 Q
	static Queue<String> MoveDir = new LinkedList<>();			//회전 방향 Q(오른쪽/왼쪽)
	static int [][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};		//순서대로 오른쪽, 아래, 왼쪽, 위. (index가 1 증가할수록 오른쪽 회전, 1 감소할수록 왼쪽 회전)
	static Deque<Integer> SNAKE = new LinkedList<>();			//뱀의 형태를 기록하는 DQ
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());		//맵의 범위
		tokens = new StringTokenizer(read.readLine());
		
		K = Integer.parseInt(tokens.nextToken());		//사과 개수
		for(int i = 0; i < K; i++) {
			tokens = new StringTokenizer(read.readLine());
			Apples.add(todirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		
		
		tokens = new StringTokenizer(read.readLine());
		L = Integer.parseInt(tokens.nextToken());		//회전 수
		for(int i = 0; i < L; i++) {
			tokens = new StringTokenizer(read.readLine());
			RotateTiming.add(Integer.parseInt(tokens.nextToken()));	//회전해야하는 타이밍을 기록한 Q
			MoveDir.add(tokens.nextToken());						//회전 방향을 기록한 Q
		}
		
		PresentDir = 0;		//방향 전환
		x = 1;				//머리의 위치
		y = 1;				//머리의 위치
		
		int resulttime = MoveSnake(0);
		
		System.out.println((resulttime + 1));
		
	}

	private static int MoveSnake(int time) {
		//초기 위치는 1,1
		SNAKE.add(todirXY(x, y));	//초기 시작 위치
		while(true) {
			//움직임
			if(!RotateTiming.isEmpty()) {	//움직여야하는 게 아직 남아있을 때
				if(time == RotateTiming.peek()) { //지금이 회전해야하는 타이밍이라면
					RotateTiming.poll();	//시간 뺴내주고
					Rotate();				//머리 방향 회전 메서드
				}
			}
			
			//방향 PresentDir에 맞게 움직여야함
			x += deltas[PresentDir][0];
			y += deltas[PresentDir][1];
			
			int nextXY = todirXY(x,y); //다음 움직여야할 위치
			
			if(!isIn(x,y)) {	//다음 가야할 위치가 범위를 벗어남
				return time;
			}

			for(int i = 0; i < SNAKE.size(); i++) {	
				if(SNAKE.contains(nextXY)) { 	//다음 위치가 뱀 몸이 있는 곳임
					return time;
				}
			}
			
			boolean ate = false;	//default 사과 먹지 않음
			for(int i = 0; i < Apples.size(); i++) {
				if(Apples.get(i).equals(nextXY)) {	//사과가 있는 위치임
					Apples.set(i,0);	//먹은 사과 없애기
					ate = true;			//먹었어요
					break;
				}
			}
			if(!ate) {	//먹지 않음
				SNAKE.removeLast();	// 사과 먹지 않았을 때만 꼬리 자름
			}
			
			SNAKE.addFirst(nextXY);	//사과를 먹든 안 먹든 머리는 앞으로 이동함
			
			time++;
		}
		
	}
	private static boolean isIn(int x, int y) {	//맵 안에 있는가?
		return x>=1 && x <= N && y >= 1 && y <= N;
	}
	
	static int PresentDir;
	private static void Rotate(){				//회전 지시에 맞게 방향 회전
		switch(MoveDir.poll()) {
		case("D"):{				//오른쪽으로 돌아야하니 deltas에서 오른쪽 회전하는 index로 바꾸기 위해 +1
			PresentDir++;
			break;
		}
		case("L"):{				//왼쪽으로 돌아야하니 deltas에서 오른쪽 회전하는 index로 바꾸기 위해 -1
			PresentDir--;
			break;
		}
		}
		if(PresentDir < 0) {	//값이 음수가 됐을 때 대비 
			PresentDir += 4;
		}else {
			PresentDir %= 4;	//5 이상일 때 대비
		}
	}
	
	private static int todirXY(int x, int y) {	//좌표를 int로 변환해주는 메서드
		return x*1000 + y;
	}

}
