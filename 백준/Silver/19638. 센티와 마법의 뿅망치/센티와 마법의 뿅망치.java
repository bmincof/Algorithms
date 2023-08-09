import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        while (N-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int use = 0;
        while (pq.peek() >= H && use < T) {
            int height = pq.poll();
            pq.offer(height == 1 ? 1 : (height / 2));
            use++;
        }

        if (use == T && pq.peek() >= H) {
            System.out.println("NO");
            System.out.println(pq.peek());
        } else {
            System.out.println("YES");
            System.out.println(use);
        }
    }
}
