import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1655 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int a = Integer.parseInt(br.readLine());
            if (pq.size() == pq2.size()) pq.offer(-a);
            else pq2.offer(a);

            if (!pq2.isEmpty() && (-pq.peek() > pq2.peek())) {
                int tmp = -pq.poll();
                pq.offer(-pq2.poll());
                pq2.offer(tmp);
            }

            sb.append(-pq.peek()).append('\n');
        }

        System.out.println(sb);
    }
}
