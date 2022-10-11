package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CodeForce_1722_C {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(read.readLine());
			ArrayList<String> A = new ArrayList<>();
			ArrayList<String> B = new ArrayList<>();
			ArrayList<String> C = new ArrayList<>();
			HashMap<String, Integer> Dict = new HashMap<>();
			int ar = 0;
			int br = 0;
			int cr = 0;
			tokens = new StringTokenizer(read.readLine());
			StringTokenizer tokens2 = new StringTokenizer(read.readLine());
			StringTokenizer tokens3 = new StringTokenizer(read.readLine());
			for (int i = 0; i < N; i++) {
				String a = tokens.nextToken();
				String b = tokens2.nextToken();
				String c = tokens3.nextToken();
				A.add(a);
				B.add(b);
				C.add(c);
				if (!Dict.containsKey(a)) {
					Dict.put(a, 3);
				} else {
					int r = Dict.get(a);
					if (r == 3) {
						Dict.replace(a, 1);
					} else if (r == 1) {
						Dict.replace(a, 0);
					}
				}
				if (!Dict.containsKey(b)) {
					Dict.put(b, 3);
				} else {
					int r = Dict.get(b);
					if (r == 3) {
						Dict.replace(b, 1);
					} else if (r == 1) {
						Dict.replace(b, 0);
					}
				}
				if (!Dict.containsKey(c)) {
					Dict.put(c, 3);
				} else {
					int r = Dict.get(c);
					if (r == 3) {
						Dict.replace(c, 1);
					} else if (r == 1) {
						Dict.replace(c, 0);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				ar += Dict.get(A.get(i));
				br += Dict.get(B.get(i));
				cr += Dict.get(C.get(i));
			}
			System.out.printf("%d %d %d\n", ar, br, cr);;
		}
	}

	private static int makeInteger(String temp) {
		int result = 0;
		for (int i = 0; i < 3; i++) {
			result += temp.charAt(i) - 'a';
		}
		return result;
	}

}
