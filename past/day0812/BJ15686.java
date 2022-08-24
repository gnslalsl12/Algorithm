package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;



public class BJ15686 {
	static int M;
	static int N;
	static ArrayList<XY> Chickens;
	static int ChickensSize;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int [][] maps = new int [N+1][N+1];
		ArrayList<XY> Home = new ArrayList<>();
		Chickens = new ArrayList<>();
		for(int i = 1; i < N+1 ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 1; j < N+1; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 1) {
					Home.add(new XY(i,j,1));
				}else if(maps[i][j] == 2) {
					Chickens.add(new XY(i,j,2,0));
				}
			}
		}//maps
		
		ChickensSize = Chickens.size();
		tempJH = new XY[M];
		JH = new ArrayList<>();
		JHBM(0,0);
		
		int totalminSum = Integer.MAX_VALUE;
		for(int jh = 0; jh < JH.size(); jh++) {
			System.out.println(JH.get(jh).toString());
			int disSum = 0;	//치킨 조합 경우의 수마다 최소 거리 합
			for(XY tH : Home) {
				int min = Integer.MAX_VALUE;
				for(int i = 0; i < JH.get(jh).length ; i++) {
					int distance = (Math.abs(tH.x - JH.get(jh)[i].x) + Math.abs(tH.y - JH.get(jh)[i].y));
					System.out.println(distance);
					if(min > distance ) {
						min = distance;
					}
				}
				disSum += min;
				
			}
			System.out.println(jh + "번쨰 : " + disSum);
			if(totalminSum > disSum) {
				totalminSum = disSum;
			}
			
			
			
		}
		
		//////////////////////////////
		
		
		System.out.println(totalminSum);
		
		
		
	}
	

	static ArrayList<XY[]> JH;
	static XY[] tempJH;
	public static void JHBM(int index,int countnow) {
		if(countnow == M) {
			System.out.println(tempJH[0].toString());
//			System.out.println(tempJH[1].toString());
			System.out.println("====");
			JH.add(tempJH);
			return;
		}
		
		for(int i = index; i < ChickensSize; i++) {
			tempJH[countnow] = Chickens.get(i);
			JHBM(i+1, countnow+1);

		}
		
		
	}
	
	

}
class XY implements Comparable<XY> {
	int x;
	int y;
	int Distances;
	int count;
	
	public XY(int x, int y, int value, int count) {
		super();
		this.x = x;
		this.y = y;
		Distances = value;
		this.count = count;
	}

	public XY(int a, int b, int v) {
		x = a;
		y = b;
		Distances = v;
	}

	public void setDistances(int D) {
		Distances = D;
	}
	public void setcount(int c) {
		count = c;
	}
	
	@Override
	public int compareTo(XY o) {
		// TODO Auto-generated method stub
		if(count == o.count) {
			return Distances - o.Distances;
		}
		return o.count - count;
	}

	@Override
	public String toString() {
		return "XY [x=" + x + ", y=" + y + ", Distances=" + Distances + ", count=" + count + "]";
	}
}




















