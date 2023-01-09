
package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1330 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int A = Integer.parseInt(tokens.nextToken());
		int B =Integer.parseInt(tokens.nextToken());
		if(A > B) write.write(">\n");
		else if(A < B) write.write("<\n");
		else write.write("==\n");
		write.close();
	}
}