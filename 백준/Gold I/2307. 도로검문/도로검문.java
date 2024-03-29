import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] NodeList = new ArrayList[1005];
    static int[][] EdgeMap = new int[1005][1005];
    static int[] MinPath = new int[1005];
    static int MinValue = 0;
    static int MaxValue = 0;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        N = readInt();
        for (int n = 1; n <= N; n++) {
            NodeList[n] = new ArrayList<>();
        }
        M = readInt();
        for (int m = 1; m <= M; m++) {
            int a = readInt();
            int b = readInt();
            int t = readInt();
            NodeList[a].add(b); // 각 노드에 연결된 엣지 번호
            NodeList[b].add(a);
            EdgeMap[a][b] = EdgeMap[b][a] = t; // 엣지당 걸리는 시간, 엣지 번호
        }
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read();
        while (n <= 45) {
            n = System.in.read();
        }
        n &= 15;
        while ((c = System.in.read()) >= 45) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static void solv() throws IOException {
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getDefaultDijkstra();
        getBlockedDijkstra();
        write.write(getResult() + "\n");
        write.close();
    }

    private static void getDefaultDijkstra() {
        int[] dijkMap = new int[N + 1];
        Arrays.fill(dijkMap, INF);
        dijkMap[1] = 0;

        PriorityQueue<int[]> dijkPq = new PriorityQueue<>((x1, x2) -> x1[0] - x2[0]);
        dijkPq.add(new int[] { 0, 1 });
        while (!dijkPq.isEmpty()) {
            int[] current = dijkPq.poll();
            int timeStacked = current[0];
            int from = current[1];
            // 최소값이 갱신됐으면 패스
            if (dijkMap[from] != timeStacked)
                continue;
            // 도착지점 최소값보다 크면 / 끝까지 도착시 중지
            if (dijkMap[N] < timeStacked || from == N)
                break;
            for (int to : NodeList[from]) {
                int newTime = EdgeMap[from][to] + timeStacked;
                if (dijkMap[to] > newTime) {
                    dijkMap[to] = newTime;
                    MinPath[to] = from; // 최단 경로 중 to로 오는 node 번호는 from
                    dijkPq.add(new int[] { newTime, to });
                }
            }
        }
        MinValue = dijkMap[N];
    }

    private static void getBlockedDijkstra() {
        int to = N;
        int from = MinPath[N];
        while (from > 0) {
            setBlocked(from, to);
            int tempFrom = from;
            from = MinPath[from];
            to = tempFrom;
        }
    }

    private static void setBlocked(int minFrom, int minTo) {
        int[] dijkMap = new int[N + 1];
        Arrays.fill(dijkMap, INF);
        dijkMap[1] = 0;

        PriorityQueue<int[]> dijkPq = new PriorityQueue<>((x1, x2) -> x1[0] - x2[0]);
        dijkPq.add(new int[] { 0, 1 });

        while (!dijkPq.isEmpty()) {
            int[] current = dijkPq.poll();
            int from = current[1];
            int timeStacked = current[0];
            if (dijkMap[from] != timeStacked)
                continue;
            if (dijkMap[N] < timeStacked) {
                break;
            }
            for (int to : NodeList[from]) {
                int newTime = EdgeMap[from][to] + timeStacked;
                if ((from == minFrom && to == minTo) || (from == minTo && to == minFrom))
                    continue;
                if (dijkMap[to] > newTime) {
                    dijkMap[to] = newTime;
                    dijkPq.add(new int[] { newTime, to });
                }
            }
        }
        MaxValue = Math.max(MaxValue, dijkMap[N]);
    }

    private static int getResult() {
        // 도착 불가능 케이스가 존재한다면
        if (MaxValue == INF) {
            return -1;
        } else {
            return MaxValue - MinValue;
        }
    }

}