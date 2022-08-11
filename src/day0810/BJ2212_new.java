package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ2212_new {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(read.readLine());
		int K = Integer.parseInt(read.readLine());
		
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		HashSet<Integer> tempSensors = new HashSet<>();
		
		while(tokens.hasMoreTokens()) {
			tempSensors.add(Integer.parseInt(tokens.nextToken()));
		}
		
		ArrayList<Integer> Sensors = new ArrayList<>(tempSensors);
		Collections.sort(Sensors);
		//센서 입력 및 중복 제거, 정렬화
		ArrayList<Integer> SNSsubs = new ArrayList<>();
		for(int i = 1; i < Sensors.size(); i++) {
			SNSsubs.add(Sensors.get(i)-Sensors.get(i-1));
		}
		int totalsum = 0;
		Collections.sort(SNSsubs, Collections.reverseOrder());
		for(int i = 0 ; i < SNSsubs.size(); i++) {
			if(i >=K-1) {
				totalsum+=SNSsubs.get(i);
			}
		}
//		while(Concent != K) {							//시간초과
//			for(int i = 0; i < SNSsubs.size(); i++) {
//				int MaxGab = Collections.max(SNSsubs);
//				if(SNSsubs.get(i) == MaxGab) {
//					SNSsubs.set(i, 0); // 센서 감지 영역이 아닌 곳은 -1 
//					Concent++;			//(중복값 제거했으니 센서 거리가 0이 나올 수가 없음)
//					if(Concent == K) {	//감지국 수가 도달했을 때
//						break;
//					}
//				}
//			}
//		}
		System.out.println(totalsum);
	}
}