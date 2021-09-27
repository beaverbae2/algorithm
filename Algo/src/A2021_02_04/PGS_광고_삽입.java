package A2021_02_04;

/**
 * Simulation
 * @author beaverbae
 * @see https://tech.kakao.com/2021/01/25/2021-kakao-recruitment-round-1/
 */

public class PGS_광고_삽입 {
	
	public String solution(String play_time, String adv_time, String[] logs) {
		int play_time_sec = getSec(play_time);
		int adv_time_sec = getSec(adv_time);
		
		long[] total_time = new long[play_time_sec+1];
		
		for (int i = 0; i < logs.length; i++) {
			String log = logs[i];
			String[] temp = log.split("-");
			int logs_start_sec = getSec(temp[0]);
			int logs_end_sec = getSec(temp[1]);
			
			// total_time[j] : 각 시각에 재생하는 사람의 수
			for (int j = logs_start_sec; j < logs_end_sec; j++) {
				total_time[j]++;
			}
		}
		
		// total_time[i] : 0초 부터 i+1초까지의 누적 재생시간
		for (int i = 1; i <= play_time_sec; i++) {
			total_time[i] = total_time[i] + total_time[i-1];
		}
		
		int time = 0;
		long at = total_time[adv_time_sec-1];
		for (int i = adv_time_sec; i <= play_time_sec; i++) {
			if (at < total_time[i] - total_time[i-adv_time_sec]) {
				at = total_time[i] - total_time[i - adv_time_sec];
				time = i - adv_time_sec+1;
			}
		}
		
		String answer = getStringSec(time);
		return answer;
	}
	
	public int getSec(String s_time) {
		String[] temp = s_time.split(":");
		int hr = Integer.parseInt(temp[0]);
		int min = Integer.parseInt(temp[1]);
		int sec = Integer.parseInt(temp[2]);
	
		return hr * 3600 + min * 60 + sec;
	}
	
	public String getStringSec(int time) {
		int hr = time/3600;
		int min = (time%3600)/60;
		int sec = time%60;
		
		String s_hr = "";
		String s_min = "";
		String s_sec = "";
		
		if (hr<10) {
			s_hr = "0"+hr;
		}else {
			s_hr = ""+hr;
		}
		
		if (min<10) {
			s_min = "0"+min;
		}else {
			s_min = ""+min;
		
		}if (sec<10) {
			s_sec = "0"+sec;
		}else {
			s_sec = ""+sec;
		}

		
		return s_hr+":"+s_min+":"+s_sec;
	}

	public static void main(String[] args) throws Exception {
		new PGS_광고_삽입().solution("02:03:55", "00:14:15" , new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
		new PGS_광고_삽입().solution("99:59:59", "25:00:00", new String[] {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"});
		new PGS_광고_삽입().solution("50:00:00", "50:00:00", new String[] {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"});
	}
}
