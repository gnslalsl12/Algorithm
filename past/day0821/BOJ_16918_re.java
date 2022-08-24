package day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Bitmasking으로 해보자

//N%2 == 0 : 모두 폭탄
//N == 1 : 초기폭탄
//N>1 이고 N%4 == 1: 
public class BOJ_16918_re {
	
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int R,C,N;
	static StringBuilder sb = new StringBuilder();
	static int T;
	static int[][] deltas = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		ArrayList<Integer> BitmaskList = new ArrayList<>();
		T = (N-1)%4;	//N == 0 : 초기맵, N == 1,3 : 모두 폭탄, N == 2 : 반대폭탄 
		
		for(int i = 0; i < R; i++) {
			int BitMask = 0;
			String templine = read.readLine();
			for(int j = 0; j < C; j++) {
				if(templine.charAt(j) == 'O') {
					BitMask |= 1<<j;
				}
			}
			BitmaskList.add(BitMask);	//맵 입력받아서 Bitmask화
		}
		Mapping(BitChange(BitmaskList));
		System.out.print(sb);
	}
	
	private static boolean isIn(int x, int y) {		//범위 안에 들어오는가
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	private static ArrayList<Integer> BitChange(ArrayList<Integer> BML) {	//시간에 맞게 맵의 BM 변경
		switch(T) {
		case(0):{	//초기 맵 그대로
			return BML;
		}//case0
		case(1):
		case(3):{	//모두 폭탄 맵
			ArrayList<Integer> AllBombBML = new ArrayList<>();
			for(int i = 0; i < R; i++) {
				AllBombBML.add((int)Math.pow(2, C)-1);
			}
			return AllBombBML;
		}//case1,3
		case(2):{	//처음 게 터진 맵
			ArrayList<Integer> AllBombBML = new ArrayList<>();	//모두 폭탄 bm(초기 폭탄을 터뜨리고 남겨야함)
			for(int i = 0; i < R; i++) {
				AllBombBML.add((int)Math.pow(2, C)-1);
			}
			for(int i = 0; i < R; i++) {
				int checkBMpop = BML.get(i);	//초기 맵에서 뽑아낸 폭탄 확인용 BM
				for(int j = 0; j < C; j++) {
					if((checkBMpop & 1<<j) != 0) { //터져야하는 true(초기에 둔 폭탄)이면
						for(int dir = 0; dir < 5; dir++) {
							int tempx = i + deltas[dir][0];
							int tempy = j + deltas[dir][1];
							if(!isIn(tempx,tempy)) continue;	//상하좌우가 범위를 벗어나면 생략
							int changeBM = AllBombBML.get(tempx);	//모두 폭탄인 맵을 가져옴
							changeBM &= ~(1<<tempy);			//상하좌우를 0(빈공간)으로 만듦 (터뜨림)
							AllBombBML.set(tempx, changeBM);	//상하좌우 하나씩 터뜨린 BM을 다시 Allbomb에 넣어줌
						}//for(deltas)
					}//if(초기 폭탄 검색)
				}//for(cloumn)
			}//for(row)
			return AllBombBML;
		}//case2
		}//switch
		return BML;
	}
	
	private static void Mapping(ArrayList<Integer> BML) {	//Bitmask 토대로 맵 만들기
		for(int i = 0; i < R; i++) {
			int tempBM = BML.get(i);
			for(int j = 0; j < C; j++) {
				if((tempBM & 1<<j) != 0) {	//true면
					sb.append('O');
				}else {
					sb.append('.');
				}
			}
			if(i == R-1) {
				break;
			}
			sb.append("\n");
		}
	}
}