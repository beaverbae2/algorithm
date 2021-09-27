package A2021_03_18;

/**
 * Parametric Search
 * 38MIN
 * @author beaverbae
 *
 */

public class PGS_징검다리_건너기 {
	int left = 1, right;
	int N;
	
	public int solution(int[] stones, int k) {
		N = stones.length;
		for (int stone : stones) {
			right = Math.max(right, stone);
		}
		
		return parametric_search(stones, k);
	}
	
	
	private int parametric_search(int[] stones, int k) {
		int ans = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (canGo(stones, mid, k)) {// 인원 더 늘림
				ans = mid;
				left = mid+1;
			} else {// 인원 줄임
				right = mid-1;
			}
		}
		
		return ans;
	}
	
	private boolean canGo(int[] stones, int mid, int k) {
		int start = -1;
		int i;
		for (i = 0; i < stones.length; i++) {
			if (stones[i] - mid >= 0) {
				if (i - start <= k) start = i;
				else return false;
			} else {
				if (i - start > k) return false;
			}
		}
		
		if (N - start <= k) return true; 
		
		return false;
	}


	public static void main(String[] args) {
		System.out.println(new PGS_징검다리_건너기().solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));// 3
	}
}
