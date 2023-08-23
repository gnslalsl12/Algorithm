import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static ArrayList<Integer> Dict;
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
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Integer> tempPQ = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			tempPQ.add(Integer.parseInt(read.readLine()));
		}
		Dict = new ArrayList<>();
		while (!tempPQ.isEmpty()) {
			Dict.add(tempPQ.poll());
		}
		Result = 0;
		read.close();
	}

	private static void solv() {
		getTwoPointer();
	}

	private static void getTwoPointer() {
		if (M == 0) {
			Result = 0;
			return;
		}
		int indexL = 0;
		int indexR = 1;
		int gap = Integer.MAX_VALUE;
		int maxI = Dict.size() - 1;
		while (indexR <= maxI) {
			if (indexL > indexR) {
				indexR++;
				continue;
			}
			int left = Dict.get(indexL);
			int right = Dict.get(indexR);
			if (Math.abs(right - left) < M) { // 차이가 아직 작다 => 오른쪽으로 벌려
				indexR++;
			} else { // 차이가 같거나 크다 => 그 중 최솟값을 유지하고 왼쪽을 줄여
				gap = Math.min(gap, Math.abs(right - left));
				indexL++;
			}
		}
		Result = gap;
	}

}