package A2021_01_25;

import java.util.*;

/**
 * Sliding Window
 * 
 * @author beaverbae
 *
 */
public class PGS_징검다리_건너기Fail {

	public int solution(int[] stones, int k) {
		Deque<Integer> dq = new LinkedList<>();
		int dq_max = 0;

		for (int i = 0; i < k; i++) {
			dq.add(stones[i]);
			dq_max = Math.max(dq_max, stones[i]);
		}

		int answer = dq_max;
		for (int i = k; i < stones.length; i++) {
			int first = dq.poll();

			dq.add(stones[i]);
			if (first == dq_max) {// dq_max가 날라간 경우
				dq_max = 0;
				for (int value : dq) {
					dq_max = Math.max(dq_max, value);
				}
			} else {// 아닌 경우
				if (dq_max < dq.peekLast()) {// 새로 들어온 값이 더 큰 경우
					dq_max = dq.peekLast();// 갱신
				}
			}

			answer = Math.min(answer, dq_max);
		}
		return answer;
	}

	public static void main(String[] args) {
		new PGS_징검다리_건너기Fail().solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3);
	}
}
