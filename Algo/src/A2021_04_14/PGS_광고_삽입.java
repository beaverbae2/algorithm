package A2021_04_14;

import java.util.*;

/**
 * Prefix sum
 * 
 * @author beaverbae
 * @see https://www.youtube.com/watch?v=FX9n1PFv2K4
 * 
 */

public class PGS_광고_삽입 {
	public String solution(String play_time, String adv_time, String[] logs) {
		int[] play_sum = new int[stoi(play_time) + 2];
		int adv = stoi(adv_time);

		// 시간 표시
		for (String log : logs) {
			String[] arr = log.split("-");
			int start = stoi(arr[0]);
			int end = stoi(arr[1]);

			play_sum[start] += 1;
			play_sum[end] -= 1;// end에서 나가므로(중요)
		}

		// 특정 초에 시청 중인 사람 수 계산
		for (int i = 1; i < play_sum.length; i++) {
			play_sum[i] += play_sum[i - 1];
		}

		long max = 0;// 오버플로우가 발생할 수 있으니 long 타입으로 해야 한다
		int max_start = 0;

		// 누적 재생 시간 구하기
		// 1. 초기화(0초 일때)
		for (int i = 0; i < adv; i++) {
			max += play_sum[i];
		}

		// 2. 나머지 시간
		long sum = max;
		for (int i = 1; i <= play_sum.length - 2 - adv; i++) {
			sum -= play_sum[i - 1];
			sum += play_sum[i + adv - 1];

			if (max < sum) {
				max = sum;
				max_start = i;
			}
		}
		return itos(max_start);
	}

	private int stoi(String s) {
		String[] arr = s.split(":");
		int hr = Integer.parseInt(arr[0]) * 3600;
		int min = Integer.parseInt(arr[1]) * 60;
		int sec = Integer.parseInt(arr[2]);
		return hr + min + sec;
	}

	private String itos(int time) {
		String hr = Integer.toString(time / 3600);
		time = time % 3600;
		String min = Integer.toString(time / 60);
		time = time % 60;
		String sec = Integer.toString(time);

		if (hr.length() == 1)
			hr = "0" + hr;
		if (min.length() == 1)
			min = "0" + min;
		if (sec.length() == 1)
			sec = "0" + sec;

		return hr + ":" + min + ":" + sec;
	}

	public static void main(String[] args) {
		new PGS_광고_삽입().solution("02:03:55", "00:14:15", new String[] { "01:20:15-01:45:14", "00:40:31-01:00:00",
				"00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30" });
		new PGS_광고_삽입().solution("99:59:59", "25:00:00",
				new String[] { "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00" });
		new PGS_광고_삽입().solution("50:00:00", "50:00:00",
				new String[] { "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45" });
	}
}
