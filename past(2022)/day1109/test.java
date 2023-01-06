package day1109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class test {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String s = input.readLine();
		String n = s.toUpperCase();
		HashMap<Character, Integer> map = new HashMap<>();

		// map 초기
		for (int i = 0; i < n.length(); i++) {
			char key = n.charAt(i);
			if (map.containsKey(key)) {
				map.replace(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}

		// 알파벳별 숫자 세기 & 가장 큰 value값 찾기

		// 최댓값이 중복되는지 확인
		List<Integer> list = new ArrayList<>(map.values());
		Collections.sort(list, Collections.reverseOrder()); // 역순으로 뒤집어서 앞에서 두개가 중복되면 물음표 출력, 아니면 맨 앞 value에 해당하는 키값 출

		if (list.size() == 1) { // 1개면 그게 답
			char key = getKey(map, list.get(0)); // map과 value를 넣어주면 key를 리턴해
			System.out.println(key);
		} else if (list.size() > 1) {
			if ((int)list.get(0) == (int)list.get(1)) {
				System.out.println("?");
			} else {
				char key = getKey(map, list.get(0));
				System.out.println(key);
			}
		}

		// System.out.println(map);

	}

	private static char getKey(HashMap<Character, Integer> map, int intnum) {
		char result = '#';
		for(char tempkey : map.keySet()) {
			if(map.get(tempkey) == intnum) {
				result = tempkey;
				break;
			}
		}
		return result;
//		for (char key : map.keySet()) {
//			if (integer.equals(map.get(key))) {
//				return key;
//			}
//		}
//		return 'A'; // 의미없는 값..
	}

}
