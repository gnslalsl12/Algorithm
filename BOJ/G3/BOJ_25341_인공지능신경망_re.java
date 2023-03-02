package G3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_25341_인공지능신경망_re {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int Q = Integer.parseInt(tokens.nextToken());
		int[] Inputs = new int[N + 1];
		Queue<int[]> tempQ = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			String[] tempString = read.readLine().split(" ");
			int count = Integer.parseInt(tempString[0]);
			for (int j = 1; j <= count; j++) {
				tempQ.add(new int[] { Integer.parseInt(tempString[j]), Integer.parseInt(tempString[j + count]) });
			}
			tempQ.add(new int[] { -1, Integer.parseInt(tempString[count * 2 + 1]) });
		}
		tokens = new StringTokenizer(read.readLine());
		int pow = Integer.parseInt(tokens.nextToken());
		long tempresult = 0;
		while (!tempQ.isEmpty()) {
			int[] pop = tempQ.poll();
			if (pop[0] == -1) {
				tempresult += pop[1] * pow;
				pow = Integer.parseInt(tokens.nextToken());
				continue;
			}
			Inputs[pop[0]] += pop[1] * pow;
		}
		tempresult += pow;
		for (int i = 0; i < Q; i++) {
			long result = tempresult;
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= N; j++) {
				result += Inputs[j] * Integer.parseInt(tokens.nextToken());
			}
			write.write(result + "\n");
		}
		write.close();
		read.close();
	}

}
