import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-07
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Deque<String> dq = new LinkedList<>();

            while (N-- > 0) {
                String next = st.nextToken();
                if (dq.isEmpty() || next.compareTo(dq.peekFirst()) <= 0) {
                    dq.offerFirst(next);
                } else {
                    dq.offerLast(next);
                }
            }

            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst());
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}