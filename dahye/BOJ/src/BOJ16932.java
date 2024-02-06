import java.io.*;
import java.util.*;

public class BOJ16932 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, result;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int map[][];
	static boolean v[][];
	static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	static Queue<Point> queue = new LinkedList<Point>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		v = new boolean[N][M];
		int count = 1;
		
		ArrayList<Point> points = new ArrayList<Point>();

		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c * 2) - '0';

				if(map[r][c] == 0) points.add(new Point(r, c));
			}
		}

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1 && !v[r][c]) {
					bfs(r, c, count);
					count++;
				}
			}
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(Point point: points) {
			set.clear();
			
			int sum = 1;
			for(int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];
				
				if(!check(nr, nc) || map[nr][nc] == 0 || set.contains(map[nr][nc])) continue;
				set.add(map[nr][nc]);
				sum += hashMap.get(map[nr][nc]);
			}
			
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}

	private static void bfs(int r, int c, int count) {
		queue.add(new Point(r, c));
		v[r][c] = true;
		map[r][c] = count;
		int groupCount = 1;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || map[nr][nc] == 0 || v[nr][nc]) continue;
				queue.add(new Point(nr, nc));
				map[nr][nc] = count;
				v[nr][nc] = true;
				groupCount++;
			}
		}
		
		hashMap.put(count, groupCount);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}