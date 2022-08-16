package day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JO_1828 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		ArrayList<ChemiTemp> ChList = new ArrayList<>();
		for(int i = 0; i < N ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			ChList.add(new ChemiTemp(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		int count = 1;
		Collections.sort(ChList);
		int lowP = ChList.get(0).low;
		int highP = ChList.get(0).high;
		for(int i = 1; i < N ; i++) {
			if(highP >= ChList.get(i).low) {
				lowP = ChList.get(i).low;
			}
			else { 	//현재 high륿 벗어난 low
				count++;
				highP = ChList.get(i).high;
				lowP = ChList.get(i).low;
			}
		}
		System.out.println(count);
	}

}

class ChemiTemp implements Comparable<ChemiTemp>{
	int low;
	int high;
	ChemiTemp(int low, int high) {
		this.low = low;
		this.high = high;
	}
	@Override
	public int compareTo(ChemiTemp o) {
		return this.low == o.low ? this.high-o.high : this.low - o.low; 
	}
}