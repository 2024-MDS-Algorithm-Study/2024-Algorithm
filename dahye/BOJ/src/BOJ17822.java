import java.io.*;
import java.util.*;

/*
 * 원판 돌리기
 */

public class BOJ17822 {
	static boolean flag;
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int map[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<Point> queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while(T-- > 0) {
			flag = false;
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			func(x, d, k);
			find();
		}

		System.out.println(sum());
	}

	private static int sum() {
		int sum = 0;
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				sum += map[r][c];
			}
		}
		
		return sum;
	}

	private static void find() {
		int sum = 0;
		int cnt = 0;
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c] == 0) continue;
				sum += map[r][c];
				cnt++;
				
				if(bfs(r, c)) flag = true;
			}
		}

		double avg =  sum * 1.0 / cnt;
		if(!flag) {
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[0].length; c++) {
					if(map[r][c] == 0) continue;
					if(map[r][c] < avg) map[r][c]++;
					else if(map[r][c] > avg) map[r][c]--;
				}
			}
		}
	}

	private static boolean bfs(int r, int c) {
		
		queue.add(new Point(r, c));
		int num = map[r][c];
		boolean f = false;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				if(d < 2 && !check(nr, nc)) continue;
				
				if(nc >= map[0].length) {
					nc %= map[0].length;
				}
				if(nc < 0) nc += map[0].length;
				
				if(map[nr][nc] == num) {
					f = true;
					queue.add(new Point(nr, nc));
					map[nr][nc] = 0;
				}
			}
		}
		
		if(f) map[r][c] = 0;
		return f;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static void func(int x, int d, int k) {
		int tmp[] = new int[map[0].length];
		
		int dir = 0;
		int len = map[0].length;
		int start = (len - k) % len;
		int end = (len - k - 1) % len;
		
		if(d == 1) {
			start = k;
			end = (k - 1) % len;
			end = end < 0 ? end + len: end;
		}
		
		for(int r = x - 1; r < map.length; r += x) {
			int idx = 0;
			
			while(idx < len) {
				tmp[idx] = map[r][(start) % len];
				start = (start + 1) % len;
				idx++;
			}
			
			map[r] = Arrays.copyOf(tmp, tmp.length);
		}
		
	}
}
