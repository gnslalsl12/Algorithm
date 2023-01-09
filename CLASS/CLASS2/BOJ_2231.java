package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2231 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		String templine = read.readLine();
		int N = Integer.parseInt(templine);
		for (int i = N - (9 * templine.length()); i < N; i++) {
			int tempN = i;
			String temptemp = Integer.toString(tempN);
			for(int j = 0; j < temptemp.length(); j++) {
				tempN += (temptemp.charAt(j) - '0');
			}
			if(tempN == N) {
				write.write(i+"\n");
				break;
			}
			if(i == N-1) {
				write.write(0+"\n");
			}
		}
		write.close();
	}
}