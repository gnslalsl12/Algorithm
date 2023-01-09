package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9498 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		if(N >= 90) write.write("A");
		else if(N >= 80) write.write("B");
		else if(N >= 70) write.write("C");
		else if(N >= 60) write.write("D");
		else write.write("F");
		write.write("\n");
		write.close();
	}
}