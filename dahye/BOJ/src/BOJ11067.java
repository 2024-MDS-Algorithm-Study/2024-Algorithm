import java.io.*;
import java.util.*;

/*
 * 모노톤길
 */

public class BOJ11067 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static ArrayList<Integer> cafe[] = new ArrayList[100_001];
	static ArrayList<Point> points = new ArrayList<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < cafe.length; i++) cafe[i] = new ArrayList<Integer>();
			int limit = 0;
			points.clear();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				limit = Math.max(x, limit);
				cafe[x].add(y);
			}

			for(int i = 0; i < limit + 1; i++) {
				if(i == 0) {
					Collections.sort(cafe[0], (o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)));
					
				} else {
					Point last = points.get(points.size() - 1);
					Collections.sort(cafe[i], (o1, o2) -> Integer.compare(Math.abs(last.c - o1), Math.abs(last.c - o2)));
				}
				
				for(int c: cafe[i]) {
					points.add(new Point(i, c));
				}
			}
				
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < m; i++) {
				int num = Integer.parseInt(st.nextToken());
				sb.append(points.get(num - 1).r + " " + points.get(num - 1).c + "\n");
			}
		}
		System.out.println(sb);
	}
}
