import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//t = 1 : 1X1
//t = 2 : 1X2 (x,y),(x,y+1) 가로 길게
//t = 3 : 2X1 (x,y),(x+1,y) 세로 길게

//블록 채워지는 건 boolean true

public class Main {
	static int N;	//1~10,000
	static Queue<Mono> Blocks = new LinkedList<>();
	static ArrayList<Integer> mapsG = new ArrayList<>();
	static ArrayList<Integer> mapsB = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			Blocks.add(new Mono(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		for(int i = 0; i < 6; i++) {
			mapsG.add(0);	//x = 6, y = 4;
			mapsB.add(0);
		}
		ResultCount = 0;
		for(Mono input : Blocks) {
			MonoMinoGreen(input);
			if(input.t == 1) {
				MonoMinoBlue(new Mono(1, input.y, 3 - input.x));	//OK
			}else if(input.t == 2) {
				MonoMinoBlue(new Mono(3, input.y, 3 - input.x));
			}else if(input.t == 3) {
				MonoMinoBlue(new Mono(2, input.y, 3 - input.x - 1));
			}
		}
		int remaincount = 0;
		for(int i = 0; i < 6 ; i++) {
			for(int j = 0; j < 4; j++) {
				if((mapsG.get(i) & 1<<j) != 0) {
					remaincount++;
				}
				if((mapsB.get(i) & 1<<j) != 0) {
					remaincount++;
				}
			}
		}
		System.out.println(ResultCount);
		System.out.println(remaincount);
		
	}
	static int ResultCount;
	private static void MonoMinoGreen(Mono input) {
			for(int depth = 0; depth < 6; depth++) {
				if(input.t == 2) {
					
					if((mapsG.get(depth) & 1<<input.y) != 0 || (mapsG.get(depth) & 1<<(input.y + 1)) != 0) { //둘 중 하나라도 블록 만나면
						depth--;
						Type2(input, depth, mapsG);
						return;
					}
					if(depth == 5) {	//맨 밑까지 왔을 때
						Type2(input, depth, mapsG);
						return;
					}
				}else {
					if((mapsG.get(depth) & 1<<input.y) != 0) {	//블록 찾음
						depth--;
						if(input.t == 1) {
							Type1(input, depth, mapsG);
						}else {
							Type3(input, depth, mapsG);
						}
						return;
					}
					if(depth == 5) {
						if(input.t == 1) {
							Type1(input, depth, mapsG);
						}else {
							Type3(input, depth, mapsG);
						}
						return;
					}
				}
			}
		}
	
	private static void MonoMinoBlue(Mono input) {
		for(int depth = 0; depth < 6; depth++) {
			if(input.t == 2) {
				
				if((mapsB.get(depth) & 1<<input.y) != 0 || (mapsB.get(depth) & 1<<(input.y + 1)) != 0) { //둘 중 하나라도 블록 만나면
					depth--;
					Type2(input, depth, mapsB);
					return;
				}
				if(depth == 5) {	//맨 밑까지 왔을 때
					Type2(input, depth, mapsB);
					return;
				}
			}else {
				if((mapsB.get(depth) & 1<<input.y) != 0) {	//블록 찾음
					depth--;
					if(input.t == 1) {
						Type1(input, depth, mapsB);
					}else {
						Type3(input, depth, mapsB);
					}
					return;
				}
				if(depth == 5) {
					if(input.t == 1) {
						Type1(input, depth, mapsB);
					}else {
						Type3(input, depth, mapsB);
					}
					return;
				}
			}
		}
	}
	
	private static void Type1(Mono input, int depth, ArrayList<Integer> MapsSep) {
		MapsSep.set(depth, MapsSep.get(depth) | 1<<input.y); //칸 채우기
		
		//꽉 채워졌으면 지우기
		if(MapsSep.get(depth) == 15) {				//그 다음 아래 지우기
			ResultCount++;
			MapsSep.remove(depth);					//꽉 찬 공간 지워주기
			MapsSep.add(0, 0);						//맨 위에 채워주기
		}
		//아랫부분도 연한 공간에 들어왔다 해도 해당 라인 옆쪽은 절대 채워져있을 수 없기 때문에 자동 생략됨
		if(MapsSep.get(1) != 0) {		//연한 공간 아래가 블록이 남아있으면
			MapsSep.remove(5);		//맨 아래 지우고
			MapsSep.add(0,0);			//맨 위에 추가하고
		}
		return;
	}
	
	private static void Type2(Mono input, int depth, ArrayList<Integer> MapsSep) {
		MapsSep.set(depth, MapsSep.get(depth) | 1<<input.y);	//칸 채우기 왼쪽
		MapsSep.set(depth, MapsSep.get(depth) | 1<<input.y + 1);	//칸 채우기 오른쪽
		
		//양쪽이 비어서 채워줬는데 그게 연한 공간임
		if(depth <= 1) { //연한 공간에 쌓이면
			MapsSep.remove(5);//맨 아래 공간 지우기
			MapsSep.add(0,0); //맨 위에 빈 공간 추가해거 밀어주기
			return;
		}
			
		//진한 공간에 채워넣음 => 꽉 찬 라인 지우기
		if(MapsSep.get(depth) == 15) {
			ResultCount++;
			MapsSep.remove(depth);					//꽉 찬 공간 지워주기
			MapsSep.add(0, 0);						//맨 위에 채워주기
			return;	//해당 Mono의 경우는 끝
		}
		
		//연한 공간도 아니고 진한공간이지만 채워지지 않았으면 끝
		return;
	}
	
	private static void Type3(Mono input, int depth, ArrayList<Integer> MapsSep) {
		MapsSep.set(depth, MapsSep.get(depth) | 1<<input.y); //칸 채우기 아래
		MapsSep.set(depth - 1, MapsSep.get(depth - 1) | 1<<input.y); //칸 채우기 아래

		//해당 칸에 채워졌으면 지우기
		if(MapsSep.get(depth - 1) == 15) {		//위에부터 채운 거 지우기
			ResultCount++;
			MapsSep.remove(depth - 1);			//꽉 찬 공간 지워주기
			MapsSep.add(0, 0);					//맨 위에 채워주기
		}
		//윗부분이 연한 공간에 들어왔다 해도 해당 라인 옆쪽은 절대 채워져있을 수 없기 때문에 자동 생략됨
		
		//아랫부분 꽉 채워졌으면 지우기
		if(MapsSep.get(depth) == 15) {				//그 다음 아래 지우기
			ResultCount++;
			MapsSep.remove(depth);					//꽉 찬 공간 지워주기
			MapsSep.add(0, 0);						//맨 위에 채워주기
		}
		//아랫부분도 연한 공간에 들어왔다 해도 해당 라인 옆쪽은 절대 채워져있을 수 없기 때문에 자동 생략됨
		
		if(MapsSep.get(1) != 0) {		//연한 공간 아래가 블록이 남아있으면
			MapsSep.remove(5);		//맨 아래 지우고
			MapsSep.add(0,0);			//맨 위에 추가하고
		}
		if(MapsSep.get(1) != 0) {			//연한 공간 아래가 블록이 남아있으면	(한 번 더)
			MapsSep.remove(5);			//맨 아래 지우고
			MapsSep.add(0,0);				//맨 위에 추가하고
		}
	}
	
	private static class Mono{
		int t;
		int x;
		int y;
		public Mono(int t, int x, int y) {
			super();
			this.t = t;
			this.x = x;
			this.y = y;
		}
	}
}