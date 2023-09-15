import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int [][] maps;
	static ArrayList<XYXY> Chickens;
	static ArrayList<XYXY> Homes;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][N];
		Homes = new ArrayList<>();
		Chickens = new ArrayList<>();
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N ; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 2) {
					Chickens.add(new XYXY(i,j));
				}else if(maps[i][j] == 1) {
					Homes.add(new XYXY(i,j));
				}
			}
		}
		
		ArrayList<int[]> fromHomesDistance = new ArrayList<>();	//각 집에서 치킨집까지 거리를 구한 배열을 저장할 어레이
		for(int i = 0; i < Homes.size(); i++) {			//int[]는 치킨집 개수, arraylist는 홈 개수
			int [] distanceArray = new int [Chickens.size()];
			for(int j = 0; j < Chickens.size(); j++) {
				int distance = Math.abs(Homes.get(i).x - Chickens.get(j).x) + Math.abs(Homes.get(i).y - Chickens.get(j).y);
				distanceArray[j] = distance;
			}
			fromHomesDistance.add(distanceArray); // fHD의 i번쨰의 j번쨰 값은 = i번쨰 홈의 j번째 치킨집까지의 거리
		}
		
		tempChickComb = new boolean [Chickens.size()];
		SelectedChickens = new ArrayList<>();
		Comb(0,0);
		int min = Integer.MAX_VALUE;
		for(boolean[] tempComb : SelectedChickens) {
			int DisSum = 0;
			for(int i = 0; i < Homes.size(); i++) {			//각 집에서
				int tempSub = Integer.MAX_VALUE;
				for(int j = 0; j < tempComb.length; j++) {	//치킨집까지의 거리들 중
					if(tempComb[j]) {						//선택된 치킨집까지의 거리가
						if(tempSub>fromHomesDistance.get(i)[j]) {//최소인 걸 찾자
							tempSub = fromHomesDistance.get(i)[j];
						}
					}	
				}//여기까지가 한 집에서 선택된 치킨집 까지의 거리 중 최소
				DisSum += tempSub;
			}
			if(min>DisSum) {
				min = DisSum;
			}
		}
		System.out.println(min);
	}
	
	static boolean [] tempChickComb;	//고른 M개의 치킨 좌표(조합으로 가져옴)
	static ArrayList<boolean[]> SelectedChickens;
	
	public static void Comb(int countnow, int index) {
		if(countnow == M) {
			SelectedChickens.add(tempChickComb.clone()); //조합으로 가져온 M개의 치킨 좌표 경우들의 모임들
			return;
		}
		
		for(int i = index; i < Chickens.size(); i++) {
			tempChickComb[i] = true;
			Comb(countnow+1,i+1);
			tempChickComb[i] = false;
		}
	}
}

class XYXY{
	int x;
	int y;
	
	public XYXY() {
		super();
	}
	
	public XYXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}