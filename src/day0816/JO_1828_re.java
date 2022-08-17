package day0816;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JO_1828_re {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		PriorityQueue<ChemiTemp2> ChList = new PriorityQueue<>();
		for(int i = 0; i < N ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			ChList.add(new ChemiTemp2(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		int count = 1;
		int finalline = ChList.poll().high;
		while(!ChList.isEmpty()) {
			ChemiTemp2 temp = ChList.poll();
			if(temp.low > finalline) {
				count++;
				finalline = temp.high;
			}
			
		}
		System.out.println(count);
	}
}

class ChemiTemp2 implements Comparable<ChemiTemp2>{
	int low;
	int high;
	ChemiTemp2(int low, int high) {
		this.low = low;
		this.high = high;
	}
	@Override
	public int compareTo(ChemiTemp2 o) {
		return this.high == o.high ? this.low - o.low : this.high - o.high;
	}
}