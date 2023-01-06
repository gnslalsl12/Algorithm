package day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ12761_final {
	static int N,M,A,B;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		BFSfor12761();
	}
	
	static void BFSfor12761() {
		int [] maps = new int [100001];	//전체 지도
		Queue<Integer> NextLocatedStonesQ = new LinkedList<>(); // 다음에 움직여야할 돌의 위치 기억하는 Q
		
		NextLocatedStonesQ.offer(N);	//처음에 들어가야하는 돌 위치는 N
		maps[N] = 0; //초기 돌에서 시작
		
		while(!NextLocatedStonesQ.isEmpty()) {
			int Location = NextLocatedStonesQ.poll();
			if(Location == M) {
				System.out.println((int)(maps[M]));
				return;
			}
			
			for(int dir = 0; dir < 8 ; dir++) {
				int AfterMovesLocation = MoveCases(Location, dir);
				if(AfterMovesLocation<=100000 && AfterMovesLocation>=0 && maps[AfterMovesLocation] == 0) {
					// == 0은 한 번도 안 온 위치여야하니까 (똑같은 위치 또 왔다는 건 쓸데없이 이동했다는 뜻
					maps[AfterMovesLocation] = maps[Location]+1; //현재 위치까지 온 카운트에서 다음 돌까지는 +1
					NextLocatedStonesQ.offer(AfterMovesLocation); //switch 돌려서 나온 다음 돌들을 넣어줌
				}
			}
		}
		System.out.println(maps[M]);
	}
	
	static int MoveCases(int l, int d) {
		switch(d) {
		case(0):{		//한 칸 뒤로
			return l-1;
		}case(1):{		//한 칸 앞으로
			return l+1;
		}case(2):{		//A만큼 곱하기
			return l*A;
		}case(3):{		//B만큼 곱하기
			return l*B;
		}case(4):{		//A만큼 더하기
			return l+A;
		}case(5):{		//B만큼 더하기
			return l+B;
		}case(6):{		//A만큼 뺴기
			return l-A;
		}case(7):{		//B만큼 뺴기
			return l-B;
		}
		}
		return l;
	}
}