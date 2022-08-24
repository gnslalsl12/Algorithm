package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1976 {
	static int N, M, adjMatrix[][];
	static StringTokenizer tokens;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		M = Integer.parseInt(read.readLine());
		adjMatrix = new int [N][N];
		for(int i = 0; i < N ; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N ; j++) {
				if(i >= j) {
					tokens.nextToken();
					continue;
				}
				if(Integer.parseInt(tokens.nextToken()) == 1) {
					adjMatrix[i][j] = adjMatrix[j][i] = 1;	//무향그래프임
				}
			}
		}
		tokens = new StringTokenizer(read.readLine());
		int first = Integer.parseInt(tokens.nextToken())-1;
		String result = "NO";
		while(tokens.hasMoreTokens()) {
			int second = Integer.parseInt(tokens.nextToken())-1;
			if(DFS(first,second)) {
				result = "YES";
				first = second;
				continue;
			}else {
				result = "NO";
				break;
			}
		}
		if(M == 1) result = "YES";
		System.out.println(result.toString());
	}
	static boolean DFS(int start, int dest) {
		Stack<Integer> DFSstack = new Stack<>();
		boolean[] visited = new boolean[N];
		visited[start] = true;
		DFSstack.push(start);
		while(!DFSstack.isEmpty()) {
			int now = DFSstack.pop();
			if(now == dest) {
				return true;
			}
			for(int i = 0; i < N ; i++) {
				if(visited[i] == false && adjMatrix[now][i] == 1) {
					visited[i] = true;
					DFSstack.push(i);
				}
			}
			
		}
		return false;
	}
}