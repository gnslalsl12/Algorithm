package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1764 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<String, Boolean> Dict = new HashMap<String, Boolean>();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int D = Integer.parseInt(tokens.nextToken());
		int B = Integer.parseInt(tokens.nextToken());

		for (int i = 0; i < D; i++) {
			Dict.put(read.readLine(), true);
		}
		PriorityQueue<String> pq = new PriorityQueue<>();
		for (int i = 0; i < B; i++) {
			String temp = read.readLine();
			if (Dict.containsKey(temp)) {
				pq.add(temp);
			}
		}
		write.write(pq.size() + "\n");
		while (!pq.isEmpty()) {
			write.write(pq.poll() + "\n");
		}
		read.close();
		write.close();
	}
}
