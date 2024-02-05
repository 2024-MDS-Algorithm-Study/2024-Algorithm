package algo;

import java.util.*;
import java.io.*;
/*
 * 가운데를 말해요
 */
public class BOJ1655 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> max = new PriorityQueue<>();
        PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2)->{return o2-o1;});
        
        int cur;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
        	cur = Integer.parseInt(br.readLine());
        	if(min.size()<=max.size()) {
        		if(min.size()>0) {
        			if(max.peek()<cur) {
        				min.add(max.poll());
        				max.add(cur);
        			}
        			else min.add(cur);
        		}
        		else min.add(cur);
        	}
        	else {
        		if(max.size()>0) {
        			if(min.peek()>cur) {
        				max.add(min.poll());
        				min.add(cur);
        			}
        			else max.add(cur);
        		}
        		else if(min.peek()>cur) {
        			max.add(min.poll());
        			min.add(cur);
        		}
        		else max.add(cur);
        	}
    		sb.append(min.peek()+"\n");
        }
        System.out.println(sb);
    }
}