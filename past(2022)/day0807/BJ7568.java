package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ7568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		MK test = new MK();
		ArrayList<MK> tempMK = new ArrayList<>(n);
//		test.setWL(0, 0);
		for(int i = 0; i < n ; i++) {
			StringTokenizer tokens= new StringTokenizer(read.readLine());
			test.setWL(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			tempMK.add(test.getWL());
		}
		
		int[] count = new int[n];
		for(int i = 0; i < n; i++) {
			count[i] = 1;
			for(int j = 0; j < n; j ++) {
				count[i] += tempMK.get(i).compareTo(tempMK.get(j));
			}
			System.out.println(count[i]);
		}
		
		
		
	}
	
	public static class MK implements Comparable<MK>{
		int weight;
		int length;
		public MK(int weight, int length) {
			super();
			this.weight = weight;
			this.length = length;
		}
		
		public MK() {
			super();
		}
		
		public void setWL(int weight, int length) {
			this.weight = weight;
			this.length = length;
		}
		public MK getWL() {
//			MK t = new MK(this.weight, this.length);
//			return t;
			return new MK(this.weight, this.length);
		}

		@Override
		public int compareTo(MK o) {
			if(this.weight < o.weight && this.length < o.length) {
				return 1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "MK [weight=" + weight + ", length=" + length + "]";
		}
		
		
		
		
	}

}
