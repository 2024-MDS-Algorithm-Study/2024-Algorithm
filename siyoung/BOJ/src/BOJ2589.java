package algo;
import java.util.*;
import java.io.*;
 
/*
 * 보물섬
 */
public class BOJ2589 {
	static class Node{
		int i, j, cnt;
		public Node(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static char[][] map;
	static int n , k, max;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new char[n][k];
        
        for(int i=0; i<n; i++) {
        	String str = br.readLine();
        	for(int j=0; j<k; j++) {
        		map[i][j] = str.charAt(j);
        	}
        }
        max = 0;
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<k; j++) {
        		if(map[i][j]=='L') bfs(i, j);
        	}
        }
        System.out.println(max);
    }
    
    private static void bfs(int x, int y) {
		boolean[][] v = new boolean[n][k];
		Queue<Node> q = new LinkedList<>();
		v[x][y] = true;
		q.add(new Node(x, y, 0));
		while(!q.isEmpty()) {
			Node cn = q.poll();
			max = Math.max(max, cn.cnt);
			
			for(int d=0; d<4; d++) {
				int ni = cn.i+di[d];
				int nj = cn.j+dj[d];
				if(ni>=0 && ni<n && nj>=0 && nj<k && map[ni][nj]=='L' && !v[ni][nj]) {
					v[ni][nj] = true;
					q.add(new Node(ni, nj, cn.cnt+1));
				}
			}
		}
    }
}