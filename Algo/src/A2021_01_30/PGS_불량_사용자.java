package A2021_01_30;

import java.util.*;

/**
 * DFS
 * @author beaverbae
 *
 */

public class PGS_불량_사용자 {
	private String[] user_id;
	private String[] banned_id;
	private HashSet<String> set;
	private List<String> list;
	private boolean[] visited;
	
	public int solution(String[] user_id, String[] banned_id) {
		this.user_id = user_id;
		this.banned_id = banned_id;
		set = new HashSet<>();
		list = new ArrayList<>();
		visited = new boolean[user_id.length];
		
		dfs(0);
		return set.size();
	}
	
	public void dfs(int cnt) {
		if (cnt == banned_id.length) {
			List<String> temp_list = new ArrayList<>();
			for (String str : list) {
				temp_list.add(str);
			}
			Collections.sort(temp_list);
			StringBuilder sb = new StringBuilder();
			for (String str : temp_list) {
				sb.append(str).append(" ");
			}
			set.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < user_id.length; i++) {
			if (visited[i] || user_id[i].length() != banned_id[cnt].length() || !check(i, cnt)) {
				continue;
			}
			
			visited[i] = true;
			list.add(user_id[i]);
			dfs(cnt+1);
			visited[i] = false;
			list.remove(cnt);
		}
	}
	
	public boolean check(int user_idx, int ban_idx) {
		for (int i = 0; i < banned_id[ban_idx].length(); i++) {
			char user_ch = user_id[user_idx].charAt(i);
			char ban_ch = banned_id[ban_idx].charAt(i);
			
			if (ban_ch == '*') { 
				continue;
			}
			else if (user_ch != ban_ch){
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		new PGS_불량_사용자().solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "abc1**"});
		new PGS_불량_사용자().solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"*rodo", "*rodo", "******"});
		new PGS_불량_사용자().solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"});
	}
}
