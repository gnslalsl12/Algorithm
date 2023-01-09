package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10950 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		while(true) {
			String temp = read.readLine();
			if(temp == null || temp.equals("")) break;
			tokens = new StringTokenizer(temp);
			write.write((Integer.parseInt(tokens.nextToken()) + Integer.parseInt(tokens.nextToken())) + "\n");
		}
		write.close();
	}
}