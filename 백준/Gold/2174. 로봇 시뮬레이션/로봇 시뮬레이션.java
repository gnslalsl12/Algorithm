import java.util.*;
import java.io.*;

public class Main {

	static int A, B;
	static int N, M;
	static char[][] Map;
	static int[] RobotsLocs;
	static Queue<Integer> Orders;
	static int[][] Deltas;
	static String[] Answer;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		A = Integer.parseInt(tokens.nextToken()); // 열 크기
		B = Integer.parseInt(tokens.nextToken()); // 행 크기
		Map = new char[B][A];
		tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		RobotsLocs = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			tokens = new StringTokenizer(read.readLine());
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int x = B - Integer.parseInt(tokens.nextToken());
			Map[x][y] = (char) n;
			char dirC = tokens.nextToken().charAt(0);
			int dir;
			if (dirC == 'N')
				dir = 0;
			else if (dirC == 'E')
				dir = 1;
			else if (dirC == 'S')
				dir = 2;
			else
				dir = 3;
			RobotsLocs[n] = (x * A + y) * 10 + dir;
		}
		Orders = new LinkedList<>();
		for (int m = 0; m < M; m++) {
			tokens = new StringTokenizer(read.readLine());
			int robotNum = Integer.parseInt(tokens.nextToken());
			char orderC = tokens.nextToken().charAt(0);
			int count = Integer.parseInt(tokens.nextToken());
			int order;
			if (orderC == 'L')
				order = 3;
			else if (orderC == 'R')
				order = 1;
			else
				order = 2;
			Orders.add(count + order * 1000 + robotNum * 10000);
		}
		Answer = new String[1];
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setMoveRobots();
		if (Answer.length == 1)
			write.write("OK\n");
		else
			write.write("Robot " + Answer[0] + " crashes into " + Answer[1] + "\n");
		write.close();
	}

	private static void setMoveRobots() {
		while (!Orders.isEmpty()) {
			int orderNum = Orders.poll();
			int orderCount = orderNum % 1000; // 명령 횟수
			orderNum /= 1000;
			int orderDir = orderNum % 10; // 명령 방향(3? L, 2? F, R? 1)
			orderNum /= 10; // 명령 로봇 번호
			if (orderDir != 2) { // 회전
				setRotateRobot(orderNum, orderCount, orderDir);
			} else {
				setProceedRobot(orderNum, orderCount);
			}
			if (Answer.length != 1)
				return;
		}
	}

	private static void setRotateRobot(int orderNum, int orderCount, int orderDir) { // 로봇 회전
		int robotLoc = RobotsLocs[orderNum]; // 로벗 위치*10 + 방향
		int robotDir = robotLoc % 10; // 방향
		robotLoc /= 10; // 위치
		robotDir = (robotDir + orderDir * orderCount) % 4; // 방향 회전
		RobotsLocs[orderNum] = robotLoc * 10 + robotDir; // 위치와 방향 갱신
	}

	private static void setProceedRobot(int orderNum, int orderCount) { // 로봇 전진
		int robotLoc = RobotsLocs[orderNum]; // 로봇 위치*10 + 방향
		int robotDir = robotLoc % 10; // 방향
		robotLoc /= 10; // 위치
		Map[robotLoc / A][robotLoc % A] = '\0'; // 이전 위치 맵에서 초기화
		int nextI = robotLoc / A;
		int nextJ = robotLoc % A;
		for (int add = 1; add <= orderCount; add++) { // 하나씩 가면서 확인
			nextI = robotLoc / A + Deltas[robotDir][0] * add;
			nextJ = robotLoc % A + Deltas[robotDir][1] * add;
			if (!isIn(nextI, nextJ)) { // 맵 밖(벽에 부딪힘)
				Answer = new String[] { Integer.toString(orderNum), "the wall" };
				return;
			} else if (Map[nextI][nextJ] != '\0') { // 빈 곳이 아님(다른 로봇 충돌)
				Answer = new String[] { Integer.toString(orderNum),
						"robot " + Integer.toString((int) Map[nextI][nextJ]) };
				return;
			}
		}
		Map[nextI][nextJ] = (char) orderNum; // 무사히 도착 (맵 갱신)
		RobotsLocs[orderNum] = (nextI * A + nextJ) * 10 + robotDir; // 로봇 위치 갱신
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < B && j < A;
	}

}