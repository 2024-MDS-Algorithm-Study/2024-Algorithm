import java.io.*;
import java.util.*;

/*
 * 장군
 */

public class BOJ16509 {
	static class Point {
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static boolean v[][] = new boolean[10][9];
	static Queue<Point> queue = new LinkedList<Point>();
	static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		

		Point sang = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		v[sang.r][sang.c] = true;
		queue.add(sang);

		st = new StringTokenizer(br.readLine());
		Point wang = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		System.out.println(move(wang));
	}
	private static int move(Point wang) {
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			if(current.r == wang.r && current.c == wang.c)  {
				return current.d;
			}
			for(int d = 0; d < 8; d += 2) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc) || (nr == wang.r && nc == wang.c)) continue;
				
				for(int k = 0; k < 2; k++) {
					int dir = -1;
					if(k == 1) dir = k;
					
					int checkD = dir + d;
					if(checkD < 0) checkD += 8;
					if(checkD > 3) checkD %= 8;
					
					int checkR = nr;
					int checkC = nc;
					
					for(int l = 1; l < 3; l++) {
						 checkR += dr[checkD];
						 checkC += dc[checkD];
						
						if(!check(checkR, checkC) || (l == 1 && checkR == wang.r && checkC == wang.c)) break;
						if(l == 2) {
							if(v[checkR][checkC]) continue;
							v[checkR][checkC] = true;
							queue.add(new Point(checkR, checkC, current.d + 1));
						}
					}
				}
			}
		}
		
		return -1;
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 10 && nc >= 0 && nc < 9;
	}
}
