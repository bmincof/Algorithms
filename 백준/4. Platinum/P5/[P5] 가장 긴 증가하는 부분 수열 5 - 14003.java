package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-03
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] position = new int[N];
        // 길이 [i]인 LIS를 만들 수 있는 가장 작은 마지막 수
        List<Integer> lis = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(seq[0]);

        for(int i = 1; i < N; i++) {
            int next = seq[i];

            int idx = Collections.binarySearch(lis, next);
            idx = idx < 0 ? -idx-1 : idx;

            if(idx >= lis.size()) {
                lis.add(next);
            } else {
                lis.set(idx, next);
            }
            position[i] = idx;
        }

        System.out.println(lis.size());

        // 뒤에서부터 LIS에 포함되는 값들을 하나씩 스택에 추가
        Deque<Integer> selected = new ArrayDeque<>();
        int curr = lis.size() - 1;
        for(int i = N - 1; i >= 0; i--) {
            if(position[i] == curr) {
                selected.push(seq[i]);
                curr--;
            }
        }
        // 스택에서 값을 하나씩 꺼내면서 LIS 복원
        while(!selected.isEmpty())
            sb.append(selected.pop()).append(" ");
        System.out.println(sb);
    }
}
