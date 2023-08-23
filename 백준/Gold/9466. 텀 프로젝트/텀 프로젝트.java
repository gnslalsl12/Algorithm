import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static int N;
	static int[] Likes;
	static int Result;
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		init();
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(read.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(read.readLine());
			Likes = new int[N + 1];
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int n = 1; n <= N; n++) {
				Likes[n] = Integer.parseInt(tokens.nextToken());
			}
			Result = 0;
			solv();
			write.write(Result + "\n");
		}
		read.close();
	}

	private static void solv() {
		setTeammate();
	}

	private static void setTeammate() {
		int[] states = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			if (states[n] == 0)
				getTeam(n, states);
			if (states[n] != -1)
				Result++;
		}
	}

	private static void getTeam(int start, int[] states) {
		int next = start;
		states[start] = start;
		while (true) {
			next = Likes[next];
			if (states[next] == start) { // 이번 탐색에서 이미 방문했던 아이 => 순환돼서 돌아옴
				states[next] = -1; // 팀 결성
				continue;
			} else if (states[next] == 0) { // 아직 방문하지 않은 아이 => 파고들기
				states[next] = start;
			} else if (states[next] < start) // 이전 탐색에서 방문했던 아이 / 이미 팀 결성된 아이 => 팀 구성실패
				return;
		}
	}

}