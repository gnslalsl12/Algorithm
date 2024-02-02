import java.util.*;
import java.io.*;

public class Main {
    static int Map = 0;
    static int Start = 0;

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(tokens.nextToken());
                if (num == 0) {
                    Start = 8 - i * 3 - j;
                }
                Map = Map * 10 + num;
            }
        }
        read.close();
    }

    private static void solv() throws IOException {
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        write.write(getResult() + "\n");
        write.close();
    }

    private static int getResult() {
        int result = -1;
        Queue<int[]> bfsQ = new LinkedList<>();
        bfsQ.add(new int[] { Map, Start });
        HashSet<Integer> vis = new HashSet<>();
        vis.add(Map);
        breakWhile: while (!bfsQ.isEmpty()) {
            int[] current = bfsQ.poll();
            int map = current[0];
            int count = current[1] / 10;
            int currentEmpty = current[1] % 10;
            if (map == 123456780) {
                result = count;
                break;
            }
            for (int dir = 0; dir < 4; dir++) {
                int nextEmpty = nextLoc(currentEmpty, dir);
                if (nextEmpty == -1) // 이동 불가
                    continue;
                // 빈칸이 이동할 위치의 숫자
                int tempNum = (map % ((int) Math.pow(10, nextEmpty + 1))) / ((int) Math.pow(10, nextEmpty));
                // 빈칸이 이동한 경우의 맵
                int nextMap = map - tempNum * ((int) Math.pow(10, nextEmpty))
                        + tempNum * ((int) Math.pow(10, currentEmpty));
                if (vis.contains(nextMap))
                    continue;
                if (nextMap == 123456780) {
                    result = count + 1;
                    break breakWhile;
                }
                vis.add(nextMap);
                bfsQ.add(new int[] { nextMap, (count + 1) * 10 + nextEmpty });
            }
        }
        return result;
    }

    private static int nextLoc(int emptyLoc, int dir) {
        int result = -1;
        if (dir == 0 && emptyLoc < 6) { // 위로
            result = emptyLoc + 3;
        } else if (dir == 1 && emptyLoc % 3 > 0) { // 오른쪽으로
            result = emptyLoc - 1;
        } else if (dir == 2 && emptyLoc > 2) { // 아래로
            result = emptyLoc - 3;
        } else if (dir == 3 && emptyLoc % 3 < 2) { // 왼쪽으로
            result = emptyLoc + 1;
        }
        return result;
    }

}