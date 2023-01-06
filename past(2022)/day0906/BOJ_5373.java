package day0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5373 {
	static int UP = 0;
	static int DOWN = 1;
	static int FRONT = 2;
	static int BACK = 3;
	static int LEFT = 4;
	static int RIGHT = 5;
	static char[][][] Cube;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(tokens.nextToken());	//테스트케이스 
		for(int test = 1; test <= T; test++) {
			
			Queue<MoveOrder> MoveQ = new LinkedList<>();
			tokens = new StringTokenizer(read.readLine());	//몇 번 움직이기?
			int RotateN = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(read.readLine());
			while(tokens.hasMoreTokens()) {
				String templine = tokens.nextToken();
				boolean forward = true;
				if(templine.charAt(1) == '-') {
					forward = false;
				}
				MoveQ.add(new MoveOrder(templine.charAt(0), forward));
			}
			
			Cube = new char [6][3][3];
			for(int i = 0; i < 3; i ++) {
				Arrays.fill(Cube[0][i], 'w');	//UP	흰 
				Arrays.fill(Cube[1][i], 'y'); 	//DOWN	노
				Arrays.fill(Cube[2][i], 'r'); 	//FRONT	빨
				Arrays.fill(Cube[3][i], 'o'); 	//BACK	오렌
				Arrays.fill(Cube[4][i], 'g'); 	//LEFT	초
				Arrays.fill(Cube[5][i], 'b'); 	//RIGHT	파
			}
			//메서드 구현하기 (char 면, boolean 방향
			while(!MoveQ.isEmpty()) {
				MoveOrder temp = MoveQ.poll();
				Rotate(Cube[temp.plate], temp.forward);	//해당 면 돌리기
				Wheel(temp.plate, temp.forward);		//인접한 블록 회전
			}
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					sb.append(Cube[0][i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	private static void Rotate(char [][] map, boolean forward) {	//해당 면을 돌린다
		Queue<Character> tempQ = new LinkedList<>();
		for(int i = 0; i < 3; i++) {
			tempQ.add(map[0][i]);
		}
		for(int i = 1; i < 3; i++) {
			tempQ.add(map[i][2]);
		}
		for(int i = 1; i >= 0; i--) {
			tempQ.add(map[2][i]);
		}
		tempQ.add(map[1][0]);
		
		if(forward) {
			for(int i = 0; i < 3; i++) {
				map[i][2] = tempQ.poll();
			}
			for(int i = 1; i >= 0; i--) {
				map[2][i] = tempQ.poll();
			}
			for(int i = 1; i >= 0; i--) {
				map[i][0] = tempQ.poll();
			}
			map[0][1] = tempQ.poll();
		}else {
			for(int i = 2; i >= 0; i--) {
				map[i][0] = tempQ.poll();
			}
			for(int i = 1; i < 3; i++) {
				map[0][i] = tempQ.poll();
			}
			for(int i = 1; i < 3; i++) {
				map[i][2] = tempQ.poll();
			}
			map[2][1] = tempQ.poll();
		}
	}

	private static void Wheel(int whichPlate, boolean forward) {
		Queue<Character> WheelQ = new LinkedList<>();
		switch(whichPlate) {
		case(0):{
			int [] SideCube = {4,2,5,3,4,2};
			for(int i = 1; i < 5; i++) {	//옆면 index
				for(int j = 0; j < 3; j++) {		//옆면의 맨 윗줄 j값 차례로
					WheelQ.add(Cube[SideCube[i]][0][j]);
				}
			}
			if(forward) {	//시계방향 회전
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j < 3; j++) {
						Cube[SideCube[i]][0][j] = WheelQ.poll();
					}
				}
			}else {		//시계반대방향
				for(int i = 2; i < 6; i++) {
					for(int j = 0; j < 3; j++) {
						Cube[SideCube[i]][0][j] = WheelQ.poll();
					}
				}
			}
			break;
		}
		case(1):{
			int [] SideCube = {4,2,5,3,4,2};
			for(int i = 1; i < 5; i++) {	//옆면 index
				for(int j = 0; j < 3; j++) {		//옆면의 맨 윗줄 j값 차례로
					WheelQ.add(Cube[SideCube[i]][2][j]);
				}
			}
			if(forward) {	//시계방향 회전
				for(int i = 2; i < 6; i++) {
					for(int j = 0; j < 3; j++) {
						Cube[SideCube[i]][2][j] = WheelQ.poll();
					}
				}
			}else {		//시계반대방향
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j < 3; j++) {
						Cube[SideCube[i]][2][j] = WheelQ.poll();
					}
				}
			}
			break;
		}
		case(2):{
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[0][2][i]);	//윗칸 맨 아랫줄 추가
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[5][i][0]); //오른쪽 맨 왼쪽 줄 추가
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[1][0][i]);	//아래 맨 윘줄 추가
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[4][i][2]); //왼쪽 맨 오른쪽 줄 추가
			}
			//Q 완료
			if(forward) {
				for(int i = 0; i < 3; i++) {
					Cube[5][i][0] = WheelQ.poll();	//오른쪽 맨왼쪽부터 시작
				}
				for(int i = 2; i >= 0; i--) {
					Cube[1][0][i] = WheelQ.poll();	//아래
				}
				for(int i = 2; i >= 0; i--) {
					Cube[4][i][2] = WheelQ.poll(); //왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][2][i] = WheelQ.poll();	//윗칸 맨 아랫줄 에 끝
				}
			}else {
				for(int i = 2; i >= 0; i--) {
					Cube[4][i][2] = WheelQ.poll(); //왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][2][i] = WheelQ.poll();	//윗칸 
				}
				for(int i = 0; i < 3; i++) {
					Cube[5][i][0] = WheelQ.poll();	//오른쪽
				}
				for(int i = 2; i >= 0; i--) {
					Cube[1][0][i] = WheelQ.poll();	//아래
				}
			}
			break;
		}
		case(3):{
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[0][0][i]);	//윗칸 맨 윗줄 추가
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[5][i][2]); //오른쪽 맨 오른 줄 추가
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[1][2][i]);	//아래 맨 아래줄 추가
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[4][i][0]); //왼쪽 맨 왼쪽 줄 추가
			}
			//Q 완료
			if(forward) {
				for(int i = 2; i >= 0; i--) {
					Cube[4][i][0] = WheelQ.poll(); //왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][0][i] = WheelQ.poll();	//윗칸
				}
				for(int i = 0; i < 3; i++) {
					Cube[5][i][2] = WheelQ.poll();	//오른쪽
				}
				for(int i = 2; i >= 0; i--) {
					Cube[1][2][i] = WheelQ.poll();	//아래
				}
			}else {
				for(int i = 0; i < 3; i++) {
					Cube[5][i][2] = WheelQ.poll();	//오른쪽
				}
				for(int i = 2; i >= 0; i--) {
					Cube[1][2][i] = WheelQ.poll();	//아래
				}
				for(int i = 2; i >= 0; i--) {
					Cube[4][i][0] = WheelQ.poll(); //왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][0][i] = WheelQ.poll();	//윗칸
				}
			}
			break;
			
		}
		case(4):{
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[0][i][0]);	//윗칸 왼쪽
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[2][i][0]); //앞쪽 왼쪽
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[1][i][0]);	//아래 왼쪽
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[3][i][2]); //뒷쪽 오른쪽
			}
			if(forward) {
				for(int i = 0; i < 3; i++) {
					Cube[2][i][0] = WheelQ.poll(); //앞쪽 왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[1][i][0] = WheelQ.poll();	//아래 왼쪽
				}
				for(int i = 2; i >= 0; i--) {
					Cube[3][i][2] = WheelQ.poll(); //뒷쪽 오른쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][i][0] = WheelQ.poll();	//윗칸 왼쪽
				}
			}else {
				for(int i = 2; i >= 0; i--) {
					Cube[3][i][2] = WheelQ.poll(); //뒷쪽 오른쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][i][0] = WheelQ.poll();	//윗칸 왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[2][i][0] = WheelQ.poll(); //앞쪽 왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[1][i][0] = WheelQ.poll();	//아래 왼쪽
				}
			}
			break;
		}
		case(5):{
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[0][i][2]);	//윗칸 오른쪽
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[2][i][2]); //앞쪽 오른쪽
			}
			for(int i = 0; i < 3; i++) {
				WheelQ.add(Cube[1][i][2]);	//아래 오른쪽
			}
			for(int i = 2; i >= 0; i--) {
				WheelQ.add(Cube[3][i][0]); //뒷쪽 왼쪽
			}
			if(forward) {
				for(int i = 2; i >= 0; i--) {
					Cube[3][i][0] = WheelQ.poll(); //뒷쪽 왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][i][2] = WheelQ.poll();	//윗칸 오른쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[2][i][2] = WheelQ.poll(); //앞쪽 오른쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[1][i][2] = WheelQ.poll();	//아래 오른쪽
				}
			}else {
				for(int i = 0; i < 3; i++) {
					Cube[2][i][2] = WheelQ.poll(); //앞쪽 오른쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[1][i][2] = WheelQ.poll();	//아래 오른쪽
				}
				for(int i = 2; i >= 0; i--) {
					Cube[3][i][0] = WheelQ.poll(); //뒷쪽 왼쪽
				}
				for(int i = 0; i < 3; i++) {
					Cube[0][i][2] = WheelQ.poll();	//윗칸 오른쪽
				}
			}
			break;
		}
		}
	}
	
	private static class MoveOrder{
		int plate;
		boolean forward;
		public MoveOrder(char plate, boolean forward) {
			super();
			if(plate == 'U') {
				this.plate = 0;
			}else if(plate == 'D') {
				this.plate = 1;
			}else if(plate == 'F') {
				this.plate = 2;
			}else if(plate == 'B') {
				this.plate = 3;
			}else if(plate == 'L') {
				this.plate = 4;
			}else if(plate == 'R') {
				this.plate = 5;
			}
			this.forward = forward;
		}
	}
}