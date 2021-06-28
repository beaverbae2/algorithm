package A2021_06_29;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 백준 알고리즘 분류 참조
 * 어려웠던 이유
 * - BFS로 접근 -> 시간 및 공간 복잡도 감당 안됨을 느낌
 * - 입력받은 배열의 모든 값을 0으로 만드는 연산의 최소 횟수로 생각
 * 
 * @author beaverbae
 *
 */

public class BOJ_12931_두_배_더하기 {
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		while(!isAllZero()) {
			int oddCnt = getOddChangeCnt();
			if (oddCnt == 0) {
				divAllBy2();
				ans++;
			} else {
				ans += oddCnt;
			}
		}
		System.out.println(ans);
	}

	private static boolean isAllZero() {
		for (int n : arr) {
			if (n != 0) return false;
		}
		
		return true;
	}
	
	private static int getOddChangeCnt() {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			if (arr[i] % 2 != 0) {
				arr[i] -= 1;
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static void divAllBy2() {
		for (int i = 0; i < N; i++) {
			arr[i] = arr[i] / 2;
		}
	}
}
