import java.util.*;
import java.io.*;

/*
 * - : 1 감소
 * + : 1 증가
 * < : 왼쪽 한 칸
 * > : 오른쪽 한 칸
 * [ : 포인터가 0이면 다음 ]로 이동
 * ] : 포인터가 0이 아니라면 이전 [로 이동
 * . : 포인터 수 출력
 * , : 문자 하나 읽고 포인터에 저장 (입력 마지막이면 255 저장)
*/
public class Main {
	static int Sm, Sc, Si; // 배열 크기, 코드 크기, 입력 크기
	static String InputCode, InputLine;
	static int[] Array; // 포인터가 가리키는 배열
	static int[] Pairs;

	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(read.readLine());
		for (int test = 0; test < testCase; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			Sm = Integer.parseInt(tokens.nextToken());
			Sc = Integer.parseInt(tokens.nextToken());
			Si = Integer.parseInt(tokens.nextToken());
			Array = new int[Sm];
			Pairs = new int[Sc];
			InputCode = read.readLine();
			Stack<Integer> pairStack = new Stack<>();
			for (int i = 0; i < Sc; i++) {
				if (InputCode.charAt(i) == '[') {
					pairStack.push(i);
				} else if (InputCode.charAt(i) == ']') {
					int j = pairStack.pop();
					Pairs[i] = j;
					Pairs[j] = i;
				}
			}
			InputLine = read.readLine();
			solv();
		}
		read.close();
		write.close();
	}

	private static void solv() throws IOException {
		write.write(getResult() + "\n");

	}

	private static String getResult() {
		boolean result = false;
		int codeIndex = 0;
		int pointerIndex = 0;
		int lineIndex = 0;
		int infinLastIndex = 0;
		for (int let = 0; let < 100000000; let++) {
			if (codeIndex >= Sc) {
				result = true;
				break;
			}
			switch (InputCode.charAt(codeIndex)) {
			case ('-'): {
				Array[pointerIndex] = (Array[pointerIndex] + 255) % 256;
				codeIndex++;
				break;
			}
			case ('+'): {
				Array[pointerIndex] = (Array[pointerIndex] + 1) % 256;
				codeIndex++;
				break;
			}
			case ('<'): {
				pointerIndex = (pointerIndex - 1 + Sm) % Sm;
				codeIndex++;
				break;
			}
			case ('>'): {
				pointerIndex = (pointerIndex + 1) % Sm;
				codeIndex++;
				break;
			}
			case ('['): {
				if (Array[pointerIndex] == 0) {
					codeIndex = Pairs[codeIndex];
				} else {
					codeIndex++;
				}
				break;
			}
			case (']'): {
				if (Array[pointerIndex] != 0) {
					if (let > 5000000 && codeIndex > infinLastIndex) {
						infinLastIndex = codeIndex;
					}
					codeIndex = Pairs[codeIndex];
				} else {
					codeIndex++;
				}
				break;
			}
			case ('.'): {
				codeIndex++;
				break;
			}
			case (','): {
				Array[pointerIndex] = lineIndex >= Si ? 255 : InputLine.charAt(lineIndex++);
				codeIndex++;
				break;
			}
			}
		}
		return result ? "Terminates" : "Loops " + Pairs[infinLastIndex] + " " + infinLastIndex;
	}

}