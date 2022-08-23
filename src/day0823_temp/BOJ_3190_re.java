package day0823_temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_re {
	static ArrayList<Integer> Apples = new ArrayList<>();
	static int N, K, L;
	static int [][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static Queue<Integer> RotateTiming = new LinkedList<>();
	static Queue<String> MoveDir = new LinkedList<>();
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		K = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < K; i++) {
			tokens = new StringTokenizer(read.readLine());
			Apples.add(todirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		PresentDir = 0;
		x = 1;
		y = 1;
		tokens = new StringTokenizer(read.readLine());
		L = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < L; i++) {
			tokens = new StringTokenizer(read.readLine());
			RotateTiming.add(Integer.parseInt(tokens.nextToken()));
			MoveDir.add(tokens.nextToken());
		}
		
		int resulttime = MoveSnake(0);
		System.out.println(resulttime);
		
		
	}
	static int x, y;
	static Deque<Integer> SNAKE = new LinkedList<>();
	private static int MoveSnake(int time) {
		//초기 위치는 0,0
		SNAKE.add(todirXY(0, 0));	//초기 시작 위치
		while(true) {
			//움직임
			System.out.println("========" + time + "========");
			if(!RotateTiming.isEmpty()) {
				if(time == RotateTiming.peek()) { //회전해야하는 타이밍
					System.out.println("돌꼐용!!!!");
					RotateTiming.poll();	//시간 뺴내주고
					Rotate();
				}
			}
			//움직이던 안 하던 지금 방향 PresentDir에 맞게 움직여야함
			x += deltas[PresentDir][0];
			y += deltas[PresentDir][1];
			
			
			int nextXY = todirXY(x,y); //다음 움직여야할 위치
			System.out.println(nextXY);
			
			
			if(!isIn(x,y)) {	//다음 가야할 위치가 범위를 벗어남
				System.out.println("----------범위 나감");
				return time;
			}

			for(int i = 0; i < SNAKE.size(); i++) {
				if(SNAKE.)
			}
			if(SNAKE.contains(nextXY)) {		//다음 위치가 내 몸을 잡아먹음
				System.out.println("---------------------앙 물어떠");
				return time;
			}
			
			boolean ate = false;
			for(int i = 0; i < Apples.size(); i++) {
				if(Apples.get(i).equals(nextXY)) {
					System.out.println("------------------------------------사과먹고 쑦쑦");
					Apples.remove(nextXY);	//사과 먹었으니 길이 추가됨
					ate = true;
				}
			}
			if(!ate) {
				SNAKE.removeLast();
			}
			
			SNAKE.addFirst(nextXY);
			
			time++;
		}
		
		
	}
	private static boolean isIn(int x, int y) {
		return x>=1 && x <= N && y >= 1 && y <= N;
	}
	
	static int PresentDir;
	private static void Rotate(){
		switch(MoveDir.poll()) {
		case("D"):{
			PresentDir++;
			break;
		}
		case("L"):{
			PresentDir--;
			break;
		}
		}
		if(PresentDir < 0) {
			PresentDir += 4;
		}else {
			PresentDir %= 4;
		}
	}
	private static int todirXY(int x, int y) {
		return x*1000 + y;
	}
	private static class dirXY{
		int x;
		int y;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "다음 위치 : [x=" + x + ", y=" + y + "]";
		}
		
		
	}
}
