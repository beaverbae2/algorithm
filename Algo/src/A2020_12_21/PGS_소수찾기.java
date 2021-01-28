package A2020_12_21;

import java.util.*;

public class PGS_소수찾기 {
	private HashMap<Integer, Boolean> map;
	private char[] numberArr;
	private boolean[] visited;
	private int n, answer;

	public int solution(String numbers) {
		map = new HashMap<>();
		numberArr = numbers.toCharArray();
		n = numberArr.length;
		answer = 0;
		visited = new boolean[n];
		
		for (int i = 1; i <= n; i++) {
			permutation(i, 0, "");
		}
		
		return answer;
	}
	
	public void permutation(int n, int r, String str) {
		if (r == n) {

			int num = Integer.parseInt(str);
			if (map.get(num) == null) {
				if (isPrime(num)) {
					System.out.println(num);
					answer++;
				}
				map.put(num, true);
			}
			
			return;
		}
		
		for (int i = 0; i < numberArr.length; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			permutation(n, r+1, str+numberArr[i]);
			visited[i] = false;
		}
	}


	public boolean isPrime(int num) {
		if (num == 1) return false;
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		
		for (int i = 3; i < num; i += 2) {
			if (num % i == 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		PGS_소수찾기 a = new PGS_소수찾기();
		System.out.println(a.solution("17"));
		a = new PGS_소수찾기();
		System.out.println(a.solution("011"));
	}
}
