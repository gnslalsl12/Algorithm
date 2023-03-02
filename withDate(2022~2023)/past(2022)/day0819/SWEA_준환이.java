package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_준환이 {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int [] Choo;
	static int Totalsum;
	static ArrayList<ArrayList<Integer>> OriginalPerm;
	static boolean[] Selected;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(read.readLine());
		for(int test = 1; test <= T ; test++) {
			N = Integer.parseInt(read.readLine());
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			Choo = new int [N];
			count = 0;
			int number = 0;
			for(int i = 0; i < N ; i++){
				number = Integer.parseInt(tokens.nextToken());
				Choo[i] = number;
			}
			Perm(0, new int[N], new boolean[N]);
			System.out.println("#" + test + " " +count);
		}
	}
	
	private static void Perm(int countnow, int [] result, boolean [] visited) {
		if(countnow == N) {
			Makeresult(result, 0, 0, 0);
			return;
		}
		
		for(int i = 0; i < N ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[countnow] = Choo[i];
				Perm(countnow+1, result, visited);
				visited[i] = false;
			}
		}
	}
	
	private static void Makeresult(int [] Origin, int leftsum, int rightsum, int countnow) {
		if(leftsum < rightsum) return;
		if(countnow == N) {
			count++;
			return;
		}
		Makeresult(Origin, leftsum + Origin[countnow],rightsum,countnow+1);
		Makeresult(Origin, leftsum,rightsum + Origin[countnow],countnow+1);
	}
}