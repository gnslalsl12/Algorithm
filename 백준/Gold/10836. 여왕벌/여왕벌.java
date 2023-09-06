import java.util.*;
import java.io.*;

public class Main {
    static int M;
    static int N;
    static int [][] Map;
    static int [][] GrowthHistory;
    
    public static void main(String[] args) throws IOException{
        initAndSolv();
    }
    
    private static void initAndSolv()throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        Map = new int [M][M];
        GrowthHistory = new int [M][M];
        for(int m = 0; m < M; m++){
            Arrays.fill(Map[m], 1);
        }
        for(int n = 0; n < N; n++){
            tokens = new StringTokenizer(read.readLine());
            int count1 = Integer.parseInt(tokens.nextToken());
            int count2 = Integer.parseInt(tokens.nextToken());
            int count3 = Integer.parseInt(tokens.nextToken());
            setOuterBeesGrowth(count1,count2,count3);
        }
        getPrint();
        read.close();
    }
    
    private static void setOuterBeesGrowth(int count1, int count2, int count3){
        int outerIndex = M-1;
        int add=0;
        while(outerIndex > 0){
            if(add != 2){
                if(count1 > 0){
                    count1--;
                }else if(count2 > 0){
                    count2--;
                    add = 1;
                }else{
                    add = 2;
                }
            }
            GrowthHistory[outerIndex][0] = add;
            Map[outerIndex--][0] += add;
        }
        while(outerIndex < M){
            if(add != 2){
                if(count1 > 0){
                    count1--;
                }else if(count2 > 0){
                    count2--;
                    add = 1;
                }else{
                    add = 2;
                }
            }
            GrowthHistory[0][outerIndex] = add;
            Map[0][outerIndex++] += add;   
        }
        setInnerBeesGrowth();
    }
    
    private static void setInnerBeesGrowth(){
        for(int i = 1; i < M; i++){
            for(int j = 1; j < M; j++){
                int maxAround = getMaxAroundGrowth(i,j);    
                Map[i][j] += maxAround;
                GrowthHistory[i][j] = maxAround;
            }
        }
    }
    
    private static int getMaxAroundGrowth(int i, int j){
        return Math.max(Math.max(GrowthHistory[i-1][j], GrowthHistory[i][j-1]),GrowthHistory[i-1][j-1]);
    }
    
    private static void getPrint() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0;i < M; i++){
            for(int j = 0; j < M; j++){
                write.write(Map[i][j] + " ");
            }
            write.write("\n");
        }
        write.close();
    }
    
}