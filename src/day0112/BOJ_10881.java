package day0112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10881 {
	static int T;
	static Box[] Boxes;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int test = 1; test <= T; test++) {
			Boxes = new Box[3];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				tokens = new StringTokenizer(read.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				Boxes[i] = new Box(a, b);
			}
			getSubset(new boolean[3], 0);
			write.write(result + "\n");
		}
		write.close();
		read.close();
	}

	private static void getSubset(boolean[] sel, int count) { // 회전시킬 놈들
		if (count == 3) {
			setLine(sel);
			setTop(sel);
			return;
		}
		sel[count] = false;
		getSubset(sel, count + 1);
		sel[count] = true;
		getSubset(sel, count + 1);
	}

	private static void setLine(boolean[] sel) { // 위치 조합 => 쪼로록
		int a1 = sel[0] ? Boxes[0].a : Boxes[0].b;
		int b1 = sel[0] ? Boxes[0].b : Boxes[0].a;
		int a2 = sel[1] ? Boxes[1].a : Boxes[1].b;
		int b2 = sel[1] ? Boxes[1].b : Boxes[1].a;
		int a3 = sel[2] ? Boxes[2].a : Boxes[2].b;
		int b3 = sel[2] ? Boxes[2].b : Boxes[2].a;
		result = Math.min(result, (a1 + a2 + a3) * Math.max(b1, Math.max(b2, b3)));
	}

	private static void setTop(boolean[] sel) { // 위치 조합 => 탑
		int a1 = sel[0] ? Boxes[0].a : Boxes[0].b;
		int b1 = sel[0] ? Boxes[0].b : Boxes[0].a;
		int a2 = sel[1] ? Boxes[1].a : Boxes[1].b;
		int b2 = sel[1] ? Boxes[1].b : Boxes[1].a;
		int a3 = sel[2] ? Boxes[2].a : Boxes[2].b;
		int b3 = sel[2] ? Boxes[2].b : Boxes[2].a;
		int tempresult = 0;
		tempresult = Math.max(a1, a2 + a3) * Math.max(b1 + b2, b1 + b3);
		result = Math.min(tempresult, result);
		tempresult = Math.max(a2, a1 + a3) * Math.max(b2 + b1, b2 + b3);
		result = Math.min(tempresult, result);
		tempresult = Math.max(a3, a1 + a2) * Math.max(b3 + b1, b3 + b2);
		result = Math.min(tempresult, result);
	}

	private static class Box {
		int a;
		int b;

		public Box(int a, int b) {
			this.a = a;
			this.b = b;
		}

	}

}
