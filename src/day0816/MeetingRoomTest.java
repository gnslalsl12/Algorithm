package day0816;

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
			return 0;
		}
		
		
		
	}
	public static void main(String[] args) {

		
		
	}

}
