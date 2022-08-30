package day0831;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		Perm(0,0,new boolean[6]);
		System.out.println(PermBranches.toString());
		System.out.println(PermBranches.size());
	}
	
	static ArrayList<Integer> PermBranches = new ArrayList<>();
	private static void Perm(int countnow, int Sel, boolean [] visited) {
		if(countnow == 5) {
			PermBranches.add(Sel);
			return;
		}
		
		for(int i = 1; i <= 5; i++) {
			if(!visited[i]) {
				visited[i] = true;
				Sel += i*((int)Math.pow(10, countnow));
				System.out.println("엥"+Sel);
				Perm(countnow+1, Sel, visited);
				visited[i] = false;
				Sel -= i*((int)Math.pow(10, countnow));
			}
			
		}
		
	}
}
