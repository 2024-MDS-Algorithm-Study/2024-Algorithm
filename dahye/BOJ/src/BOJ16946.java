import java.io.*;
import java.util.*;

/*
 * 벽 부수고 이동하기 4
 */

public class BOJ16946 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int map[][];
	static boolean v[][];
	static Queue<Point> queue = new LinkedList<Point>();
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		HashMap<Integer, Integer> available = new HashMap<Integer, Integer>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c) - '0';
			}
		}

		int idx = 2;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1 || v[r][c]) continue;
				available.put(idx, bfs(r, c, idx));
				idx++;
			}
		}
		Set<Integer> check = new HashSet<Integer>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				
				check.clear();
				
				int availMove = 1;
				
				if(map[r][c] == 1) {
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(!check(nr, nc) || map[nr][nc] == 1) continue;
						if(!check.contains(map[nr][nc])) {
							
							availMove += available.get(map[nr][nc]);
							check.add(map[nr][nc]);
						}
					}
					
					sb.append(availMove % 10);
				} else sb.append(0);
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int r, int c, int idx) {
		v[r][c] = true;
		map[r][c] = idx;
		
		int cnt = 1;
		
		queue.add(new Point(r, c));
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
				map[nr][nc] = idx;
				v[nr][nc] = true;
				queue.add(new Point(nr, nc));
				cnt++;
			}
			
		}
		return cnt;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
