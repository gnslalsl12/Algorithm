import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] Map = new ArrayList[4005]; // 각 나무(노드)별 연결된 나무 번호
    static int[][] Dists = new int[4005][4005]; // 오솔길 거리
    static long[] FoxDists = new long[4005]; // 여우가 기록할 나무까지의 거리
    static long[][] WolfDists = new long[2][4005]; // 늑대가 기록할 나무까지의 거리 (짝수번째 도착, 홀수번째 도착 구분 [2])

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        N = readInt();
        for (int n = 1; n <= N; n++) {
            Map[n] = new ArrayList<>();
        }
        M = readInt();
        for (int m = 0; m < M; m++) {
            int nodeA = readInt();
            int nodeB = readInt();
            int value = readInt();
            Map[nodeA].add(nodeB);
            Map[nodeB].add(nodeA);
            Dists[nodeA][nodeB] = Dists[nodeB][nodeA] = value * 10; // 늑대의 1/2거리를 대비해 값을 10배로 통일 => 1/2해도 소수점 X
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
        setDists();
        write.write(getResult() + "\n");
        write.close();
    }

    private static void setDists() {
        long maxLong = Long.MAX_VALUE;
        Arrays.fill(FoxDists, maxLong);
        Arrays.fill(WolfDists[0], maxLong);
        Arrays.fill(WolfDists[1], maxLong);
        FoxDists[1] = WolfDists[1][1] = 0L; // 시작지점 초기화

        PriorityQueue<Move> tempPq = new PriorityQueue<>((x1, x2) -> Long.compare(x1.dist, x2.dist));
        tempPq.add(new Move(0, 1, 0)); // 여우 (0, 도착 노드, 움직인 거리)
        tempPq.add(new Move(1, 1, 0)); // 늑대 (움직임 횟수, 도착 노드, 움직인 거리)

        while (!tempPq.isEmpty()) {
            Move current = tempPq.poll();
            int count = current.count; // 움직인 횟수
            int from = current.nodeAt; // 현재 노드 번호
            long currentDist = current.dist; // 현재 노드까지 지나온 거리
            if ((count == 0 && FoxDists[from] < currentDist) //
                    || (count > 0 && WolfDists[count % 2][from] < currentDist)) {
                continue; // 현재 위치까지의 거리가 갱신됐으면 패스
            }

            for (int to : Map[from]) { // 이동 가능 위치들 중
                long toDist = Dists[from][to]; // 지나갈 오솔길 거리
                if (count > 0) { // 늑대라면
                    toDist *= count % 2 == 0 ? 2 : 0.5; // 짝수번째에는 2배 거리, 홀수번째에는 1/2거리
                }
                toDist += currentDist; // 지금까지의 거리 합
                if (count == 0) { // 여우
                    if (FoxDists[to] > toDist) { // 최소거리 갱신시
                        FoxDists[to] = toDist;
                        tempPq.add(new Move(0, to, toDist)); // 추가
                    }
                } else { // 늑대
                    if (WolfDists[(count + 1) % 2][to] > toDist) {// 최소거리 갱신시
                        WolfDists[(count + 1) % 2][to] = toDist;
                        tempPq.add(new Move(count + 1, to, toDist)); // 추가
                    }
                }
            }

        }
    }

    private static int getResult() {
        int result = 0;
        for (int node = 2; node <= N; node++) {
            if (FoxDists[node] < Math.min(WolfDists[0][node], WolfDists[1][node]))
                result++;
        }
        return result;
    }

    private static class Move {
        int count;
        int nodeAt;
        long dist;

        public Move(int count, int nodeAt, long dist) {
            this.count = count;
            this.nodeAt = nodeAt;
            this.dist = dist;
        }
    }

}