package day1112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22252 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		Map<String, PriorityQueue<Integer>> DealerDict = new HashMap<>();	//고릴라들의 정보 소지 사전
		long result = 0;
		for (int i = 0; i < Q; i++) {
			tokens = new StringTokenizer(read.readLine());
			if (tokens.nextToken().equals("1")) {
				String name = tokens.nextToken();	//정보를 얻은 릴라 이름
				int count = Integer.parseInt(tokens.nextToken());	//얻은 정보 수
				if (DealerDict.containsKey(name)) {	//해당 릴라를 전에 들어봤다면
					for (int c = 0; c < count; c++) {
						DealerDict.get(name).add(Integer.parseInt(tokens.nextToken()));	//이미 있는 PQ에 추가
					}
				} else {														//들어보지 못했다면
					PriorityQueue<Integer> Infos = new PriorityQueue<>(Collections.reverseOrder());
					for (int c = 0; c < count; c++) {
						Infos.add(Integer.parseInt(tokens.nextToken()));
					}
					DealerDict.put(name, Infos);	//새롭게 이름과 PQ를 사전에 추가
				}
			} else {
				String name = tokens.nextToken();	//사려는 정보의 릴라 이름
				if (!DealerDict.containsKey(name))		//들어본 적 없는 릴라여
					continue;
				int count = Integer.parseInt(tokens.nextToken());
				count = Math.min(count, DealerDict.get(name).size());	//살 수 있는 만큼(소지 수보다 많이 살 수 없음)
				for (int c = 0; c < count; c++) {
					result += DealerDict.get(name).poll();	//사!
				}
			}
		}
		System.out.println(result);
	}
}
