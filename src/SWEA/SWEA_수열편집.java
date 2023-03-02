package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_수열편집 {
	static int N, M, L;
	static ArrayList<Integer> Codes;
	static int IndexX, Value;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			Codes = new ArrayList<>();
			tokens = new StringTokenizer(read.readLine());
			for (int i = 0; i < N; i++) {
				Codes.add(Integer.parseInt(tokens.nextToken()));
			}
			for (int i = 0; i < M; i++) {
				tokens = new StringTokenizer(read.readLine());
				char type = tokens.nextToken().charAt(0);
				IndexX = Integer.parseInt(tokens.nextToken());
				if (type != 'D') {
					Value = Integer.parseInt(tokens.nextToken());
				}
				solvByStep(type);
			}
			if (Codes.size() < L) {
				write.write("#" + test + " -1\n");
			} else {
				write.write("#" + test + " " + Codes.get(L) + "\n");
			}
		}
		write.close();
		read.close();

	}

	private static void solvByStep(char type) {
		switch (type) {
		case ('I'): {
			doInsert();
			break;
		}
		case ('D'): {
			doDelete();
			break;
		}
		case ('C'): {
			doChange();
			break;
		}
		}
	}

	private static void doInsert() {
		Codes.add(IndexX , Value);
	}

	private static void doDelete() {
		Codes.remove(IndexX);
	}

	private static void doChange() {
		Codes.set(IndexX, Value);
	}
}
