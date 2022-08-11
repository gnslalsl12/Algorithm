package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17406 {
	static int N, M, K;
	static int [][] maps;
	static int [][] tempmaps;
	static int [][] orders;
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		maps = new int[N+1][M+1];
		tempmaps = new int [N+1][M+1];
		
		for(int i = 1; i<=N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 1; j <= M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				tempmaps[i][j] = maps[i][j];
			}
		}//map 만들기
		
		min = Integer.MAX_VALUE;
		
		orders = new int [K][3];
		for(int i = 0; i < K ; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			orders[i][0] = r;
			orders[i][1] = c;
			orders[i][2] = s;
			
		}
		boolean [] Selected = new boolean[K];
		BasedOrders = new int[K][3];
        
		OrderLine(0, Selected);    //순열 구해서 하나씩 해보고 min값 걸러내기
		
		System.out.println(min);
	}
	static int min;
	private static void Rotate(int rr, int cc, int ss) {
		for(int t = 0; t <= ss ; t++) {
			Queue<Integer> RotLine = new LinkedList<>();
			for(int i = cc+ss-t; i > cc-ss+t; i--) {
				RotLine.add(maps[rr-ss+t][i]);
			}
			for(int i = rr-ss+t; i < rr+ss-t; i++) {
				RotLine.add(maps[i][cc-ss+t]);
			}
			for(int i = cc-ss+t; i < cc+ss-t; i++) {
				RotLine.add(maps[rr+ss-t][i]);
			}
			for(int i = rr+ss-t; i > rr-ss+t; i--) {
				RotLine.add(maps[i][cc+ss-t]);
			}
			
			RotLine.add(RotLine.poll());
			
			for(int i = cc+ss-t; i > cc-ss+t; i--) {
				maps[rr-ss+t][i] = RotLine.poll();
			}
			for(int i = rr-ss+t; i < rr+ss-t; i++) {
				maps[i][cc-ss+t] = RotLine.poll();
			}
			for(int i = cc-ss+t; i < cc+ss-t; i++) {
				maps[rr+ss-t][i] = RotLine.poll();
			}
			for(int i = rr+ss-t; i > rr-ss+t; i--) {
				maps[i][cc+ss-t] = RotLine.poll();
			}
		}
	}
	
	static int [][] BasedOrders;
	
	private static void OrderLine(int countnow, boolean [] Selected) {	//OrderLine(0,Selected);
		if(countnow == K) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					maps[i][j] = tempmaps[i][j];
				}
			}
			//맵 초기화
			for(int [] tempo : BasedOrders) {
				Rotate(tempo[0], tempo[1], tempo[2]);
				
			}
			for(int [] ll : maps) {//각 줄 합 구하기
				int summ = 0;
				for(int llt : ll) {
					summ += llt;
				}
				if(summ == 0) {//(전체 맵은 0 라인이 포함이니까 sum = 0 제외
					continue;
				}
				if(min>summ) {
					min = summ;
				}
			}
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!Selected[i]) {
				BasedOrders[countnow] = orders[i].clone();
				Selected[i] = true;
				OrderLine(countnow+1, Selected);
				Selected[i] = false;
				
			}
		}
	}
}