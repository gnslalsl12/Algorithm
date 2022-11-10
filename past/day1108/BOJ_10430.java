package day1108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10430 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int A = Integer.parseInt(tokens.nextToken());
		int B = Integer.parseInt(tokens.nextToken());
		int C = Integer.parseInt(tokens.nextToken());
		long result = (A + B) % C;
		write.write(result + "\n");
		result = ((A % C) + (B % C)) % C;
		write.write(result + "\n");
		result = (A * B) % C;
		write.write(result + "\n");
		result = ((A % C) * (B % C)) % C;
		write.write(result + "\n");
		write.close();
	}

}
