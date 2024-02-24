import java.io.*;
import java.util.*;

/*
 * Mooyo Mooyo
 */

public class BOJ16768 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static boolean v[][];
	static ArrayList<Point> list = new ArrayList<Point>();
	static Queue<Point> queue = new LinkedList<Point>();
	static Stack<Integer> stack = new Stack<Integer>();
	static int N, K, map[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][10];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < s.length(); c++) {
				map[r][c] = s.charAt(c) - '0';
			}
		}
		
		while(true) {
			int cnt = 0;
			v = new boolean[map.length][map[0].length];
			
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[0].length; c++) {
					if(map[r][c] == 0 || v[r][c]) continue;
					list.clear();
					if(bfs(r, c).size() >= K) {
						for(Point p: list) {
							map[p.r][p.c] = 0;
						}
						cnt++;
					}
				}
			}
			if(cnt == 0) break;
			else gravity();
		}
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				sb.append(map[r][c]);
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}
	private static void printMap() {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				System.out.print(map[r][c]);
			} System.out.println();
		} System.out.println("=============");
	}
	private static void gravity() {
		
		for(int c = 0; c < map[0].length; c++) {
			stack.clear();
			for(int r = 0; r < map.length; r++) {
				if(map[r][c] == 0) continue;
				stack.add(map[r][c]);
				map[r][c] = 0;
			}
			
			for(int r = N - 1; r >= 0; r--) {
				if(stack.isEmpty()) break;
				map[r][c] = stack.pop();
			}
		}
	}
	
	private static ArrayList<Point> bfs(int r, int c) {
		v[r][c] = true;
		queue.add(new Point(r, c));
		list.add(new Point(r, c));
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] != map[r][c]) continue;
				queue.add(new Point(nr, nc));
				v[nr][nc] = true;
				list.add(new Point(nr, nc));
			}
		}
		return list;
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}
}
