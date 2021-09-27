package A2021_06_21;

/**
 * 
 * @author beaverbae
 * @see https://velog.io/@junhok82/lowerbound-upperbound
 * 
 */

public class Lower_Upper_bound {
	static int[] arr = {1, 2, 3, 3, 3, 4, 5, 6};
	static int N = arr.length;
	static final int target = 3;
	
	public static void main(String[] args) {
		System.out.println(lower_bound());
		System.out.println(upper_bound());
	}
	
	private static int lower_bound() {
		int left = 0;
		int right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	private static int upper_bound() {
		int left = 0;
		int right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
}
