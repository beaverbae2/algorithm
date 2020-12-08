package A2020_12_08;

import java.util.*;

public class PGS_불량사용자ver2 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "abc1**" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "*rodo", "*rodo", "******" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "*rodo", "******", "******" }));
	}

	static boolean[] visited;
	static HashSet<String> set;

	public static int solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		set = new HashSet<>();
		visited = new boolean[user_id.length];
		dfs(0, "" , user_id, banned_id);
		answer = set.size();
		return answer;
	}

	private static void dfs(int r, String str, String[] user_id, String[] banned_id) {
		if(r==banned_id.length) {
			String[] splited = str.split(" ");
			Arrays.sort(splited);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < splited.length; i++) {
				sb.append(splited[i]).append(' ');
			}
			set.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < user_id.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			if(checkisSame(user_id[i], banned_id[r])) {
				dfs(r+1, str+" "+user_id[i], user_id, banned_id);
			}
			visited[i] = false;
		}
	}

	private static boolean checkisSame(String user, String ban) {
		if(user.length()==ban.length()) {
			for (int i = 0; i < user.length(); i++) {
				char user_ch = user.charAt(i);
				char ban_ch = ban.charAt(i);
				if(ban_ch == '*') continue;
				if(ban_ch != user_ch) return false;
			}
			return true;
		}
		return false;
	}
	
	

}
