import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// author : bmincof
// date   : 2023-08-10
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 관계
        char[][] relation = new char[N][N];
        for (int i = 0; i < N; i++) {
            relation[i] = br.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<>();
        // i번째 사람의 2-친구수
        int[] count = new int[N];
        // 2-친구수가 가장 큰 사람의 번호
        int maxIdx = 0;

        // BFS로 모든 사람에 대해서 2-친구수 계산
        for (int i = 0; i < N; i++) {
            boolean[] vis = new boolean[N];
            vis[i] = true;
            q.offer(new int[]{i, 0});
            
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                
                // 거리가 1 또는 2인 사람의 수 + 1 (= 2-친구수 + 1)
                if (0 < curr[1] && curr[1] <= 2)
                    count[i]++;

                for (int j = 0; j < N; j++) {
                    if (vis[j] || relation[curr[0]][j] == 'N')
                        continue;

                    q.offer(new int[]{j, curr[1] + 1});
                    vis[j] = true;
                }
            }

            if (count[i] >= count[maxIdx])
                maxIdx = i;
        }

        System.out.println(count[maxIdx]);
    }
}