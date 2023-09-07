import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [][] Connects;
    static int [] DP;
    
	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        Connects = new int [N][2];
        DP = new int [N];
        for(int n = 0; n < N; n++){
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            Connects[n][0] = from;
            Connects[n][1] = to;
        }
        Arrays.sort(Connects,new Comparator<int[]>(){
            @Override
            public int compare(int[] x1, int[] x2){
                return x1[0] - x2[0];
            }
        });
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        int maxAvail = 0;
        for(int n = 0; n < N; n++){
            maxAvail = Math.max(maxAvail,getDPTopDown(n));
        }
        write.write((N - maxAvail) + "\n");
		write.close();
	}
	
	private static int getDPTopDown(int topestIndex){
	    //topestIndex의 A번호 아래의 전깃줄 들 중
	    //topestIndex의 B번호 아래에 연결돼있는 전깃줄들을 확인하면서
	    //서로 교차하지 않게 연결할 수 있는 최대값을 DP에 저장
	    int topestB = Connects[topestIndex][1];
	    if(DP[topestIndex] != 0) return DP[topestIndex];
	    DP[topestIndex] = 1;
	    
	    for(int lowerIndex = topestIndex+1; lowerIndex < N; lowerIndex++){
	        int lowerB = Connects[lowerIndex][1];
	        if(topestB < lowerB){
	            DP[topestIndex] = Math.max(DP[topestIndex], getDPTopDown(lowerIndex) + 1);
	        }
	    }
	    return DP[topestIndex];
	}

}