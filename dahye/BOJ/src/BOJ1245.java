import java.io.*;
import java.util.*;

/*
 * 농장 관리
 */

public class BOJ1245 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int map[][];
	static boolean v[][];
	static Queue<Point> queue = new LinkedList<Point>();
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(v[r][c]) continue;
				if(bfs(r, c)) result++; 
			}
		}
		System.out.println(result);
	}

	private static boolean bfs(int r, int c) {
		boolean bong = true;
		
		queue.add(new Point(r, c));
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
					
			for(int d = 0; d < 8; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[r][c] < map[nr][nc]) bong = false;
				if(v[nr][nc] || (map[r][c] != map[nr][nc])) continue;
				queue.add(new Point(nr, nc));
				v[nr][nc] = true;
			}
		}
		
		return bong;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}
}
