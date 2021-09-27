package A2021_02_26;

import java.util.*;

public class PGS_광고_삽입_review {
	public String solution(String play_time, String adv_time, String[] logs) {
		String[] split = play_time.split(":");
		int hr = Integer.parseInt(split[0])*3600;
		int min = Integer.parseInt(split[1])*60;
		int sec = Integer.parseInt(split[2]);
		
		int N = hr + min + sec;
		
		split = adv_time.split(":");
		hr = Integer.parseInt(split[0])*3600;
		min = Integer.parseInt(split[1])*60;
		sec = Integer.parseInt(split[2]);
		
		int adv = hr + min + sec;
		
		long[] time = new long[N+1];
		for (int i = 0; i < logs.length; i++) {
			split = logs[i].split("-");
			String[] split1 = split[0].split(":");
			String[] split2 = split[1].split(":");
			
			int hr1 = Integer.parseInt(split1[0])*3600;
			int min1 = Integer.parseInt(split1[1])*60;
			int sec1 = Integer.parseInt(split1[2]);
			
			int hr2 = Integer.parseInt(split2[0])*3600;
			int min2 = Integer.parseInt(split2[1])*60;
			int sec2 = Integer.parseInt(split2[2]);
			
			int start = hr1 + min1 + sec1;
			int end = hr2 + min2 + sec2;
		
			for (int j = start; j < end; j++) {
				time[j]++;
			}
			
			// 다음은 시간 초과 발생
//			for (int j = start+1; j <= end; j++) {
//				time[j]++;
//			}
		}
		
		for (int i = 1; i < time.length; i++) {
			time[i] += time[i-1];
		}
		
		long max = time[adv]-time[0];
		int max_time = 0;
		
		for (int i = 0; i < time.length; i++) {
			if (i + adv == time.length) break;
			
			long cnt = time[i+adv] - time[i];
			if (cnt > max) {
				max = cnt;
				max_time = i+1;
			}
		}
		
		String answer = getResult(max_time);
		return answer;
	}
	
	private String getResult(int t) {
		int hr = t/3600;
		t = t%3600;
		int min = t/60;
		t = t%60;
		int sec = t;
		
		StringBuilder sb = new StringBuilder();
	
		if (hr < 10) {
			sb.append("0").append(hr);
		} else {
			sb.append(hr);
		}
		
		if (min < 10) {
			sb.append(":").append("0").append(min);
		} else {
			sb.append(":").append(min);
		}
		
		if (sec < 10) {
			sb.append(":").append("0").append(sec);
		} else {
			sb.append(":").append(sec);
		}
		
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new PGS_광고_삽입_review().solution("02:03:55", "00:14:15", new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
		System.out.println(new PGS_광고_삽입_review().solution("99:59:59", "25:00:00", new String[] {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
		System.out.println(new PGS_광고_삽입_review().solution("50:00:00", "50:00:00", new String[] {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
	}
}
