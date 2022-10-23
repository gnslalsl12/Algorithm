package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11866 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		Queue<Integer> Puss = new LinkedList<>();
		for(int i = 1; i<= N; i++) {
			Puss.add(i);
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write("<");
		int count = 0;
		while(!Puss.isEmpty()) {
			int temp = Puss.poll();
			count++;
			if(count == K) {
				if(!Puss.isEmpty()) {
					write.write(String.format("%d, ", temp));
				}else {
					write.write(String.format("%d>", temp));
				}
				count = 0;
			}else {
				Puss.add(temp);
			}
		}
		write.write("\n");
		write.close();
	}
}