package A2020_09_24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_5052_전화번호목록 {
	static int T,N;
	static StringBuilder sb = new StringBuilder();
	static HashMap<String,Boolean> tels;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			tels = new HashMap<>();
			N = Integer.parseInt(br.readLine());
			boolean flag = true;
			
			for (int n = 0; n < N; n++) {
				String tel = br.readLine();
				for (int i = 0; i < tel.length()-1; i++) {
					String subTel = tel.substring(0,i+1);
					System.out.println(subTel);
					if(tels.get(subTel)==null) continue;
					
					else if(tels.get(subTel)){
						flag = false;
						break;
					}
				}
				if(!flag) break;
				else tels.put(tel, true);
			}
			
			if(flag) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}
}
