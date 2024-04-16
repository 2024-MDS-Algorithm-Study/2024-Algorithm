import java.io.*;
import java.util.*;

public class BOJ1461 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> list1, list2;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list1.add(0);
        list2.add(0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a < 0) list2.add(-a);
            else list1.add(a);
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int res = 0;
        int p1 = list1.size() - 1;
        int p2 = list2.size() - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (list1.get(p1) > list2.get(p2)) {
                if (res == 0) res += list1.get(p1);
                else res += 2 * list1.get(p1);
                p1 -= m;
            }
            else {
                if (res == 0) res += list2.get(p2);
                else res += 2 * list2.get(p2);
                p2 -= m;
            }
        }

        while (p1 >= 1) {
            res += 2 * list1.get(p1);
            p1 -= m;
        }

        while (p2 >= 1) {
            res += 2 * list2.get(p2);
            p2 -= m;
        }
        System.out.println(res);
    }
}
