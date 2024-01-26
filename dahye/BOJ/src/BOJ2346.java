import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
풍선 터뜨리기
 */

public class BOJ2346 {
    static class Point {
        int idx, value;

        public Point(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static Deque<Point> deque = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < N + 1; i++) {
            deque.add(new Point(i, Integer.parseInt(st.nextToken())));
        }

        Point point = null;
        point = deque.pollFirst();
        sb.append(point.idx + " ");
        int value = point.value;

        while(!deque.isEmpty()) {

            if(value < 0) {
                value *= -1;
                while(--value > 0) {
                    point = deque.pollLast();
                    deque.addFirst(point);
                }

                point = deque.pollLast();
            } else {
                while(--value > 0) {
                    point = deque.pollFirst();
                    deque.addLast(point);
                }
                point = deque.pollFirst();
            }
            value = point.value;
            sb.append(point.idx + " ");
        }

        System.out.println(sb);
    }
}
