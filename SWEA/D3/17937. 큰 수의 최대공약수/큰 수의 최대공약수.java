import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
    static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(read.readLine());
        for (int test = 1; test <= testCase; test++) {
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            String result;
            String A = tokens.nextToken();
            if (A.equals(tokens.nextToken())) {
                result = A;
            } else {
                result = "1";
            }
            write.write("#" + test + " " + result + "\n");
        }
        read.close();
        write.close();
    }

}