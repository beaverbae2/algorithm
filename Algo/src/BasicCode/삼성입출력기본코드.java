package BasicCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class 삼성입출력기본코드 {
	static String src = "테스트 케이스 내용";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(src)); //테스트 케이스 확인용
		
		StringBuilder sb = new StringBuilder();//답안 출력용
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			
			//입력 받고 작업 진행
			
			sb.append("번호별 정답").append("\n");
		}
		System.out.println(sb);
	}
}
