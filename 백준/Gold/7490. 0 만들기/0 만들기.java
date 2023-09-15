import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int N;
    static BufferedWriter write;
    
    public static void main(String args[]) throws IOException{
        init();
    }
    
    private static void init() throws IOException{
        write = new BufferedWriter(new OutputStreamWriter(System.out));
        T = readInt();
        while(T-- > 0){
            solv(readInt());
            if(T != 0) write.write("\n");
        }
        write.close();
    }
    
    private static int readInt() throws IOException{
        int n, c;
        boolean negative = false;
        do{
            n = System.in.read();
            if(n == 45) negative = true;
        }while(n <= 45);
        n &= 15;
        while((c = System.in.read()) > 45) n = (n<<3) + (n<<1) + (c&15);
        return negative? -n : n;
    }
    
    private static void solv(int n) throws IOException{
        N = n;
        getWholeCases();
    }
    
    private static void getWholeCases()throws IOException{
        getCase(0, 0);
    }
    
    private static void getCase(int count, int calcs)throws IOException{
        if(count == N-1){
            isSumZero(calcs);
            return;
        }
        getCase(count+1, calcs*10);    //공백
        getCase(count+1, calcs*10 + 1);  //+
        getCase(count+1, calcs*10 + 2);    //-
    }
    
    private static void isSumZero(int calc) throws IOException{
        String result = "1";
        for(int i = N-2; i >= 0; i--){
            int c = (calc/(int)Math.pow(10, i))%10;
            if(c == 0){
                result += " ";
            }else if(c == 1){
                result += "+";
            }else{
                result += "-";
            }
            result += Integer.toString(N-i);
        }
        int calcIndex = 1;
        int nextNum = 2;
        int sum = 0;
        int temp = 1;
        boolean pastPlus = true;
        while(true){
            char c= result.charAt(calcIndex);
            if(c != ' '){
                sum += pastPlus? temp : -temp;
                pastPlus = c == '+';
                temp = nextNum;
            }else{
                temp = temp*10 + nextNum;
            }
            if(nextNum == N){
                sum += pastPlus? temp : -temp;
                break;
            }
            nextNum++;
            calcIndex+=2;
        }
        if(sum == 0) write.write(result + "\n");
       
    }
    
}