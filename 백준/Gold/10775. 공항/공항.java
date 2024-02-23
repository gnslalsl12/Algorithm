import java.util.*;
import java.io.*;

public class Main {
   static int G;
   static int P;
   static Queue<Integer> PList = new LinkedList<>();
   static int[] SectionIndex = new int[100005];

   public static void main(String[] args) throws IOException {
      init();
      solv();
   }

   private static void init() throws IOException {
      G = readInt();
      P = readInt();
      for (int p = 0; p < P; p++) {
         PList.offer(readInt());
      }
   }

   private static int readInt() throws IOException {
      int c, n = System.in.read();
      while (n <= 45) {
         n = System.in.read();
      }
      n &= 15;
      while ((c = System.in.read()) > 45) {
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
      for (int g = 1; g <= G; g++) {
         SectionIndex[g] = g;
      }
      int count = 0;
      while (!PList.isEmpty()) {
         int setResult = setThisPlane(PList.poll()); // 반환값 : 세팅된 자리 -1
         if (setResult == -1) // 0의 자리는 세팅 불가능
            break;
         count++;
      }
      return count;
   }

   private static int setThisPlane(int p) {
      if (SectionIndex[p] == p) { // 현재 자리에 세팅 가능
         return --SectionIndex[p];
      }
      // 더 작은 자리에 세팅해야함
      return SectionIndex[p] = setThisPlane(SectionIndex[p]);
   }

}