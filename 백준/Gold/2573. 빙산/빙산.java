import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] map, copyMap;
	static boolean[][] visited;
	static List<Point> list = new ArrayList<>();
	static int N, M;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] != 0) {
					list.add(new Point(r,c));
				}
			}
		}
		int time = 0;
		outer : while(true) {
			
			// 빙산이 다 녹았으면 break
			if(list.size() == 0) {
				time = 0;
				break outer;
			}
			
			// 빙산 개수 세기
			int cnt =0;
			visited = new boolean[N][M];
			cnt = counting(list.get(0), 0);
			if(list.size() > cnt) break outer;
			
			time++;
			// 빙산 녹이기
			melt();
			
		}
		
		System.out.println(time);
		
	}

	private static void melt() {
		copyMap = new int[N][M];
		// 리스트 돌기
		for(int i=0; i<list.size(); i++) {
			Point cur = list.get(i);
			int cnt = 0;
			for(int d=0; d<dx.length; d++) {
				int dr = cur.r + dy[d];
				int dc = cur.c + dx[d];
				if(map[dr][dc] == 0) cnt++;
			}
			int newSize = map[cur.r][cur.c] - cnt;
			if(newSize <0)	newSize = 0;
			// map에 빙산 사이즈 갱신
			copyMap[cur.r][cur.c] = newSize;
			if(newSize <=0) {
				list.remove(i);
				i--;
			}
		}
		map = copyMap;
		
	}
	
	private static int counting(Point start, int cnt) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		visited[start.r][start.c]=true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			cnt++;
			
			for(int i=0; i<dx.length; i++) {
				int dr = cur.r + dy[i];
				int dc = cur.c + dx[i];
				if(!isIn(dr, dc)) continue;
				if(map[dr][dc] == 0 || visited[dr][dc]) continue;
				
				queue.offer(new Point(dr, dc));
				visited[dr][dc] = true;
			}
		}
		
		return cnt;
	}
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M); 
	}
}