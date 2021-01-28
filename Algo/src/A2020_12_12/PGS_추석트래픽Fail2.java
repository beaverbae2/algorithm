package A2020_12_12;

import java.util.*;

public class PGS_추석트래픽Fail2 {
	private List<Time> trafficList;
	private int answer;
	
	static class Time{
		int num;
		int hr, min, sec;
		public Time(int num, int hr, int min, int sec) {
			this.num = num;
			this.hr = hr;
			this.min = min;
			this.sec = sec;
		}
		@Override
		public String toString() {
			return "[num=" + num + ", hr=" + hr + ", min=" + min + ", sec=" + sec + "]";
		}
	}
	
	
	public int solution(String[] lines) {
		answer = 0;
		trafficList = new ArrayList<>();
		
		int num = 0;
		for (int i = 0; i < lines.length; i++) {
			String[] split = lines[i].split(" ");
			String[] date = split[1].split(":");
			String[] s_sec = date[2].split("[.]");
			
			int hr = Integer.parseInt(date[0])+1;
			int min = Integer.parseInt(date[1]);
			int sec = Integer.parseInt(s_sec[0]+s_sec[1]);
			int processTime = (int) (1000 * Double.parseDouble(split[2].substring(0, split[2].length()-1)))-1;
//			System.out.println(processTime);
			trafficList.add(new Time(num, hr, min, sec));
			getStartResponseTime(num, hr, min, sec, processTime);
			sortTrafficList();
			
			num++;
		}
		for (int i = 0; i < trafficList.size(); i++) {
			getMaxTraffic(i);
		}
		System.out.println(trafficList);
		System.out.println();
		return answer;
	}
	
	public void getMaxTraffic(int idx) {
		Set<Integer> trafficNumSet = new HashSet<>();
		
		Time base = trafficList.get(idx);
		trafficNumSet.add(base.num);
		int base_hr = base.hr;
		
		int base_min = base.min;
		int base_sec = base.sec;
		
		//1초 : 더하기 999
		base_sec+=999;
		if(base_sec>=60000) {
			base_sec -= 60000;
			base_min++;
		}
		
		if(base_min >= 60) {
			base_min -= 60;
			base_hr++;
		}
		
		for (int i = idx+1; i < trafficList.size(); i++) {
			Time t = trafficList.get(i);
			
			int hr = t.hr;
			int min = t.min;
			int sec = t.sec;
			
			if(trafficNumSet.contains(t.num)) continue;
			
			if(IsInOneSecond(base_hr, base_min, base_sec, hr, min, sec)) {
				trafficNumSet.add(t.num);
			}else break;
		}
//		System.out.println(base);
//		System.out.println("size : "+trafficNumSet.size());
//		System.out.println();
		answer = Math.max(answer, trafficNumSet.size());
	}

	public boolean IsInOneSecond(int base_hr, int base_min, int base_sec, int hr, int min, int sec) {
		int baseMilli = base_hr*3600000+base_min*60000+base_sec;
		int targetMilli = hr*3600000+min*60000+sec;
		int subMill = baseMilli - targetMilli;
		
		if(subMill>=0 && subMill<1000) return true;
		return false;
		
//		if (base_hr < hr) return false;
//		if (base_min < min) return false;
//		if (base_sec < sec) return false;
		
	}

	public void sortTrafficList() {
		Collections.sort(trafficList, new Comparator<Time>() {

			@Override
			public int compare(Time o1, Time o2) {
				if(o1.hr != o2.hr) {
					return Integer.compare(o1.hr, o2.hr);
				}else {
					if(o1.min != o2.min) {
						return Integer.compare(o1.min, o2.min);
					}else {
						return Integer.compare(o1.sec, o2.sec);
					}
				}
			}
		});
	}
	
	public void getStartResponseTime(int num, int hr, int min, int sec, int processTime) {
		sec -= processTime;
		
		if (sec<0) {
			sec += 60000;
			min -= 1;
		}
		
		if (min<0) {
			min += 60;
			hr -= 1;
		}
		
		trafficList.add(new Time(num, hr, min, sec));
	}

	public static void main(String[] args) {
		PGS_추석트래픽Fail2 a = new PGS_추석트래픽Fail2();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽Fail2();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽Fail2();
		System.out.println(a.solution(new String[] {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"}));
		
		a = new PGS_추석트래픽Fail2();
		System.out.println(a.solution(new String[] {"2016-09-15 00:00:00.00 0.002s", "2016-09-15 00:00:01.001 0.002s"}));
		
	}
}
