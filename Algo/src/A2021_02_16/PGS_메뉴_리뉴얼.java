package A2021_02_16;

import java.util.*;

/**
 * 54MIN
 * DFS
 * @author beaverbae
 *
 */

public class PGS_메뉴_리뉴얼 {
	HashMap<String, Integer> map;
	List<Pair> list;
	char[] input;
	char[] selected;
	TreeSet<String> set;

	public String[] solution(String[] orders, int[] course) {
		set = new TreeSet<>();

		for (int n : course) {
			map = new HashMap<>();
			list = new ArrayList<>();

			for (String order : orders) {
				input = order.toCharArray();
				Arrays.sort(input);
				selected = new char[n];
				combination(n, 0, 0, "");
			}

			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String s = iter.next();
				int cnt = map.get(s);
				Pair p = new Pair(s, cnt);
				list.add(p);
			}

			Collections.sort(list, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o2.cnt, o1.cnt);
				}
			});

			if (list.isEmpty())
				continue;

			int max = list.get(0).cnt;

			if (max < 2)
				continue;

			for (int i = 0; i < list.size(); i++) {
				Pair p = list.get(i);
				if (p.cnt < max)
					break;
				else {
					set.add(p.order);
				}
			}

		}

		String[] answer = new String[set.size()];
		int idx = 0;
		for (String order : set) {
			answer[idx] = order;
			idx++;
		}
		
		return answer;
	}

	void combination(int n, int start, int cnt, String s) {
		if (cnt == n) {
			if (map.containsKey(s)) {
				int v = map.get(s);
				map.put(s, v + 1);
			} else {
				map.put(s, 1);
			}
			return;
		}

		for (int i = start; i < input.length; i++) {
			combination(n, i + 1, cnt + 1, s + input[i]);
		}
	}

	static class Pair {
		String order;
		int cnt;

		public Pair(String order, int cnt) {
			this.order = order;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Pair [order=" + order + ", cnt=" + cnt + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_메뉴_리뉴얼().solution(new String[] { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" },
				new int[] { 2, 3, 4 });
		new PGS_메뉴_리뉴얼().solution(new String[] { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" },
				new int[] { 2, 3, 5 });
		new PGS_메뉴_리뉴얼().solution(new String[] { "XYZ", "XWY", "WXA" }, new int[] { 2, 3, 4 });
	}
}
