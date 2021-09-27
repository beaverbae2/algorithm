package A2021_02_23;

import java.util.*;

/**
 * String
 * 
 * @author beaverbae
 *
 */

public class PGS_매칭_점수_review {
	HashMap<String, Integer> indices = new HashMap<>();// url(현재 page) -> index(번호)
	List<Integer> index_list = new ArrayList<>();// indices에서 페이지의 url인 index저장
	LinkedHashMap<Integer, Integer> basic_scores = new LinkedHashMap<>();// index -> 기본 점수
	HashMap<Integer, ArrayList<Integer>> inner_links = new HashMap<>();// 현재 index로 들어오는 외부 index모음 : index -> 링크들
	HashMap<Integer, Integer> outer_link_size = new HashMap<>();// 현재 index에서 외부 index로 나가는 링크의 개수 
	int idx = 0;
	
	public int solution(String word, String[] pages) {
		word = word.toLowerCase();
		for (String page : pages) {
			page = page.toLowerCase();
			int index = getIndex(page);
			getBasicScore(index, word, page);
			findOuterLinks(index, page);
		}

		int index = 0;
		List<Node> list = new ArrayList<>();
		for (int i = 0; i < index_list.size(); i++) {
			int url_index = index_list.get(i);
			double basic_score = (double) basic_scores.get(url_index);
			ArrayList<Integer> inner = inner_links.get(url_index);
			
			double matching_score = basic_score;
			
			if(inner != null) {
				for (int j = 0; j < inner.size(); j++) {
					int link_index = inner.get(j);
					
					double link_size = (double) outer_link_size.get(link_index);
					double link_basic_score = (double) basic_scores.get(link_index);
					
					matching_score += (link_basic_score/link_size);
				}
			}
			
			list.add(new Node(index, matching_score));
			index++;
		}
		
		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.matching_score != o2.matching_score) {
					return Double.compare(o2.matching_score, o1.matching_score);
				} else {
					return Integer.compare(o1.id, o2.id);
				}
			}
		});
		
		return list.get(0).id;
	}
	
	// 현재 페이지 index 구하기
	public int getIndex(String page) {
		String open = "<meta property=\"og:url\" content=\"";
		String close = "\"/>";
		
		int start = page.indexOf(open)+open.length();
		int end = page.indexOf(close, start);
		
		String url = page.substring(start, end);
		
		if (!indices.containsKey(url)) {// url이 없는 경우
			indices.put(url, idx);
			index_list.add(idx);
			idx++;
		} else {// 이미 url이 등록 된 경우
			index_list.add(indices.get(url));
		}
		
		return indices.get(url);
	}
	
	// 기본 점수 구하기
	public void getBasicScore(int index, String word, String page) {
		int same_cnt = 0;
		
		String open = "<body>";
		String close = "</body>";
		
		int start = page.indexOf(open)+open.length();
		int end = page.indexOf(close, start);
		
		// 알파벳이 아닌 다른 문자를 빈칸으로 변환
		String contents = page.substring(start, end);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < contents.length(); i++) {
			char ch = contents.charAt(i);
			
			if (!isAlpabet(ch)) {
				ch = ' ';
			}
			sb.append(ch);
		}
		
		contents = sb.toString();
		
		// word와 동일한 문자열 개수 구하기
		String[] contents_words = contents.split(" ");
		for (String contents_word : contents_words) {
			if (contents_word.equals(word)) {
				same_cnt++;
			}
		}
		
		basic_scores.put(index, same_cnt);
	}
	
	public void findOuterLinks (int url_index, String page) {
		// 반복문
		while(true) {
			String open = "<a href=\"";
			String close = "\">";
			
			int start = page.indexOf(open);
			
			if (start == -1) break;
			
			start += open.length();
			int end = page.indexOf(close, start);
			
			String outer_url = page.substring(start, end);
			if (!indices.containsKey(outer_url)) {// 등록 안된 경우 url 새로 등록
				indices.put(outer_url, idx++);
			}
			
			// 외부 링크 개수 갱신
			if (!outer_link_size.containsKey(url_index)) {
				outer_link_size.put(url_index, 1);
			} else {
				outer_link_size.put(url_index, outer_link_size.get(url_index)+1);
			}
			
			// 외부 index로 들어오는 링크 갱신
			int outer_index = indices.get(outer_url);
			if (!inner_links.containsKey(outer_index)) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(url_index);
				inner_links.put(outer_index, list);
			} else {
				ArrayList<Integer> list = inner_links.get(outer_index);
				list.add(url_index);
			}
			
			page = page.substring(end);
		}
		
	}
	
	public boolean isAlpabet(char ch) {
		return ch>='a' && ch<='z';
	}
	
	static class Node {
		int id;
		double matching_score;
		
		public Node(int id, double matching_score) {
			this.id = id;
			this.matching_score = matching_score;
		}

		@Override
		public String toString() {
			return "Node [id=" + id + ", matching_score=" + matching_score + "]";
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_매칭_점수_review().solution("blind", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
				+ "<head>\n  <meta charset=\"utf-8\">\n  "
					+ "<meta property=\"og:url\" content=\"https://a.com\"/>\n"
				+ "</head>  \n"
				+ "<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n"
					+ "<a href=\"https://b.com\"> Link to b </a>\n"
				+ "</body>\n"
				+ "</html>",
				
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
				+ "<head>\n  "
					+ "<meta charset=\"utf-8\">\n  "
					+ "<meta property=\"og:url\" content=\"https://b.com\"/>\n"
				+ "</head>  \n"
				+ "<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n"
					+ "<a href=\"https://a.com\"> Link to a </a>\n"
				+ "blind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n"
					+ "<a href=\"https://c.com\"> Link to c </a>\n"
				+ "</body>\n"
				+ "</html>",
				
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
				+ "<head>\n  "
				+ "<meta charset=\"utf-8\">\n  "
				+ "<meta property=\"og:url\" content=\"https://c.com\"/>\n"
				+ "</head>  \n"
				+ "<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n"
				+ "<a href=\"https://a.com\"> Link to a </a>\n"
				+ "</body>\n"
				+ "</html>" }));
		System.out.println(new PGS_매칭_점수_review().solution("Muzi", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" }));
	}
}
