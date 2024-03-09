import java.io.*;
import java.util.*;

/*
 * 여러분의 다리가 되어 드리겠습니다!
 */

public class BOJ17352 {
	static int p[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		p = new int[N + 1];
		
		for(int i = 0; i < p.length; i++) p[i] = i;

		for(int i = 0; i < N - 2; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		for(int i = 1; i < N + 1; i++) {
			p[i] = find(i);
		}
		
		int start = p[1];
		
		for(int i = 2; i < p.length; i++) {
			if(p[i] != start) {
				System.out.println(start + " " + p[i]);
				break;
			}
		}
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p[b] = a;
	}

	private static int find(int a) {
		return p[a] = a == p[a] ? a : find(p[a]);
	}
}
