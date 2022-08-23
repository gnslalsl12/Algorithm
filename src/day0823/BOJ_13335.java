package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335 {
	static int N, W, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 트럭 수
		W = Integer.parseInt(tokens.nextToken()); // 다리 길이
		L = Integer.parseInt(tokens.nextToken()); // 최대 하중
		
		Queue<Integer> Trucks = new LinkedList<>();
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < N; i++) {
			Trucks.add(Integer.parseInt(tokens.nextToken()));
		}
		
		int Weight = 0;	//현재 다리 위 무게
		int TCount = 0;	//현재 올라간 트럭 수
		int time = 0;	//시간
		Queue<Integer> presentW = new LinkedList<>();	//현재 올라간 트럭 목록
		
		for(int i = 1; i <= W-1; i++) {
			presentW.add(0);				//처음에 빈공간 채워주기
		}
		
		while(!Trucks.isEmpty()) {
			int temp = Trucks.peek();	//던질까 말까
			if(Weight + temp > L) { //지금 들어가면 최대 하중 넘어간다??
				presentW.add(0);	//들어가지마(하나씩 앞으로 떙겨
				if(presentW.peek() != 0) {
					TCount--;	//맨 앞에가 진짜 트럭임(나감)
				}
				Weight -= presentW.poll();	//맨 앞에 있는 애 빼
				time++;
				continue;
			}
			
			//아직 들어가진 않은 상태
			//들어가도 최대 하중은 넘어가지 않아
			if(TCount == W) {	//꽉 찼어				//하나씩 보냄
				TCount--;
				Weight -= presentW.poll();	//꽊 찬 상태니까 무조건 트럭이 나감
				time++;
				continue;
				
			}
			
			//꽉차지도 않고, 지금 들어가도 무게는 버팀
			presentW.add(Trucks.poll());	//트럭 넣어줌
			if(presentW.peek() != 0) {
				TCount--;	//맨 앞에가 진짜 트럭임(나감)
			}
			TCount++;
			Weight += temp;
			Weight -= presentW.poll();	//앞의 트럭은 빼줌
			time++;
		}
		
		time += W;	//가장 마지막 애가 나가는 동안의 시간
		System.out.println(time);
	}
}