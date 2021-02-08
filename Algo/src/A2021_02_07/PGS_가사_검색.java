package A2021_02_07;

import java.util.*;
/**
 * 
 * @author beaverbae
 * @see https://blog.junghl.ee/ps/programmers/lyrics-search-60060/
 *
 */
public class PGS_가사_검색 {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		
		Trie[] trie = new Trie[10001];
		Trie[] rev = new Trie[10001];
		
 		
		for (String word : words) {
			int len = word.length();
			
			if (trie[len] == null) {
				trie[len] = new Trie();// 루트 노드
			}
			trie[len].insert(word);
			
			
			if (rev[len] == null) {
				rev[len] = new Trie();// 루트 노드
			}
			rev[len].insert(reverse(word));
		}
		
		for (int i = 0; i < queries.length; i++){
			String query = queries[i];
			int len = query.length();
			
			
			if (query.charAt(0) == '?') {
				if (rev[len] != null) {
					answer[i] = rev[len].getChildrenCount(reverse(query));
				}
			}else {
				if (trie[len] != null) {
					answer[i] = trie[len].getChildrenCount(query);
				}
			}
		}
		return answer;
	}
	
	private String reverse(String word) {
		StringBuilder sb = new StringBuilder();
		for (int i = word.length()-1; i >= 0; i--) {
			sb.append(word.charAt(i));
		}
		return sb.toString();
	}

	
	static class Trie {
		private final Node root;
		
		static class Node { // node
			private final HashMap<Character, Node> children;
			private boolean leaf; 
			private int cnt;
			
			Node() {
				children = new HashMap<>();
			}
		}
		
		Trie(){
			root = new Node();
		}
		
		
		public void insert(String str) {
			Node cur = root;
			int len = str.length();
			
			for (int i = 0; i < len; i++) {
				char ch = str.charAt(i);
			
				cur.cnt++;
				Node child = cur.children.get(ch);
				
				if (child == null) {
					child = new Node();
					cur.children.put(ch, child);
				}
				cur = child;
			}
			cur.leaf = true;
		}
		
		public int getChildrenCount(String str) {
			Node cur = root;
			int len = str.length();
			
			for (int i = 0; i < len; i++) {
				char ch = str.charAt(i);
				
				if (ch == '?') {
					return cur.cnt;
				}
				
				Node child = cur.children.get(ch);
				if (child == null) {
					return 0;
				}
				cur = child;
			}
			return cur.cnt;
		}
	}

	public static void main(String[] args) {
		new PGS_가사_검색().solution(new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??", "????o", "fr???", "fro???", "pro?"});
	}
}
