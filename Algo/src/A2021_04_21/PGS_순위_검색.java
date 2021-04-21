package A2021_04_21;

import java.util.*;

/**
 * String, DFS, lower bound
 * 오래 걸린 이유
 * - info에서 - 를 고려한 data 저장 -> subset 활용
 * - lower bound에서 인자로 들어온 score(점수)가 없는 경우 처리
 * @author beaverbae
 *
 */

public class PGS_순위_검색 {
	HashMap<String, Integer> map = new HashMap<>();
	List<Integer>[] list = new List[108]; // 108: 조합 개수(4*3*3*3)
	boolean[] visited;
	
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < info.length; i++) {
			String s = info[i];
			String[] split = s.split(" ");
			
			String[] temp_info = new String[4];
			visited = new boolean[4];
			
			for (int j = 0; j < temp_info.length; j++) {
				temp_info[j] = split[j];
			}
			
			int score = Integer.parseInt(split[4]);
		
			dfs(0, temp_info, score);
		}
		
		for (int i = 0; i < list.length; i++) {
			Collections.sort(list[i]);
		}
		
		for (int i = 0; i < query.length; i++) {
			String q = query[i];
//			String[] split1 = q.split(" and ");
//			String[] split2 = split1[split1.length-1].split(" ");
//			
//			StringBuilder sb = new StringBuilder();
//			for (int j = 0; j < split1.length-1; j++) {
//				sb.append(split1[j]).append(" ");
//			}
//			sb.append(split2[0]).append(" ");
//			
//			String str = sb.toString();
//			int score = Integer.parseInt(split2[1]);
//			
//			if (!map.containsKey(str)) {
//				answer[i] = 0;
//				continue;
//			}
//			
//			int idx = map.get(str);
//			
//			int cnt = getCnt(idx, score);
//			answer[i] = cnt;

			// 더 빠름
			StringBuilder sb = new StringBuilder();
			int start = 0;
			int end = q.indexOf(" and ");
			int add = " and ".length();
			
			for (int j = 0; j < 3; j++) {
				sb.append(q.substring(start, end)).append(" ");
				start = end+add;
				end = q.indexOf(" and ", start);
			}
			
			end = q.indexOf(" ", start);
			sb.append(q.substring(start, end)).append(" ");
			
			String str = sb.toString();
			if (!map.containsKey(str)) {
				answer[i] = 0;
				continue;
			}
			
			int score = Integer.parseInt(q.substring(end+1, q.length()));	
			int idx = map.get(str);
			
			int cnt = getCnt(idx, score);
			answer[i] = cnt;
		}
		
		return answer;
	}

	private int getCnt(int idx, int score) {
		int left = 0;
		int right = list[idx].size()-1;
		int i = list[idx].size();// 중요 score가 list[idx]에 없는 경우생각
		
		// lower bound
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (list[idx].get(mid) < score) {
				left = mid+1;
			} else {
				right = mid-1;
				i = mid;
			}
		}
		
		return list[idx].size() - i;
	}

	private void dfs(int idx, String[] temp_info, int score) {
		if (idx == 4) {
			String str = "";
			for (int i = 0; i < temp_info.length; i++) {
				if (visited[i]) {
					str += temp_info[i]+" ";
				} else {
					str += "-"+" ";
				}
			}
			
			if (!map.containsKey(str)) {
				map.put(str, map.size());
			}
			
			list[map.get(str)].add(score);
			
			return;
		}
		
		visited[idx] = true;
		dfs(idx+1, temp_info, score);
		
		visited[idx] = false;
		dfs(idx+1, temp_info, score);
	}

	public static void main(String[] args) {
		new PGS_순위_검색().solution(new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
	}
}
