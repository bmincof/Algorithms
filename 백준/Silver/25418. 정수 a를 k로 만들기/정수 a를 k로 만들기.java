import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-09-18
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] count = new int[1_000_001];


        Queue<Integer> q = new LinkedList<>();
        count[A] = 1;
        q.offer(A);

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == K) break;

            if (curr + 1 <= 1_000_000 && count[curr + 1] == 0) {
                q.offer(curr + 1);
                count[curr + 1] = count[curr] + 1;
            }
            if (curr * 2 <= 1_000_000 && count[curr * 2] == 0) {
                q.offer(curr * 2);
                count[curr * 2] = count[curr] + 1;
            }
        }

        System.out.println(count[K] - 1);
    }
}