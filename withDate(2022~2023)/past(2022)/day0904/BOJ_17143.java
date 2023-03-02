package day0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_17143 {
	static int[][] deltas = { {0, 0}, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int R,C,M;
	static int [][] maps;
	static boolean [][] removed;
	static ArrayList<Sharks> Sharklist = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int [R+1][C+1];
		removed = new boolean[R+1][C+1];
		int r, c, s, d, z;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			r = Integer.parseInt(tokens.nextToken());	//위치r
			c = Integer.parseInt(tokens.nextToken());	//위치c
			s = Integer.parseInt(tokens.nextToken());	//속력
			d = Integer.parseInt(tokens.nextToken());	//이동방향
			z = Integer.parseInt(tokens.nextToken());	//크기
			Sharks temp = new Sharks(r, c, s, d, z);	//맵에 표시됨
			Sharklist.add(temp);
		}
		
		int BaitResult = 0;
		Collections.sort(Sharklist);
		
		for(int i = 1; i <= C; i++) {
			for(int search = 1; search <= R; search++) {
				if(maps[search][i] != 0) {
					BaitResult += maps[search][i];
					maps[search][i] = 0;	//잡았으니 없어짐
					removed[search][i] = true;	//얘가 사라졌어요!!
					break;
				}
			}
			int len = Sharklist.size();
			for(int sn = 0; sn < len; sn++) {
				Sharks S = Sharklist.remove(0);
				if(removed[S.r][S.c]) {	//낚씨꾼한테 잡혀감
					S.Dead = true;
					removed[S.r][S.c] = false;
				}else {
					S.sharkmove();	//큰 놈부터 움직이기
				}
				if(!S.Dead) {	//살아있어?
					Sharklist.add(S);
				}
				
			}
		}
		System.out.println(BaitResult);
	}
	
	private static class Sharks implements Comparable<Sharks>{
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean Dead;
		
		public Sharks(int r, int c, int s, int d, int z){
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			maps[r][c] = this.z;
		}
		
		private void Turning() {	//범위 나갔을 때 되돌려주기
			while(this.r <= 0 || this.r > R || this.c <= 0 || this.c > C) {
				if(this.r <= 0) {
					this.s *= -1;
					this.r = Math.abs(this.r)+2;
					continue;
				}else if(this.r > R) {
					this.s *= -1;
					this.r = 2*R - this.r;
				}
				if(this.c <= 0) {
					this.s *= -1;
					this.c = Math.abs(this.c)+2;
				}else if(this.c > C) {
					this.s *= -1;
					this.c = 2*C - this.c;
				}
			}
		}
		
		public void sharkmove() {
			if(maps[this.r][this.c] != this.z) {	//다른 상어가 와서 겹쳐져있음
				maps[this.r][this.c] -= this.z; 	//몰래 빠져나가
			}else {									//나 혼자면
				maps[this.r][this.c] = 0; 			//당당하게 나가
			}
			this.r = this.r + this.s*deltas[this.d][0];
			this.c = this.c + this.s*deltas[this.d][1];
			this.Turning();		//벽에 부딪히면 돌아감
			
			if(maps[this.r][this.c] > this.z) {	//가려는 목적지에 나보다 큰 애가 있음
				Dead = true;
				return;
			}else {								//가려는 목적지에 나보다 작은 애가 아직 안 움직임 => 덮어씌워
				maps[this.r][this.c] += this.z;
			}
		}

		@Override
		public int compareTo(Sharks o) {
			return Integer.compare(o.z, this.z);
		}
	}
}