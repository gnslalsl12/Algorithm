import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static long Result;
	static ArrayList<Integer> CrainsDescending = new ArrayList<>();
	static ArrayList<Integer> BoxesDescending = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			CrainsDescending.add(Integer.parseInt(tokens.nextToken()));
		}
		Collections.sort(CrainsDescending, Collections.reverseOrder());
		M = Integer.parseInt(read.readLine());
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < M; i++) {
			BoxesDescending.add(Integer.parseInt(tokens.nextToken()));
		}
		Collections.sort(BoxesDescending, Collections.reverseOrder());
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void solv() {
		if (CrainsDescending.get(0) < BoxesDescending.get(0)) { // 옮길 수 없는 무게가 있음
			Result = -1;
			return;
		}
		doDistributeFromHigh();
	}

	private static void doDistributeFromHigh() {
		long time = 0;
		while (!BoxesDescending.isEmpty()) {
			time++;
			boolean[] crainVisit = new boolean[N];
			int vCount = 0;
			for (int b = 0; b < BoxesDescending.size(); b++) { // 가장 무거운 박스부터 하나씩
				int box = BoxesDescending.get(b);
				for (int c = 0; c < N; c++) {
					if (crainVisit[c]) // 이미 들고있는 크레인은 제외
						continue;
					int crain = CrainsDescending.get(c);
					if (crain >= box) { // 이 박스를 들 수 있는 크레인 찾음
						BoxesDescending.remove(b); // 박스 제거
						b--; // 제거한 박스의 index부터 다시 박스 찾아야하니까 -1
						crainVisit[c] = true; // 크레인 들고있음 체크
						vCount++;
						box = -1;
						break;
					} else { // 빈 크레인 중에 가장 센 크레인도 못 드는 무게면 다음으로 작은 박스 탐색
						break;
					}
				}
				if (vCount == N)
					break;
				// 만약 박스 끝까지 봤는데 다 못드는 거면 time++
			}

		}
		Result = time;
	}

}