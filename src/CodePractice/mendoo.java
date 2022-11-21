package CodePractice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class mendoo {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int i = 0; i < T; i++) {
			String[] temp = read.readLine().split(",");
			write.write((Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]) + "\n"));
		}
		write.close();
	}
}