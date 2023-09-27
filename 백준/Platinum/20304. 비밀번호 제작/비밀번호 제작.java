import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-27
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 각 비밀번호의 안전도
        // 최대 20개의 비트를 사용하므로 안전도는 최대 20
        int[] safety = new int[N + 1];
        Arrays.fill(safety, 21);

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        while (M-- > 0) {
            int tried = Integer.parseInt(st.nextToken());
            safety[tried] = 0;
            q.offer(tried);
        }

        int answer = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();

            // curr과 비트 하나가 차이나는 비밀번호 찾아보기
            for (int i = 0; i < 21; i++) {
                int next = curr ^ (1 << i);

                // N 이하의 숫자이고 발견했던 안전도가 최소가 아니라면
                if (next <= N && safety[next] > safety[curr] + 1) {
                    safety[next] = safety[curr] + 1;
                    q.offer(next);
                    answer = Math.max(answer, safety[next]);
                }
            }
        }

        System.out.println(answer);
    }
}