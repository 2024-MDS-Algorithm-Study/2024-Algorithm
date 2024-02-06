import java.io.*;
import java.util.*;

/*
 * 직사각형 탈출
 */

public class BOJ16973 {
	static class Point {
		int r, c, d;
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int N, M, map[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> walls;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		walls = new ArrayList<Point>();
		
		for(int r = 1; r < N + 1; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c < M + 1; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) walls.add(new Point(r, c, 0));
			}
		}

		st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		Point finish = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		System.out.println(func(start, finish, h, w));
	}

	private static int func(Point start, Point finish, int h, int w) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean v[][] = new boolean[N + 1][M + 1];
		
		queue.add(start);
		v[start.r][start.c] = true;
		
		int result = -1;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			if(current.r == finish.r && current.c == finish.c) {
				result = current.d;
				break;
			}
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == '1' || v[nr][nc]) continue;
				if(!canExistRectangle(nr, nc, h, w)) {
					v[nr][nc] = true;
					continue;
				}
				v[nr][nc] = true;
				queue.add(new Point(nr, nc, current.d + 1));
			}
		}
		return result;
	}

	private static boolean canExistRectangle(int nr, int nc, int h, int w) {
		if(nr + h > N + 1 || nc + w > M + 1) return false;
		for(int i = 0; i < walls.size(); i++) {
			int r = walls.get(i).r;
			int c = walls.get(i).c;
			
			if(r >= nr && r < nr + h && c >= nc && c < nc + w) return false;
		}
		return true;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 1 && nr < N + 1 && nc >= 1 && nc < M + 1;
	}
}
