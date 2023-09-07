import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int T;
	static HashMap<Integer, ArrayList<int[]>> Roads;
	static int StartDir;
	static int[] StartPoint;
	static int[][] Deltas;
	static int OneLapTime;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		Roads = new HashMap<>();
		for (int n = 0; n < N; n++) {
			tokens = new StringTokenizer(read.readLine());
			int sC = Integer.parseInt(tokens.nextToken());
			int sR = Integer.parseInt(tokens.nextToken());
			int eC = Integer.parseInt(tokens.nextToken());
			int eR = Integer.parseInt(tokens.nextToken());
			if (n == 0) {
				StartPoint = new int[] { sR, sC };
				if (sC == eC) { // 세로 이동
					if (sR < eR) { // 위로
						StartDir = 0;
					} else { // 아래로
						StartDir = 2;
					}
				} else { // 가로 이동
					if (sC < eC) { // 오른쪽
						StartDir = 1;
					} else { // 왼쪽
						StartDir = 3;
					}
				}
			}
			if (sC == eC) { // 열이 같으므로 세로로 된 도로
				if (!Roads.containsKey(sC)) { // 헤당 세로 쭉에서 도로 생성
					Roads.put(sC, new ArrayList<int[]>());
				}
				Roads.get(sC).add(new int[] { Math.min(sR, eR), Math.max(sR, eR) }); // sR~eR 행까지 도로
			} else { // 행이 같으므로 가로로 된 도로
				if (!Roads.containsKey((sR + 1) * -1)) { // 해당 가로 쭉에 도로 생성 (세로도 0이 있을테니 +1하고 -1곱하기)
					Roads.put((sR + 1) * -1, new ArrayList<int[]>());
				}
				Roads.get((sR + 1) * -1).add(new int[] { Math.min(sC, eC), Math.max(sC, eC) }); // sC~eC 열까지 도로 생성
			}
		}
		Deltas = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		OneLapTime = -1;
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getOneLapTime();
		if (OneLapTime != -1) { // 한 바퀴 시간을 얻게됨
			T %= OneLapTime;
			getOneLapTime();
		}
		write.write(StartPoint[1] + " " + StartPoint[0] + "\n");
		write.close();
	}

	// 시작 지점이 교차지점이라면?

	private static void getOneLapTime() {
		int timeCount = 0;
		int[] current = StartPoint.clone();
		int currentDir = StartDir;
		do {
			boolean turnLeft = false;
			if (currentDir % 2 == 1) { // 가로 이동일 때(열 값 변화)
				if (Roads.containsKey(current[1])) {
					for (int[] rowSection : Roads.get(current[1])) { // 현재 위치의 열에 세로로 된 도로가 존재한다면
						if (rowSection[0] < current[0] && current[0] < rowSection[1]) {
							currentDir = (currentDir + 3) % 4;
							turnLeft = true;
							break;
						}
					}
				}
			} else { // 세로 이동일 때(행 값 변화)
				if (Roads.containsKey((current[0] + 1) * -1)) {
					for (int[] colSection : Roads.get((current[0] + 1) * -1)) { // 현재 위치의 행에서 가로로 된 도로가 존재한다면
						if (colSection[0] < current[1] && current[1] < colSection[1]) {
							currentDir = (currentDir + 3) % 4;
							turnLeft = true;
							break;
						}
					}
				}
			}
			// 회전하지 않았다면 도로의 끝지점까지 왔는지를 확인해야함 (첫 시작은 정해진 방향으로 가면 됨)
			if (timeCount != 0 && !turnLeft) {
				if (currentDir % 2 == 1) { // 가로 이동(열 값 변화) => 가로 도로 탐색
					if (Roads.containsKey((current[0] + 1) * -1)) {
						for (int[] colSection : Roads.get((current[0] + 1) * -1)) {
							if (colSection[0] == current[1] || colSection[1] == current[1]) {
								currentDir = (currentDir + 2) % 4;
								break;
							}
						}
					}
				} else { // 세로 이동(행 값 변화) => 세로 도로 탐색
					if (Roads.containsKey(current[1])) {
						for (int[] rowSection : Roads.get(current[1])) {
							if (rowSection[0] == current[0] || rowSection[1] == current[0]) {
								currentDir = (currentDir + 2) % 4;
								break;
							}
						}
					}
				}
			}
			current[0] += Deltas[currentDir][0]; // 행
			current[1] += Deltas[currentDir][1]; // 열
			timeCount++;
			if (timeCount == T) { // 다 돌기 전에 시간이 끝나버림
				OneLapTime = -1;
				StartPoint = current;
				return;
			}
		} while (current[0] != StartPoint[0] || current[1] != StartPoint[1]);
		// 한바퀴 돔
		OneLapTime = timeCount;
	}

}