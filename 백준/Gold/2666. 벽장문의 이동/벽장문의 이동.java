import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int OpenA, OpenB;
    static int O;
    static int[] Orders;
    static int Result;

    public static void main(String args[])  throws IOException {
      init();
      solv();
    }
    
    private static void init() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        StringTokenizer tokens =new StringTokenizer(read.readLine());
        OpenA = Integer.parseInt(tokens.nextToken());
        OpenB = Integer.parseInt(tokens.nextToken());
        O = Integer.parseInt(read.readLine());
        Orders = new int [O];
        for(int o = 0; o < O; o++){
            Orders[o] = Integer.parseInt(read.readLine());
        }
        Result = Integer.MAX_VALUE;
        read.close();
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getResultBrute();
        write.write(Result + "\n");
        write.close();
    }
    
    private static void getResultBrute(){
        Queue<int[]> caseQ = new LinkedList<>();
        caseQ.add(new int [] {0, OpenA*100 + OpenB});   //민 횟수 * 10000 + 왼쪽 인덱스 * 100 + 오른쪽인덱스스
        while(!caseQ.isEmpty()){
            int[] info = caseQ.poll();
            if(info[0] == O){
                Result = Math.min(info[1]/10000,Result);
                continue;
            }
            int nextOpen = Orders[info[0]]; //이번에 열어야 할 문
            int openedCount = info[1]/10000;    //지금까지 민 횟수
            int openLeft = (info[1]%10000)/100; //열려있는 왼쪽 문
            int openRight = info[1]%100;    //열려있는 오른쪽 문
            
            //왼쪽을 미는 경우
            int count = Math.abs(nextOpen - openLeft);
            caseQ.add(new int [] {info[0]+1, (openedCount + count)*10000 + nextOpen*100 + openRight});
            //오른쪽을 미는 경우우
            count = Math.abs(openRight - nextOpen);
            caseQ.add(new int [] {info[0]+1, (openedCount + count)*10000 + openLeft*100 + nextOpen});
            
        }
    }
}