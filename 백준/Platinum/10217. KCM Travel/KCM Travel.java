import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static ArrayList<Planes>[] NodeList = new ArrayList[103]; // 각 공항별 티켓 정보
    static int[][] DistMap = new int[103][10003]; // N위치까지 C비용으로 가는 최소 시간

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        read.readLine();
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        N = Integer.parseInt(tokens.nextToken());
        for (int n = 1; n <= N; n++) {
            NodeList[n] = new ArrayList<>();
        }
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        for (int k = 0; k < K; k++) {
            tokens = new StringTokenizer(read.readLine());
            int u = Integer.parseInt(tokens.nextToken()); // from
            int v = Integer.parseInt(tokens.nextToken()); // to
            int c = Integer.parseInt(tokens.nextToken()); // cost
            int d = Integer.parseInt(tokens.nextToken()); // time
            NodeList[u].add(new Planes(v, c, d));
        }
        read.close();
    }

    private static void solv() throws IOException {
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        write.write(getResult() + "\n");
        write.close();
    }

    private static String getResult() {
        int result = Integer.MAX_VALUE;

        for (int n = 1; n <= N; n++) {
            Arrays.fill(DistMap[n], INF);
        }
        DistMap[1][0] = 0;

        for (int cost = 0; cost <= M; cost++) { // cost 비용을 가진 상태에서
            for (int from = 1; from <= N; from++) { // from에서
                if (DistMap[from][cost] == INF) // cost로 해당 from 공항까지 도착하는 방법이 없다면 패스
                    continue;
                for (Planes next : NodeList[from]) { // 소지한 티켓 중
                    if (cost + next.cost <= M) { // 갈 수 있는 비용이라면
                        DistMap[next.to][cost + next.cost] = Math.min(DistMap[next.to][cost + next.cost],
                                DistMap[from][cost] + next.time);
                        // 해당 티켓으로 가는 최소 시간 갱신 (기본 값, from까지 cost 비용으로 오는 최소 시간 + 다음 공항까지 시간)
                    }
                }
            }
        }

        for (int m = 0; m <= M; m++) {
            result = Math.min(DistMap[N][m], result);
        }
        return result == INF ? "Poor KCM" : "" + result;

    }

    private static class Planes {
        int to;
        int cost;
        int time;

        public Planes(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }

    }

}