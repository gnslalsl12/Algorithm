package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3040 {
	static int [] Hobits;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		Hobits = new int [9];
		for(int i = 0; i < 9 ; i++) {
			Hobits[i] = Integer.parseInt(read.readLine());
		}
		Selected = new boolean[9];
		Comb(0,0,0);
	}
	static boolean [] Selected; 
	public static void Comb(int countnow, int index, int Sum) {
		if(countnow == 7) {
			if(Sum == 100) {
				for(int j = 0; j < 9 ; j++) {
					if(Selected[j]) {
						System.out.println(Hobits[j]);
					}
				}
			}
			return;
		}
		for(int i = index; i < 9; i++) {
			Sum += Hobits[i];
			Selected[i] = true;
			Comb(countnow+1, i+1,Sum);
			Sum -= Hobits[i];
			Selected[i] = false;
		}
	}
}