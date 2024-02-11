import java.io.*;
import java.util.*;

/*
 * 소용돌이 예쁘게 출력하기
 */

public class BOJ1022 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int length;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int map[][] = new int[p2.r - p1.r + 1][p2.c - p1.c + 1];

		int max = 0;
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = calArea(p1.r + r, p1.c + c);
				max = Math.max(map[r][c], max);
			} 
		}

		int maxDigit = calDigit(max);
		int diffDigit = 0;
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(calDigit(map[r][c]) < maxDigit) {
					diffDigit = maxDigit - calDigit(map[r][c]);
					while(diffDigit-- > 0) sb.append(" ");
				}
				sb.append(map[r][c] + " ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static int calArea(int r, int c) {
		if(r < 0) {
			if(Math.abs(r) >= Math.abs(c)) {
				return (Math.abs(r) * 2) * (Math.abs(r) * 2) + 1 - (c - r);
			}
			else {
				if(c > 0) return (2 * c - 1) * (2 * c - 1) + (c - r);
				else return (Math.abs(c) * 2) * ((Math.abs(c) * 2)) + 1 + (r - c);
			}
		} else {
			if(Math.abs(r) >= Math.abs(c)) return (2 * r + 1) * (2 * r + 1) + (c - r);
			else {
				if(c >= 0) return (2 * c - 1) * (2 * c - 1) + (c - r);
				else return (Math.abs(c) * 2) * ((Math.abs(c) * 2)) + 1 + (r - c);
			}
		}
	}

	private static int calDigit(int num) {
		int i = 0;
		
		while(num > 0) {
			num /= 10;
			i++;
		}
		return i;
	}
}
