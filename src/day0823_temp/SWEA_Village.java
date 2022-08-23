package day0823_temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_Village {
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			make();
			
			HashSet<Integer> resultcount = new HashSet<>();
			for(int i = 0; i < M; i++) {
				tokens = new StringTokenizer(read.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
					union(a,b);	//작은 놈이 앞으로
//					System.out.println("==========");
//					for(int ii = 0; ii <= N; ii++) {
//						
//						System.out.println(ii + "의 부모 : " + parents[ii]);
//					}
//					System.out.println("==========");
			}
			
			boolean [] group = new boolean[N+1];
			int cnt = 0;
			for(int n=1; n<=N; n++) {
				int p = find(n);
				if(!group[p]) {
					group[p] = true;
					cnt++;
				}
			}
			
//			for(int i : parents) {
//				resultcount.add(i);
//			}
//			int result = resultcount.size()-1;
			sb.append("#" + test + " " + cnt + "\n");
		}
		System.out.print(sb);
		
		
		
	}
	static int [] parents;
	static void make() {
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static void union(int a, int b) {
		int af = find(a);
		int bf = find(b);
		if(af == bf) return;
		parents[bf] = af;	//b의 대표자(find(b)) 의 부모를 a의 대표자로
		return;
	}
}
