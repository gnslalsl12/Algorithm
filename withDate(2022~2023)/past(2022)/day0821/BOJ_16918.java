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
public class BOJ_16918 {
	
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int R,C,N;
	static StringBuilder sb = new StringBuilder();
	static int[][] deltas = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		ArrayList<Integer> FirstBombPlant = new ArrayList<>();
		
		for(int i = 0; i < R; i++) {
			int BitMask = 0;
			String templine = read.readLine();
			for(int j = 0; j < C; j++) {
				if(templine.charAt(j) == 'O') {
					BitMask |= 1<<j;
				}
			}
			FirstBombPlant.add(BitMask);	//맵 입력받아서 Bitmask화
		}
		
		if(N == 1) {
			Mapping(FirstBombPlant);
			System.out.print(sb);
			return;
		}else {
			ArrayList<Integer> OutMap = (ArrayList<Integer>) FirstBombPlant.clone();
			ArrayList<Integer> SecondBombPlant = new ArrayList<>();
			int time = 2;
			while(true) {
				//2초
				SecondBombPlant = FillBomb(OutMap);
				OutMap = AllBomb();
				if(time == N) break;
				time++;
				
				//3초
				SecondBombPlant = BombBlast(SecondBombPlant, FirstBombPlant);	//2초에 설치한 폭탄 중에서 처음 폭탄 ㅓㅁ위에 들어오면 없앰
				OutMap = BombBlast(OutMap, FirstBombPlant);
				if(time == N) break;
				time++;
				
				//4초
				FirstBombPlant = FillBomb(OutMap);	//이전 맵에서 충전해줌
				OutMap = AllBomb();
				if(time == N) break;
				time++;
				
				//5초
				FirstBombPlant = BombBlast(FirstBombPlant, SecondBombPlant);
				OutMap = BombBlast(OutMap, SecondBombPlant);
				if(time == N) break;
				time++;
			}
			
			Mapping(OutMap);
			System.out.print(sb);
		}
		return;

	}
	
	private static boolean isIn(int x, int y) {		//범위 안에 들어오는가
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	private static ArrayList<Integer> FillBomb(ArrayList<Integer> BML){
		ArrayList<Integer> FilledBML = new ArrayList<>();
		
		for(int i = 0; i < R; i++) {
			int tempBMpop = BML.get(i);
			int BMtopush = 0;
			for(int j = 0; j < C; j++) {
				if((tempBMpop & 1<<j) == 0) { //현재 맵에서 빈공간이면
					BMtopush |= 1<<j;	//채워준 위치 기억
				}
			}
			FilledBML.add(BMtopush);
		}
		return FilledBML;	//이번에 채워준 폭탄만 기억
	}
	
	private static ArrayList<Integer> AllBomb(){	//모두 폭탄인 맵 제작
		ArrayList<Integer> AllBombBML = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			AllBombBML.add((int)Math.pow(2, C)-1);
		}
		return AllBombBML;
	}
	
	private static void Mapping(ArrayList<Integer> BML) {	//Bitmask 토대로 맵 만들기
		for(int i = 0; i < R; i++) {
			int tempBM = BML.get(i);
			for(int j = 0; j < C; j++) {
				if((tempBM & 1<<j) != 0) {	//true면
					sb.append("O");
				}else {
					sb.append(".");
				}
			}
			if(i == R-1) {
				break;
			}
			sb.append("\n");
		}
	}
	
	private static ArrayList<Integer> BombBlast(ArrayList<Integer> OriginMap, ArrayList<Integer> BML) {	//폭탄 터뜨리기

		for(int i = 0; i < R; i++) {
			int checkBMpop = BML.get(i);	//초기 맵에서 뽑아낸 폭탄 확인용 BM
			for(int j = 0; j < C; j++) {
				if((checkBMpop & 1<<j) != 0) { //터져야하는 true(초기에 둔 폭탄)이면
					for(int dir = 0; dir < 5; dir++) {
						int tempx = i + deltas[dir][0];
						int tempy = j + deltas[dir][1];
						if(!isIn(tempx,tempy)) continue;	//상하좌우가 범위를 벗어나면 생략
						int changeBM = OriginMap.get(tempx);	//모두 폭탄인 맵을 가져옴
						changeBM &= ~(1<<tempy);			//상하좌우를 0(빈공간)으로 만듦 (터뜨림)
						OriginMap.set(tempx, changeBM);	//상하좌우 하나씩 터뜨린 BM을 다시 Allbomb에 넣어줌
					}//for(deltas)
				}//if(초기 폭탄 검색)
			}//for(cloumn)
		}//for(row)
		return OriginMap;
	}
}