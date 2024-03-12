import java.io.*;
import java.util.*;

/*
 * 스타트 택시
 */
public class BOJ19238 {
	static class Point implements Comparable<Point> {
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			if(o.d == this.d) {
				if(this.r == o.r) return Integer.compare(this.c, o.c);
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.d, o.d);
		}
	}

	static class Person {
		Point s, e;
		int d;
		public Person(Point s, Point e, int d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}
	}
	static int N;
	static int dr[] = {-1, 0, 0, 1};
	static int dc[] = {0, -1, 1, 0};
	static int map[][];
	static HashMap<Integer, Person> people = new HashMap<Integer, Person>();
	static PriorityQueue<Point> queue = new PriorityQueue<Point>();
	static Queue<Point> q = new LinkedList<Point>();
	static boolean v[][], v2[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < N; c++) {
				map[r][c] = s.charAt(c * 2) - '0';
				if(map[r][c] == 1) map[r][c] = -1;
			}
		}
	
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		Point taxi = new Point(r - 1, c - 1, f);
	
		int idx = 1;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			Point s = point(st);
			Point e = point(st);
			
			map[s.r][s.c] = idx;
			int d = dis(s, e);
			if(d == Integer.MAX_VALUE) {
				System.out.println(-1);
				return;
			}
			people.put(idx++, new Person(s, e, d));
			
		}

		boolean flag = true;
		while(people.size() != 0) {
			
			queue.clear();
			queue.add(new Point(taxi.r, taxi.c, 0));
			v = new boolean[N][N];
			v[taxi.r][taxi.c] = true;
			
			while(!queue.isEmpty()) {
				Point current = queue.poll();
				
				if(map[current.r][current.c] != 0 && map[current.r][current.c] != -1) {
					int key = map[current.r][current.c];
				
					taxi.d -= current.d;
					
					if(taxi.d < 0) {
						flag = false;
						break;
					}
					
					taxi.r = people.get(key).e.r;
					taxi.c = people.get(key).e.c;
					taxi.d -= people.get(key).d;
					if(taxi.d < 0) {
						flag = false;
						break;
					}
					
					taxi.d += 2 * people.get(key).d;
					
					map[current.r][current.c] = 0;
					people.remove(key);
					
					break;
				}
				
				for(int d = 0; d < 4; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					
					if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == -1) continue;
					queue.add(new Point(nr, nc, current.d + 1));
					
					v[nr][nc] = true;
				}
			}
			
			if(taxi.d < 0 || !flag) break;
		}
		
		System.out.println(flag ? taxi.d : -1);
	}

	private static int dis(Point s, Point e) {
		
		q.clear();
		q.add(s);
		v2 = new boolean[N][N];
		v2[s.r][s.c] = true;
		
		int dis = Integer.MAX_VALUE;
		
		L: while(!q.isEmpty()) {
			Point c = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = c.r + dr[d];
				int nc = c.c + dc[d];
				
				if(!check(nr, nc) || v2[nr][nc] || map[nr][nc] == -1) continue;
				if(nr == e.r && nc == e.c) {
					dis = c.d + 1;
					break L;
				}
 				q.add(new Point(nr, nc, c.d + 1));
				v2[nr][nc] = true;
			}
		}
		
		return dis;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static Point point(StringTokenizer st) {
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		return new Point(r, c, 0);
	}
}
