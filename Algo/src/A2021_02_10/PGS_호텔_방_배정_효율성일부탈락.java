package A2021_02_10;

import java.util.*;

/**
 * Map + recursion
 * 
 * @author beaverbae
 * @see https://bcp0109.tistory.com/entry/Kakao-2019-Internship-Winter-%ED%98%B8%ED%85%94-%EB%B0%A9-%EB%B0%B0%EC%A0%95-Java
 *
 */

public class PGS_호텔_방_배정_효율성일부탈락 {
	HashMap<Long, Long> map = new HashMap<>();

	public long[] solution(long k, long[] room_number) {
		long[] answer = {};
		LinkedList<Long> list = new LinkedList<>();
		
		for (long i = 1; i <= k; i++) {
			map.put(i, i);
		}
		
		for (long customer : room_number) {
			list.add(getNextRoom(customer));
		}

		answer = new long[list.size()];
		int idx = 0;
		for (long n : list) {
			answer[idx] = n;
			idx++;
		}
		
		return answer;
	}

	private long getNextRoom(long n) {
		if (map.get(n) == n) {// 빈방
			map.put(n, n + 1);
			return n;
		}
		long nextRoom = getNextRoom(map.get(n));
		map.put(n, nextRoom);
		return map.get(n);
	}

	public static void main(String[] args) {
		new PGS_호텔_방_배정_효율성일부탈락().solution(10, new long[] { 1, 3, 4, 1, 3, 1 });
	}
}
