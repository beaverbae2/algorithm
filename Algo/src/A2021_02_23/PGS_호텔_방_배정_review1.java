package A2021_02_23;

import java.util.*;


/**
 * Recursion
 * @author beaverbae
 *
 */

public class PGS_호텔_방_배정_review1 {
	HashMap<Long, Long> room = new HashMap<>();
	long v;
	
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		
		for (int i = 0; i < room_number.length; i++) {
			getRoom(room_number[i]);
			answer[i] = v;
			
		}
		return answer;
	}
	
	public long getRoom(long n) {
		if (!room.containsKey(n)) {
			room.put(n, n+1);
			v = n;
			return n;
		}
		
		long next = room.get(n);
		return room.put(n, getRoom(next));
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new PGS_호텔_방_배정_review1().solution(10, new long[] {1,3,4,1,3,1})));
	}
}