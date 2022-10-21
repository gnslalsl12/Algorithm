package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2439 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		for(int i = 1; i <= N; i++) {
			for(int j = N; j > i; j--) {
				write.write(" ");
			}
			for(int k = i; k >0; k--) {
				write.write("*");
			}
			write.write("\n");
		}
		write.close();
	}
}