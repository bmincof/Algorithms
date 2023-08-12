import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] a = st.nextToken().toCharArray();
        char[] b = st.nextToken().toCharArray();

        int lenA = a.length;
        int lenB = b.length;

        int minDiff = 1000;
        // A의 양 옆으로 모자란 개수만큼 B와 같은 글자를 붙이면 됨
        // 기존의 B의 부분 문자열 중에서 A와 가장 작은 차이가 정답
        for (int i = 0; i < lenB - lenA + 1; i++) {
            int count = 0;
            for (int j = 0; j < lenA; j++) {
                if (a[j] != b[i + j]) {
                    count++;
                }
            }
            minDiff = Math.min(minDiff, count);
        }

        System.out.println(minDiff);
    }
}
