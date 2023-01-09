package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2884 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int h = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		if (m < 45) {
			m += 60;
			h--;
			if(h < 0) h += 24;
		}
		write.write(h + " " + (m - 45) + "\n");
		write.close();
	}
}