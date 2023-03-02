package day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1700_re_re {
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
		int [] remainuse = new int [K+1];	//남은 사용횟수
		boolean [] UsingElecs = new boolean [K+1];
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < K; i++) {
			tempE = Integer.parseInt(tokens.nextToken());
			if(Tablen >= N) {	//탭이 꽉참
				remainuse[tempE]++;
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
		
		int count = 0;
		
		for(int i = 0; i < Elecs.size(); i++) {
			int nextE = Elecs.get(i);	//다음에 꽂을 애
			remainuse[nextE]--;
			if(UsingElecs[nextE]) continue;	//이미 꽂은애
			if(i != Elecs.size()-1 && Elecs.get(i+1) == nextE) continue;	//연속으로 오는 애임
			
			//아직 안 꽂혀있는 애네?
			int selectedindex = -1;
			//다시 안 쓰는 애가 꽂혀잇나?
			for(int j = 0; j < N; j++) {
				if(remainuse[Tab[j]] == 0) {
					selectedindex = j;
				}
			}
			if(selectedindex == -1) {
				boolean [] templencheck = new boolean[K+1];
				int foundcheck = 0;
				for(int nexti = i + 1; nexti < Elecs.size(); nexti++) {
					if(UsingElecs[Elecs.get(nexti)]) {	//현재 꽂힌 애일 때
						
						if(templencheck[Elecs.get(nexti)]) continue;	//이미 찾은 애야
						
						if(!templencheck[Elecs.get(nexti)])	{	//아직 발견 못한 꽂힌 애
							foundcheck++;
							templencheck[Elecs.get(nexti)] = true; //발견처리
							//아직 발견 못한 꽂힌 애면
						}
						
						if(foundcheck == N) {	//탭에 꽂힌 거 다 찾았을 때 마지막놈
							for(int lasti = 0; lasti < N; lasti++) {	//그 마지막놈의 인덱스는?
								if(Tab[lasti] == Elecs.get(nexti)) {
									selectedindex = lasti;
									break;
								}
							}
						}
						if(selectedindex != -1) break;	//찾았으니까 탐색 금지~
					}
				}
			}
			UsingElecs[Tab[selectedindex]] = false;	//원래 있던 애 이제 안써
			Tab[selectedindex] = nextE;	//바꿔껴
			UsingElecs[nextE] = true;	//얘 꽂혀있어
			count++;
		}
		System.out.println(count);
	}
}