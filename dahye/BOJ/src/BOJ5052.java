import java.io.*;
import java.util.*;

/*
 * 전화번호 목록
 */
public class BOJ5052 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < t; test_case++) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<String> list = new ArrayList<>();

            for(int i = 0; i < n; i++) list.add(br.readLine());

            list.sort(new Comparator<String>() {

                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

            boolean flag = true;
            L: for(int i = 0; i < list.size() - 1; i++) {
                if(list.get(i + 1).startsWith(list.get(i))) {
                    flag = false;
                    break L;
                }
            }

            if(flag) sb.append("YES" + "\n");
            else sb.append("NO" + "\n");
        }
        System.out.println(sb);
    }
}
