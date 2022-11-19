package CodePractice;

import java.util.HashSet;
import java.util.Set;

public class test1119 {

	public static void main(String[] args) {
		Set<int[]> tag = new HashSet<int[]>();
		int[] t = { 1, 2, 0, 3 };
		tag.add(t);
		int ht = t.hashCode();
		int[] k = {1,2,0,3};
		int kt = k.hashCode();
		System.out.println(ht + ", " + kt);
		System.out.println(tag.contains(k));
	}

}
