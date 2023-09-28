import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-28
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이 시작점, 동생 시작점
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 홀수/짝수 시간에 처음 도착한 시간
        int[][] firstArrive = new int[2][500_001];
        Arrays.fill(firstArrive[0], Integer.MAX_VALUE);
        Arrays.fill(firstArrive[1], Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, N});
        firstArrive[0][N] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int time = curr[0] + 1;
            int pos = curr[1];
            
            for (int next : new int[]{pos + 1, pos - 1, pos * 2}) {
                if (isInRange(next) && isFirstTime(firstArrive[time % 2][next])) {
                    firstArrive[time % 2][next] = time;
                    q.offer(new int[]{time, next});
                }
            }
        }

        int answer = 0;
        while (isInRange(K)) {
            if (firstArrive[answer % 2][K] <= answer) {
                System.out.println(answer);
                return;
            }
            answer++;
            K += answer;
        }

        System.out.println(-1);
    }

    static boolean isInRange(int index) {
        return 0 <= index && index <= 500_000;
    }

    static boolean isFirstTime(int time) {
        return time == Integer.MAX_VALUE;
    }
}