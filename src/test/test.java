package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class test {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static char[][] map;
	static Queue<Point> queue;
	static List<Cross> list;	// 십자가 정보 담을 리스트
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int maxSize;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N][M];
		queue = new LinkedList<>();
		list = new ArrayList<>();
		maxSize = Integer.MIN_VALUE;
		
		for(int r=0; r<N; r++) {
			String str = input.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == '#') {
					queue.offer(new Point(r, c));
				}
			}
		}
		
		
		// queue에서 하나씩 꺼내기
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int cr = cur.r;
			int cc = cur.c;
			
//			System.out.println(cr+" "+cc);
			
			// 십자가 size 1짜리 객체 만들기
			boolean[][] checked = new boolean[N][M];
			checked[cr][cc] = true;
			int size = 1;
			
			// 리스트에 넣기
			list.add(new Cross(cr, cc, size, checked));
			int cnt =0;
			// 십자가 더 늘릴수있는지 확인
			outer : while(true) {
				// 한바퀴 돌면 Cross 객체 생성, 중간에 .나오면 종료
				cnt++;
				boolean[][] checked2 = new boolean[N][M];
				checked2[cr][cc] = true;
				for(int d=0; d<dx.length; d++) {
					int dr = cr + dx[d]*cnt;
					int dc = cc + dy[d]*cnt;
//					
//					System.out.println("c: "+cr+", "+cc);
//					System.out.println("d: "+dr+", "+dc);
					
					if(!isIn(dr, dc)) break outer;
					if(map[dr][dc]=='.') break outer;
					checked2[dr][dc] = true;
				}
				
				// 정상적으로 돌았으면 size 증가
				size += 4;
				// for문 정상적으로 다 돌았으면 Cross 객체 생성
//				System.out.println("========================");
//				System.out.println(cr+", "+cc);
//				for(boolean[] b : checked2) {
//					System.out.println(Arrays.toString(b));
//				}
				list.add(new Cross(cr, cc, size, checked2));
			}
		}
		
		// 이중 for문 돌려서 십자가 2개씩 체크
		for(int i=0; i<list.size(); i++) {
			for(int j=i+1; j<list.size(); j++) {
				Cross c1 = list.get(i);
				Cross c2 = list.get(j);
				
				// 십자가 겹치는지 확인
				boolean flag = true;
				outer2 : for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						// 같은 위치에서 둘다 true면 패스
						if(c1.checked[r][c] && c2.checked[r][c]) {
							flag = false;
							break outer2;
						}
						
					}
				}
				
				if(flag) {
					
					int sizeTimes = c1.size * c2.size;
					maxSize = Math.max(maxSize, sizeTimes);
					
				}
			}
		}
		
		System.out.println(maxSize);
		
		// System.out.println(queue.toString());
		
//		for(char[] c : map) {
//			System.out.println(Arrays.toString(c));
//		}
		

	}
	
	// 십자가 정보
	static class Cross{
		int r, c;
		int size;
		boolean[][] checked;
		
		public Cross(int r, int c, int size, boolean[][] checked) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.checked = checked;
		}

		@Override
		public String toString() {
			return "Cross [r=" + r + ", c=" + c + ", size=" + size + ", checked=" + Arrays.toString(checked) + "]";
		}
		
		
		
		
	}
	
	// #위치를 표시하기 위함
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + " , " + c + ")";
		}
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}
}
