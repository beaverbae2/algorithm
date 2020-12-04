package A2020_12_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1543_문서검색 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		String input = br.readLine();
		String target = br.readLine();
		
		
		for (int k = 0; k < input.length(); k++) {
			int temp_answer = 0;
			int same = 0;
			int i = k;
			int j = 0;
			
			while(i<input.length()) {
				char ch1 = input.charAt(i);
				char ch2 = target.charAt(j);
				
				if(ch1 == ch2) {
					same++;
					i++;
					j++;
				}else {
					same = 0;
					i++;
					j = 0;
				}
				
				if(same==target.length()) {
					temp_answer++;
					same = 0;
					j = 0;
				}
			}
			answer = Math.max(answer, temp_answer);
		}
		
		
		System.out.println(answer);
	}
}
