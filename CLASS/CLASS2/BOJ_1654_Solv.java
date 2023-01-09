package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1654_Solv {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(tokens.nextToken());
		long K = Long.parseLong(tokens.nextToken());
		ArrayList<Integer> Lines = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			Lines.add(Integer.parseInt(read.readLine()));
		}
		Collections.sort(Lines);
		long min = 1;
		long max = Lines.get(Lines.size()-1);
		
		while(true) {
			long templen = (min + max)/2;
			if(min > max) {
				write.write(templen + "\n");
				break;
			}
			long count = 0;
			for(Integer pop : Lines) {
				count += pop/templen;
			}
			if(count < K) {	//길이 줄여야함
				max = templen-1;
			}else if(count >= K) {	//길이 늘려도됨
				min = templen+1;
			}
		}
		write.close();
	}
}