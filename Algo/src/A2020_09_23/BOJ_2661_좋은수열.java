package A2020_09_23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//백트래킹
public class BOJ_2661_좋은수열 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs(0,"");
	}
	private static void dfs(int cnt, String str) {
		if(cnt==N) {
			System.out.println(str);
			System.exit(0);
		}
		
		for (int i = 1; i <= 3; i++) {
			String next_str = str+i;
			if(isOk(next_str)) dfs(cnt+1,next_str);
		}
	}
	private static boolean isOk(String str) {
		if(str.equals("")) return true;
		else {
			int total_cnt = str.length()/2;
			int cnt = 0;
			int last_idx = str.length()-1;
			int idx = 0;
			while(true) {
				int next_idx = last_idx - 2*idx-1;
				if(next_idx<0) break;
			
				int mid_idx = 1+(next_idx+last_idx)/2;
				String left = str.substring(next_idx,mid_idx);
				String right = str.substring(mid_idx);
			
				if(!left.equals(right)) cnt++;
				idx++;
			}
			if(total_cnt==cnt) return true;
			else return false;
		}
	}
	
	
}
