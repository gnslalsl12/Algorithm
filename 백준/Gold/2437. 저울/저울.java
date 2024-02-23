import java.util.*;
import java.io.*;

public class Main {
   static int N;
   static PriorityQueue<Integer> NList = new PriorityQueue<>(); // 입력 (오름차순)

   public static void main(String[] args) throws IOException {
      init();
      solv();
   }

   private static void init() throws IOException {
      N = readInt();
      for (int n = 0; n < N; n++) {
         NList.offer(readInt()); // 입력 오름차순 정렬
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

   private static int getResult() {
      int max = NList.poll(); // 최소 무게 추
      if (max >= 2) { // 1을 만들 수 없음
         return 1;
      }
      // 1 ~ max(1)까지 정수가 조합 가능한 숫자임을 보장
      while (!NList.isEmpty()) {
         int nextN = NList.poll();
         if (nextN > max + 1) {
            // 1~max와 nextN을 조합할 때,
            // max + 1보다 큰 숫자 nextN과는 max + 1이라는 숫자를 조합 불가능
            break;
         }
         max += nextN;
         // 1 ~ max까지 보장돼있으니, 각각 nextN을 더해서 nextN + 1, nextN + 2, ... nextN + max까지 보장 갱신
      }
      return max + 1;
   }

}