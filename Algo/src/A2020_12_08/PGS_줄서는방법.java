package A2020_12_08;

import java.util.*;

public class PGS_줄서는방법 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(3, 5)));
		System.out.println(Arrays.toString(solution(4, 10)));
		System.out.println(Arrays.toString(solution(5, 2)));
	}
	
	public static int[] solution(int n, long k) {
		int[] answer = new int[n];
		ArrayList<Integer> nums = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		long K = k-1;
		int N = n-1;
		int idx = 0;
		while(true) {
			if(N<0) break; 
			
			long F = getFactorial(N);
			int value = (int) (K/F);
			answer[idx] = nums.get(value);
			nums.remove(value);
			
			K = K%F;
			N--;
			idx++;
		}
		
		
		return answer;
	}
	
	public static long getFactorial(int n) {
		if(n==1||n==0) return 1;
		return n*getFactorial(n-1);
	}
}
