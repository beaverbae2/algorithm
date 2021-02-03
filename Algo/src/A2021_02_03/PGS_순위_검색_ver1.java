package A2021_02_03;

import java.util.*;
/**
 * Subset + Binary Search
 * 
 * @author beaverbae
 * @see https://ryulurala.tistory.com/100
 *
 */

public class PGS_순위_검색_ver1 {
	static HashMap<String, Integer> map = new HashMap<>();
	static ArrayList<Integer> selected = new ArrayList<>();
	static ArrayList<Integer>[][][][] list = new ArrayList[4][3][3][3];

	public int[] solution(String[] info, String[] query) {
		map.put("-", 0);
		map.put("cpp", 1);
		map.put("java", 2);
		map.put("python", 3);

		map.put("backend", 1);
		map.put("frontend", 2);

		map.put("junior", 1);
		map.put("senior", 2);

		map.put("chicken", 1);
		map.put("pizza", 2);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						list[i][j][k][l] = new ArrayList<>();
					}
				}
			}
		}

		for (int i = 0; i < info.length; i++) {
			String[] split = info[i].split(" ");
			boolean[] visited = new boolean[split.length];

			subset(0, split, visited);
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						Collections.sort(list[i][j][k][l]);
					}
				}
			}
		}

		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String[] split = query[i].split(" and ");
			String[] split2 = split[split.length - 1].split(" ");
			String food = split2[0];
			int point = Integer.parseInt(split2[1]);

			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < split.length - 1; j++) {
				temp.add(map.get(split[j]));
			}
			temp.add(map.get(food));

			int a = temp.get(0);
			int b = temp.get(1);
			int c = temp.get(2);
			int d = temp.get(3);

			int N = list[a][b][c][d].size();
			
			int cnt = N - binary_search(point, list[a][b][c][d]);
			answer[i] = cnt;
		}
		return answer;
	}
	
	public int binary_search(int point, ArrayList<Integer> list) {
		int left = 0;
		int right = list.size()-1;
		
		while(left < right) {
			int mid = (left + right)/2;
			
			if (list.get(mid) < point) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		
		return left;
		
	}

	public void subset(int cnt, String[] input, boolean[] visited) {
		if (cnt == input.length - 1) {
			int a = selected.get(0);
			int b = selected.get(1);
			int c = selected.get(2);
			int d = selected.get(3);

			list[a][b][c][d].add(Integer.parseInt(input[input.length - 1]));
			return;
		}

		visited[cnt] = true;
		selected.add(0);
		subset(cnt + 1, input, visited);
		selected.remove(cnt);

		visited[cnt] = false;
		selected.add(map.get(input[cnt]));
		subset(cnt + 1, input, visited);
		selected.remove(cnt);
	}

	public static void main(String[] args) {
		new PGS_순위_검색_ver1().solution(
				new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
						"python frontend senior chicken 150", "cpp backend senior pizza 260",
						"java backend junior chicken 80", "python backend senior chicken 50" },
				new String[] { "java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
						"- and backend and senior and - 150", "- and - and - and chicken 100",
						"- and - and - and - 150" });
	}
}
