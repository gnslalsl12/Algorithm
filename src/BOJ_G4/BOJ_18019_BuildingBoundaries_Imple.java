package BOJ_G4;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_18019_BuildingBoundaries_Imple {

	static int[] Len;
	static int TotalLen;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			Len = new int[6];
			TotalLen = 0;
			for (int i = 0; i < 6; i++) {
				Len[i] = Integer.parseInt(tokens.nextToken());
				TotalLen += Len[i];
			}
			write.write(solv() + "\n");
		}
		write.close();
		read.close();
	}

	private static long solv() {
		return getSmallest();
	}

	private static long getSmallest() {
		return Math.min(Math.min(getArea(2, 4), getArea(3, 4)), Math.min(getArea(2, 5), getArea(3, 5)));
	}

	private static long getArea(int index2, int index3) {
		long a1 = Len[0];
		long b1 = Len[1];
		long a2 = Len[index2];
		long b2 = index2 == 2 ? Len[3] : Len[2];
		long a3 = Len[index3];
		long b3 = index3 == 4 ? Len[5] : Len[4];
		long Area = (a1 + a2 + a3) * (Math.max(Math.max(b1, b2), b3));
		Area = Math.min(Area, (b1 + b2 + b3) * Math.max(Math.max(a1, a2), a3));
		// a1이 a2 위로
		Area = Math.min(Area, getStackedArea(Math.max(a1, a2), a3, b1 + b2, b3));
		Area = Math.min(Area, getStackedArea(Math.max(b1, b2), b3, a1 + a2, a3));
		// a1이 a3 위로
		Area = Math.min(Area, getStackedArea(Math.max(a1, a3), a2, b1 + b3, b2));
		Area = Math.min(Area, getStackedArea(Math.max(b1, b3), b2, a1 + a3, a2));
		// a2가 a3위로
		Area = Math.min(Area, getStackedArea(Math.max(a2, a3), a1, b2 + b3, b1));
		Area = Math.min(Area, getStackedArea(Math.max(b2, b3), b1, a2 + a3, a1));
		return Area;
	}

	private static long getStackedArea(long stackedwidth, long remainwidth, long stackedheight, long remainheight) {
		return (stackedwidth + remainwidth) * (Math.max(stackedheight, remainheight));
	}

}
