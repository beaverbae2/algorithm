package A2021_01_23;

import java.util.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */

public class PGS_추석_트래픽 {
	private int answer = 0;
	private List<Time> trafficList = new ArrayList<>();
	private final int SECOND = 1000;

	public int solution(String[] lines) {
		for (String line : lines) {
			String[] split_blank = line.split(" ");

			String[] split_colon = split_blank[1].split("[:]");
			String[] split_doc = split_colon[2].split("[.]");

			String shr = split_colon[0];
			String sm = split_colon[1];
			String ss = split_doc[0];
			String sms = split_doc[1];

			String sprocessTime = split_blank[2].substring(0, split_blank[2].length() - 1);
			String[] split_sprocessTime = sprocessTime.split("[.]");

			int front = Integer.parseInt(split_sprocessTime[0]) * 1000;
			int end = 0;

			if (split_sprocessTime.length > 1) {
				end = Integer.parseInt(split_sprocessTime[1]);
			}

			int processTime = front + end;

			int endTrafficTime = stoi(shr, sm, ss, sms);
			int startTrafficTime = endTrafficTime - processTime + 1;

			trafficList.add(new Time(startTrafficTime, endTrafficTime));
		}

		getMax();
		return answer;
	}

	public void getMax() {
		for (int i = 0; i < trafficList.size(); i++) {
			Time traffic = trafficList.get(i);
			
			
			// 다음은 가능한 모든 경우의 수 이다
			int processStartTime1 = traffic.start - SECOND + 1;
			int processEndTime1 = traffic.start;
			int processStartTime2 = traffic.start;
			int processEndTime2 = traffic.start + SECOND - 1;
			int processStartTime3 = traffic.end - SECOND + 1;
			int processEndTime3 = traffic.end;
			int processStartTime4 = traffic.end;
			int processEndTime4 = traffic.end + SECOND - 1;

			List<Integer> processTimeList = new ArrayList<>();
			processTimeList.add(processStartTime1);
			processTimeList.add(processEndTime1);
			processTimeList.add(processStartTime2);
			processTimeList.add(processEndTime2);
			processTimeList.add(processStartTime3);
			processTimeList.add(processEndTime3);
			processTimeList.add(processStartTime4);
			processTimeList.add(processEndTime4);

			for (int j = 0; j < processTimeList.size() / 2; j++) {
				int processStartTime = processTimeList.get(2 * j);
				int processEndTime = processTimeList.get(2 * j + 1);

				int cnt = 1;
				for (int k = 0; k < trafficList.size(); k++) {
					if (i == k)
						continue;

					Time target = trafficList.get(k);

					if (target.start >= processStartTime && target.end <= processEndTime) {
						cnt++;
					} else if (target.end >= processStartTime && target.end <= processEndTime) {
						cnt++;
					} else if (target.start >= processStartTime && target.start <= processEndTime) {
						cnt++;
					} else if (target.start <= processStartTime && target.end >= processEndTime) {
						cnt++;
					}
				}
				answer = Math.max(answer, cnt);
			}
		}
	}

	public int stoi(String shr, String sm, String ss, String sms) {
		int hr = 60 * 60 * 1000 * Integer.parseInt(shr);
		int m = 60 * 1000 * Integer.parseInt(sm);
		int s = 1000 * Integer.parseInt(ss);
		int ms = Integer.parseInt(sms);

		return hr + m + s + ms;
	}

	static class Time {
		int start, end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Time [start=" + start + ", end=" + end + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		new PGS_추석_트래픽().solution(new String[] { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" });
		new PGS_추석_트래픽().solution(new String[] { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" });
		new PGS_추석_트래픽().solution(new String[] { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" });
	}
}