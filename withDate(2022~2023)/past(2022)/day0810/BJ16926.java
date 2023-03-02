package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16926 {
	static int N;
	static int M;
	static int R;
	static Queue<Integer> MapLines;
	static int [][] maps;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		maps = new int [N][M];
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i = 0; i < (int)(Math.round(1.0*(Math.min(N, M)/2))); i++) {
			OnebyOne(i);
		}
//		System.out.println("===============================");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M ; j++) {
				sb.append(maps[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
		
	}
	static Queue<Integer> MapLine;
	private static void OnebyOne(int Lineth) {	//lineth = 0, 1, 2,..., (int) Math.round(1.0*Math.min(M,N)/2)
		MapLine = new LinkedList<>();
		
		for(int i = Lineth; i<M-Lineth; i++) {//x층
			MapLine.add(maps[Lineth][i]);
		}//맨 윗줄은 그대로 넣기
		
		//이제 맨 오른쪽들부터
		for(int i = Lineth+1; i< N-Lineth; i++) {
			MapLine.add(maps[i][M-1-Lineth]);
		}
		
		//이제 맨 아래 라인
		for(int i = M-1-1-Lineth; i>=Lineth; i--) {
			MapLine.add(maps[N-1-Lineth][i]);
		}
		
		//이제 맨 왼쪽 라인
		for(int i = N-1-1-Lineth; i >Lineth; i--) {
			MapLine.add(maps[i][Lineth]);
		}
		
		for(int i = 0; i < R; i++) {
			MapLine.add(MapLine.poll());
		}											//돌리기
		
		for(int i = Lineth; i<M-Lineth; i++) {
			maps[Lineth][i] = MapLine.poll();
		}//윗쪽 라인 넣기
		
		for(int i = Lineth+1; i< N-Lineth; i++) {
			maps[i][M-1-Lineth] = MapLine.poll();
		}//오른쪽 라인 넣기
		
		for(int i = M-1-1-Lineth; i>=Lineth; i--) {
			maps[N-1-Lineth][i] = MapLine.poll();
		}//아래 라인 넣기
		
		for(int i = N-1-1-Lineth; i >Lineth; i--) {
			maps[i][Lineth] = MapLine.poll();
		}//왼쪽 라인 넣기
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
