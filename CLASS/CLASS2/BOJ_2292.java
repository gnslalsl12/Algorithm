package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2292 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		long N = Long.parseLong(read.readLine());
		if(N == 1)write.write(1 + "\n");
		else {
			long count =1;
			int shell = 1;
			while(true) {
				count += 6*shell++;
				if(count >= N) {
					write.write(shell + "\n");
					break;
				}
			}
		}
		write.close();
	}
}