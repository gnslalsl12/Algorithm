package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16935 {
	static int N;
	static int M;
	static int biggerlength;
	static int R;
	static int [][] maps;
	static int [][] tempmaps;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		biggerlength = Math.max(N, M);
		maps = new int [biggerlength][biggerlength];
		tempmaps = new int [biggerlength][biggerlength];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j<M ; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}//maps 제작 완료 , tempmaps 제작 완료(도화지)
		
		tokens = new StringTokenizer(read.readLine());
		Queue<Integer> swch = new LinkedList<>();
		while(tokens.hasMoreTokens()) {
			swch.add(Integer.parseInt(tokens.nextToken()));
		}
		//입력값 다 받음
		
		for(int c : swch) {
			switch(c) {
			case(1):{
				ReverseSH();
				break;
			}
			case(2):{
				ReverseJW();
				break;
			}
			case(3):{
				RotateRR();
				break;
			}
			case(4):{
				RotateLL();
				break;
			}
			
			case(5):{
				SplitedMove5();
				break;
			}
			case(6):{
				SplitedMove6();
				break;
			}
			}
		}
		for(int [] l : maps) {
			for(int li : l) {
				if(li == 0) {
					continue;
				}
				sb.append(li).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void ReverseSH() {    //상하반전
		for(int i = 0; i < N ; i++) {
			tempmaps[i] = maps[N-1-i];
		}
		maps = tempmaps.clone();
		tempmaps = new int [biggerlength][biggerlength];
	}
	
	private static void ReverseJW() {    //좌우반전
		RotateRR();
		ReverseSH();
		RotateLL();
	}
	
	private static void RotateRR() {    //오른쪽회전
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tempmaps[j][N-1-i] = maps[i][j];
			}
		}
		maps = tempmaps.clone();
		tempmaps = new int [biggerlength][biggerlength];
		int templ = N;
		N = M;
		M = templ;
	}
	
	private static void RotateLL() {    //왼쪽회전
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j < M; j++) {
				tempmaps[M-1-j][i] = maps[i][j];
			}
		}
		maps = tempmaps.clone();
		tempmaps = new int [biggerlength][biggerlength];
		int templ = N;
		N = M;
		M = templ;
	}
	
	private static void SplitedMove5() {    //5번
		Split4();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0; j < M ; j++) {
				if(i>=(N/2)) {
					if(j>=(M/2)) {
						 maps[i][j] = Area2[i-(N/2)][j-(M/2)];//원래 area3
					}else {
						maps[i][j] = Area3[i-(N/2)][j] ;	//원래 area4
					}
				}else {
					if(j>=(M/2)) {
						 maps[i][j] = Area1[i][j-(M/2)];	//원래 area2
					}else {
						maps[i][j] = Area4[i][j];			//원래 area1
					}
				}
			}
		}
	}
	
	private static void SplitedMove6() {    //6번
		Split4();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0; j < M ; j++) {
				if(i>=(N/2)) {
					if(j>=(M/2)) {
						 maps[i][j] = Area4[i-(N/2)][j-(M/2)];//원래 area3
					}else {
						maps[i][j] = Area1[i-(N/2)][j] ;	//원래 area4
					}
				}else {
					if(j>=(M/2)) {
						 maps[i][j] = Area3[i][j-(M/2)];	//원래 area2
					}else {
						maps[i][j] = Area2[i][j];			//원래 area1
					}
				}
			}
		}
	}
	
	static int [][] Area1, Area2, Area3, Area4;
	private static void Split4() {    //4분할
		Area1 = new int[N/2][M/2];
		Area2 = new int[N/2][M/2];
		Area3 = new int[N/2][M/2];
		Area4 = new int[N/2][M/2];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0; j < M ; j++) {
				
				if(i>=(N/2)) {
					if(j>=(M/2)) {
						Area3[i-(N/2)][j-(M/2)] = maps[i][j];
					}else {
						Area4[i-(N/2)][j] = maps[i][j];
					}
				}else {
					if(j>=(M/2)) {
						Area2[i][j-(M/2)] = maps[i][j];
					}else {
						Area1[i][j] = maps[i][j];
					}
				}
			}
		}
	}
}