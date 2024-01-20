package algo;
import java.util.*;
import java.io.*;
/*
 * 청소년 상어
 */
public class BOJ19236 {
	static class node{
		int num, dir;
		public node(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	static int[][] dir = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	static node[][] map = new node[4][4];
	static int max = 0;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num, dir;
        for(int i=0; i<4; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<4; j++) {
        		num = Integer.parseInt(st.nextToken());
        		dir = Integer.parseInt(st.nextToken());
        		map[i][j] = new node(num, dir);
        	}
        }
        max = map[0][0].num;
        map[0][0] = new node(0, map[0][0].dir);
        move(map, max);
        System.out.println(max);
        
    }
    private static node[][] moveFish(node[][] curmap) {
    	node[][] nextmap = new node[4][4];
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			nextmap[i][j] = new node(curmap[i][j].num, curmap[i][j].dir);
    		}
    	}
    	for(int n=1; n<=16; n++) {
    		L: for(int i=0; i<4; i++) {
    			for(int j=0; j<4; j++) {
    				if(nextmap[i][j].num==n) {
    					int cd = nextmap[i][j].dir;
    					for(int d=1; d<=8; d++) {
    						int ni = i+dir[cd][0];
							int nj = j+dir[cd][1];
							if(ni>=0 && ni<4 && nj>=0 && nj<4 && nextmap[ni][nj].num!=0) {
								if(nextmap[ni][nj].num==-1) {
						    		nextmap[ni][nj] = new node(nextmap[i][j].num, cd);
						    		nextmap[i][j] = new node(-1, 0);
									break L;
								}
								else {
									node tmp = new node(nextmap[ni][nj].num, nextmap[ni][nj].dir);
									nextmap[ni][nj] = new node(nextmap[i][j].num, cd);
									nextmap[i][j] = new node(tmp.num, tmp.dir);
									break L;
								}
							}
							cd = (cd+1)%8;
							if(cd==0) cd = 8;
    					}
    				}
    			}
    		}
    	}
		return nextmap;
    }
    private static void move(node[][] curmap, int score) {
    	node[][] afterFishMove = moveFish(curmap);
    	boolean flag = false;
    	for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(afterFishMove[i][j].num == 0) {
					for(int dist=1; dist<4; dist++) {
						int ni = i+dir[afterFishMove[i][j].dir][0]*dist;
						int nj = j+dir[afterFishMove[i][j].dir][1]*dist;
						if(ni>=0 && ni<4 && nj>=0 && nj<4 && afterFishMove[ni][nj].num>0) {
							flag = true;
							node prev = new node(afterFishMove[ni][nj].num, afterFishMove[ni][nj].dir);
							int add = afterFishMove[ni][nj].num;
							afterFishMove[ni][nj] = new node(0, afterFishMove[ni][nj].dir);
							afterFishMove[i][j] = new node(-1, afterFishMove[i][j].dir);
							move(afterFishMove, score+add);
							afterFishMove[ni][nj] = new node(prev.num, prev.dir);
							afterFishMove[i][j] = new node(0, afterFishMove[i][j].dir);
						}
					}
				}
			}
    	}
    	if(!flag) {
    		max = Math.max(max, score);
    	}
    	return;
    }
}