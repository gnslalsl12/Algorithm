import java.util.*;
import java.io.*;

public class Main {
  static int N;
  static int M;
  static int K;
  static ArrayList<int[]>[] NodeList = new ArrayList[10003];

  static final long INF = Long.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    init();
    solv();
  }

  private static void init() throws IOException {
    N = readInt();
    M = readInt();
    K = readInt();
    for (int n = 1; n <= N; n++) {
      NodeList[n] = new ArrayList<>();
    }
    for (int m = 0; m < M; m++) {
      int nodeA = readInt();
      int nodeB = readInt();
      int value = readInt();
      NodeList[nodeA].add(new int[] { nodeB, value });
      NodeList[nodeB].add(new int[] { nodeA, value });
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
    write.write(getResult() + "\n");
    write.close();
  }

  private static long getResult() {
    long[][] dijkMap = new long[N + 1][K + 1];
    PriorityQueue<Move> dijkPq = new PriorityQueue<>();
    for (int n = 2; n <= N; n++) {
      Arrays.fill(dijkMap[n], INF);
    }
    dijkPq.add(new Move(1, 0, 0));

    while (!dijkPq.isEmpty()) {
      Move current = dijkPq.poll();
      int arrived = current.arrived;
      if (arrived == N) {
        break;
      }
      long stackedTime = current.stackedTime;
      int wrapped = current.wrapped;
      boolean pass = false;
      for (int w = 0; w < wrapped; w++) { // 현재 위치까지 더 적게 포장하고 더 적은 비용이 드는 경로가 존재하면 진행X
        if (dijkMap[arrived][w] <= stackedTime) {
          pass = true;
          break;
        }
      }
      if (pass || dijkMap[arrived][wrapped] != stackedTime) // 또는 현재 도착 위치의 값이 최소값이 아닐 때(이미 갱신됨)
        continue;
      for (int[] next : NodeList[arrived]) {
        int nextNode = next[0];
        long newTime = stackedTime + next[1];
        // 포장하지 않고 바로
        if (dijkMap[nextNode][wrapped] > newTime) {
          dijkMap[nextNode][wrapped] = newTime;
          dijkPq.add(new Move(nextNode, newTime, wrapped));
        }
        // 포장 가능한 경우 포장 케이스
        if (wrapped < K) {
          boolean addable = true;
          for (int w = 0; w <= wrapped + 1; w++) {
            if (dijkMap[nextNode][w] <= stackedTime) { // 현재 위치까지 적게 포장하고 적은 비용이 드는 경로가 존재하면 추가 X
              addable = false;
              break;
            }
          }
          if (addable) {
            dijkMap[nextNode][wrapped + 1] = stackedTime;
            dijkPq.add(new Move(nextNode, stackedTime, wrapped + 1));
          }
        }
      }
    }
    long result = INF;
    for (int w = 0; w <= K; w++) {
      result = Math.min(dijkMap[N][w], result);
    }
    return result;
  }

  private static class Move implements Comparable<Move> {
    int arrived;
    long stackedTime;
    int wrapped;

    public Move(int arrived, long stackedTime, int wrapped) {
      this.arrived = arrived;
      this.stackedTime = stackedTime;
      this.wrapped = wrapped;
    }

    @Override
    public int compareTo(Move obj) {
      return Long.compare(this.stackedTime, obj.stackedTime);
    }

  }

}