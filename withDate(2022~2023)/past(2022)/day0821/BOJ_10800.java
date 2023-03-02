package day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10800 {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int [] ColorCountSave;
	static ArrayList<BallPlayer> tempPlayers = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			BallPlayer temp = new BallPlayer(i, Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			tempPlayers.add(temp);
		}
		int Psize = tempPlayers.size();
		Collections.sort(tempPlayers);;	//size별로 정렬
		
		Queue<BallPlayer> PlayersList = new LinkedList<>();	//size별로 정렬해서 Que에 넣어줌
		for(int i = 0; i < Psize; i++) {
			PlayersList.add(tempPlayers.get(i));
		}
		
		ColorCountSave = new int[N];	//색깔 숫자를 index로 취급해서 현재까지(나보다 작은 size)의 크기 합 저장
		
		
		ArrayList<PlayerOnlyCount> CountResult = new ArrayList<>();	//결과를 저장할 arraylist
		
		SameSizeCheck Sizecheck = new SameSizeCheck(-1, 0);
		while(!PlayersList.isEmpty()) {	//size가 작은 것 순서부터 (어차피 같은 size는 못 먹으니까 )
			BallPlayer tempplayer = PlayersList.poll();
			if(Sizecheck.size == tempplayer.size) {//현재 플레이어 사이즈가 앞선 사람 사이즈와 같을 때 : 
				Sizecheck.count += tempplayer.size;
			}else {
				Sizecheck = new SameSizeCheck(tempplayer.size, 0);
			}
			ColorCountSave[tempplayer.color-1] += tempplayer.size;
			tempplayer.setCount(CountDiff(tempplayer.color-1) - Sizecheck.count);	//현재까지 저장돼있는 색깔별 size 합을 카운트(먹을 수 있느 거)
			
			
			CountResult.add(new PlayerOnlyCount(tempplayer.number, tempplayer.count));
		}
		Collections.sort(CountResult);
		for(PlayerOnlyCount r : CountResult) {
			sb.append(r.count + "\n");
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
	private static int CountDiff(int A) {	//색깔 A가 아닌 다른 숫자들의 현재 count값
		int result = 0;
		for(int i = 0; i < N; i++) {
			if(A == i) continue;
			result += ColorCountSave[i];
		}
		return result;
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
		int count;
		public BallPlayer(int number, int color, int size) {
			super();
			this.number = number;
			this.color = color;
			this.size = size;
		}

		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public int compareTo(BallPlayer o) {
			return this.size - o.size;
		}
		
		
	}

}
