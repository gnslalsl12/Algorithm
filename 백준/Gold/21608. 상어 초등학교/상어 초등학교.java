import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] Seated;
	static ArrayList<int[]> LikeList;
	static int[][] Map;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Seated = new int[N * N + 1];
		Arrays.fill(Seated, -1);
		LikeList = new ArrayList<>();
		Map = new int[N][N];
		for (int n = 0; n < N * N; n++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			LikeList.add(new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),
					Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),
					Integer.parseInt(tokens.nextToken()) });
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setSit();
		getHappyValue();
		write.write(Result + "\n");
		write.close();
	}

	private static void setSit() {
		for (int[] info : LikeList) {
			int student = info[0];
			Queue<Integer> sideLocs = new LinkedList<>(); // 이 아이가 앉을 자리들 후보
			for (int index = 1; index <= 4; index++) { // 선호하는 아이 4명
				int like = info[index];
				if (Seated[like] != -1) { // 선호하는 아이가 앉아있다면
					int likeLocI = Seated[like] / N;
					int likeLocJ = Seated[like] % N; // 해당 아이의 위치
					for (int[] dir : Deltas) { // 해당 위치의 인접위치
						int sideLocI = likeLocI + dir[0];
						int sideLocJ = likeLocJ + dir[1];
						if (!isIn(sideLocI, sideLocJ) || Map[sideLocI][sideLocJ] != 0)
							continue; // 범위 밖 /누가 앉아있음음
						sideLocs.add(sideLocI * N + sideLocJ); // 앉을 수 있는 선호 아이 옆자리들
					}
				}
			}
			int sideLocsSize = sideLocs.size();
			int[][] locsCount = new int[N][N];
			while (sideLocsSize-- > 0) {
				int loc = sideLocs.poll();
				locsCount[loc / N][loc % N]++;
				if (locsCount[loc / N][loc % N] == 1)
					sideLocs.add(loc); // 하나씩만 넣기
			} //후보 자리마다 인접한 선호 학생 카운트
			sideLocsSize = sideLocs.size();

			int selectedLoc = -1;
			int maxLikes = 0;
			int maxEmpties = 0;
			while (sideLocsSize-- > 0) {    //후보 자리들 중
				int loc = sideLocs.poll();
				int locI = loc / N;
				int locJ = loc % N;
				int like = locsCount[locI][locJ];
				int empty = 0;
				for (int[] dir : Deltas) {
					if (isIn(locI + dir[0], locJ + dir[1]) && Map[locI + dir[0]][locJ + dir[1]] == 0)
						empty++;
				}
				if (like > maxLikes) {    //문제의 조건에 맞게 적정 위치 선별
					maxLikes = like;
					maxEmpties = empty;
					selectedLoc = loc;
				} else if (like == maxLikes) {
					if (empty > maxEmpties) {
						maxEmpties = empty;
						selectedLoc = loc;
					} else if (empty == maxEmpties) {
						if (selectedLoc > loc) {
							selectedLoc = loc;
						}
					}
				}
			}
			if (selectedLoc == -1) {    //만약 선호한 아이 옆에 앉을 수 있는 자리가 없다면
				selectedLoc = getProferEmpty();    //빈자리 중 적정 위치 하나 가져오기
			}

			Map[selectedLoc / N][selectedLoc % N] = student;    //아이 앉히기
			Seated[student] = selectedLoc;    //아이 위치 기록
		}
	}

	private static int getProferEmpty() {    //맵 전체를 둘러보며 빈자리 중 적당한 위치 찾기
		int loc = -1;
		int maxEmptyCount = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Map[i][j] != 0)
					continue;
				int emptyCount = 0;
				for (int[] dir : Deltas) {
					int nextI = i + dir[0];
					int nextJ = j + dir[1];
					if (!isIn(nextI, nextJ) || Map[nextI][nextJ] != 0)
						continue;
					emptyCount++;
				}
				if (emptyCount > maxEmptyCount) {
					loc = i * N + j;
					maxEmptyCount = emptyCount;
					if (emptyCount == 4)
						return loc;
				}
			}
		}
		return loc;
	}

	private static void getHappyValue() {
		for (int[] info : LikeList) {
			int studentLoc = Seated[info[0]];
			int count = -1;
			for (int index = 1; index <= 4; index++) {
				int likesLoc = Seated[info[index]];
				if (studentLoc + N == likesLoc || studentLoc - N == likesLoc) // 위 또는 아래
					count++;
				else if (studentLoc / N == likesLoc / N && Math.abs(studentLoc - likesLoc) == 1) //양 옆
					count++;
			}
			if (count > -1)
				Result += Math.pow(10, count);
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}
}