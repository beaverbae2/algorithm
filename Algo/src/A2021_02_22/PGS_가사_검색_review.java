package A2021_02_22;

import java.util.*;

/**
 * Trie
 * 
 * @author beaverbae
 *
 */

public class PGS_가사_검색_review {

	public int[] solution(String[] words, String[] queries) {
		Trie[] trie = new Trie[10001];
		Trie[] rev_trie = new Trie[10001];

		for (String word : words) {
			int len = word.length();

			if (trie[len] == null) {
				trie[len] = new Trie();
			}
			trie[len].insert(word);

			StringBuilder reverse = new StringBuilder();
			for (int i = word.length() - 1; i >= 0; i--) {
				reverse.append(word.charAt(i));
			}

			if (rev_trie[len] == null) {
				rev_trie[len] = new Trie();
			}
			rev_trie[len].insert(reverse.toString());
		}

		int idx = 0;
		int[] answer = new int[queries.length];

		for (String query : queries) {
			int len = query.length();
			if (query.charAt(0) == '?') {
				if (rev_trie[len] != null) {
					StringBuilder reverse = new StringBuilder();
					for (int i = query.length() - 1; i >= 0; i--) {
						reverse.append(query.charAt(i));
					}

					answer[idx] = rev_trie[len].findCnt(reverse.toString());
				}
			} else {
				if (trie[len] != null) {
					answer[idx] = trie[len].findCnt(query);
				}
			}
			idx++;
		}
		return answer;
	}

	static class Trie {
		Node root;

		static class Node {
			HashMap<Character, Node> children;
			int cnt;

			Node() {
				children = new HashMap<>();
			}
		}

		Trie() {
			root = new Node();
		}

		void insert(String word) {
			Node node = root;

			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);

				node.cnt++;
				if (!node.children.containsKey(ch)) {
					node.children.put(ch, new Node());
				}

				node = node.children.get(ch);
			}
		}

		int findCnt(String query) {
			Node node = root;

			for (int i = 0; i < query.length(); i++) {
				char ch = query.charAt(i);
				if (ch == '?') {
					return node.cnt;
				}

				if (!node.children.containsKey(ch)) {
					return 0;
				}

				node = node.children.get(ch);
			}
			return node.cnt;
		}
	}

	public static void main(String[] args) {
		new PGS_가사_검색_review().solution(new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" },
				new String[] { "fro??", "????o", "fr???", "fro???", "pro?" });
	}
}
