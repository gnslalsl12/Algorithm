import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int K;
    static int[][] ItemList;
    static int [][] DPBag;
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
        ItemList = new int [N][2];
        for(int n = 0; n < N; n++){
            tokens = new StringTokenizer(read.readLine());
            ItemList[n] = new int [] {Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())};
        }
        DPBag = new int [N+1][K+1];
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
        for(int n = 0; n < N; n++){    //n번 아이템까지 고려했을 때
            for(int k= 1; k <= K; k++){ //들어갈 수 있는 무게를 k라고 한다면
                if(n == 0 && k >= ItemList[0][0]){
                    DPBag[n][k] = ItemList[0][1];
                    continue;
                }
                if(k >= ItemList[n][0]){    //n 아이템을 넣을 수 있는 배낭 무게라면
                    DPBag[n][k] = Math.max(DPBag[n>= 1? n-1 : 0][k], DPBag[n>= 1? n-1 : 0][k-ItemList[n][0]] + ItemList[n][1]);
                }else{  //아니라면면
                    DPBag[n][k] = DPBag[n>= 1? n-1 : 0][k];   
                }
            }
        }
        Result = DPBag[N-1][K];
    }

}