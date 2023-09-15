import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int Q;
    static Queue<Integer> QList;
    static int C;
    static int [] CAP;
    static int CurrentPage;
    static int CurrentCache;
    static Deque<Integer> Befores;
    static Stack<Integer> Afters;

    public static void main(String args[]) throws IOException{
        init();
        solv();
    }
    
    private static void init() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        N = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());
        QList = new LinkedList<>();
        C = Integer.parseInt(tokens.nextToken());
        CAP = new int [N+1];
        tokens = new StringTokenizer(read.readLine());
        for(int n = 1; n <= N; n++){
            CAP[n] = Integer.parseInt(tokens.nextToken());
        }
        for(int q = 0; q < Q; q++){
            tokens = new StringTokenizer(read.readLine());
            char c = tokens.nextToken().charAt(0);
            if(c == 'B'){
                QList.add(-1);
            }else if(c == 'F'){
                QList.add(-2);
            }else if(c == 'A'){
                int pageIndex = Integer.parseInt(tokens.nextToken());
                QList.add(pageIndex);
            }else{
                QList.add(-3);
            }
        }
        CurrentPage = -1;
        CurrentCache = 0;
        Befores = new LinkedList<>();
        Afters = new Stack<>();
        read.close();
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        while(!QList.isEmpty()){
            int nextOrder = QList.poll();
            if(nextOrder == -1){
                setBefore();
            }else if(nextOrder == -2){
                setAfter();
            }else if(nextOrder == -3){
                setCompress();
            }else{
                setAccess(nextOrder);
            }
        }
        write.write(CurrentPage + "\n");
        if(Befores.isEmpty()){
            write.write("-1\n");
        }else{
            while(!Befores.isEmpty()){
                write.write(Befores.pollLast() + (Befores.isEmpty()? "\n" : " "));
            }
        }
        if(Afters.isEmpty()){
            write.write("-1\n");
        } else{
            while(!Afters.isEmpty()){
                write.write(Afters.pop() + (Afters.isEmpty()? "\n" : " "));
            }
        }
        write.close();
    }
    
    private static void setBefore(){    //뒤로가기
        if(Befores.isEmpty() || CurrentPage == -1){
            return;
        }
        Afters.add(CurrentPage);
        CurrentPage = Befores.pollLast();
    }
    
    private static void setAfter(){ //앞으로 가기기
        if(Afters.isEmpty() || CurrentPage == -1){
            return;
        }
        Befores.addLast(CurrentPage);
        CurrentPage = Afters.pop();
    }
    
    private static void setCompress(){  //압축축
        if(Befores.isEmpty() || CurrentPage == -1){
            return;
        }
        Deque<Integer> tempDQ = new LinkedList<>();
        int past = -1;
        while(!Befores.isEmpty()){
            int present = Befores.pollFirst();
            if(past == present){
                CurrentCache -= CAP[past];
            }else{
                past = present;
                tempDQ.addLast(past);
            }
        }
        Befores = tempDQ;
    }
    
    private static void setAccess(int newPageIndex){    //접속
        if(CurrentPage == -1){
            CurrentPage = newPageIndex;
            CurrentCache += CAP[CurrentPage];
            return;
        }
        while(!Afters.isEmpty()){
            CurrentCache -= CAP[Afters.pop()];
        }
        Befores.addLast(CurrentPage);
        CurrentPage = newPageIndex;
        CurrentCache += CAP[CurrentPage];
        while(CurrentCache > C){
            CurrentCache -= CAP[Befores.pollFirst()];
        }
    }

}