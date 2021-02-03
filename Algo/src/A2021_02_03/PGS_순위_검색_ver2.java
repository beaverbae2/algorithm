package A2021_02_03;

import java.util.*;

/**
 * Subset + Binary Search
 * 
 * @author beaverbae
 * @see ?
 */

public class PGS_순위_검색_ver2 {
	private HashMap<String, ArrayList<Integer>> map = new HashMap<>();// <info에서 만들 수 있는 모든 쿼리, 점수>

	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		for (int i = 0; i < info.length; i++) {
			String[] splitInfo = info[i].split(" ");

			int score = Integer.parseInt(splitInfo[splitInfo.length - 1]);
			subset(splitInfo, "", 0, 4, score);
		}

		// 점수를 오름 차순 정렬
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			Collections.sort(map.get(s));
		}

		int idx = 0;
		for (String q : query) {
			String[] split = q.split(" ");

			q = q.replace(" and", "");

			int target = Integer.parseInt(split[split.length - 1]);
			q = q.substring(0, q.length() - split[split.length - 1].length());

			if (map.get(q) == null) {// 아예 없는 경우
				answer[idx++] = 0;
				continue;
			}

			answer[idx++] = binary_search(target, map.get(q));
		}
		return answer;
	}

	// 이분탐색으로 target점수 이상의 개수 구하기
	public int binary_search(int target, ArrayList<Integer> scores) {
		int start = 0;
		int end = scores.size() - 1;
		int N = scores.size();
		int idx = scores.size();

		while (start <= end) {
			int mid = (start + end) / 2;

			if (scores.get(mid) < target) {
				start = mid + 1;
			} else {
				idx = mid;
				end = mid - 1;
			}
		}

		return N - idx;
	}

	// 부분집합으로 info에서 만들수 있는 쿼리를 구하고 점수와 같이 map에 저장
	public void subset(String[] splitInfo, String s, int cnt, int n, int score) {
		if (cnt == n) {
			if (!map.containsKey(s)) {
				map.put(s, new ArrayList<>());
			}
			map.get(s).add(score);
			return;
		}

		subset(splitInfo, s + "-" + " ", cnt + 1, n, score);
		subset(splitInfo, s + splitInfo[cnt] + " ", cnt + 1, n, score);
	}

	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(Arrays.toString(new PGS_순위_검색_ver2().solution(info, query)));
	}
}
