import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static PriorityQueue<convertInt> Bottles;
    static int[] Result;
    
    public static void main(String args[]) throws IOException{
     init();
     solv();
    }
    
    private static void init() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        Bottles = new PriorityQueue<>();
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        for(int n = 0; n < N; n++){
            Bottles.add(new convertInt(Integer.parseInt(tokens.nextToken())));
        }
        Result = new int [2];
        read.close();
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getResult();
        write.write(Result[0] + " " + Result[1] + "\n");
        write.close();
    }
    
    private static void getResult(){
        int min = Integer.MAX_VALUE;
        int valueBefore = 0;
        int valueAfter = Bottles.poll().num;
        while(!Bottles.isEmpty()){
            valueBefore = valueAfter;
            valueAfter = Bottles.poll().num;
            if(min > Math.abs(valueBefore + valueAfter)){
                min = Math.abs(valueBefore + valueAfter);
                if(valueBefore < valueAfter){
                    Result[0] = valueBefore;
                    Result[1] = valueAfter;
                }else{
                    Result[0] = valueAfter;
                    Result[1] = valueBefore;
                }
                if(min == 0) return;
            }
        }
    }
    
    private static class convertInt implements Comparable<convertInt>{
        int num;
        
        public convertInt(int num){
            this.num = num;
        }
        
        @Override
        public int compareTo(convertInt input){
            return Math.abs(num) - Math.abs(input.num);
        }
    }

}