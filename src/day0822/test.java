import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class test {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static PriorityQueue<Player> PlayerList = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i< N; i++) {
			tokens = new StringTokenizer(read.readLine());
			PlayerList.add(new Player(i,Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		//Playerlist 사이즈 작은 순서대로 정렬
		
		PriorityQueue<PlayerWithOnlySize> PlayerListToOut = new PriorityQueue<>();
		
		int[] ColorizeSizeSum = new int[200001];	//color값 index화 해서 저장한 color별 사이즈 합
		 int[] SizerizeSum = new  int [2001];		//같은 사이즈를 가진 볼 제외해주기 위해 저장한 사이즈별 합계값
		
		 int TotalSumSize = 0;
		 int SameSizeSameColor = 0;					//색이 같고 사이즈가 같은 놈을 빼주기 위한 합게깞
		
		int PresentColor = PlayerList.peek().Color; //이전에 탐색한 놈의 색
		int PresentSize = PlayerList.peek().Size;	//이전에 탐색한 놈의 사이즈
		
		
		while(!PlayerList.isEmpty()) {
			Player temp = PlayerList.poll();
			
			if(temp.Size == PresentSize && temp.Color == PresentColor) {	//앞의 놈과 색이 같고 사이즈가 같으면
				SameSizeSameColor += temp.Size;		//해당 개수만큼 사이즈 축척
			}else {									//둘 중 하나라도 다르면 초기화
				SameSizeSameColor = temp.Size;
			}
			
			ColorizeSizeSum[temp.Color] +=  temp.Size;	//색깔별 사이즈 합계
			SizerizeSum[temp.Size] +=  temp.Size;		//사이즈별 사이즈 합계
			
			TotalSumSize +=  temp.Size;		//지금까지 모든 사이즈 합계
			
			 int SumToPush = TotalSumSize - SizerizeSum[temp.Size] - ColorizeSizeSum[temp.Color] + SameSizeSameColor;
							// 모든 사이즈 합계) - (지금 사이즈와 동일한 것들의 사이즈 합계) - (같은 색을 가진 사이즈 합계) + 동색동사 사이즈들
			
			PlayerListToOut.add(new PlayerWithOnlySize(temp.Nth, SumToPush));
			
			PresentColor = temp.Color;	//지금 검색한 놈으로 저장
			PresentSize = temp.Size;
			
		}
		int len = PlayerListToOut.size();
		for(int i = 0; i < len; i++) {
			sb.append(PlayerListToOut.poll().SizeSum);
			if(i == len-1) break;
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	private static class PlayerWithOnlySize implements Comparable<PlayerWithOnlySize>{
		int Nth;
		 int SizeSum;
		public PlayerWithOnlySize(int nth,  int sizeSum) {
			super();
			Nth = nth;
			SizeSum = sizeSum;
		}
		@Override
		public int compareTo(PlayerWithOnlySize o) {
			return this.Nth - o.Nth;
		}
		
	}
	
	private static class Player implements Comparable<Player>{
		int Nth;
		int Color;
		int Size;
		public Player(int nth, int color, int size) {
			super();
			Nth = nth;
			Color = color;
			Size = size;
		}
		@Override
		public int compareTo(Player o) {
			if(this.Size == o.Size) return this.Color - o.Color;
			return this.Size - o.Size;
		}
	}
}