package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-02
public class Main {
    // 가장 긴 증가하는 부분 수열을 찾는 문제
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] boxes = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        
        // 가장 긴 수열의 길이
        int maxLength = 0;
        // [i]번째 박스까지 사용했을 때 만들 수 있는 가장 긴 수열
        int[] lis = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int longest = 0;
            for (int j = 0; j < i; j++) {
                // [j]번째 박스가 [i]번째 박스보다 작을 때
                // [j]번째 박스를 마지막에 담은 결과가 가장 긴 수열이라면
                if (boxes[j] < boxes[i] && longest <= lis[j]) {
                    longest = lis[j];
                }
            }
            // i상자보다 작은 상자들 중 가장 긴 수열에 + 1
            lis[i] = longest + 1;
            maxLength = Math.max(maxLength, lis[i]);
        }

        System.out.println(maxLength);
    }
}
