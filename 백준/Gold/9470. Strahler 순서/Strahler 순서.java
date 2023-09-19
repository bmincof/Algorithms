import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-09-19
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            int[] tmpStrahler = new int[M + 1];
            int[] strahler = new int[M + 1];
            List<Integer>[] from = new LinkedList[M + 1];
            int[] inDegree = new int[M + 1];

            for (int i = 1; i <= M; i++) {
                from[i] = new LinkedList<>();
            }

            while (P-- > 0) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                from[A].add(B);
                inDegree[B]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= M; i++) {
                if (inDegree[i] == 0) {
                    strahler[i] = 1;
                    q.offer(i);
                }
            }

            int strahlerOrder = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                strahlerOrder = Math.max(strahlerOrder, strahler[curr]);

                for (int next : from[curr]) {
                    // Strahler 값 계산
                    if (strahler[curr] > tmpStrahler[next]) {
                        tmpStrahler[next] = strahler[curr];
                    } else if (strahler[curr] == tmpStrahler[next]) {
                        strahler[next] = strahler[curr] + 1;
                    }

                    if (--inDegree[next] == 0) {
                        q.offer(next);
                        strahler[next] = strahler[next] == 0 ? tmpStrahler[next] : strahler[next];
                    }
                }
            }

            sb.append(K).append(" ").append(strahlerOrder).append("\n");
        }

        System.out.println(sb);
    }
}