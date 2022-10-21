package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_8958 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		for(int i = 0; i < N; i++) {
			String temp = read.readLine();
			int result = 0;
			int count = 0;
			for(int j = 0; j < temp.length(); j++) {
				if(temp.charAt(j) == 'O') {
					count++;
					result += count;
				}
				else {
					count = 0;
				}
			}
			write.write(result + "\n");
		}
		write.close();
	}
}