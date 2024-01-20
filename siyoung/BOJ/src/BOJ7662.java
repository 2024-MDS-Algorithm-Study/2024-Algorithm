package algo;
import java.util.*;
import java.io.*;
/*
 * 이중 우선순위 큐 
 */
public class BOJ7662 {
	static class maxnode implements Comparable<maxnode>{
		int num;
		public maxnode(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(maxnode o) {
			if(o.num>this.num) return 1;
			else return -1;
		}
	}
	static class minnode implements Comparable<minnode>{
		int num;
		public minnode(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(minnode o) {
			if(o.num>this.num) return -1;
			else return 1;
		}
		
	}
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        
        for(int testCnt=0; testCnt<t; testCnt++) {
        	int k = Integer.parseInt(br.readLine());
        	HashMap<Integer, Integer> map = new HashMap<>();
        	PriorityQueue<maxnode> maxpq = new PriorityQueue<>();
        	PriorityQueue<minnode> minpq = new PriorityQueue<>();
        	for(int i=0; i<k; i++) {
        		st = new StringTokenizer(br.readLine());
        		char order = st.nextToken().charAt(0);
        		int number = Integer.parseInt(st.nextToken());
//        		System.out.println(order+" "+number);
        		if(order=='I') {
        			maxpq.add(new maxnode(number));
        			minpq.add(new minnode(number));
        			if(map.get(number)!=null) {
        				map.put(number, map.get(number)+1);
        			}
        			else map.put(number, 1);
        		}
        		else if(order=='D') {
        			if(number==-1) {
        				while(!minpq.isEmpty()) {
        					minnode cn = minpq.poll();
        					if(map.get(cn.num)!=null) {
        						map.put(cn.num, map.get(cn.num)-1);
        						if(map.get(cn.num)==0) map.remove(cn.num);
        						break;
        					}
        				}
        			}
        			else if(number==1) {
        				while(!maxpq.isEmpty()) {
        					maxnode cn = maxpq.poll();
        					if(map.get(cn.num)!=null) {
        						map.put(cn.num, map.get(cn.num)-1);
        						if(map.get(cn.num)==0) map.remove(cn.num);
        						break;
        					}
        				}
        			}
        		}
        	}
        	
        	while(!minpq.isEmpty()) {
				minnode cn = minpq.peek();
				if(map.get(cn.num)!=null) {
					break;
				}
				else {
					minpq.poll();
				}
			}
			while(!maxpq.isEmpty()) {
				maxnode cn = maxpq.peek();
				if(map.get(cn.num)!=null) {
					break;
				}
				else {
					maxpq.poll();
				}
			}
        	
        	if(maxpq.isEmpty()||minpq.isEmpty()) System.out.println("EMPTY");
        	else {
        		System.out.println(maxpq.peek().num+" "+minpq.peek().num);
        	}
        }
    }
}