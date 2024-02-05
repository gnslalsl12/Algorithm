import java.util.*;
import java.io.*;

public class Solution {
    static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int M;
    static String[] NLine = new String[21];
    static String[] MLine = new String[21];
    static Queue<Integer> QList = new LinkedList<>();

    static String[] WholeSets;
    static int LCM;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(read.readLine());
        for (int test = 1; test <= testCase; test++) {
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            tokens = new StringTokenizer(read.readLine());
            for (int n = 0; n < N; n++) {
                NLine[n] = tokens.nextToken();
            }
            tokens = new StringTokenizer(read.readLine());
            for (int m = 0; m < M; m++) {
                MLine[m] = tokens.nextToken();
            }
            int qSize = Integer.parseInt(read.readLine());
            while (qSize-- > 0) {
                QList.add(Integer.parseInt(read.readLine()) - 1);
            }
            write.write("#" + test + " " + solv() + "\n");
        }
        read.close();
        write.close();
    }

    private static String solv() throws IOException {
        String result = "";
        getWholeSets();
        while (!QList.isEmpty()) {
            result += WholeSets[QList.poll() % LCM];
            if (!QList.isEmpty()) {
                result += " ";
            }
        }
        return result;
    }

    private static void getWholeSets() {
        LCM = getLCM();
        WholeSets = new String[LCM + 5];
        int n = 0;
        int m = 0;
        int index = 0;
        do {
            WholeSets[index++] = NLine[n++] + MLine[m++];
            n %= N;
            m %= M;
        } while (n != 0 || m != 0);
    }

    private static int getLCM() {
        return (N * M) / getGCD();
    }

    private static int getGCD() {
        int tempN = Math.max(N, M);
        int tempM = Math.min(N, M);
        while (tempM != 0) {
            int remain = tempN % tempM;
            tempN = tempM;
            tempM = remain;
        }
        return tempN;
    }

}