package algo;

import java.util.*;
import java.io.*;
/*
 * 고냥이
 */
public class BOJ16472 {
	static class node{
		char type;
		int cnt;
		public node(char type, int cnt) {
			this.type = type;
			this.cnt = cnt;
		}
	}
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        
        ArrayList<node> arr = new ArrayList<>();
        int cnt = 1;
        for(int i=1; i<str.length(); i++) {
        	if(str.charAt(i) == str.charAt(i-1)) {
        		cnt++;
        	}
        	else {
        		arr.add(new node(str.charAt(i-1), cnt));
        		cnt = 1;
        	}
        }
        arr.add(new node(str.charAt(str.length()-1), cnt));
        int max = 0;
        
        HashMap<Character, Integer> dic = new HashMap<>();
        int start = 0, end = 0;
        int curLen = 0;
        while(end<arr.size()) {
        	node cn = arr.get(end);
        	if(dic.containsKey(cn.type)) {
        		dic.put(cn.type, dic.get(cn.type)+1);
        		curLen+=cn.cnt;
            	end++;
        	}
        	else {
        		dic.put(cn.type, 1);
        		while(dic.size()>n) {
        			if(dic.get(arr.get(start).type)>0) {
        				dic.put(arr.get(start).type, dic.get(arr.get(start).type)-1);
        			}
        			if(dic.get(arr.get(start).type)==0) dic.remove(arr.get(start).type);
        			curLen-=arr.get(start).cnt;
        			start++;
        		}
        		curLen+=cn.cnt;
        		end++;
        	}
        	max = Math.max(max, curLen);
        }
        System.out.println(max);
    }
}