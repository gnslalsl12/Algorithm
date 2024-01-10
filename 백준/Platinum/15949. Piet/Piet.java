import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] Map = new int[105][105];
	static ArrayList<Section> SectionList = new ArrayList<>(); // 구역 정보

	static int[][] Deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static class Section {
		char sectionValue;
		int[][] sectionInfo;

		public Section(char sectionValue, int[][] sectionInfo) {
			this.sectionValue = sectionValue;
			this.sectionInfo = sectionInfo;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		for (int n = 0; n < N; n++) {
			String inputLine = read.readLine();
			for (int m = 0; m < M; m++) {
				Map[n][m] = inputLine.charAt(m); // 'A' ~ 'M', 'X'의 아스키코드값(65~)으로 기록
			}
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getSectionDivide(); // 같은 값(A~M)을 가진 구역 나누기
		write.write(getResult() + "\n");
		write.close();
	}

	private static void getSectionDivide() {
		boolean[][] vis = new boolean[N][M];
		int sectionNum = 0; // Map상의 A~M 아스키코드 값을 음의 정수 값으로 치환(SectionList상의 인덱스값 활용)
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (!vis[n][m] && Map[n][m] != 'X') {
					getThisSection(sectionNum++, n, m, vis, Map[n][m]); // n,m기준으로 구역 나누기
				}
			}
		}
	}

	private static void getThisSection(int sectionNum, int n, int m, boolean[][] vis, int mapValue) {
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.add(n * M + m);
		vis[n][m] = true;
		// 현재 구역의 [[최상단 좌 위치, 최상단 우 위치, 최상단 n값], [최우측 좌 위치, 최우측 우 위치, 최우측 m값], ...] 정보
		int[][] sectionInfo = { { M, -M, N }, { N, -N, -M }, { -M, M, -N }, { -N, N, M } };
		while (!bfsQ.isEmpty()) {
			int loc = bfsQ.poll();
			int locN = loc / M;
			int locM = loc % M;
			Map[locN][locM] = -sectionNum; // 구역 번호(음수값 => SecitonList의 인덱스값으로 활용 예정)으로 재기록
			setEdgeSectionInfo(sectionInfo, locN, locM); // 현재 위치를 기준으로 구역 정보 갱신
			for (int[] dir : Deltas) {
				int nextN = locN + dir[0];
				int nextM = locM + dir[1];
				if (!isIn(nextN, nextM) || vis[nextN][nextM] || Map[nextN][nextM] != mapValue) // 벙뮈 밖 / 방문 / 다른 값
					continue;
				vis[nextN][nextM] = true;
				bfsQ.add(nextN * M + nextM);
			}
		}
		SectionList.add(new Section((char) mapValue, sectionInfo)); // SectionList에 추가(sectionNum 순서대로 들어가게 됨)
	}

	private static void setEdgeSectionInfo(int[][] sectionInfo, int n, int m) { // 각 구역의 4방 좌,우 위치 갱신
		int[] up = sectionInfo[0];
		if (n < up[2]) { // 최상단 n값 갱신이라면
			up[2] = n; // n값 갱신
			up[0] = up[1] = n * M + m; // 좌,우 갱신
		} else if (n == up[2]) { // 최상단의 같은 n값을 가진 위치라면
			up[0] = Math.min(up[0], n * M + m); // 좌, 우 갱신
			up[1] = Math.max(up[1], n * M + m);
		}

		int[] right = sectionInfo[1];
		if (m > right[2]) {
			right[2] = m;
			right[0] = right[1] = n * M + m;
		} else if (m == right[2]) {
			right[0] = Math.min(right[0], n * M + m);
			right[1] = Math.max(right[1], n * M + m);
		}

		int[] down = sectionInfo[2];
		if (n > down[2]) {
			down[2] = n;
			down[0] = down[1] = n * M + m;
		} else if (n == down[2]) {
			down[0] = Math.max(down[0], n * M + m);
			down[1] = Math.min(down[1], n * M + m);
		}

		int[] left = sectionInfo[3];
		if (m < left[2]) {
			left[2] = m;
			left[0] = left[1] = n * M + m;
		} else if (m == left[2]) {
			left[0] = Math.max(left[0], n * M + m);
			left[1] = Math.min(left[1], n * M + m);
		}
	}

	private static String getResult() { // 결과값 구하기 탐색
		String result = "";
		int currentN = 0; // 출발 위치
		int currentM = 0;
		int dp = 1; // dp 기본 우
		int cc = 0; // dp 기본 좌

		while (true) {
			int sectionNum = -Map[currentN][currentM]; // 구역 번호(SectinoList의 index값)
			char sectionValue = SectionList.get(sectionNum).sectionValue; // 구역의 원 char값
			int[][] sectionInfo = SectionList.get(sectionNum).sectionInfo; // 구역의 각 방향 끝위치값
			result += sectionValue; // 결과값 갱신
			boolean found = false; // 8방 탐색 성공 여부
			for (int addDp = 0; addDp < 4; addDp++) {
				int nextN = sectionInfo[dp][cc] / M + Deltas[dp][0]; // dp방향의 cc측 기준에서 본 dp방향의 위치값
				int nextM = sectionInfo[dp][cc] % M + Deltas[dp][1];
				if (isIn(nextN, nextM) && Map[nextN][nextM] != 'X') { // 다른 구역과 맞닿음
					found = true;
					currentN = nextN;
					currentM = nextM;
					break;
				}
				cc = (cc + 1) % 2; // cc 전환
				nextN = sectionInfo[dp][cc] / M + Deltas[dp][0]; // 재탐색
				nextM = sectionInfo[dp][cc] % M + Deltas[dp][1];
				if (isIn(nextN, nextM) && Map[nextN][nextM] != 'X') {
					found = true;
					currentN = nextN;
					currentM = nextM;
					break;
				}
				dp = (dp + 1) % 4; // dp전환 (cc 유지)
			}
			if (!found) { // 8방 탐색 실패 (종료)
				break;
			}
			// 8방 탐색 성공 (dp, cc 유지 후 재탐색)
		}
		return result;
	}

	private static boolean isIn(int n, int m) {
		return n >= 0 && n < N && m >= 0 && m < M;
	}

}