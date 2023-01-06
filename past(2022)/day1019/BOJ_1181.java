package day1019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		ArrayList<String> WD = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			WD.add(read.readLine());
		}
		Collections.sort(WD, new Comparator<String>(){
			@Override
			public int compare(String l1, String l2) {
				if(l1.length() == l2.length()) {
					return l1.compareTo(l2);
				}
				return Integer.compare(l1.length(), l2.length());
			}
		});
        write.write(WD.get(0)+"\n");
		for(int i = 1; i < WD.size(); i++) {
			System.out.println(WD.get(i));
			if(!WD.get(i-1).equals(WD.get(i))) {
				write.write(WD.get(i) + "\n");
			}
		}
//		write.close();
	}
}