import java.util.*;
import java.io.*;

public class Solution {
    static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static Queue<Integer> PriceList;
    static HashMap<Integer, Integer> PriceCount;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(read.readLine());
        for (int test = 1; test <= testCase; test++) {
            N = Integer.parseInt(read.readLine());
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            PriceList = new LinkedList<>();
            PriceCount = new HashMap<>();
            while (tokens.hasMoreTokens()) {
                int price = Integer.parseInt(tokens.nextToken());
                if (PriceCount.containsKey(price)) {
                    PriceCount.put(price, PriceCount.get(price) + 1);
                } else {
                    PriceCount.put(price, 1);
                }
                PriceList.add(price);
            }
            write.write("#" + test + " " + solv() + "\n");
        }
        read.close();
        write.close();
    }

    private static String solv() throws IOException {
        String result = "";
        while (!PriceList.isEmpty()) {
            int discounted = PriceList.poll();
            if (PriceCount.get(discounted) == 0)
                continue;
            PriceCount.put(discounted, PriceCount.get(discounted) - 1);
            PriceCount.put((discounted / 3) * 4, PriceCount.get((discounted / 3) * 4) - 1);
            result += discounted;
            if (!PriceList.isEmpty()) {
                result += " ";
            }
        }
        return result;
    }

}