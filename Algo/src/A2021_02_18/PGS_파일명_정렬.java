package A2021_02_18;

import java.util.*;

/**
 * String
 * 
 * @author beaverbae
 *
 */

public class PGS_파일명_정렬 {
	public String[] solution(String[] files) {
		LinkedList<Node> list = new LinkedList<>();

		String[] temp_files = new String[files.length];

		for (int i = 0; i < files.length; i++) {
			temp_files[i] = files[i].toLowerCase();
		}

		int idx = 0;
		for (String file : temp_files) {
			int stage = 0;
			StringBuilder head = new StringBuilder();
			StringBuilder number = new StringBuilder();
			String tail = "";

			int i = 0;
			for (i = 0; i < file.length(); i++) {
				char ch = file.charAt(i);

				if (stage == 0) {
					if (isNum(ch)) {
						stage = 1;
						number.append(ch);
					} else {
						head.append(ch);
					}
				} else if (stage == 1) {
					if (isNum(ch) && number.length() < 5) {
						number.append(ch);
					} else {
						break;
					}
				}
			}
			
			// tail이 아예 없는 경우도 있으므로
			tail = file.substring(i);
			list.add(new Node(idx, head.toString(), Integer.parseInt(number.toString()), tail));

			idx++;
		}
		Collections.sort(list);

		String[] answer = new String[files.length];
		int i = 0;
		for (Node node : list) {
			answer[i] = files[node.idx];
			i++;
		}
//		System.out.println(Arrays.toString(answer));
		return answer;
	}

	public boolean isNum(char ch) {
		return ch >= '0' && ch <= '9';
	}

	static class Node implements Comparable<Node> {
		int idx;
		String head;
		int number;
		String tail;

		public Node(int idx, String head, int number, String tail) {
			this.idx = idx;
			this.head = head;
			this.number = number;
			this.tail = tail;
		}

		@Override
		public String toString() {
			return "[idx=" + idx + ", head=" + head + ", number=" + number + ", tail=" + tail + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (!this.head.equals(o.head)) {
				return this.head.compareTo(o.head);
			} else {
				return Integer.compare(this.number, o.number);
			}
		}
	}

	public static void main(String[] args) {
		new PGS_파일명_정렬()
				.solution(new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" });
		new PGS_파일명_정렬().solution(
				new String[] { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" });
		new PGS_파일명_정렬().solution(new String[] { "foo010bar020.zip", "F-15" });
	}
}
