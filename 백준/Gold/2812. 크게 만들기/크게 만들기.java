import java.util.*;
import java.io.*;

public class Main {
   static int N;
   static int K;
   static Queue<Character> Right = new LinkedList<>();
   static Deque<Character> Left = new LinkedList<>();

   public static void main(String[] args) throws IOException {
      init();
      solv();
   }

   private static void init() throws IOException {
      BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      String[] firstLine = read.readLine().split(" ");
      N = Integer.parseInt(firstLine[0]);
      K = Integer.parseInt(firstLine[1]);
      String secondLine = read.readLine();
      for (int n = 0; n < N; n++) {
         Right.offer(secondLine.charAt(n));
      }
      read.close();
   }

   private static void solv() throws IOException {
      BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
      getResult();
      while (!Left.isEmpty()) {
         write.write(Left.pollFirst());
      }
      while (!Right.isEmpty()) {
         write.write(Right.poll());
      }
      write.write("\n");
      write.close();
   }

   private static void getResult() {
      for (int k = 0; k < K; k++) { // k번만큼 지우기 실행
         while (true) {
            if (Right.isEmpty()) { // 오른쪽이 모두 지워졌다면
               // 왼쪽은 내림차순 정렬된 숫자들
               Left.pollLast(); // 가장 작은 숫자 제거
               break;
            }
            if (Left.isEmpty()) { // 왼쪽이 비어있다면
               Left.offer(Right.poll()); // 오른쪽 숫자 하나 밀기
            }
            if (Left.peekLast() < Right.peek()) { // 상승 => 왼쪽 숫자 제거
               Left.pollLast();
               break;
            } else { // 같거나 하강 => 오른쪽 숫자 왼쪽으로 밀기
               Left.offer(Right.poll());
            }
         }
      }
   }

}