import java.util.*;
import java.io.*;

public class Main {

	static int N, M, D;
	static int[] TempMap;
	static int[] GlobalMap;
	static int Opps;
	static Queue<Integer> Positions;
	static int[][] ArrowDeltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	public static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());
		TempMap = new int[N];
		GlobalMap = new int[N];
		Opps = 0;
		Positions = new LinkedList<>();
		ArrowDeltas = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 } };
		Result = 0;
		for (int n = 0; n < N; n++) {
			tokens = new StringTokenizer(read.readLine());
			for (int m = 0; m < M; m++) {
				if (tokens.nextToken().equals("1")) {
					GlobalMap[n] |= 1 << m;
					Opps++;
				}
			}
		}
		read.close();
	}

	public static void solv() {
		getCombArchPos(0, 0, 0); // 조합으로 궁수 포지션 구하기
		doWholePosCases(); // 모든 케이스에 따라 게임 진행
	}

	private static void getCombArchPos(int count, int start, int sel) {
		if (count == 3) {
			Positions.add(sel);
			return;
		}
		for (int current = start; current < M; current++) {
			sel |= 1 << current;
			getCombArchPos(count + 1, current + 1, sel);
			sel &= ~(1 << current);
		}
	}

	private static void doWholePosCases() {
		while (!Positions.isEmpty()) { // 모든 궁수 포지션 경우마다
			TempMap = GlobalMap.clone(); // 맵 초기화
			setGameFlows(Positions.poll()); // 게임 진행
		}
	}

	private static void setGameFlows(int pos) {
		// 궁수 Y좌표 구하기
		int[] positions = new int[3];
		int posCount = 0;
		for (int m = 0; m < M; m++) {
			if ((pos & 1 << m) != 0) {
				positions[posCount++] = m;
				if (posCount == 3)
					break;
			}
		}
		// 궁수 Y좌표에 따라 게임 진행
		int tempOppsCount = Opps; // 현재 맵 상에 남은 적 수 카운트
		int tempResult = 0; // 현재 궁수 좌표 케이스에서 잡은 적 수 카운트
		while (tempOppsCount > 0) { // 남은 적이 없을 때까지
			int elims = doShoot(positions); // 맵에서 적을 없애고 그 수만큼 값을 반환
			tempResult += elims; // 잡은 적 수 카운트
			tempOppsCount -= elims; // 남은 적 수 카운트
			tempOppsCount -= setOppsAdvance(); // 적 전진시키기 + 성에 도착해서 제외된 적 수 카운트
		}
		Result = Math.max(Result, tempResult);
	}

	private static int doShoot(int[] positions) { // 맵에서 적 없애고 그 수만큼 값을 반환
		int[][] target = new int[3][2]; // 잡은 적들 위치
		for (int m = 0; m < 3; m++) {
			target[m] = getBFS(positions[m]); // BFS로 각 궁수가 없앨 최선의 적 위치 구하기
		}

		int targetCount = 0; // 총 몇 명 잡았는지 카운트
		for (int[] e : target) { // 타겟 위치값들 하나씩 확인
			if (e[0] == -1)
				continue;
			else {
				targetCount += (TempMap[e[0]] & 1 << e[1]) != 0 ? 1 : 0; // 이미 잡은 타겟인지 확인
				TempMap[e[0]] &= ~(1 << e[1]); // 맵상에서 없애기
			}
		}
		return targetCount; // 총 잡은 수 반환
	}

	private static int[] getBFS(int pos) {
		Queue<int[]> BFSQ = new LinkedList<>();
		int[] vis = new int[N];
		BFSQ.add(new int[] { N - 1, pos }); // 탐색 시작위치
		vis[N - 1] |= 1 << pos; // 시작 위치 방문처리
		int len = 1; // 범위 제한

		while (!BFSQ.isEmpty()) {
			if (Math.sqrt(len++) > D) // 최대 거리를 넘어선 범위라면 X
				break;
			int[] current = BFSQ.poll();
			if ((TempMap[current[0]] & 1 << current[1]) != 0) // 현재 탐색 위치에 적이 있다면
				return current; // 그 위치를 반환
			for (int[] delta : ArrowDeltas) { // BFS
				int nextI = current[0] + delta[0];
				int nextJ = current[1] + delta[1];
				if (!isIn(nextI, nextJ) || (vis[nextI] & 1 << nextJ) != 0)
					continue;
				BFSQ.add(new int[] { nextI, nextJ });
				vis[nextI] |= 1 << nextJ;
			}
		}
		return new int[] { -1, -1 }; // 범위 내에 적이 없다면 -1 반환
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j <= M;
	}

	private static int setOppsAdvance() { // 적 전진시키기
		int excepted = 0; // 성까지 도착해서 제외된 적 수
		for (int m = 0; m < M; m++) {
			if ((TempMap[N - 1] & 1 << m) != 0)
				excepted++;
		}
		for (int n = N - 1; n >= 1; n--) {
			TempMap[n] = TempMap[n - 1];
		}
		TempMap[0] = 0;
		return excepted;
	}

}