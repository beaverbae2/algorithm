package A2020_12_21;

import java.util.*;

//https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC-Java
public class PGS_입국심사 {
	public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;
		Arrays.sort(times);

		// binary search
		long left = 1;
		long right = (long) n * times[times.length - 1];
		
		while (left <= right) {
			long mid = (left + right) / 2;

			if (isPassed(times, n, mid)) {
				answer = Math.min(answer, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}

	public boolean isPassed(int[] times, int n, long mid) {
		long cnt = 0;

		for (int i = 0; i < times.length; i++) {
			cnt += mid / times[i];
		}

		if (cnt >= n)
			return true;

		return false;
	}

	public static void main(String[] args) {
		PGS_입국심사 a = new PGS_입국심사();
		System.out.println(a.solution(6, new int[] { 7, 10 }));
	}
}
