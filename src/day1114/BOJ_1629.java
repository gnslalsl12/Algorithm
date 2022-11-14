package day1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1629 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		long A = Integer.parseInt(tokens.nextToken());
		long B = Integer.parseInt(tokens.nextToken());
		long C = Integer.parseInt(tokens.nextToken());
		ArrayList<Long> Mods = new ArrayList<>();
		long tempresult = A;
		tempresult *= A;
		while (true) {
			tempresult *= A;
			if (Mods.contains(tempresult%C)) {
				break;
			} else {
				Mods.add(tempresult % C);
			}
		}
		System.out.println(Mods.get((int)B%Mods.size()));
	}

}
