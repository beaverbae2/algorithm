package Binary_Search;

import java.util.Arrays;

public class Lower_Upper_bound {
	static int[] arr = { 2, 3, 4, 7, 2, 3, 1, 1, 4, 7, 7, 6 };

	public static void main(String[] args) {
		Arrays.sort(arr);// 반드시 정렬
		System.out.println(Arrays.toString(arr));
		
		// 배열에 존재하는 값이 아닌 경우는 사용하지 않는다
		System.out.println(lower_bound(2));
		System.out.println(upper_bound(2));
		
	}

	private static int lower_bound(int target) {
		int left = 0;
		int right = arr.length - 1;
		int idx = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
				idx = mid;
			}
		}

		return idx;
	}

	private static int upper_bound(int target) {
		int left = 0;
		int right = arr.length - 1;
		int idx = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
				idx = mid;
			}
		}
		
		return idx;
	}
}
