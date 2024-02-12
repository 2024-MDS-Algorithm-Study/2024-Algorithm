import java.io.*;
import java.util.*;

/*
 * 색종이 붙이기
 */

public class BOJ17136 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static boolean flag;
	static ArrayList<Point> list = new ArrayList<Point>();
	static int result = Integer.MAX_VALUE;
	static int papers[] = {0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int map[][] = new int[10][10];
		
		for(int r = 0; r < 10; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < 10; c++) {
				map[r][c] = s.charAt(c * 2) - '0';
				if(map[r][c] == 1) list.add(new Point(r, c));
			}
		}
		
		func(0, 0, map);
		
		System.out.println(result = result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void func(int idx, int cnt, int map[][]) {
		if(check(map)) {
			result = Math.min(result, cnt);

			return;
		}
		
		if(map[list.get(idx).r][list.get(idx).c] == 1) {
			for(int k = 5; k > 0; k--) {
				if(!checkPaper(map, list.get(idx), k) || papers[k] == 5) continue;
				papers[k]++;
				
				func(idx + 1, cnt + 1, copyMap(map, list.get(idx), k));
				papers[k]--;
			}
		} else {
			func(idx + 1, cnt, map);
		}
	}

	private static boolean check(int[][] map) {
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				if(map[r][c] == 1) return false;
			}
		}
		return true;
	}

	private static int[][] copyMap(int map[][], Point p, int k) {
		int copy[][] = new int[10][10];
		
		for(int r = 0; r < 10; r++) {
			copy[r] = Arrays.copyOf(map[r], map[r].length);
		}
		
		for(int r = p.r; r < p.r + k; r++) {
			for(int c = p.c; c < p.c + k; c++) {
				copy[r][c] = 0;
			}
		}
		
		return copy;
	}

	private static boolean checkPaper(int map[][], Point point, int k) {
		for(int r = point.r; r < point.r + k; r++) {
			for(int c = point.c; c < point.c + k; c++) {
				if(!check(r, c) || map[r][c] == 0) return false;
			}
		}
		return true;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}
}
