package day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1700_re {
	static int N;	//플러그 수
	static int K;	//전자기기 수
	static ArrayList<Integer> Elecs = new ArrayList<>();
	static int [] Tab;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Tab = new int [N];
		int tempE = 0;
		int Tablen = 0;
		boolean [] UsingElecs = new boolean [K+1];
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < K; i++) {
			tempE = Integer.parseInt(tokens.nextToken());
			if(Tablen >= N) {	//탭이 꽉참
				Elecs.add(tempE);
			}else {//탭이 아직 덜 찼음
				if(UsingElecs[tempE]) {	//꽂으려는 애가 이미 꽂혀있는 애임
					continue;
				}else {	//처음 꽂는 애야
					Tab[Tablen++] = tempE;
					UsingElecs[tempE] = true;
				}
			}
		}
		System.out.println("초기 탭 : " + Arrays.toString(Tab));
		int count = 0;
		for(int i = 0; i < Elecs.size(); i++) {
			System.out.println();
			int nextE = Elecs.get(i);
			System.out.println("꽂으려는 애 : " + nextE);
			if(UsingElecs[nextE]) {		//이미 꽂혀있는 애임
				System.out.println("이미 꽂ㅎ여ㅣㅆ음");
				continue;
			}
			if(i != Elecs.size() - 1 && Elecs.get(i+1) == nextE) {
				System.out.println("연속 두 번이니까 패스");
				continue;	//두 번 연속으로 쓰는 애임 => 맨 마지막 경우에서 뺴면 됨
			}
			
			//새로운 애야
			
			
			//////////////구간 사이 애들 사용횟수 섹;
			int [] tempremain = new int [K+1];	//다음에 또 나올 떄까지 지금 꽂ㅎ여있는 애들의 사용 횟수
			
			
//			Elecs.get(temi) : 다음으로 나오는 기다리는 기기
			for(int tempi = i+1; tempi < Elecs.size(); tempi++) {	//현재 위치에서 지금 꽂으려는 기기의 재사용 타이밍 사이에서 
				System.out.println("꽂으려는 애 다음에 오는 애 : " + Elecs.get(tempi));
				if(Elecs.get(tempi) == nextE) break;	//재사용 타이밍 확인
				if(!UsingElecs[Elecs.get(tempi)]) {
					System.out.println("탭에 꽂혀있는 애가 아니자나?");
					continue;	//아직 탭에 꽂혀있는 애가 아님
				}
				//꽂혀있는 애임
				tempremain[Elecs.get(tempi)]++;
			}
			System.out.println(nextE + " 다음으로 오는 애들의 사용 횟수 : " + Arrays.toString(tempremain));
//			tempremain에 탭에 꽂혀이쓴 애들의 임시 사용 횟수ㅏ가 있음
			int min = Integer.MAX_VALUE;
			int selminuse = 0;
			for(int ti = 0; ti < N; ti++) {
				int EonTab = Tab[ti];
				if(min > tempremain[EonTab]) {
					min = tempremain[EonTab];
					selminuse = ti;	//Tab상의 ti번 기기가 가자 ㅇ사용횟수가 적음
				}
			}
			System.out.println(Tab[selminuse] + " 의 기기가 가장 적은 사용횟수임");
			UsingElecs[Tab[selminuse]] = false;	//기기 뻈음
			Tab[selminuse] = nextE;
			UsingElecs[nextE] = true;	//기기 꽂음
			
			count++;
			
		}
		System.out.println(count);
	}
}
