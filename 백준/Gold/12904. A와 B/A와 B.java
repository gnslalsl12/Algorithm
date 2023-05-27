import java.io.*;

public class Main {

	static String S;
	static StringBuffer T;
	static boolean Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		S = read.readLine();
		T = new StringBuffer(read.readLine());
		solv();
		write.write(Result ? "1\n" : "0\n");
		write.close();
		read.close();
	}

	private static void solv() {
		doRemoveAroundT();
		Result = checkTwithS();
	}

	private static void doRemoveAroundT() {
		int lenT = T.length() - 1;
		while (lenT != S.length() - 1) {
			if (T.charAt(lenT) == 'A') {
				T.deleteCharAt(lenT);
			} else {
				T.deleteCharAt(lenT);
				T.reverse();
			}
			lenT--;
		}
	}

	private static boolean checkTwithS() {
		return S.equals(T.toString());
	}

}