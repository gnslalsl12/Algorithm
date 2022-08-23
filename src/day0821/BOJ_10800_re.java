package day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10800_re {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int [] ColorCountSave;
	static PriorityQueue<BallPlayer> tempPlayers = new PriorityQueue<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			BallPlayer temp = new BallPlayer(i, Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			tempPlayers.add(temp);
		}	//size 오름차순으로 Q 정렬
		
		
		ColorCountSave = new int[200001];	//색깔 숫자를 index로 취급해서 현재까지(나보다 작은 size)의 크기 합 저장
		
		PriorityQueue<PlayerOnlyCount> CountResult = new PriorityQueue<>();	//결과를 저장할 arraylist
		
		int TotalCount = 0;	//현재까지 들어온 Size값 합산
		int SamesizeSamecolor = 0; //같사같색에 더해줄 temp값
		int tempcolor = 0;
		
		int samesizesum = 0;
		BallPlayer beforetemp = new BallPlayer(-1, -1, -1);
		
		SameSizeCheck Sizecheck = new SameSizeCheck(-1, 0);
		while(!tempPlayers.isEmpty()) {	//size가 작은 것 순서부터 (어차피 같은 size는 못 먹으니까 )
			
			BallPlayer tempP = tempPlayers.poll();

			if(tempP.size == beforetemp.size) { //바로 앞에와 같은 사이즈 : 
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			BallPlayer tempplayer = tempPlayers.poll();
//			if(Sizecheck.size != tempplayer.size) {//현재 플레이어 사이즈가 앞선 사람 사이즈와 같을 때 : 
//				//새로운 사이즈면
//				tempcolor = tempplayer.color;
//				Sizecheck = new SameSizeCheck(tempplayer.size, 0);
//				SamesizeSamecolor = 0;
//			}else {//같은 사이즈들이면
//				if(tempcolor == tempplayer.color) {//이전과 같은 사이즈 같고 색 같은 경우
//					SamesizeSamecolor += tempplayer.size;
//				}else {//같은 사이즈지만 색이 달라
//					SamesizeSamecolor = 0;
//				}
//				
//			}
//			
//			Sizecheck.count += tempplayer.size;
//			TotalCount += tempplayer.size;
//			
//			int byHereCount = TotalCount - Sizecheck.count - ColorCountSave[tempplayer.color-1] + SamesizeSamecolor; 
//			//나보다 <= 사이즈의 합 - 같은 사이즈의 합 - 같은 색깔의 사이즈 합 + 색 같고 사이즈 같은 거
//			
//			ColorCountSave[tempplayer.color-1] += tempplayer.size;
//			
//			CountResult.add(new PlayerOnlyCount(tempplayer.number, byHereCount));
		}
		
		int s = CountResult.size();
		for(int i = 0; i<s; i++) {
			sb.append(CountResult.poll().count);
			if(i == s-1) {
				break;
			}
			sb.append("\n");
		}
		
		System.out.print(sb);

	}
	private static class SameSizeCheck {
		int size;
		int count;
		public SameSizeCheck(int size, int count) {
			super();
			this.size = size;
			this.count = count;
		}
		
	}
	private static class PlayerOnlyCount implements Comparable<PlayerOnlyCount>{
		int number;
		int count;
		public PlayerOnlyCount(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}
		@Override
		public int compareTo(PlayerOnlyCount o) {
			// TODO Auto-generated method stub
			return this.number - o.number;
		}
	}
	private static class BallPlayer implements Comparable<BallPlayer>{
		int number;
		int color;
		int size;
		public BallPlayer(int number, int color, int size) {
			super();
			this.number = number;
			this.color = color;
			this.size = size;
		}
		@Override
		public int compareTo(BallPlayer o) {
			return this.size - o.size;
		}
	}
}