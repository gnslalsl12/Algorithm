package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2562 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int index = 0;
		for (int i = 1; i <= 9; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (max < temp) {
				max = temp;
				index = i;
			}
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(max + "\n" + index + "\n");
		write.close();
	}
}