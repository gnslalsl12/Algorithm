package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {
	static dirXYZ6593 Sxy;
	static dirXYZ6593 Exy;
	static int R,C,L;
	static String[][][] Totalmap;
	static int[][] deltas = {{-1,0,0},{0,1,0},{1,0,0},{0,-1,0},{0,0,1},{0,0,-1}};
	static int finalcount;
	static Queue<dirXYZ6593> moveQ;
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));;
	static StringTokenizer tokens;
	public static void main(String[] args) throws IOException {
		StringBuilder sb= new StringBuilder();
		while(true) {
			tokens = new StringTokenizer(read.readLine());	//첫번쨰 줄 숫자 세 개
			L = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			if(L + R + C == 0) {
				break;
			}
			Totalmap = new String[L][R][C];
			for(int i = 0 ; i < L ; i++) {
				for(int j = 0; j < R ; j++) {
					Totalmap[i][j] = read.readLine().split("");
					for(int k = 0; k < C ; k++) {
						if(Totalmap[i][j][k].equals("S")) {
							Sxy = new dirXYZ6593(j,k,i); // 앞의 totalmap 사이즈는 현재 층
						}else if(Totalmap[i][j][k].equals("E")) {
							Exy = new dirXYZ6593(j,k,i);
						}
					}
				}
				String t = read.readLine();
			}
			moveQ = new LinkedList<>();
			//매핑 완료
			finalcount = Integer.MAX_VALUE;
			BFS3rd(Sxy,1);

			if(finalcount != Integer.MAX_VALUE) {
				sb.append("Escaped in " + finalcount + " minute(s).\n");
				continue;
			}
			sb.append("Trapped!\n");
		}
		System.out.print(sb);
	}
	static int Layer;
	static void BFS3rd(dirXYZ6593 input, int count) {
		moveQ.add(input);
		Layer = 1;
		while(!moveQ.isEmpty()) {
			if(Layer==0) {
				count++;
				Layer = moveQ.size();
			}
			dirXYZ6593 now = moveQ.poll();
			for(int i = 0; i < 6; i++) {
				dirXYZ6593 temp = new dirXYZ6593(now.x + deltas[i][0], now.y + deltas[i][1], now.z + deltas[i][2]);
				if(IsIn(temp)) {//범위 내에서
					if(Totalmap[temp.z][temp.x][temp.y].equals("#") || Totalmap[temp.z][temp.x][temp.y].equals("S") ) {
						continue;
					}
					if(temp.equals(Exy)) {//탈출구라면
						if(count < finalcount) {
							finalcount = count;
						}
						continue;
					} //벽도 아니고 S도 아니고 탈출구도 아니니 움직일 수 있음 (그리고 범위 안)
					Totalmap[temp.z][temp.x][temp.y] = "#"; 
					moveQ.add(temp);
				}
				else {
					continue;
				}		
			}
			Layer--;
		}
	}
	static boolean IsIn(dirXYZ6593 temp) {
		if(temp.x >= 0 && temp.x <R && temp.y >=0 && temp.y < C && temp.z >= 0 && temp.z < L ) {
			return true;
		}
		return false;
	}
}

class dirXYZ6593{
	int x;
	int y;
	int z;
	public dirXYZ6593() {
		super();
	}
	public dirXYZ6593(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		dirXYZ6593 other = (dirXYZ6593) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
}