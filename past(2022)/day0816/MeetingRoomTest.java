package day0816;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomTest {
	static class Meeting implements Comparable<Meeting>{
		int starttime, endtime;
		
		public Meeting(int starttime, int endtime) {
			super();
			this.starttime = starttime;
			this.endtime = endtime;
		}

		@Override
		public int compareTo(Meeting o) {	//종료시간 기준 오름차순, 같으면 시작시간 오름
			// TODO Auto-generated method stub
			return this.endtime != o.endtime ? this.endtime - o.endtime : this.starttime - o.starttime;
		}
		
		
		
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Meeting[] meetings = new Meeting[N];
		
		for(int i = 0; i < N ; i++) {
			meetings[i] = new Meeting(sc.nextInt(),sc.nextInt());
		}
		List<Meeting> result = getSchedule(meetings);
		System.out.println("총 회의 개수 : " + result.size());
		for(Meeting meeting : result) {
			System.out.println(meeting.starttime + " " + meeting.endtime);
		}
		
		
	}
	private static List<Meeting> getSchedule(Meeting[] meetings) {
		//모든 회의를 종료시간 오름차, 같다면 시간시간 오름차
		List<Meeting> result = new ArrayList<Meeting>();
		Arrays.sort(meetings);
		result.add(meetings[0]);
		for(int i = 1, size = meetings.length; i< size; i++) {
			if(meetings[i].starttime >= result.get(result.size()-1).endtime) { 
				//넣어놓은 회의 중 마지막 회의의 종료시간이 현재 시작시간보다 앞일 때 추가 가능
				result.add(meetings[i]);
			}
		}
		return result;
	}
}
