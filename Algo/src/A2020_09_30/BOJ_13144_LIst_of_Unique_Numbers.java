package A2020_09_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//투포인터
public class BOJ_13144_LIst_of_Unique_Numbers {
	static int N, answer;
	static int[] input;
	static boolean[] isNumVisited = new boolean[100001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		
		while(true) {
			if(end==input.length) break;
			
			if(!isNumVisited[input[end]]) {
				answer += end-start+1;
				isNumVisited[input[end]] = true;
				end++;
			}else { 
				isNumVisited[input[start]] = false;
				start++;
			}
		}
		System.out.println(answer);
	}
}
