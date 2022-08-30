package day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18428 {
	static int N;
	static char [][] maps;
	static ArrayList<int []> Empty = new ArrayList<>();
	static ArrayList<int []> Teachers = new ArrayList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean Success;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		maps = new char [N][N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j++) {
				maps[i][j] = tokens.nextToken().charAt(0);
				if(maps[i][j] == 'X') {
					Empty.add(new int [] {i,j});
				}else if(maps[i][j] == 'T') {
					Teachers.add(new int [] {i,j});
				}
			}
		}
		for(int [] tempT : Teachers) {
			if(NexttoT(tempT)) {
				System.out.println("NO");
				return;
			}
		}
		Success = false;
		Comb(0, 0, (long)0);
		
		if(Success) System.out.println("YES");
		else System.out.println("NO");
		return;
		
	}
	
	private static boolean NexttoT(int [] XY) {
		for(int dir = 0; dir < 4; dir++) {
			int tempx = XY[0] + deltas[dir][0];
			int tempy = XY[1] + deltas[dir][1];
			if(!isIn(tempx, tempy)) continue;
			if(maps[tempx][tempy] == 'S') return true;
		}
		return false;
	}
	
	private static void Comb(int countnow, int startpoint, long SelBM) {
		if(Success) return; //앞의 경우에서 성공함
		if(countnow == 3) {
			//맵마다 설치해서 선생이 지켜보기
			char [][] exmaps = new char[N][N];
			for(int i = 0; i < N; i++) {
				exmaps[i] = maps[i].clone();
			}
			TeacherView(SelBM, exmaps);
			return;
		}
		
		for(int i = startpoint; i < Empty.size(); i++) {
			SelBM |= 1<<i;
			Comb(countnow + 1, i+1, SelBM);
			SelBM &= ~(1<<i);
		}
	}
	
	private static void TeacherView(long BM, char[][] tempmaps) {
		int count = 0;
		for(int index = 0; index < Empty.size() ; index++) {
			if((BM & 1<<index) != 0) {	//선택된 index
				tempmaps[Empty.get(index)[0]][Empty.get(index)[1]] = 'O';
				count++;
			}
			if(count == 3) break;
		}
		for(int [] tempXY : Teachers) {
			for(int dir = 0; dir < 4; dir++) {
				for(int len = 1; len < N; len ++) {
					int tempx = tempXY[0] + deltas[dir][0]*len;
					int tempy = tempXY[1] + deltas[dir][1]*len;
					if(!isIn(tempx, tempy)) break;
					if(tempmaps[tempx][tempy] == 'O') {
						break;
					}
					if(tempmaps[tempx][tempy] == 'S') {
						tempmaps = maps.clone();
						return;	//다른 경우의 수로 돌아가기
					}
				}
			}
		}
		//어디에서도 학생을 찾을 수 없음
		Success = true;
		return;
	}
	
	private static boolean isIn(int tempx, int tempy) {
		return tempx >= 0 && tempx < N && tempy >= 0 && tempy < N;
	}
}