package A2021_01_23;

import java.util.*;

public class PGS_추석_트래픽Fail {
   private int answer = 0 , N = 0;
	private List<Time> trafficList = new ArrayList<>();
	private final int SECOND = 1000;
	private boolean[] visited;
	
	public int solution(String[] lines) {
		for (String line: lines) {
			String[] split_blank = line.split(" ");
			
			String[] split_colon = split_blank[1].split("[:]");
			String[] split_doc = split_colon[2].split("[.]");
			
			String shr = split_colon[0];
			String sm = split_colon[1];
			String ss = split_doc[0];
			String sms = split_doc[1];
			
			String sprocessTime = split_blank[2].substring(0, split_blank[2].length()-1);
			String[] split_sprocessTime = sprocessTime.split("[.]");
			
			int front = Integer.parseInt(split_sprocessTime[0]) * 1000;
			int end = 0;
			
			if (split_sprocessTime.length > 1) {
				end = Integer.parseInt(split_sprocessTime[1]);
			}
			
			int processTime = front + end;
			
			int endTrafficTime = stoi(shr, sm, ss, sms);
			int startTrafficTime = endTrafficTime - processTime + 1;
			
//			System.out.println("start : "+startTrafficTime+", end : "+endTrafficTime);
			trafficList.add(new Time(N, startTrafficTime));
			trafficList.add(new Time(N, endTrafficTime));
			N++;
		}
		Collections.sort(trafficList);
//		System.out.println(trafficList);
		
		getMax();
//		System.out.println(answer);
//		System.out.println();
		return answer;
	}
	
	public void getMax() {
		
		for (int i = 0; i < trafficList.size(); i++) {
			visited = new boolean[N];
			int cnt = 0;
			Time start = trafficList.get(i);
			
			int startTime = start.time;
			int startN = start.n;
			visited[startN] = true;
			cnt++;
			
			int endTime = startTime + SECOND;
//			System.out.println("end time : "+endTime);
			for (int j = i+1; j < trafficList.size(); j++) {
				Time target = trafficList.get(j);
				
				int targetTime = target.time;
				int targetN = target.n;
				
//				System.out.println("target time "+targetTime);
				if (targetTime < endTime) {
					if (visited[targetN]) continue;
					
					visited[targetN] = true;
					cnt++;
				}else {
					break;
				}
			}
//			System.out.println("cnt : "+cnt);
			answer = Math.max(answer, cnt);
//			System.out.println();
		}
	}
	
	
	public int stoi(String shr, String sm, String ss, String sms) {
		int hr = 60 * 60 * 1000 * Integer.parseInt(shr);
		int m = 60 * 1000 * Integer.parseInt(sm);
		int s = 1000 * Integer.parseInt(ss);
		int ms = Integer.parseInt(sms);
	
		return hr+m+s+ms;
	}
	
	static class Time implements Comparable<Time>{
		int n, time;

		public Time(int n, int time) {
			this.n = n;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Time [n=" + n + ", time=" + time + "]";
		}
		
		@Override
		public int compareTo(Time o) {
			return Integer.compare(this.time, o.time);
		}
	}
}