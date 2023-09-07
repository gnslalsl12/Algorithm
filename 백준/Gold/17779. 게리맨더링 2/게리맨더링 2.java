import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [][] Map;
    static int Result;
    
    public static void main(String[] args) throws IOException{
        init();
        solv();
    }
    
    private static void init() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        Map = new int [N+1][N+1];
        for(int i = 1;i <= N; i++){
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            for(int j = 1; j <= N; j++){
                Map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        Result = Integer.MAX_VALUE;
        read.close();
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getWholeCase();
        write.write(Result + "\n");
        write.close();
    }
    
    private static void getWholeCase(){
        for(int x = 1; x<=N ; x++){
            for(int y = 1; y <= N; y++){
                for(int d1 = 1; d1 <= N; d1++){
                    for(int d2 = 1; d2 <= N; d2++){
                        if(x + d1 + d2 > N || y-d1 < 1 || y + d2 > N) break;
                        if(!isIn(x+d1, y-d1) || !isIn(x+d2,y+d2) || !isIn(x+d1+d2, y-d1+d2)) continue;
                        getSectionDiv(x,y,d1,d2);
                    }
                }
            }
        }
    }
    
    private static void getSectionDiv(int x, int y, int d1, int d2){
        int [] SectionFive = setSectionFive(x,y,d1,d2);
        int[] Sum = new int [5];
        for(int r= 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if((SectionFive[r] & (1<<c)) != 0) Sum[4] += Map[r][c];
                else if((r < x+d1) && (c <= y)) Sum[0]+=Map[r][c];
                else if((r <= x+d2) && (y < c)) Sum[1] += Map[r][c];
                else if((x+d1 <= r) &&(c < y-d1+d2)) Sum[2]+=Map[r][c];
                else if((x+d2 < r) && (y-d1+d2<=c)) Sum[3]+=Map[r][c];
            }
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < 5; i++){
            if(Sum[i] == 0) return;
            min = Math.min(Sum[i], min);
            max = Math.max(Sum[i],max);
        }
        Result = Math.min(max-min, Result);
    }
    
    private static int[] setSectionFive(int x, int y, int d1, int d2){

        int [] section = new int [N+1];
        int left= 0;
        int right = 0;
        boolean lMinus = false;
        boolean rMinus = false;
        for(int i = x; i <= x+d1+d2; i++){
            for(int j = y-left; j <= y+right; j++){
                section[i] |= 1<<j;
            }

            if(!lMinus && left < d1) left++;
            else{
                left--;
                lMinus = true;
            }
            if(!rMinus && right < d2) right++;
            else{
                rMinus = true;    
                right--;
            } 
        }
        return section;
    }
    
    private static boolean isIn(int i, int j ){
        return i >= 1 && j >= 1 && i <= N && j <= N;
    }
}