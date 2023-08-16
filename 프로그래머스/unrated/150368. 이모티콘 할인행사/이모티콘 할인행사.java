class Solution {
   static int[][] Disct;
	    static int[] Answer;
	    static boolean[] Visits;
	    static int[] IndexList;
	    
	        public int[] solution(int[][] users, int[] emoticons) {
	        Answer = new int[2];
	        init(emoticons.length);
	        dfs(users,emoticons, 0, emoticons.length);
	        
	        return Answer;
	    }
	    
	    public void init(int N) {
	        int dc = 40;
	        Disct = new int[4][N];
	        Visits = new boolean[N];
	        IndexList = new int[N + 1];        
	        for(int i = 0; i < 4; i++, dc -= 10)
	            for(int j = 0; j < N; j++)
	                Disct[i][j] = dc;

	        for(int i = 0; i < IndexList.length; i++)
	            IndexList[i] = 0;
	    }
	    public void dfs(int[][] users, int[] emoticons,int start, int N) {
	        if(start == N) {
	            int join = 0;
	            int sell = 0;
	            int sum = 0;
	            for(int i = 0; i < users.length; i++) {
	                sum = 0;
	                for(int j = 0; j < N; j++) {
	                    if(users[i][0] <= Disct[IndexList[j]][j]) {
	                        int price = emoticons[j] - emoticons[j] * Disct[IndexList[j]][j] / 100;
	                        sum += price;
	                    }
	                }
	                if(sum >= users[i][1])
	                    join++;
	                else
	                    sell += sum;
	            }
	            if(join > Answer[0]) {
	                Answer[0] = join;
	                Answer[1] = sell;
	            }
	            else if(join == Answer[0] && Answer[1] < sell)
	                Answer[1] = sell;
	            return ;
	        }
	        
	        for(int i = 0; i < 4; IndexList[start]++,i++) {
	            IndexList[start + 1] = 0;
	            dfs(users, emoticons, start + 1, N);
	        }
	    }
}