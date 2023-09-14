import java.io.*;

public class Main {
    static int N;
    static int [] Bottles;
    static int [] Result;
    
    public static void main(String[] args) throws IOException{
        init();
        solv();
    }
    
    private static void init() throws IOException{
        N = readInt();
        Bottles = new int [N];
        for(int n = 0; n < N; n++){
            Bottles[n] = readInt();
        }
        Result = new int [2];
    }
    
    private static int readInt() throws IOException{
        int n, c;
        boolean negative = false;
        do{
            n = System.in.read();
            if(n == 45) negative = true;
        } while(n <= 45);
        n &= 15;
        while((c = System.in.read()) > 45) n = (n<<3) + (n<<1) + (c&15);
        return negative? -n : n;
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getResult();
        write.write(Result[0] + " " + Result[1] + "\n");
        write.close();
    }
    
    private static void getResult(){
        int left = 0;
        int right =  N-1;
        int diff;
        int min = Integer.MAX_VALUE;
        while(left < right){
            diff = Bottles[left] + Bottles[right];
            if(min > Math.abs(diff)){
                min = Math.abs(diff);
                Result[0] = Bottles[left];
                Result[1] = Bottles[right];
            }
            if(diff == 0){
                return;
            }else if(diff > 0){ //양수를 줄여줘야함
                right--;
            }else if(diff < 0){ //음수를 높여야함
                left++;
            }
        }
    }
}