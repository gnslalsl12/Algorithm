package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp = read.readLine();
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < temp.length();j++) {
				if(temp.charAt(j) - 'a' == i) {
					write.write((j) + " " );
					break;
				}
				if(j == temp.length()-1) write.write(-1 + " ");
			}
		}
		write.write("\n");
		write.close();
	}
}