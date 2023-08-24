import java.util.*;
import java.io.*;

public class Main {

	static int N, K, L;
	static char[][] Map;
	static Deque<Integer> Snake;
	static int SnakeDir;
	static int[][] Deltas;
	static Queue<Integer> Moves;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Map = new char[N][N];
		Snake = new LinkedList<>();
		Snake.add(0);
		SnakeDir = 1;
		K = Integer.parseInt(read.readLine());
		for (int k = 0; k < K; k++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			Map[Integer.parseInt(tokens.nextToken()) - 1][Integer.parseInt(tokens.nextToken()) - 1] = 'A';
		}
		Map[0][0] = 'S';
		L = Integer.parseInt(read.readLine());
		Moves = new LinkedList<>();
		for (int l = 0; l < L; l++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int move = Integer.parseInt(tokens.nextToken()) * 10;
			if (tokens.nextToken().equals("L"))
				move += 3;
			else
				move += 1;
			Moves.add(move);
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		read.close();
	}

	private static void solv() {
		while (true) {
			Result++;
			if (!setMoveSnake())
				break;
		}
	}

	private static boolean setMoveSnake() {
		switch (setMoveHead()) {
		case ('S'): { // 죽음 => 게임 끝
			return false;
		}
		case ('\0'): { // 빈공간 => 꼬리 줄이기
			setMoveTail();
		}
		// 사과 => 꼬리 줄이기 X (아무것도 안 함)
		}
		if (!Moves.isEmpty() && Result == Moves.peek() / 10) { // 이동 전환
			SnakeDir = (SnakeDir + Moves.poll() % 10) % 4;
		}
		return true;
	}

	private static char setMoveHead() {
		int headLoc = Snake.peekFirst();
		int nextI = headLoc / N + Deltas[SnakeDir][0];
		int nextJ = headLoc % N + Deltas[SnakeDir][1];
		if (!isIn(nextI, nextJ))
			return 'S';
		char nextPoint = Map[nextI][nextJ];
		Map[nextI][nextJ] = 'S';
		Snake.addFirst(nextI * N + nextJ);
		return nextPoint;
	}

	private static void setMoveTail() {
		int tailLoc = Snake.pollLast();
		Map[tailLoc / N][tailLoc % N] = '\0';
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}