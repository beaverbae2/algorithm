package A2021_02_11;

import java.util.*;

/**
 * String
 * 
 * @author beaverbae
 * @see https://codingjuny.tistory.com/39
 */

public class PGS_매칭_점수 {
	private LinkedHashMap<String, Integer> url2idx = new LinkedHashMap<>();
	private HashMap<Integer, Double> basic_points = new HashMap<>();
	private HashMap<Integer, ArrayList<Integer>> external_links = new HashMap<>();
	private HashMap<Integer, Integer> external_size = new HashMap<>();
	private List<Pair> matching_point_list = new ArrayList<>();
	private List<String> url_list = new ArrayList<>();
	private int next_index = 0;

	public int solution(String word, String[] pages) {
		word = word.toLowerCase();

		for (int i = 0; i < pages.length; i++) {
			String page = pages[i].toLowerCase();

			// 현재 웹 페이지의 url 구해서 index까지 구함
			int index = getMyIndex(page);

			// 기본 점수 구하기
			double basic_point = getBasicPoint(page, word);

			// 기본 점수 저장
			basic_points.put(index, basic_point);

			// 외부 링크 구하기 -> 구한 후 external_links에 저장
			getExternalIndex(page, index);
		}

		// 링크 점수 구한 후 매칭 점수까지 구하기
		for (int i = 0; i < url_list.size(); i++) {
			String url = url_list.get(i);
			int index = url2idx.get(url);

			if (basic_points.containsKey(index)) {

				double basic_point = basic_points.get(index);
				double link_point = 0;

				if (external_links.containsKey(index)) {
					ArrayList<Integer> external_link = external_links.get(index);
					for (int link : external_link) {
						if (!basic_points.containsKey(link))
							continue;

						double link_cnt = external_size.get(link);
						link_point += (basic_points.get(link) / link_cnt);
					}
				}

				double matching_point = basic_point + link_point;
				matching_point_list.add(new Pair(i, matching_point));
			}
		}

		Collections.sort(matching_point_list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.matching_point != o2.matching_point) {
					return Double.compare(o2.matching_point, o1.matching_point);
				}

				return Integer.compare(o1.index, o2.index);
			}
		});

		int answer = matching_point_list.get(0).index;
		return answer;
	}

	// 현재 웹 페이지의 url 구해서 index까지 구함
	public int getMyIndex(String page) {
		// meta
		String https_open = "<meta property=\"og:url\" content=\"";
		String https_close = "\"";

		int start = page.indexOf(https_open) + https_open.length();
		int end = page.indexOf(https_close, start);

		String my_url = page.substring(start, end);
		int index = 0;

		url_list.add(my_url);
		if (url2idx.containsKey(my_url)) {
			index = url2idx.get(my_url);
		} else {
			index = next_index++;
			url2idx.put(my_url, index);
		}

		return url2idx.get(my_url);
	}

	// 기본 점수 구하기
	public double getBasicPoint(String page, String word) {
		double point = 0;
		String body = "<body>";

		int start = page.indexOf(body) + body.length();
		int end = page.indexOf("</body>", start);
		String text = page.substring(start, end);

		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);

			if (ch < 'a' || ch > 'z') {
				text = text.replace(ch, ' ');
			}
		}

		String[] split = text.split(" ");
		for (String str : split) {
			if (str.equals(word))
				point++;
		}

		return point;
	}

	// 외부 링크 구하기 -> 구한 후 external_links에 저장
	public void getExternalIndex(String page, int my_index) {
		String a_start = "<a href=\"";
		String a_end = "\"";
		int st = 0;
		int size = 0;
		while (true) {
			int start = page.indexOf(a_start, st) + a_start.length();
			int end = page.indexOf(a_end, start);
			int index = 0;

			if (start == a_start.length() - 1)
				break;

			st = start;
			String external_url = page.substring(start, end);
			if (url2idx.containsKey(external_url)) {
				index = url2idx.get(external_url);

				if (external_links.containsKey(index)) {
					ArrayList<Integer> external_link = external_links.get(index);
					external_link.add(my_index);
					external_links.put(index, external_link);
				} else {
					ArrayList<Integer> external_link = new ArrayList<>();
					external_link.add(my_index);
					external_links.put(index, external_link);
				}

			} else {
				index = next_index++;

				if (external_links.containsKey(index)) {
					ArrayList<Integer> external_link = external_links.get(index);
					external_link.add(my_index);
					external_links.put(index, external_link);
				} else {
					ArrayList<Integer> external_link = new ArrayList<>();
					external_link.add(my_index);
					external_links.put(index, external_link);
				}

				url2idx.put(external_url, index);
			}
			
			size++;
		}
		external_size.put(my_index, size);
	}

	static class Pair {
		int index;
		double matching_point;

		public Pair(int index, double matching_point) {
			this.index = index;
			this.matching_point = matching_point;
		}

		@Override
		public String toString() {
			return "[index=" + index + ", matching_point=" + matching_point + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_매칭_점수().solution("blind", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" });
		new PGS_매칭_점수().solution("Muzi", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" });
	}
}
