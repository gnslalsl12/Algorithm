package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1157 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp = read.readLine();
		int[] count = new int[26];
		int max = 0;
		int result = 0;
		for (int i = 0; i < temp.length(); i++) {
			int ascii = temp.charAt(i) - 'A';
			if (ascii > 27) {
				ascii -= 32;
			}
			count[ascii]++;
			if (max < count[ascii]) {
				max = count[ascii];
				result = ascii;
			}
		}
		boolean dup = false;
		for (int i = 0; i < count.length; i++) {
			if (count[i] == max) {
				if (dup) {
					write.write("?\n");
					write.close();
					return;
				}
				dup = true;
			}
		}
		write.write((char) (result + 65) + "\n");
		write.close();
	}
}