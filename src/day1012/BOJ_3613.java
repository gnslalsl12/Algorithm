package day1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3613 {
	static int size;
	static StringBuilder sb = new StringBuilder();
	static boolean error;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String templine = read.readLine();
		error = false;
		size = templine.length();
		if (templine.charAt(templine.length() - 1) == '_' || templine.charAt(0) == '_') {
			error = true;
		} else if (!containLarge(templine)) { // 대문자가 없음
			if (templine.contains("_")) { // 소문자로 이뤄진 _ 포함 C
				CtoJ(templine);
			} else { // 소문자만 있고 _없음
				System.out.println(templine.toString());
				return;
			}
		} else { // 대문자가 있음
			if (templine.contains("_")) {
				error = true;
			} else if (templine.charAt(0) >= 97) { // 첫 문자가 소문자 => Java
				JtoC(templine);
			} else {
				error = true;
			}
		}
		if (error) {
			System.out.println("Error!");
			return;
		}
		System.out.println(sb);
	}

	private static void CtoJ(String templine) {
		char[] temparray = templine.toCharArray();
		for (int i = 0; i < size; i++) {
			if (temparray[i] == '_') {
				if (i == size - 1)
					break;
				char tempc = temparray[i + 1];
				if (tempc == '_') {
					error = true;
					return;

				}
				sb.append((char) ((int) tempc - 32));
				i++;
			} else {
				sb.append(temparray[i]);
			}
		}
	}

	private static void JtoC(String templine) {
		char[] temparray = templine.toCharArray();
		for (int i = 0; i < size; i++) {
			if ((int) temparray[i] < 97) { // 대문자면
				sb.append("_");
				sb.append((char) (temparray[i] + 32));
			} else {
				sb.append(temparray[i]);
			}
		}
	}

	private static boolean containLarge(String templine) {
		for (int i = 0; i < templine.length(); i++) {
			if (templine.charAt(i) - 'A' >= 0 && templine.charAt(i) - 'A' < 26) {
				return true;
			}
		}
		return false;
	}

}
