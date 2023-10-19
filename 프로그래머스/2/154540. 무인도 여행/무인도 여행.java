import java.util.*;

class Solution {
    static int N;
    static int M;
    static int [][] Map;
    static PriorityQueue<Integer> PQ;
    static int [][] Deltas;
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        Map = new int [N][M];
        PQ = new PriorityQueue<>();
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                char value = maps[n].charAt(m);
                Map[n][m] = value=='X'? 0 : value-'0';
            }
        }
        Deltas = new int [][] {{-1,0},{0,1},{1,0},{0,-1}};
        solv();
        int [] answer = new int [PQ.size()];
        if(PQ.size() == 0){
            answer = new int [] {-1};
        }else{
            for(int i = 0; i < answer.length; i++){
                answer[i] = PQ.poll();
            }
        }
        return answer;
    }
    
    private static void solv(){
        setIslDivide();
    }
    
    private static void setIslDivide(){
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if( Map[n][m] > 0){
                    getIslBfs(n,m);
                }
            }
        }
    }
    
    private static void getIslBfs(int n, int m){
        Queue<Integer> bfsQ = new LinkedList<>();
        bfsQ.add(n*M + m);
        int sum = Map[n][m];
        Map[n][m] = 0;
        while(!bfsQ.isEmpty()){
            int loc = bfsQ.poll();
            int locN = loc/M;
            int locM = loc%M;
            for(int[] dir : Deltas){
                int nextN = locN + dir[0];
                int nextM = locM + dir[1];
                if(!isIn(nextN,nextM) || Map[nextN][nextM] == 0) continue;
                sum += Map[nextN][nextM];
                Map[nextN][nextM] = 0;
                bfsQ.add(nextN*M + nextM);
            }
        }
        PQ.add(sum);
    }
    
    private static boolean isIn(int n , int m){
        return n >= 0 && n < N && m >= 0 && m < M;
    }
}