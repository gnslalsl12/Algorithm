package LGUPLUS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class prob2 {
	static Type[] Comps;
	static Type[] Apps;
	static int Compcount, Appcount;

	public static void main(String[] args) {
		String[] companies1 = { "A fabdec 2", "B cebdfa 2", "C ecfadb 2" };
		String[] applicants1 = { "a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2" };
		String[] companies2 = { "A abc 2", "B abc 1" };
		String[] applicants2 = { "a AB 1", "b AB 1", "c AB 1" };
		solution(companies1, applicants1);
		solution(companies2, applicants2);
	}

	public static String[] solution(String[] companies, String[] applicants) {
		String[] answer = {};
		Compcount = companies.length;
		Appcount = applicants.length;
		Comps = new Type[26];
		Apps = new Type[26];
		for (int i = 0; i < Compcount; i++) {
			String[] temp = companies[i].split(" ");
			int name = temp[0].charAt(0) - 'A';
			Comps[name] = new Type(0);
			Comps[name].Name = name;
			for (int j = 0; j < Appcount; j++) {
				Comps[name].Comeon.add(temp[1].charAt(j) - 'a');
			}
			Comps[name].count = Integer.parseInt(temp[2]);
		}
		for (int i = 0; i < Appcount; i++) {
			String[] temp = applicants[i].split(" ");
			int name = temp[0].charAt(0) - 'a';
			Apps[name] = new Type();
			Apps[name].Name = name;
			for (int j = 0; j < Compcount; j++) {
				Apps[name].Likes.add(temp[1].charAt(j) - 'A');
			}
			Apps[name].count = Integer.parseInt(temp[2]);
		}
		return answer;
	}

	public static String[] solv() {
		String[] result = new String[Compcount];
		while (doApply()) {
			getApplys();
		}
		int count = 0;
		for (Type tempC : Comps) {
			if (tempC == null)
				continue;
			char Name = (char) (tempC.Name + 'A');
			String Finals = "";
			for (int i = 0; i < 26; i++) {
				if ((tempC.FinalSel & (1 << i)) != 0) {
					Finals += (char) (i + 'a');
				}
			}
			result[count++] = Name + "_" + Finals;
		}
		return result;
	}

	public static void getApplys() {
		for (Type tempC : Comps) {
			if (tempC == null)
				continue;
			int appsel = tempC.AppSel;
			for (int want : tempC.Comeon) {
				if (Apps[want].Pass || (tempC.FinalSel & (1 << want)) != 0) // 이미 여기에 합격 /다른데 합격
					continue;
				if ((appsel & 1 << want) != 0) { // 원하던 애가 지원함
					tempC.FinalSel |= 1 << want;
					Apps[want].Pass = true; // 일단 합격시키기
					if (tempC.count > 0) {
						tempC.count--;
						if (tempC.count == 0)
							break;
					} else {
						for (int j = tempC.Comeon.size() - 1; j >= 0; j--) {
							if ((tempC.FinalSel & (1 << tempC.Comeon.get(j))) != 0) { // 가장 선호도 낮은 합격자
								tempC.FinalSel &= ~(1 << j); // 불합격시키기
								Apps[j].Pass = false;
								break;
							}
						}
					}
				}
			}
			tempC.AppSel = 0;
		}
	}

	public static boolean doApply() {
		boolean moreThanOne = false;
		for (Type tempA : Apps) {
			if (tempA == null)
				continue;
			if (tempA.count == 0 || tempA.Pass)
				continue;
			moreThanOne = true;
			int Aname = tempA.Name;
			int Target = tempA.Likes.poll();
			tempA.count--;
			Comps[Target].AppSel |= 1 << Aname;
		}
		return moreThanOne;
	}

	public static class Type {
		int Name;
		Queue<Integer> Likes;
		ArrayList<Integer> Comeon;
		int count;
		int AppSel;
		int FinalSel;
		boolean Pass;

		public Type() {
			Likes = new LinkedList<>();
			this.Pass = false;
		}

		public Type(int AppSel) {
			this.AppSel = AppSel;
			this.FinalSel = 0;
			Comeon = new ArrayList<>();
		}
	}

}
