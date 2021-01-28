package A2020_12_12;

import java.util.*;

public class PGS_추석트래픽Fail {
	private String[] one = {"00","01","02","03","04","05","06","07","08","09"};
	private ArrayList<Elem> list;
	private int answer;
	
	static class Elem{
		int num;
		String clock;
		
		public Elem(int num, String clock) {
			super();
			this.num = num;
			this.clock = clock;
		}

		@Override
		public String toString() {
			return "Elem [num=" + num + ", clock=" + clock + "]";
		}
	}
	
	public int solution(String[] lines) {
		answer = 0;
		list = new ArrayList<Elem>();
		int num = 0;
		for (int i = 0; i < lines.length; i++) {
			String[] splited = lines[i].split(" ");
			
			String c = splited[1];
			String[] c_splited = c.split(":");
			int end_h = Integer.parseInt(c_splited[0]);
			end_h++;
			int end_m = Integer.parseInt(c_splited[1]);
			double end_s = Double.parseDouble(c_splited[2]);
			
			String end_clock = end_h+":"+end_m+":"+end_s;
			String during = splited[2].substring(0, splited[2].length()-1);
			String start_clock = sub(end_clock, during);
			
//			System.out.println("time : "+during);
//			System.out.println("start_clock : "+start_clock);
//			System.out.println("end_clock : "+end_clock);
			
			list.add(new Elem(num, start_clock));
			list.add(new Elem(num, end_clock));
			num++;
		}
		
		sortList();
//		System.out.println(list);
		getAnswer();
		return answer;
	}
	
	
	
	public void getAnswer() {
		for (int i = 0; i < list.size(); i++) {
			answer = Math.max(answer, getMax(i, i+1, list.size()));
		}
	}


	public int getMax(int k , int start, int end) {
		int result = 1;
		
		int n1 = list.get(k).num;
		String start_time = list.get(k).clock;
		String end_time = addOneMinute(start_time);
		
//		System.out.println("start : "+start_time);
//		System.out.println("end : "+end_time);
		
		for (int i = start; i < end; i++) {
			int n2 = list.get(i).num; 
			String time = list.get(i).clock;
			if (isInOneMinute(n1, n2, end_time, time)) {
				result++;
			}else break;
		}
//		System.out.println("result : "+result);
		return result;
	
	}



	private boolean isInOneMinute(int n1, int n2, String end_time, String time) {
		if(n1==n2) return false;
		
		String[] splited = end_time.split(":");
		int h1 = Integer.parseInt(splited[0]);
		int m1 = Integer.parseInt(splited[1]);
		double s1 = Double.parseDouble(splited[2]);
		
		String[] splited2 = time.split(":");
		int h2 = Integer.parseInt(splited2[0]);
		int m2 = Integer.parseInt(splited2[1]);
		double s2 = Double.parseDouble(splited2[2]);
		
		if(h1<h2) return false;
		if(m1<m2) return false;
		if(s1<s2) return false;
		
		return true;
	}



	private String addOneMinute(String start_time) {
		String[] splited = start_time.split(":");
		int h = Integer.parseInt(splited[0]);
		int m = Integer.parseInt(splited[1]);
		double s = Double.parseDouble(splited[2]);
		
		s += 0.999;
		if(s>=60) {
			s -= 60;
			m +=1;
		}
		
		if(m>=60) {
			m -= 60;
			h +=1;
		}
		
		StringBuilder sb = new StringBuilder();
		String s_s = "";
		
		s_s = (int) s+String.format("%.3f", s-(int) s).substring(1);
		
		sb.append(h).append(":").append(m).append(":").append(s_s);
		return sb.toString();
	}



	public void sortList() {
		Collections.sort(list, new Comparator<Elem>() {

			@Override
			public int compare(Elem o1, Elem o2) {
				String[] splited1 = o1.clock.split(":");
				String[] splited2 = o2.clock.split(":");
						
				
				int h1 = Integer.parseInt(splited1[0]);
				int h2 = Integer.parseInt(splited2[0]);
				
				if(h1!=h2) {
					return Integer.compare(h1, h2);
				}else {
					int m1 = Integer.parseInt(splited1[1]);
					int m2 = Integer.parseInt(splited2[1]);
				
					if(m1!=m2) {
						return Integer.compare(m1, m2);
					}else {
						double s1 = Double.parseDouble(splited1[2]);
						double s2 = Double.parseDouble(splited2[2]);
						
						return Double.compare(s1, s2);
					}
				}
			}
		});
	}



	public String sub(String clock, String during) {
		String[] splited = clock.split(":");
		int hour = Integer.parseInt(splited[0]);
		int minute = Integer.parseInt(splited[1]);
		double second = Double.parseDouble(splited[2]);
		double time = Double.parseDouble(during);
		
		second -= time;
		second += 0.001;
		
		if(second<0) {
			second += 60.0;
			minute -= 1;
		}
		if(minute<0) {
			minute += 60;
			hour -= 1;
		}
		
		StringBuilder sb = new StringBuilder();
		String s_second = "";
		s_second = (int) second+String.format("%.3f", second-(int) second).substring(1);
		
		sb.append(hour).append(":").append(minute).append(":").append(s_second);
		return sb.toString();
	}

	public static void main(String[] args) {
		PGS_추석트래픽Fail a = new PGS_추석트래픽Fail();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽Fail();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽Fail();
		System.out.println(a.solution(new String[] {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"}));
		
//		PGS_추석트래픽 a = new PGS_추석트래픽();
//		System.out.println(a.solution(new String[] {"2016-09-15 00:00:00.001 2.0s", "2016-09-15 00:00:01.00 2s"}));
		
	}
}
