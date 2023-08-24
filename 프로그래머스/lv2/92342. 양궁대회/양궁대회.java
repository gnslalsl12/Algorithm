import java.util.*;

class Solution {
    static int N;
    static int[] Info;
    static int[] Answer;    
    static int Gap;
    static Queue<Integer> SubSets;
    
    public int[] solution(int n, int[] info) {
        N = n;
        Info = info;
        Answer = new int [] {-1};
        Gap = 0;
        SubSets = new LinkedList<>();
        solv();
        return Answer;
    }
    
    private static void solv(){
        getSubSetCases(0, 0);
        while(!SubSets.isEmpty())
        getWholeCase(SubSets.poll());
    }
    
    private static void getSubSetCases(int index, int vis){  //비트마스킹 활용 부분집합
        if(index == 11){
            int tempArrowCount = 0;
            for(int i = 0; i <= 10; i++){   //갖고있는 화살 수로 감당 하능한 케이스인지 확인 (어피치보다 하나씩 더 쏘기 가능한지)
                if((vis & (1<<i)) != 0) tempArrowCount += Info[i] + 1;
            }
            if(tempArrowCount <= N)
                SubSets.add(vis);
            return;
        }
        vis &= ~(1<<index);
        getSubSetCases(index+1, vis);
        vis |= 1<<index;
        getSubSetCases(index+1, vis);
    }
    
    private static void getWholeCase(int subsetCase){
        int remain = N;
        int[] checked = new int [11];
        int ryan = 0;
        int apeach = 0;
        
        for(int i = 0; i <= 10; i++){
            if((subsetCase & (1<<i)) != 0){ //라이언이 해당 점수를 가져가는 경우
                remain -= Info[i] + 1;  //어피치보다 하나 더 쏨
                ryan += (10-i);
                checked[i] = Info[i] + 1;
            }
            else if(Info[i] != 0) apeach += (10-i); //어피치가 점수를 가져가는 경우
        }
        //세팅 끝  //라이언 점수, 어피치 점수, 라이언 과녁 상태, 남은 화살 수 기록돼있음
        if(ryan > apeach){  //라이언이 이긴 경우만 보기
            
            checked[10] += remain;  //남은 화살이 있으면 0점에 꼬라박기
            
            if(Answer.length == 1){ //처음 갱신
                Answer = new int [11];
                Answer = checked;
                Gap = ryan - apeach;
            }else{
                if(Gap < ryan - apeach){    //점수차가 더 큰 걸로 갱신
                    Answer = checked;
                    Gap = ryan - apeach;
                }else if(Gap == ryan - apeach){ //점수차가 같을 때
                    for(int i = 10; i >= 0; i--){   //낮은 점수를 더 많이 쏜 경우에만 갱신
                        int aLow = Answer[i];
                        int cLow = checked[i];
                        if(aLow > cLow) return; //원래 경우가 낮은 점수에 더 많이 쏨 => X
                        else if(cLow > aLow){   //현재 경우가 낮은 점수에 더 많이 쏨 => O
                            Answer = checked;
                            Gap = ryan - apeach;
                            return;
                        }
                    }
                }
            }
        }
        
    }
}