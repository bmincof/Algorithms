package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-31
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 현재 멀티탭에 연결되어 있는 용품
        Set<Integer> multiTab = new HashSet<>();

        // 각 시간별 사용하는 물품
        int[] useOrder = new int[K+1];
        // 각 물품이 사용되는 시간
        List<Integer>[] usedTime = new ArrayList[K+1];
        for (int i = 1; i <= K; i++) {
            usedTime[i] = new ArrayList<>();
        }

        // 각 전기용품별로 사용되는 시간을 기록하기
        st = new StringTokenizer(br.readLine());
        for (int t = 0; t < K; t++) {
            int item = Integer.parseInt(st.nextToken());
            usedTime[item].add(t);
            useOrder[t+1] = item;
        }

        // 각 시간에 대해서 뺄 플러그를 찾기
        // 멀티탭에 연결된 용품들 중 다음 사용 시간이 가장 멀리 있는 것
        int time = 0;
        int count = 0;
        while (time < K) {
            time++;
            int thisTimeUse = useOrder[time];

            if (multiTab.contains(thisTimeUse) || multiTab.size() < N) {
                multiTab.add(thisTimeUse);
                continue;
            }

            // 멀티탭에 공간이 없다면 가장 오래동안 사용하지 않을 물건을 빼기
            int longestNotUse = 0;
            int lnuNum = 0;
            for (int item : multiTab) {
                // 현재 시간 이후로 다시 이 물건을 사용하는 시간
                int nextTime = Collections.binarySearch(usedTime[item], time);
                if (nextTime < 0) {
                    nextTime = -nextTime-1;
                }
                // 앞으로 더 이용될 상황이 없다면
                if (nextTime == usedTime[item].size()) {
                    lnuNum = item;
                    break;
                }
                // 가장 늦게 재사용 되는 물건 찾기
                if (usedTime[item].get(nextTime) > longestNotUse) {
                    longestNotUse = usedTime[item].get(nextTime);
                    lnuNum = item;
                }
            }

            multiTab.remove(lnuNum);
            multiTab.add(useOrder[time]);
            count++;
        }

        System.out.println(count);
    }
}
