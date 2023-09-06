import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int K;
    static Queue<int[]> ItemList;
    static int [] DPBag;
    static long Result;

    public static void main(String[] args)throws IOException
    {
        init();
        solv();
    }

    private static void init() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        ItemList = new LinkedList<>();
        for(int n = 0; n < N; n++){
            tokens = new StringTokenizer(read.readLine());
            ItemList.add(new int [] {Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())});
        }
        DPBag = new int [K+1];
        Result = 0L;
        read.close();
    }

    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getDPBagStack();
        write.write(Result + "\n");
        write.close();
    }

    private static void getDPBagStack(){
        while(!ItemList.isEmpty()){
            int [] item = ItemList.poll();
            for(int k = K; k >= item[0]; k--){  //고려하는 배낭 무게가 item의 무게를 버틸 수 있는 경우만 탐색
                if(DPBag[k] < DPBag[k-item[0]] + item[1]) DPBag[k] = DPBag[k-item[0]] + item[1];
                //이전까지 본 모든 item을 고려한 경우들 중 이번 item을 넣을 수 있는 배낭 상태들 중에서 이번 item 가치를 더헀을 때가 더 가치가 높다면 그걸로 기록
            }
        }
        Result = DPBag[K];
    }

}