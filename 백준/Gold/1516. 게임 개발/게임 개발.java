import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] BuildInfo = new int[510][2];
	static ArrayList<Integer>[] NextBuilds = new ArrayList[510];
	static int[] Results = new int[510];

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		for (int n = 1; n <= N; n++) {
			NextBuilds[n] = new ArrayList<>();
		}
		for (int n = 1; n <= N; n++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			BuildInfo[n][0] = Integer.parseInt(tokens.nextToken()); // n건물을 짓기 위한 시간
			while (tokens.hasMoreTokens()) {
				int num = Integer.parseInt(tokens.nextToken());
				if (num == -1)
					break;
				NextBuilds[num].add(n); // "num 건물을 지으면 n건물을 지을 수 있게 된다"
				BuildInfo[n][1]++; // n 건물을 짓기 위해 필요한 선행 건물 갯수
			}
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResults();
		for (int n = 1; n <= N; n++) {
			write.write(Results[n] + "\n");
		}
		write.close();
	}

	private static void getResults() {
		Queue<Integer> tempQ = new LinkedList<>();
		for (int n = 1; n <= N; n++) {
			if (BuildInfo[n][1] == 0) { // 먼저 지어야하는 건물이 없음
				tempQ.add(n);
				Results[n] = BuildInfo[n][0];
			}
		}
		while (!tempQ.isEmpty()) {
			int buildNum = tempQ.poll(); // 지을 수 있는 건물
			for (int nextBuild : NextBuilds[buildNum]) { // 해당 건물의 건설로 영향받는 다른 건물 nextBuild
				Results[nextBuild] = Math.max(Results[nextBuild], Results[buildNum] + BuildInfo[nextBuild][0]);
				// 해당 영향 건물을 짓는 시간은 방금 지은 건물 짓는 시간 + 해당 영향 건물 짓는 시간의 최댓값
				if (--BuildInfo[nextBuild][1] == 0) { // 해당 건물도 이제 지을 수 있게 되면
					tempQ.add(nextBuild);
				}
			}
		}
	}

}