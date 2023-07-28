import java.util.*;

class Solution {
    
    static int DefaultMin, DefaultFee, UnitMin, UnitFee;
    static HashMap<Integer, int[]> RecordMap;
    public int[] solution(int[] fees, String[] records) {
        
        DefaultMin = fees[0];   //1~1439    //기본 시간
        DefaultFee = fees[1];   //0~100,000 //기본 요금
        UnitMin = fees[2];      //1~1439    //단위시간
        UnitFee = fees[3];      //1~10,000  //단위요금
        RecordMap = new HashMap<>();
        PriorityQueue<Integer> VehNumPQ = new PriorityQueue<>();
        for(int r = 0; r < records.length; r++){
            StringTokenizer tokens = new StringTokenizer(records[r]);
            StringTokenizer timeToken = new StringTokenizer(tokens.nextToken(), ":");
            int time = Integer.parseInt(timeToken.nextToken())*60 + Integer.parseInt(timeToken.nextToken());
            int vehNum = Integer.parseInt(tokens.nextToken());
            if(!RecordMap.containsKey(vehNum)){
                RecordMap.put(vehNum, new int [] {time, 0});
                VehNumPQ.add(vehNum);
            }else {
                int [] vehInfo = RecordMap.get(vehNum);
                if(vehInfo[0] == -1){    //최근이 출차
                    vehInfo[0] = time;
                }else{  //최근이 입차
                    vehInfo[1] += time-vehInfo[0];
                    vehInfo[0] = -1;
                }
            }
        }
        int [] answer = new int [VehNumPQ.size()];
        for(int i = 0; i < answer.length; i++){
            int num = VehNumPQ.poll();
            int [] info = RecordMap.get(num);
            answer[i] = getCalcFee(info);
        }
        return answer;
    }
    
    private static int getCalcFee(int [] info){
        int wholeTime = info[1] - DefaultMin + (info[0] == -1? 0 : (1439 - info[0]));
        if(wholeTime < 0) return DefaultFee;
        int resultFee = DefaultFee + ((wholeTime/UnitMin) + (wholeTime%UnitMin == 0? 0 : 1))*UnitFee;
        return resultFee;
    }
}