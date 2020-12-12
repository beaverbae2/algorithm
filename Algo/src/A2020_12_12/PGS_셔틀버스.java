package A2020_12_12;

import java.util.*;

public class PGS_셔틀버스 {
	private Time[] timeArray;
	private Time[] busArray;
	private String[] one = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
	
	static class Time{
		int hour;
		int minute;
		
		public Time(int hour, int minute) {
			this.hour = hour;
			this.minute = minute;
		}

		@Override
		public String toString() {
			return "[hour=" + hour + ", minute=" + minute + "]";
		}
	}
	
	public String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		timeArray = new Time[timetable.length];
		busArray = new Time[n];
		
		for (int i = 0; i < timetable.length; i++) {
			String[] split = timetable[i].split(":");
			int hour = Integer.parseInt(split[0]);
			int minute = Integer.parseInt(split[1]);
			timeArray[i] = new Time(hour, minute);
		
		}
		
		sortTimeArray();
//		System.out.println(Arrays.toString(timeArray));
		getBusArray(n,t);
//		System.out.println(Arrays.toString(busArray));
		
		ArrayList<Time> last = new ArrayList<>();
		
		int idx = 0;
		for (int i = 0; i < busArray.length; i++) {
			int inBusCrew = 0;
			int next_idx = idx;
			
			
			for (int j = idx; j < timeArray.length; j++) {
				if(compareTime(busArray[i], timeArray[j])) {
					if(i == busArray.length-1) {
						last.add(new Time(timeArray[j].hour, timeArray[j].minute));
					}
					next_idx++; 
					inBusCrew++;
				}else{
					idx = next_idx;
					break;
				}
				if(inBusCrew==m) {
					idx = next_idx;
					break;
				}
			}
		}
//		System.out.println("last");
//		System.out.println(last);
		
		String s_hour = "";
		String s_minute = "";
		
		if(last.size()<m) {
			Time time = busArray[n-1];
			int hour = time.hour;
			int minute = time.minute;
			
			if(hour<10) s_hour += one[hour];
			else s_hour += hour;
			
			if(minute<10) s_minute += one[minute];
			else s_minute += s_minute;
			
			answer = s_hour+":"+s_minute;
		}else {
			Time time = last.get(last.size()-1);
			int hour = time.hour;
			int minute = time.minute-1;
			
			if(minute<0) {
				minute += 60;
				hour--;
			}
			
			if(hour<10) s_hour += one[hour];
			else s_hour += hour;
			
			if(minute<10) s_minute += one[minute];
			else s_minute += minute;
			
			answer = s_hour+":"+s_minute;
		}
		
//		System.out.println("answer : "+answer);
//		System.out.println();
		
		return answer;
	}
	
	public void getBusArray(int n, int t) {
		int hour = 9;
		int minute = 0;
		int cnt = 0;
		
		busArray[0] = new Time(hour, minute);
		cnt++;
		if (cnt == n) return;
		
		while(cnt<n) {
			minute+=t;
			if(minute>=60) {
				minute -= 60;
				hour++;
			}
			busArray[cnt] = new Time(hour, minute);
			cnt++;
		}
		
	}

	public boolean compareTime(Time bus, Time crew) {
		if(crew.hour > bus.hour) return false;
		else if(crew.hour < bus.hour) return true;
		else {
			if(crew.minute>bus.minute) return false;
			else return true;
		}
	}
	
	public void sortTimeArray() {
		Arrays.sort(timeArray, new Comparator<Time>() {

			@Override
			public int compare(Time o1, Time o2) {
				if(o1.hour != o2.hour) {
					return Integer.compare(o1.hour, o2.hour);
				}else {
					return Integer.compare(o1.minute, o2.minute);
				}
			}
		});
	}

	public static void main(String[] args) {
		PGS_셔틀버스 a  = new PGS_셔틀버스();
		System.out.println(a.solution(1, 1, 5, new String[] {"08:00", "08:01", "08:02", "08:03"}));
		a  = new PGS_셔틀버스();
		System.out.println(a.solution(2, 10, 2, new String[] {"09:10", "09:09", "08:00"}));
		a  = new PGS_셔틀버스();
		System.out.println(a.solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"}));
		a  = new PGS_셔틀버스();
		System.out.println(a.solution(1, 1, 5, new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"}));
		a  = new PGS_셔틀버스();
		System.out.println(a.solution(1, 1, 1, new String[] {"23:59"}));
		a  = new PGS_셔틀버스();
		System.out.println(a.solution(10, 60, 45, new String[] {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
	}
}
