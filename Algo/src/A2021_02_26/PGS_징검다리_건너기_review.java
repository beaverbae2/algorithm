package A2021_02_26;

import java.util.*;

/**
 * Parametric Search
 * @author beaverbae
 *
 */

public class PGS_징검다리_건너기_review {
	int max;
	
	public int solution(int[] stones, int k) {
		for (int i = 0; i < stones.length; i++) {
			max = Math.max(max, stones[i]);
		}
		
		return parametric_search(stones, k);
	}
	
	private int parametric_search(int[] stones, int k) {
		int result = 0;
		int start = 0;
		int end = max;
		
		while (start <= end) {
			int mid = (start + end)/2;
			boolean canGo = true;
			
			int zeroCnt = 0;
			for (int i = 0; i < stones.length; i++) {
				if (stones[i] - (mid-1) > 0) {
					zeroCnt = 0;
				} else {
					zeroCnt++;
					if (zeroCnt == k) {
						canGo = false;
						break;
					}
				}
			}
			
			if (canGo) {// 사람 수 늘림
				result = mid;
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_징검다리_건너기_review().solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
	}
}
