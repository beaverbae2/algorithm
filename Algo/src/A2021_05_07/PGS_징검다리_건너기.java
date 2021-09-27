package A2021_05_07;

import java.util.*;

public class PGS_징검다리_건너기 {
	public int solution(int[] stones, int k) {
		int max = 0;
		for (int stone : stones) {
			max = Math.max(max, stone);
		}
		
		return parametric_search(stones, max, k);
	}

	private int parametric_search(int[] stones, int max, int k) {
		int result = -1;
		int left = 0;
		int right = max;
		
		while (left <= right) {
			int mid = (left + right) / 2;// 징검다리 건널 수 있는 인원 수
			
			if (!isGo(stones, mid, k)) {
				right = mid-1;
			} else {
				left = mid+1;
				result = mid;
			}
			
		}
		
		return result;
	}

	private boolean isGo(int[] stones, int mid, int k) {
		int start = -1;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] - mid < 0) continue; 
			
			if (i-start > k) return false;
			else {
				start = i;
			}
		}
		
		if (stones.length - start > k) return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_징검다리_건너기().solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
	}
}
