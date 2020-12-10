package A2020_12_08;

import java.util.*;

public class PGS_불량사용자 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "abc1**" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "*rodo", "*rodo", "******" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "*rodo", "******", "******" }));
	}

	static int answer;
	static boolean[] visited;
	static int N;
	static int B;
	static String[] selected,result;
	static HashSet<String> set;

	public static int solution(String[] user_id, String[] banned_id) {
		N = user_id.length;
		B = banned_id.length;
		visited = new boolean[N];
		selected = new String[B];
		result = new String[B];
		set = new HashSet<>();

		dfs(0, 0, user_id, banned_id);

		answer = set.size();
		return answer;
	}
	
	//n개에서 B개 만큼 선택(조합)
	public static void dfs(int start, int r, String[] user_id, String[] banned_id) {
		if (r == B) {
			String[] copy_selected = new String[B];
			for (int i = 0; i < copy_selected.length; i++) {
				copy_selected[i] = selected[i];
			}
			dfs2(0,copy_selected,banned_id);
			return;
		}

		for (int i = start; i < user_id.length; i++) {
			selected[r] = user_id[i];
			dfs(i + 1, r + 1, user_id, banned_id);
		}
	}
	
	//n! 실행
	public static void dfs2(int r, String[] copy_selected,String[] banned_id) {
		if(r==B) {
//			System.out.println(Arrays.toString(result));
//			System.out.println(Arrays.toString(banned_id));
//			System.out.println();
			
			boolean flag = true;
			for (int i = 0; i < result.length; i++) {
				String s1 = result[i];
				String s2 = banned_id[i];
					
				if(s1.length()==s2.length()) {
					for (int j = 0; j < s1.length(); j++) {
						char c1 = s1.charAt(j);
						char c2 = s2.charAt(j);
						if(c2=='*') continue;
						else if(c1!=c2) {
							flag = false;
							break;
						}
					}
				}else {
					flag = false;
					break;
				}
			}
			if(flag) {
				//Arrays.sort(result);
				String[] copy_result = new String[B];
				for (int i = 0; i < result.length; i++) {
					copy_result[i] = result[i];
				}
				Arrays.sort(copy_result);
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < copy_result.length; i++) {
					sb.append(copy_result[i]).append(' ');
				}
				set.add(sb.toString());
			}
			return;
		}
		
		for (int i = 0; i < copy_selected.length; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			result[r] = copy_selected[i];
			dfs2(r+1, copy_selected, banned_id);
			visited[i] = false;
		}
	}
}
