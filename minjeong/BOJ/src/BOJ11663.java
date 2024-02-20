import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11663 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m, points[];
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        points = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(points);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s, e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            int sIdx = Arrays.binarySearch(points, s);
            int eIdx = Arrays.binarySearch(points, e);
            if (sIdx < 0) sIdx = -(sIdx + 1);
            if (eIdx < 0) eIdx = -(eIdx + 1);
            else eIdx++;
            sb.append(eIdx - sIdx).append('\n');
        }

        System.out.println(sb);
    }
}
