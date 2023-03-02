package G5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2096_내려가기 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int[] MapMin = new int[3];
		int[] MapMax = new int[3];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int m1 = Integer.parseInt(tokens.nextToken());
			int m2 = Integer.parseInt(tokens.nextToken());
			int m3 = Integer.parseInt(tokens.nextToken());
			int temp1 = m1 + Math.min(MapMin[0], MapMin[1]);
			int temp2 = m2 + Math.min(Math.min(MapMin[0], MapMin[1]), MapMin[2]);
			int temp3 = m3 + Math.min(MapMin[1], MapMin[2]);
			MapMin[0] = temp1;
			MapMin[1] = temp2;
			MapMin[2] = temp3;
			temp1 = m1 + Math.max(MapMax[0], MapMax[1]);
			temp2 = m2 + Math.max(Math.max(MapMax[0], MapMax[1]), MapMax[2]);
			temp3 = m3 + Math.max(MapMax[1], MapMax[2]);
			MapMax[0] = temp1;
			MapMax[1] = temp2;
			MapMax[2] = temp3;
		}
		int min = Math.min(Math.min(MapMin[0], MapMin[1]), MapMin[2]);
		int max = Math.max(Math.max(MapMax[0], MapMax[1]), MapMax[2]);
		write.write(max + " " + min + "\n");
		write.close();
		read.close();
	}

}
