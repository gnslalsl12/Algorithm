package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_3052 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean[] check = new boolean[42];
		for (int i = 0; i < 10; i++) {
			check[Integer.parseInt(read.readLine()) % 42] = true;
		}
		int count = 0;
		for (int i = 0; i < 42; i++) {
			if (check[i])
				count++;
		}
		write.write(count + "\n");
		write.close();
	}
}