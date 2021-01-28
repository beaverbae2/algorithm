package A2021_01_18;

import java.util.*;
/**
 * 특정 위치의 순열 구하기
 * @author beaverbae
 * @see https://zin0-0.tistory.com/67
 */

public class PGS_줄서는_방법 {
	public int[] solution(int n, long k) {
		int[] answer = new int[n];
		
		// 숫자 리스트
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		// factorial 구하기
		long[] factorial = new long[n+1];
		Arrays.fill(factorial, 1);
		for (int i = 2; i < factorial.length; i++) {
			factorial[i] *= factorial[i-1] * i;
		}
		
		//순열 구하기
		int num = n-1;
		int idx = 0;
		long K = k-1;
		
		while(idx < n) {
			int div = (int) (K / factorial[num]);
			long mod = (K % factorial[num]);
			
			answer[idx] = list.get(div);
			list.remove(div);
			idx++;
			num--;
			
			K = mod;
		}
		
		return answer;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 24; i++) {
			System.out.println(Arrays.toString(new PGS_줄서는_방법().solution(4, i)));
		}
		//System.out.println(Arrays.toString(new PGS_줄서는_방법().solution(3, 5)));
		//System.out.println(Arrays.toString(new PGS_줄서는_방법().solution(4, 17)));
		//System.out.println(Arrays.toString(new PGS_줄서는_방법().solution(20, 2000000000)));
		//System.out.println(Arrays.toString(new PGS_줄서는_방법().solution(20, 2000000001)));
	}
}
