package A2021_01_25;

import java.util.*;
import java.io.*;
/**
 * 
 * @author beaverbae
 * @see https://h-kyung.tistory.com/2
 *
 */

 
public class BOJ_1790_수_이어쓰기2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long remain_K = K;
		long digitCnt = 9;// 자리수의 숫자 개수 
		long digit = 1;// 자리수 : 1부터 시작
		long cnt = digitCnt * digit;// 총 숫자의 개수
		long num = 0;// k번쨰 수를 가지고 있는 실제 숫자
		
		while (remain_K > cnt) {
			remain_K -= cnt;
			num += digitCnt;
			
			digit++;
			digitCnt *= 10;
			cnt = digitCnt * digit;
		}
		
		num = (num + 1) + (remain_K - 1) / digit;// (맨 앞자리 숫자) + (나머지 자리수 숫자)
		if (num > N) {// 범위 벗어난 경우
			System.out.println(-1);
		}else {
			int idx = (int) ((remain_K - 1) % digit);// k번쨰 수의 위치
			System.out.println(String.valueOf(num).charAt(idx));
		}
	}
}
