package A2020_12_12;

//reference : https://iron-jin.tistory.com/entry/2018-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%B8%94%EB%9D%BC%EC%9D%B8%EB%93%9C-%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8-1%EC%B0%A8-%EC%B6%94%EC%84%9D-%ED%8A%B8%EB%9E%98%ED%94%BD-feat-Java?category=804770

import java.util.*;
public class PGS_추석트래픽 {
	private List<Traffic> trafficList;
	private int answer;
	
	static class Traffic {
		int start, end;

		public Traffic(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Traffic [start=" + start + ", end=" + end + "]";
		}
	}
	
	public int solution(String[] lines) {
		answer = 0;
		trafficList = new ArrayList<>();
		
		for (int i = 0; i < lines.length; i++) {
			String[] split = lines[i].split(" ");
			String[] date = split[1].split(":");
			String[] s_sec = date[2].split("[.]");
			
			int hr = Integer.parseInt(date[0])+1;
			int min = Integer.parseInt(date[1]);
			int sec = Integer.parseInt(s_sec[0]+s_sec[1]);
			
			int end = getMillsTime(hr, min, sec);
			int processTime = (int) (1000 * Double.parseDouble(split[2].substring(0, split[2].length()-1)))-1;
			int start = getStartResponseTime(end, processTime);

			trafficList.add(new Traffic(start, end));
			sortTrafficList();
			
		}
		for (int i = 0; i < trafficList.size(); i++) {
			getMaxTraffic(i);
		}
//		System.out.println(trafficList);
//		System.out.println();
		return answer;
	}
	
	private int getMillsTime(int hr, int min, int sec) {
		return hr*3600000+min*60000+sec;
	}

	public void getMaxTraffic(int i) {
		Traffic cur = trafficList.get(i);
		int range = cur.end+999;//1초 추가
		
		int cnt = 0;
		
		for (int j = i; j < trafficList.size(); j++) {
			Traffic next = trafficList.get(j);
			
			if (range>=next.start) cnt++;
		}
		answer = Math.max(answer, cnt);
	}

	public void sortTrafficList() {
		Collections.sort(trafficList, new Comparator<Traffic>() {

			@Override
			public int compare(Traffic o1, Traffic o2) {
				if (o1.end != o2.end) {
					return Integer.compare(o1.end, o2.end);
				}else {
					return Integer.compare(o2.start, o1.start);
				}
			}
		});
	}
	
	public int getStartResponseTime(int end, int processTime) {
		return end - processTime;
	}

	public static void main(String[] args) {
		PGS_추석트래픽 a = new PGS_추석트래픽();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽();
		System.out.println(a.solution(new String[] {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
		a = new PGS_추석트래픽();
		System.out.println(a.solution(new String[] {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"}));
		
		a = new PGS_추석트래픽();
		System.out.println(a.solution(new String[] {"2016-09-15 00:00:00.00 0.002s", "2016-09-15 00:00:01.001 0.002s"}));
		
	}
}
