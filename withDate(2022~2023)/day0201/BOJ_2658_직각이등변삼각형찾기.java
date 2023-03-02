package day0201;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_2658_직각이등변삼각형찾기 {
	static ArrayList<int[]> Edges = new ArrayList<>();
	static int[] Map = new int[10];
	static int[] E1, E2, E3;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int MaxCount = 0;
		int[][] DoubleEdge = new int[2][2];
		boolean endsearch = false;
		for (int i = 0; i < 10; i++) {
			String temp = read.readLine();
			int LineCount = 0;
			int FirstJ = 0;
			int LastJ = 0;
			int j = 0;
			for (; j < 10; j++) {
				if (temp.charAt(j) == '1') {
					if (LineCount == 0)
						FirstJ = j;
					Map[i] |= 1 << j;
					LineCount++;
					LastJ = j;
				}
			}
			if (MaxCount < LineCount) {
				MaxCount = LineCount;
				DoubleEdge[0] = new int[] { i, FirstJ };
				DoubleEdge[1] = new int[] { i, LastJ };
			}
			if (LineCount == 1) {
				Edges.add(new int[] { i, FirstJ });
			}
			if (endsearch && LineCount != 0) {
				Edges = new ArrayList<>();
				break;
			}
			if (LineCount == 0 && Edges.size() > 0) {
				endsearch = true;
			}
		}
		if (Edges.size() == 0) {
			write.write("0\n");
			write.close();
			read.close();
			return;
		}
		if (Edges.size() == 1) {
			Edges.add(DoubleEdge[0]);
			Edges.add(DoubleEdge[1]);
		} else {
			if (Edges.get(0)[1] == DoubleEdge[0][1]) {
				Edges.add(DoubleEdge[1]);
			} else {
				Edges.add(DoubleEdge[0]);
			}
		}
		Comparator<int[]> Sorting = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]);
			}
		};
		Collections.sort(Edges, Sorting);
		E1 = Edges.get(0);
		E2 = Edges.get(1);
		E3 = Edges.get(2);
		if (checkWhichShape()) {
			write.write((E1[0] + 1) + " " + (E1[1] + 1) + "\n");
			write.write((E2[0] + 1) + " " + (E2[1] + 1) + "\n");
			write.write((E3[0] + 1) + " " + (E3[1] + 1) + "\n");
		} else {
			write.write("0\n");
		}
		write.close();
		read.close();
	}

	private static boolean checkWhichShape() {
		boolean result;
		if (E1[0] == E2[0]) { // 수평 하향
			if (E1[1] == E3[1]) { // 좌상단 직각
				result = checkCornerRightAngle(false, E1[0], E3[0], E2[1], -1);
			} else if (E2[1] == E3[1]) { // 우상단 직각
				result = checkCornerRightAngle(false, E1[0], E3[0], E1[1], 1);
			} else { // 하단 직각
				result = checkUpDownRightAngle(false, E1[0], E1[1], E2[1]);
			}
		} else if (E2[0] == E3[0]) { // 수평 상향
			if (E1[1] == E2[1]) { // 좌하단 직각
				result = checkCornerRightAngle(true, E1[0], E2[0], E1[1], 1);
			} else if (E1[1] == E3[1]) { // 우하단 직각
				result = checkCornerRightAngle(true, E1[0], E2[0], E1[1], -1);
			} else { // 상단 직각
				result = checkUpDownRightAngle(true, E1[0], E1[1], E1[1]);
			}
		} else {
			if (E2[1] > E1[1]) { // 수직 우측
				result = checkLeftRightRightAngle(false, E1[0], E2[0], E1[1]);
			} else { // 수직 좌측
				result = checkLeftRightRightAngle(true, E1[0], E2[0], E1[1]);
			}
		}
		return result;
	}

	private static boolean checkCornerRightAngle(boolean Asc, int StartLine, int EndLine, int J, int var) {
		int Amount = Map[StartLine];
		if (Asc) {
			for (int i = StartLine; i < 10; i++) {
				if (Map[i] == 0) {
					if (i != EndLine + 1)
						return false;
					break;
				}
				if (Amount != Map[i]) {
					return false;
				}
				J += var;
				Amount |= 1 << J;
			}
		} else {
			for (int i = StartLine; i < 10; i++) {
				if (Map[i] == 0) {
					if (i != EndLine + 1)
						return false;
					break;
				}
				if (Amount != Map[i]) {
					return false;
				}
				Amount &= ~(1 << J);
				J += var;
			}
		}
		return true;
	}

	private static boolean checkLeftRightRightAngle(boolean LeftRightAngle, int StartLine, int MaxLine, int J) {
		int var = LeftRightAngle ? -1 : 1;
		int Amount = Map[StartLine];
		boolean Asc = true;
		for (int i = StartLine; i < 10; i++) {
			if (Map[i] == 0) {
				if (i != E3[0] + 1)
					return false;
				break;
			}
			if (Map[i] != Amount) {
				return false;
			}
			if (Asc) {
				J += var;
				Amount |= 1 << J;
			} else {
				J += var;
				Amount &= ~(1 << J);
			}
			if (i == MaxLine) {
				Asc = false;
				var *= -1;
				Amount = Map[MaxLine];
				J += var;
				Amount &= ~(1 << J);
			}
		}
		return true;
	}

	private static boolean checkUpDownRightAngle(boolean UpRightAngle, int StartLine, int LeftJ, int RightJ) {
		int Amount = Map[StartLine];
		if (UpRightAngle) {
			for (int i = StartLine; i < 10; i++) {
				if (Map[i] == 0) {
					if (i != E3[0] + 1)
						return false;
					break;
				}
				if (Map[i] != Amount)
					return false;
				Amount |= 1 << (--LeftJ);
				Amount |= 1 << (++RightJ);
			}
		} else {
			for (int i = StartLine; i < 0; i++) {
				if (Map[i] == 0) {
					if (i != E3[0] + 1)
						return false;
					break;
				}
				if (Map[i] != Amount)
					return false;
				if (LeftJ != RightJ) {
					Amount &= ~(1 << (LeftJ++));
					Amount &= ~(1 << (RightJ--));
				}
			}
		}
		return true;
	}

}
