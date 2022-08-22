package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_20208 {

	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, H, MintCount;
	static ArrayList<dirXY> MintList = new ArrayList<>();
	static dirXY Home;
	static ArrayList<dirXY> tempHistoryOfRemainHP = new ArrayList<>();
	static ArrayList<dirXY> HistoryOfRemainHP = new ArrayList<>();
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		MintCount = 0;
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N ; j++) {
				int temp = Integer.parseInt(tokens.nextToken());
				if(temp == 1) {
					Home = new dirXY(i,j,0);
				}else if(temp == 2) {
					MintList.add(new dirXY(i,j, ++MintCount));		//민트 카운트 ++
				}
			}
		}
		Home.HP = M;
		
		for(dirXY temp : MintList) {
			if(AvailGo(Home, temp)) {	//집에서 접근 가능한 건물이면
				Refresh();
				DFSforMint(temp);				//그걸 시작점으로 BFS 하나씩 돌려보기
			}
		}
		
		Collections.sort(HistoryOfRemainHP);	//count 내림차순으로 정렬됨
		dirXY result = new dirXY();
		for(dirXY r : HistoryOfRemainHP) {
			if(AvailReturn(r)) {
				result = (dirXY) r.clone();	
				break;
			}
		}
		System.out.println(result.count);
	}
	private static void makeTrueThis(dirXY check) {
		check.visited[check.Number] = true;
	}
	private static boolean CheckHere(dirXY now, dirXY next) {
		return now.visited[next.Number];
	}
	
	private static void Refresh() {
		for(dirXY p : MintList) {
			p.count = 0;
			p.HP = 0;
			p.visited = new boolean[11];
		}
	}
	
	private static void DFSforMint(dirXY startpoint) throws CloneNotSupportedException {		//첫번째 방문 민트집 넣어줄 거임
		tempHistoryOfRemainHP = new ArrayList<>();
		Stack<dirXY> BFSqueue = new Stack<>();
//		Queue<dirXY> BFSqueue = new LinkedList<>();
		startpoint.HP = (M - getDist(Home, startpoint)) + H;	//초기 출발 시 깎이는 체력 + 민초우유
		startpoint.count = 1;								//초기 도착이니까 count = 1;
		BFSqueue.add(startpoint);
		while(!BFSqueue.isEmpty()) {
			dirXY temp = BFSqueue.pop();	//도착한 건물(HP도 유지)
			makeTrueThis(temp);				//도착한 건물 true화
			tempHistoryOfRemainHP.add(temp);	//각 단계마다 그때의 HP 기록(맨 마지막이 가장 마지막 도착 and count
			for(dirXY popped : MintList) {
				if(CheckHere(temp,popped)) {	//현재 위치 temp에서 다음 위치 popped가 true이면 continue;
					continue;
				}	//일단 가지 않은 곳임
				if(AvailGo(temp,popped)) { //현재 체력(temp.HP)로 갈 수 있는 거리인가?
					dirXY nextone = (dirXY) popped.clone();
					nextone.visited = temp.visited.clone();	//현재까지의 방문 복사
					makeTrueThis(nextone);					//다음 위치의 방문 True
					nextone.count = temp.count + 1;	//다음 도착이니까 count+1
					nextone.HP = temp.HP - getDist(temp,popped) + H;	//다음 체력은 현재 체력에서 이동한 거리를 뺀 후 
					BFSqueue.add(nextone);							//민트초코를 마셔서 충전한 체력
				}
			}//현재 체력으로 갈 수 있는 것드을 넣음
			
		}
		dirXY result = new dirXY();
		Collections.sort(tempHistoryOfRemainHP);	//템프 기록을 정렬하고
		for(dirXY r : tempHistoryOfRemainHP) {
			if(AvailReturn(r)) {			//가장 count가 큰 놈부터 시작해서	집으로 갈 수 있을 때
				result = (dirXY) r.clone();	
				break;
			}
		}
		HistoryOfRemainHP.add(result);
	}
	
	private static boolean AvailGo(dirXY from, dirXY to) {
		return getDist(from,to) <= from.HP;		//현재 체력으로 갈 수 있는가
	}
	private static boolean AvailReturn(dirXY from) {
		return getDist(from,Home) <= from.HP;		//만약 간다면 도착 했을 떄의 집에 돌아갈 수는 있는가
	}
	
	private static int getDist(dirXY A, dirXY B) {		//거리 구하기
		return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
	}
	
	private static class dirXY implements Comparable<dirXY>, Cloneable{
		int x;
		int y;
		int Number;
		int HP;
		int count;
		boolean[] visited = new boolean[11];
		public dirXY(int x, int y, int Number) {
			super();
			this.x = x;
			this.y = y;
			this.Number = Number;
		}
		public dirXY() {
			super();
		}
		@Override
		public int compareTo(dirXY o) {
			return o.count - this.count;			//정렬은 횟수가 많은 순서대로
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		
	}
}