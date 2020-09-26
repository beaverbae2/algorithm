package A2020_09_23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2661_좋은수열Fail {
	static ArrayList<String>[] answer = new ArrayList[81]; 
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < answer.length; i++) {
			answer[i] = new ArrayList<String>();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		answer[1].add("1");
		answer[1].add("2");
		answer[1].add("3");
		
		for (int i = 2; i <= N; i++) {
			String temp_str = "";
			for (int j = 0; j <answer[i-1].size(); j++) {
				int total_cnt = i/2;
				for (int k = 1; k <= 3; k++) {
					temp_str = answer[i-1].get(j)+Integer.toString(k);
					int cnt = 0;
					int last_idx = temp_str.length()-1;
					int idx = 0;
					while(true) {
						int next_idx = last_idx - 2*idx-1;
						if(next_idx<0) break;
					
						int mid_idx = 1+(next_idx+last_idx)/2;
						String left = temp_str.substring(next_idx,mid_idx);
						String right = temp_str.substring(mid_idx);
					
						if(!left.equals(right)) cnt++;
						idx++;
					}
					if(total_cnt==cnt) {
						answer[i].add(temp_str);
					}
				}
				
				
			}
		}
		System.out.println(answer[N]);
		System.out.println(answer[N].get(0));
	}
}
