import java.io.*;

public class Main {
  static int R, C;
  static int[][] Map = new int[1003][1003];
  static int MinLoc;
  static String Result = "";

  public static void main(String[] args) throws IOException {
    init();
    solv();
  }

  private static void init() throws IOException {
    R = readInt();
    C = readInt();
    Map[1002][1002] = Integer.MAX_VALUE;
    int minValue = 1005;
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        Map[r][c] = readInt();
        if (r % 2 != c % 2) { // != 블록 위치 중 최소값 위치 구하기
          if (minValue > Map[r][c]) {
            minValue = Map[r][c];
            MinLoc = r * C + c;
          }
        }
      }
    }
  }

  private static int readInt() throws IOException {
    int n, c;
    do {
      n = System.in.read();
    } while (n <= 45);
    n &= 15;
    while ((c = System.in.read()) >= 45) {
      n = (n << 3) + (n << 1) + (c & 15);
    }
    return n;
  }

  private static void solv() throws IOException {
    BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
    getResult();
    write.write(Result + "\n");
    write.close();
  }

  private static void getResult() {
    if (R % 2 == 1 || C % 2 == 1) { // R,C 중 하나라도 홀수라면 브루트포스 경로
      getOddCase();
    } else { // R,C 모두 짝수인 경우
      getEvenCase();
    }
  }

  private static void getOddCase() {
    String tempA = "";
    String tempB = "";
    if (R % 2 == 1) { // 홀수 행
      for (int c = 1; c < C; c++) {
        tempA += 'R';
        tempB += 'L';
      }
      for (int r = 0; r < R; r++) {
        if (r % 2 == 0) {
          Result += tempA;
        } else {
          Result += tempB;
        }
        if (r < R - 1) {
          Result += 'D';
        }
      }
    } else {
      for (int r = 1; r < R; r++) {
        tempA += 'D';
        tempB += 'U';
      }
      for (int c = 0; c < C; c++) {
        if (c % 2 == 0) {
          Result += tempA;
        } else {
          Result += tempB;
        }
        if (c < C - 1) {
          Result += 'R';
        }
      }
    }
  }

  private static void getEvenCase() { // 행, 열 위치가 짝/홀로 다른 최소 값 블록 하나를 제외한 경로 구성
    int minR = MinLoc / C;
    int minC = MinLoc % C;
    boolean rowBias = minR % 2 == 0; // 가로로 쭉쭉 : true : false
    int maxI = rowBias ? R : C;
    int maxJ = rowBias ? C : R;
    String tempA = "";
    String tempB = "";
    String zigA = rowBias ? "DR" : "RD";
    String zigB = rowBias ? "UR" : "LD";
    String zigZ = rowBias ? "R" : "D";
    boolean originZ = true;
    for (int j = 0; j < maxJ - 1; j++) {
      tempA += rowBias ? 'R' : 'D';
      tempB += rowBias ? 'L' : 'U';
    }
    boolean origin = true;
    for (int i = 0; i < maxI; i++) {
      if (rowBias ? (i / 2 == minR / 2) : (i / 2 == minC / 2)) {
        // 가로 방향에 제외 블록의 R*2 구역에 들어섰음
        // 세로 방향에 제외 블록의 C*2 구역에 들어섰음
        // => 지그재그
        origin = false;
        for (int j = 0; j < maxJ - 1; j++) {
          if (rowBias ? (i == minR && j == minC) : (i == minC && j == minR)) { // zigZ
            Result += zigZ;
            originZ = false;
          } else {
            Result += j % 2 == 0 ? (originZ ? zigA : zigB) : (originZ ? zigB : zigA);
          }
        }
        i++;
        if (!originZ) { // 지그 없이 끝났으면 추가X
          Result += rowBias ? "D" : "R";
        }
        if (i != maxI - 1) { // 끝 지점이 아니므로 한 줄 아래로
          Result += rowBias ? "D" : "R";
        }

      } else {
        Result += origin ? (i % 2 == 0 ? tempA : tempB) : (i % 2 == 0 ? tempB : tempA);
        if (i < maxI - 1)
          Result += rowBias ? 'D' : 'R';
      }
    }

  }
}