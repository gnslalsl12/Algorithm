import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static ArrayList<int[]>[] Ways = new ArrayList[210];
	static int[][][] Result = new int [210][210][2];

	public static void main(String args[]) throws IOException { //다익스트라라
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		for (int n = 1; n <= N; n++) {
			Ways[n] = new ArrayList<>();
		}
		for (int m = 0; m < M; m++) {
			tokens = new StringTokenizer(read.readLine());
			int nodeA = Integer.parseInt(tokens.nextToken());
			int nodeB = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			Ways[nodeA].add(new int[] { nodeB, value });
			Ways[nodeB].add(new int[] { nodeA, value });
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getMinDir();
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				write.write((from == to ? "-" : Result[from][to][0]) + (to == N ? "\n" : " "));
			}
		}
		write.close();
	}

	private static void getMinDir() {   //최단 경로 구하기
		int INF = Integer.MAX_VALUE;
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) {
					Result[from][to] = new int[] { -1, -1 };    //자기 자신으로의 최단 경로는 -1로 초기화
				} else {
					Result[from][to] = new int[] { -1, INF };   //나머지는 첫 경로를 -1, 경로값을 INF로 초기화
				}
			}
			setDIJK(from);  //from노드에서 다른 노드로의 최단 경로 정보 구하기
		}
	}
    
	private static void setDIJK(int from) {
		PriorityQueue<int[]> tempPq = new PriorityQueue<>((x1, x2) -> {
			return Integer.compare(x1[2], x2[2]);
		}); // {도착 노드, 첫번째 경로 노드, 경로값}
		tempPq.add(new int[] { from, -1, 0 });
		while (!tempPq.isEmpty()) {
			int[] info = tempPq.poll();
			int currentNode = info[0];
			int firstNode = info[1];
			int values = info[2];
			for (int[] nextWay : Ways[currentNode]) {   //다익스트라
				int nextNode = nextWay[0];
				int nextValue = nextWay[1] + values;
				if (Result[from][nextNode][1] > nextValue) {    //from 노드에서 nextNode로의 경로값이 최소로 갱신된다면
					Result[from][nextNode][0] = firstNode == -1 ? nextNode : firstNode; //첫번쨰 노드번호 갱신
					Result[from][nextNode][1] = nextValue;  //경로값 갱신
					tempPq.add(new int[] { nextNode, firstNode == -1 ? nextNode : firstNode, nextValue });  //pq에 추가해 계속 탐색
				}
			}
		}
	}

}