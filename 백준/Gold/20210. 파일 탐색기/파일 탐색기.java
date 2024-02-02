import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    static int N;
    static String[] Lines;
    static ArrayList<String>[] SplittedLines;
    static Comparator<String> Comp = (x1, x2) -> {
        return x1.charAt(0) - x2.charAt(2);
    };
    static int ACode = 'A' + 0;
    static int zCode = 'z' + 0;

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        Lines = new String[N];
        SplittedLines = new ArrayList[N];
        for (int n = 0; n < N; n++) {
            Lines[n] = read.readLine();
            SplittedLines[n] = new ArrayList<>();
        }
        read.close();
    }

    private static void solv() throws IOException {
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        setSplitLines();
        setComparedSplittedLines();
        for (ArrayList<String> line : SplittedLines) {
            for (String str : line) {
                write.write(str);
            }
            write.write("\n");
        }
        write.close();
    }

    private static void setSplitLines() {
        for (int n = 0; n < N; n++) {
            String line = Lines[n];
            ArrayList<String> splitted = SplittedLines[n];
            String tempNum = "";
            boolean hasNum = false;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) < 60) { // 숫자
                    tempNum += line.charAt(i);
                    hasNum = true;
                } else {
                    if (hasNum) {
                        hasNum = false;
                        splitted.add(tempNum);
                        tempNum = "";
                    }
                    splitted.add("" + line.charAt(i));
                }
            }
            if (hasNum) {
                splitted.add(tempNum);
            }
        }
    }

    private static void setComparedSplittedLines() {
        Arrays.sort(SplittedLines, (list1, list2) -> {
            int size1 = list1.size();
            int size2 = list2.size();
            for (int i = 0; i < Math.min(size1, size2); i++) {
                String st1 = list1.get(i);
                String st2 = list2.get(i);
                if (st1.equals(st2)) // 그냥 같은 문자면
                    continue; // 다음 비교
                if (st1.charAt(0) >= ACode && st2.charAt(0) >= ACode) { // 둘 다 문자
                    if (st1.toUpperCase().charAt(0) == st2.toUpperCase().charAt(0)) { // 같은 aA, bB, cC
                        return st1.charAt(0) - st2.charAt(0); // 대문자가 더 작다
                    } else { // 다른 문자
                        return st1.toUpperCase().charAt(0) - st2.toUpperCase().charAt(0); // A~Z 순서 비교
                    }
                } else if (st1.charAt(0) >= ACode) { // 1만 문자
                    return 1; // 숫자가 앞으로 (2가 더 작다)
                } else if (st2.charAt(0) >= ACode) { // 2만 문자
                    return -1; // 숫작 ㅏ앞으로 (1이 더 작다)
                } else { // 둘 다 숫자
                    BigInteger bi1 = new BigInteger(st1);
                    BigInteger bi2 = new BigInteger(st2);
                    if (bi1.compareTo(bi2) == 0) { // 같은 값이면
                        return st1.length() - st2.length(); // 0의 개수가 적은, 길이가 작은 것이 앞으로
                    } else { // 다른 값
                        return bi1.compareTo(bi2); // biginteger 비교
                    }
                }
            }
            // 작은 문자열 끝까지 비교했는데 같음
            if (size1 == size2) { // 그냥 둘이 같은 문자열
                return 0;
            } else if (size1 < size2) { // 1이 더 짧다
                return -1; // 짧은 문자열이 앞으로
            } else {
                return 1;
            }
        });

    }

}