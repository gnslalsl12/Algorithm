package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Map<String, Integer> PokeDict = new HashMap<>();
		Map<Integer, String> PokeDictIndex = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			String poke = read.readLine();
			PokeDict.put(poke, i);
			PokeDictIndex.put(i, poke);
		}
		for(int i = 0; i < M; i++) {
			String templine = read.readLine();
			if(templine.charAt(0) - '0' >= 0 && templine.charAt(0) - '0' < 10) {
				write.write(PokeDictIndex.get(Integer.parseInt(templine)) + "\n");
			}else {
				write.write(PokeDict.get(templine) + "\n");
			}
		}
		read.close();
		write.close();
	}
}
