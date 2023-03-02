package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_¾ÏÈ£¹®3 {
	static int N, M;
	static ArrayList<Integer> Codes;
	static int IndexX, CountY;
	static Stack<Integer> InputStack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		for (int test = 1; test <= 10; test++) {
			N = Integer.parseInt(read.readLine());
			Codes = new ArrayList<>();
			tokens = new StringTokenizer(read.readLine());
			for (int i = 0; i < N; i++) {
				Codes.add(Integer.parseInt(tokens.nextToken()));
			}
			M = Integer.parseInt(read.readLine());
			tokens = new StringTokenizer(read.readLine());
			for (int i = 0; i < M; i++) {
				char type = tokens.nextToken().charAt(0);
				if (type == 'I') {
					IndexX = Integer.parseInt(tokens.nextToken());
					CountY = Integer.parseInt(tokens.nextToken());
					InputStack = new Stack<>();
					for (int j = 0; j < CountY; j++) {
						InputStack.add(Integer.parseInt(tokens.nextToken()));
					}
				} else if (type == 'D') {
					IndexX = Integer.parseInt(tokens.nextToken());
					CountY = Integer.parseInt(tokens.nextToken());
				} else {
					CountY = Integer.parseInt(tokens.nextToken());
					InputStack = new Stack<>();
					for (int j = 0; j < CountY; j++) {
						InputStack.add(Integer.parseInt(tokens.nextToken()));
					}
				}
				solvBySteps(type);
			}
			write.write("#" + test);
			for (int i = 0; i < 10; i++) {
				write.write(" " + Codes.get(i));
			}
			write.write("\n");
		}
		write.close();
	}

	private static void solvBySteps(char type) {
		switch (type) {
		case ('I'): {
			doInsert();
			break;
		}
		case ('D'): {
			doDelete();
			break;
		}
		case ('A'): {
			doAdd();
			break;
		}
		}
	}

	private static void doInsert() {
		while (!InputStack.isEmpty()) {
			Codes.add(IndexX, InputStack.pop());
		}
	}

	private static void doDelete() {
		while (CountY-- > 0) {
			Codes.remove(IndexX);
		}
	}

	private static void doAdd() {
		while (!InputStack.isEmpty()) {
			Codes.add(InputStack.pop());
		}
	}

}
