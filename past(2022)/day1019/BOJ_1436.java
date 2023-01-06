package day1019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1436 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		int count = 0;
		for (int num = 666;; num += 1) {
			String temp = Integer.toString(num);
			if(temp.contains("666")) {
				count++;
				if(count == N) {
					write.write(num + "\n");
					write.close();
					return;
				}
			}
		}
	}
}