import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-09-15
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // [i]번 건물을 지으면 다음에 지을 수 있는 건물들
        List<Integer>[] canBuildNext = new LinkedList[N + 1];
        // [i]번 건물을 짓기 전에 지어야 하는 건물들 수
        int[] inDegree = new int[N + 1];
        // [i]번 건물을 짓는데 걸리는 시간
        int[] buildingTime = new int[N + 1];
        // [i]번 건물을 짓는데 걸리는 최소 시간
        int[] minTime = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            canBuildNext[i] = new LinkedList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            buildingTime[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int buildingNum = Integer.parseInt(st.nextToken());
                if (buildingNum == -1) {
                    break;
                }

                canBuildNext[buildingNum].add(i);
                inDegree[i]++;
            }
        }

        // 위상 정렬하면서 최소 시간을 계산하기
        // 젤 처음 지어야하는 건물들 찾기
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                minTime[i] = buildingTime[i];
                q.offer(i);
            }
        }
        // 위상 정렬
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : canBuildNext[curr]) {
                // next를 짓는데 걸리는 최소 시간 =
                // next를 짓기 위해 먼저 지어야 하는 건물 중 가장 긴 시간 + next를 짓는 시간
                minTime[next] = Math.max(minTime[next], minTime[curr] + buildingTime[next]);
                // curr번 건물을 지어서 다음 건물을 지을 수 있게 되면
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            answer.append(minTime[i]).append("\n");
        }
        System.out.println(answer);
    }
}