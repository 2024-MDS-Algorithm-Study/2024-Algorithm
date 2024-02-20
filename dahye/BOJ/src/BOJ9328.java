import java.io.*;
import java.util.*;

/*
 * 열쇠
 */

public class BOJ9328 {
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int result;
	static char map[][];
	static Queue<Point> queue = new LinkedList<Point>();
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static HashMap<Character, ArrayList<Point>> hashMap;
	static boolean v[][];
	static boolean keys[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		
		int T = Integer.parseInt(br.readLine());
		hashMap = new HashMap<Character, ArrayList<Point>>();
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map = new char[h + 2][w + 2];
			v = new boolean[h + 2][w + 2];
			result = 0;
			hashMap.clear();
			keys = new boolean[26];
			
			for(int r = 0; r < h + 2; r++) {
				String s = "";
				if(r != 0 && r != h + 1) s = br.readLine();
				for(int c = 0; c < w + 2; c++) {
					if(r == 0 || r == h + 1 || c == 0 || c == w + 1) {
						map[r][c] = '.';
						continue;
					}
					map[r][c] = s.charAt(c - 1);
				}
			}
			String k = br.readLine();
			
			if(!k.equals("0")) {
				for(int i = 0; i < k.length(); i++) {
					keys[k.charAt(i) - 'a'] = true;
				}
			}
			
			func();
			sb.append(result + "\n");
		}
		
		System.out.println(sb);
	}

	private static void func() {
		queue.add(new Point(0, 0));
		v[0][0] = true;
		
		while(!queue.isEmpty()) {

			Point current = queue.poll();
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == '*') continue;
				
				if(checkDoor(map[nr][nc])) {
					if(keys[map[nr][nc] - 'A']) {
						
						map[nr][nc] = '.';
						v[nr][nc] = true;
						queue.add(new Point(nr, nc));
					} else {
						if(hashMap.get(map[nr][nc]) == null) {
							ArrayList<Point> points = new ArrayList<Point>();
							points.add(new Point(nr, nc));
							hashMap.put(map[nr][nc], points);
						} else {
							hashMap.get(map[nr][nc]).add(current);
						}
					}
				} else if(checkKey(map[nr][nc])) {
					keys[map[nr][nc] - 'a'] = true;
					v[nr][nc] = true;
					queue.add(new Point(nr, nc));
					
					if(hashMap.get((char) (map[nr][nc] - 32)) == null) continue;
					for(Point p: hashMap.get((char) (map[nr][nc] - 32))) {
						map[p.r][p.c] = '.';
						v[p.r][p.c] = true;
						queue.add(new Point(p.r, p.c));
					}
				} else if(map[nr][nc] == '$') {
					result++;
					v[nr][nc] = true;
					queue.add(new Point(nr, nc));
				} else {
					v[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}
	}

	private static boolean checkKey(char c) {
		return c >= 'a' && c <= 'z';
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static boolean checkDoor(char c) {
		return c >= 'A' && c <= 'Z';
	}
}