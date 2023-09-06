import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Comparator<int[]> Comp;
    static PriorityQueue<int[]> ClassList;
    static int Result;
    
    public static void main(String[] args) throws IOException{
        init();
        solv();
    }
    
    private static void init()throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        Comp = new Comparator<int[]>(){
            @Override
            public int compare(int[] x1, int[] x2){
                return x1[0]==x2[0]? Integer.compare(x1[1], x2[1]) : Integer.compare(x1[0],x2[0]);
            }
        };
        ClassList = new PriorityQueue<>(Comp);
        while(N-- > 0){
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            ClassList.add(new int [] {Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())});
        }
        Result = 0;
        read.close();
    }
    
    private static void solv() throws IOException{
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
        getClassRoomCount();
        write.write(Result + "\n");
        write.close();
    }
    
    private static void getClassRoomCount(){
        PriorityQueue<Integer> endTimeList = new PriorityQueue<>();
        endTimeList.add(0);
        //=> 가장 빨리 끝나는 하나의 강의실로도 커버가 안되면 뒤에 강의실도 다 안되는 거니까
        while(!ClassList.isEmpty()){
            int earliestEndTime = endTimeList.peek();   //가장 빨리 끝나는 강의실 시간
            int [] nextClass = ClassList.poll();    //다음 강의의
            if(earliestEndTime <= nextClass[0]){ //가장 빨리 끝나는 강의 다음으로 붙일 수 있음
                endTimeList.poll(); //해당 강의실 시간 없애기
            }
            endTimeList.add(nextClass[1]);//새로운 강의 끝시간 추가
        }
        Result = endTimeList.size();
    }
}