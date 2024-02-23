import java.io.*;
import java.util.*;

/*
카드 정렬하기
 */

public class BOJ1715 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();

		int num = Integer.parseInt(br.readLine());
		que.add(num);
		
		for(int i = 1; i < N; i++) {
			que.add(Integer.parseInt(br.readLine()));
		}
		
		int result = 0;
		
		while(que.size() > 1) {
			int a = que.poll();
			int b = que.poll();
			
			result += (a + b);
			
			que.add(a + b);
		}
		
		System.out.println(result);
	}
}
