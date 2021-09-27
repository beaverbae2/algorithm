package A2021_04_09;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {6, 15, 5, 7, 25, 12};
		bubble_sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void bubble_sort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {// 끝 범위
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j+1]) {
					// swap
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
}

