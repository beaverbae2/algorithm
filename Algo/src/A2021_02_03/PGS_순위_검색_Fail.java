package A2021_02_03;

import java.util.*;

public class PGS_순위_검색_Fail {
	public int[] solution(String[] info, String[] query) {
		String[][] appliers = new String[info.length][5];
		
		for (int i = 0; i < info.length; i++) {
			String s = info[i];
			String[] split = info[i].split(" ");
			for (int j = 0; j < split.length; j++) {
				appliers[i][j] = split[j];
			}
		}
		
		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String s = query[i];
			String[] split = s.split(" and ");
			int cnt = 0;
			for (int j = 0; j < appliers.length; j++) {
				int temp = 0;
				for (int k = 0; k < split.length; k++) {
					if (k != split.length-1) {
						if (split[k].equals("-") || split[k].equals(appliers[j][k])) {
							temp++;
						}else {
							break;
						}
					}else {
						int idx = split.length - 1;
						String[] split2 = split[idx].split(" ");
						
						for (int l = 0; l < split2.length; l++) {
							if (l != split2.length - 1) {
								if (split2[l].equals("-") || split2[l].equals(appliers[j][idx+l])) {
									temp++;
								}else break;
							}else {
								int a = Integer.parseInt(appliers[j][idx+l]);
								int b = Integer.parseInt(split2[l]);
								
								if (a >= b) temp++;
								else break;
							}
						}
						
						
						
						
					}
					if (temp == 5) cnt++;
				}
			}
			answer[i] = cnt;
		}
		
		return answer;
	}

	public static void main(String[] args) {
		new PGS_순위_검색_Fail().solution(
				new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
						"python frontend senior chicken 150", "cpp backend senior pizza 260",
						"java backend junior chicken 80", "python backend senior chicken 50" },
				new String[] { "java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
						"- and backend and senior and - 150", "- and - and - and chicken 100",
						"- and - and - and - 150" });
	}
}
