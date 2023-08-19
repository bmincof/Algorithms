import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-08-19
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 징검다리에 쓰인 수
        int[] bridge = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bridge[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        // i번째 다리까지 도착하는 최소 횟수
        int[] move = new int[N + 1];

        q.offer(a);
        move[a] = 1;

        // BFS로 최소 이동횟수 찾기
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == b) break;

            for (int i = curr + bridge[curr]; i <= N; i += bridge[curr]) {
                if (move[i] != 0) continue;
                q.offer(i);
                move[i] = move[curr] + 1;
            }
            for (int i = curr - bridge[curr]; i > 0; i -= bridge[curr]) {
                if (move[i] != 0) continue;
                q.offer(i);
                move[i] = move[curr] + 1;
            }
        }

        System.out.println(move[b] - 1);
    }
}
