import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] NodeList = new ArrayList[1005];
    static int[][] EdgeMap = new int[1005][1005];
    static boolean[] MinPath = new boolean[5005];
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
            EdgeMap[a][b] = EdgeMap[b][a] = t * 10000 + m; // 엣지당 걸리는 시간, 엣지 번호
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
        for (int m = 1; m <= M; m++) {
            // 최소 경로에 포함되는 엣지인 경우에 새로운 최소 경로 구하기
            if (MinPath[m]) {
                getBlockedDijkstra(m);
            }
        }
        write.write(getResult() + "\n");
        write.close();
    }

    private static void getDefaultDijkstra() {
        int[] dijkMap = new int[N + 1];
        Arrays.fill(dijkMap, INF);
        dijkMap[1] = 0;

        PriorityQueue<PathCase> dijkPq = new PriorityQueue<>();
        dijkPq.add(new PathCase(1, 0, new LinkedList<>(), 0));
        Queue<Integer> minPath = new LinkedList<>();
        while (!dijkPq.isEmpty()) {
            PathCase current = dijkPq.poll();
            int from = current.arrived;
            int timeStacked = current.timeStacked;
            // 최소값이 갱신됐으면 패스
            if (dijkMap[from] != timeStacked)
                continue;
            // 도착지점 최소값보다 크면 중지
            if (dijkMap[N] < timeStacked)
                break;
            // 도착지점까지 온 경우 minPath 갱신
            if (from == N) {
                minPath = current.path;
                continue;
            }
            for (int to : NodeList[from]) {
                int newPathInfo = EdgeMap[from][to];
                int pathNum = newPathInfo % 10000;
                int newTime = newPathInfo / 10000 + timeStacked;
                if (dijkMap[to] > newTime) {
                    dijkMap[to] = newTime;
                    dijkPq.add(new PathCase(to, newTime, current.path, pathNum));
                }
            }
        }
        while (!minPath.isEmpty()) {
            MinPath[minPath.poll()] = true;
        }
        MinValue = dijkMap[N];
    }

    private static void getBlockedDijkstra(int blocked) {
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
                int newPathInfo = EdgeMap[from][to];
                int pathNum = newPathInfo % 10000;
                if (pathNum == blocked)
                    continue;
                int newTime = newPathInfo / 10000 + timeStacked;
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

    private static class PathCase implements Comparable<PathCase> {
        int arrived;
        int timeStacked;
        Queue<Integer> path;

        PathCase(int arrived, int timeStacked, Queue<Integer> beforePath, int newPath) {
            this.arrived = arrived;
            this.timeStacked = timeStacked;
            this.path = new LinkedList<>(beforePath);
            this.path.add(newPath);
        }

        @Override
        public int compareTo(PathCase obj) {
            return this.timeStacked - obj.timeStacked;
        }

    }

}