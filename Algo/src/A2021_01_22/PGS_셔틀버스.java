package A2021_01_22;

import java.util.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */
public class PGS_셔틀버스 {
	public String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		TreeMap<Integer, Integer> timeMap = new TreeMap<>();// <도착 시간, 크루 수>
		for (int i = 0; i < timetable.length; i++) {
			int time = stoi(timetable[i]);

			if (timeMap.containsKey(time)) {
				int cnt = timeMap.get(time);
				timeMap.put(time, cnt + 1);
			} else {
				timeMap.put(time, 1);
			}
		}

		int bustime = 9 * 60; // 9시
		for (int i = 0; i < n; i++) {
			int remain = m;// 남아 있는 버스 좌석 수
			int time = 0;// 크루의 도착 시간
			int cnt = 0;// 해당 시간에 도착하는 크루의 수

			while (!timeMap.isEmpty()) {
				time = timeMap.firstKey();
				cnt = timeMap.get(time);

				if (time > bustime)
					break;

				if (cnt >= remain) {// 크루 수 >= 남아 있는 버스 좌석 수
					if (cnt == remain)
						timeMap.remove(time);// 다 탈 수 있으므로 삭제
					else {
						timeMap.put(time, cnt - remain);
					}
					remain = 0;// 남아 있는 좌석이 없으므로
					break;
				} else {// 크루 수 < 남아 있는 버스 좌석 수
					timeMap.remove(time);// 다 탈 수 있으므로 삭제
					remain -= cnt;
				}
			}

			if (i == n - 1) {
				if (remain != 0) {// 남아있는 좌석이 있을 때
					answer = itos(bustime);// 버스 도착 시간에 도착
				} else {// 남아 있는 좌석 없을 때
					answer = itos(time - 1);// 가장 마지막에 탄 크루보다 1분 빨리 도착
				}
			} else {
				bustime += t;// 다음 버스
			}
		}
		return answer;
	}

	public int stoi(String s) {
		String[] splited = s.split("[:]");
		int hr = Integer.parseInt(splited[0]);
		int min = Integer.parseInt(splited[1]);

		return hr * 60 + min;
	}

	public String itos(int i) {
		int hr = i / 60;
		int min = i % 60;

		String shr = Integer.toString(hr);
		String smin = Integer.toString(min);

		if (hr < 10)
			shr = "0" + shr;
		if (min < 10)
			smin = "0" + smin;

		return shr + ":" + smin;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_셔틀버스().solution(1, 1, 5, new String[] { "08:00", "08:01", "08:02", "08:03" }));
		System.out.println(new PGS_셔틀버스().solution(2, 10, 2, new String[] { "09:10", "09:09", "08:00" }));
		System.out.println(new PGS_셔틀버스().solution(2, 1, 2, new String[] { "09:00", "09:00", "09:00", "09:00" }));
		System.out.println(
				new PGS_셔틀버스().solution(1, 1, 5, new String[] { "00:01", "00:01", "00:01", "00:01", "00:01" }));
		System.out.println(new PGS_셔틀버스().solution(1, 1, 1, new String[] { "23:59" }));
		System.out.println(
				new PGS_셔틀버스().solution(10, 60, 45, new String[] { "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
						"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59" }));
	}
}
