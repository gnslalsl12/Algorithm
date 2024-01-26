import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Character> KSG = new ArrayList<>();
    static ArrayList<Character> CLV = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        solv();
    }

    private static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String inputKSG = read.readLine();
        N = inputKSG.length();
        String inputCLV = read.readLine();
        for (int n = 0; n < N; n++) {
            KSG.add(inputKSG.charAt(n));
            CLV.add(inputCLV.charAt(n));
        }
        KSG.sort(null);
        CLV.sort(null);

        read.close();
    }

    private static void solv() throws IOException {
        char[] result = new char[N];
        int indexL = 0;
        int indexR = N - 1;
        int leftKSG = 0; // KSG 최소문자
        int rightKSG = N % 2 == 0 ? (N / 2) - 1 : N / 2; // KSG가 쓸 문자의 개수 만큼의 범위 중 최대 문자
        int rightCLV = N - 1; // CLV의 최대문자
        int leftCLV = N % 2 == 0 ? N / 2 : (N / 2) + 1; // CLV가 쓸 문자의 개수 만큼의 범위 중 최소문자
        for (int n = 0; n < N; n++) {
            if (n % 2 == 0) { // KSG turn
                if (KSG.get(leftKSG) >= CLV.get(rightCLV)) {// KSG의 최소 문자가 CLV의 최대 문자보다 크면
                    result[indexR--] = KSG.get(rightKSG--); // KSG의 최대 문자를 가장 오른쪽에 배치 (최소 문자는 아껴야하니까)
                } else { // 아니면 가장 왼쪽에 최소 문자 배치
                    result[indexL++] = KSG.get(leftKSG++);
                }
            } else { // CLV turn
                if (CLV.get(rightCLV) <= KSG.get(leftKSG)) { // CLV의 최대 문자가 KSG의 최소 문자보다 작으면
                    result[indexR--] = CLV.get(leftCLV++); // CLV의 최소 문자를 가장 오른쪽에 배치
                } else { // 아니면 가장 왼쪽에 최대 문자 배치
                    result[indexL++] = CLV.get(rightCLV--);
                }
            }
        }
        System.out.println(new String(result));
    }

}