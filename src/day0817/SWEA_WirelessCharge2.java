package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_WirelessCharge2 {
	static int [][] maps;
	static int M;
	static int A;
	static ArrayList<BCinfo> BC;
	static dirXY Axy, Bxy;
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		
		for(int test = 1; test <= T ; test++) {
			int finalresult = 0;
			Queue<Integer> Amove = new LinkedList<>();
			Amove.add(0);	//초기 위치
			Queue<Integer> Bmove = new LinkedList<>();
			Bmove.add(0); //초기 위치
			BC = new ArrayList<>();
			tokens = new StringTokenizer(read.readLine());
			maps = new int[10][10];
			M = Integer.parseInt(tokens.nextToken());	//총 이동 시간
			A = Integer.parseInt(tokens.nextToken());	//충전기 개수
			tokens = new StringTokenizer(read.readLine());
			while(tokens.hasMoreTokens()) {
				Amove.add(Integer.parseInt(tokens.nextToken()));
			}	//A움직임 정보
			tokens = new StringTokenizer(read.readLine());
			while(tokens.hasMoreTokens()) {
				Bmove.add(Integer.parseInt(tokens.nextToken()));
			}	//B 움직임 정보
			Axy = new dirXY(1, 1);
			Bxy = new dirXY(10,10);
			
			for(int i = 0; i < A ; i++) {
				tokens = new StringTokenizer(read.readLine());
				BC.add(new BCinfo(Integer.parseInt(tokens.nextToken()),
						Integer.parseInt(tokens.nextToken()),
						Integer.parseInt(tokens.nextToken()),
						Integer.parseInt(tokens.nextToken())));
			} //충전기 정보 저장 완료
			
			for(int i = 0; i <= M ; i++) {
				ABmove(Axy, Amove.poll());	//A움직임
				BCDuplicate Achargeinfo = BCApproachSensor(Axy);	//A의 충전 정보가 충전기 번호, 충전 량 만큼 나타남
				ABmove(Bxy, Bmove.poll());	//B움직임
				BCDuplicate Bchargeinfo = BCApproachSensor(Bxy);	//A의 충전 정보가 충전기 번호, 충전 량 만큼 나타남
				if(Achargeinfo.number == Bchargeinfo.number) {
					finalresult += Achargeinfo.MaxAmount + Math.max(Achargeinfo.minAmount, Bchargeinfo.minAmount);				//같은 충전기면 반만 더하니까 하나만 더해주기
				
				}else {
					finalresult += Achargeinfo.MaxAmount + Bchargeinfo.MaxAmount; //다른 충전기면 따로 더하기
				}
			}
			System.out.println("#" + test + " " + finalresult);
		}
	}
	static int HowFar(int x, int y, int xx, int yy) {
		return Math.abs(x-xx) + Math.abs(y-yy);
	}
	
	static BCDuplicate BCApproachSensor(dirXY xy) {	//현재 위치에서 각 BC까지 거리 구해서 충전 정보
		int amountA = 0;
		int MAX_number = -1;
		int amountB = 0;
		for(int i = 0; i < A ; i++) {
			if(HowFar(xy.x, xy.y, BC.get(i).x, BC.get(i).y) <= BC.get(i).C) {
				if(amountA != 0) {	//앞에 값이 있으면
					//들어오는 값과 지금 값을 비교해서 큰 값을 A에
					if(amountB < BC.get(i).P) {
						amountB = BC.get(i).P;
					}
					if(amountA < amountB) { //  B가 더 크면 자리 바꿈
						int temp = amountA;
						amountA = amountB;
						amountB = temp;
						MAX_number = i;
					}//A(지금 값이 더 크면 안 바꿈
				}else {//처음 값이면
					amountA = BC.get(i).P;
					MAX_number = i;
				}
			}
		} //한 충전기씩 거리 구해서 범위에 들어오면 그 값을 넣어줌
		return new BCDuplicate(MAX_number, amountA, amountB); //number가 -1이면 충전 못한 거임(어차피 MAX가 0이 될 거긴 함)
	}
	
	static void ABmove(dirXY xy, int Swtch) {
		switch(Swtch) {
		case(0):{
			return;
		}
		case(1):{	//위로 한 칸
			xy.y -= 1;
			break;
		}
		case(2):{	//우로 한 칸
			xy.x += 1;
			break;
		}
		case(3):{	//하로 한 칸
			xy.y += 1;
			break;
		}
		case(4):{	//좌로 한 칸
			xy.x -= 1;
			break;
		}
		}
	}
}

class dirXY{
	int x;
	int y;
	public dirXY(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}


class BCDuplicate {
	int number;	//충전기 번호
	int MaxAmount;	//충전량
	int minAmount;
	public BCDuplicate(int number, int Maxamount, int minAmount) {
		super();
		this.number = number;
		this.MaxAmount = Maxamount;
		this.minAmount = minAmount;
	}
}

class BCinfo{
	int x;
	int y;
	int C;//충전범위
	int P;// 충전 성능
	public BCinfo(int x, int y, int c, int p) {
		super();
		this.x = x;
		this.y = y;
		C = c;
		P = p;
	}
}