class Solution {
    static char[][][] Map;
    static int [][] Deltas;
    static int [] Answer;
    
    public int[] solution(String[][] places) {
        Map = new char [5][5][5];
        for(int room = 0; room < 5; room++){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    Map[room][i][j] = places[room][i].charAt(j);
                }
            }
        }
        Deltas = new int [][] {{-1,0},{0,1},{1,0},{0,-1}};
        Answer = new int [5];
        solv();
        return Answer;
    }
    
    private static void solv(){
        for(int room = 0; room < 5; room++){
            Answer[room] = getSearch(Map[room])? 1 : 0;
        }
    }
    
    private static boolean getSearch(char[][] room){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(room[i][j] == 'P'){
                    for(int dir = 0; dir < 4; dir++){
                        //거리 1
                        int nextI = i + Deltas[dir][0];
                        int nextJ = j + Deltas[dir][1];
                        if(!isIn(nextI,nextJ)) continue;
                        if(room[nextI][nextJ] == 'P') return false; //바로 옆 응시자
                        else if(room[nextI][nextJ] == 'O'){ //바로 옆 빈 공간
                            for(int addDir = 0; addDir < 2; addDir++){
                                int nextDir = (dir + addDir)%4;
                                int nextNextI = nextI + Deltas[nextDir][0];
                                int nextNextJ = nextJ + Deltas[nextDir][1];
                                if(!isIn(nextNextI, nextNextJ)) continue;
                                if(room[nextNextI][nextNextJ] == 'P') return false; //빈공간 옆 응시자
                            }
                        } 
                    }
                    
                    
                }
            }
        }
        return true;
    }
    
    private static boolean isIn(int i , int j){
        return i >= 0 && i < 5 && j >= 0 && j < 5;
    }
}