import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb;
     
    static int[] p1;
    static int[] p2;
    static boolean[] check;
    static int winCnt;
    static int loseCnt;
    static int Score1;
    static int Score2;
     
    public static void main(String args[]) throws Exception
    {
         
        int T;
        T=Integer.parseInt(input.readLine());
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            tokens = new StringTokenizer(input.readLine());
            p1 = new int[9];
            p2 = new int[9];
            check = new boolean[19];
             
            for(int i=0; i<9; i++) {
                p1[i] = Integer.parseInt(tokens.nextToken());
                check[p1[i]] = true;
            }
             
            int tmp =0;
            for(int i=1; i<19; i++) {
                if(check[i] == false) {
                    p2[tmp++] = i;
                }
            }
             
            /// 입력끝
             
            winCnt =0;
            loseCnt =0; 
            Perm(0, new int[9], new boolean[9]);
            System.out.println("#"+test_case+" "+winCnt+" "+loseCnt );
             
        }
    }
     
    static void Perm(int index, int[] choosed, boolean[] visited) {
        if(index == choosed.length) {
            sum(p1, choosed);
            return;
        }
         
        for (int i=0; i<p2.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                choosed[index] = p2[i];
                Perm(index+1, choosed, visited);
                visited[i] = false;
            }
        }
    }
     
    // 규영이와 인영이가 가위바위보를 해서 승패를 가리는 로직
    static void sum(int[] p1, int[] p2) {
        Score1=0;
        Score2=0;
        for(int i=0; i<9; i++) {
            if(p1[i] > p2[i]) {
                Score1 += (p1[i] + p2[i]);
            }else if(p1[i] < p2[i]) {
                Score2 += (p1[i] + p2[i]);
            }
        }
        if(Score1 > Score2) {
            winCnt++;
        }else if(Score1 < Score2) {
            loseCnt++;
        }
    }
}