import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// author : bmincof
// date   : 2023-08-05
public class Main {
    // 다른 방법으로는 단어들을 사전순으로 정렬해서 셋으로 비교하는 방법이 있음
    // 아래는 정렬보다 빠른 방식임
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 나올 수 있는 모든 접두사
        Set<String> usedPrefix = new HashSet<>();
        // 단어 목록
        List<String> words = new LinkedList<>();

        // 모든 단어로 만들 수 있는 접두사들을 셋에 저장
        while (N-- > 0) {
            String word = br.readLine();
            StringBuilder sb = new StringBuilder();

            List<String> prefixes = new LinkedList<>();

            for (int i = 0; i < word.length() - 1; i++) {
                sb.append(word.charAt(i));
                prefixes.add(sb.toString());
            }

            usedPrefix.addAll(prefixes);
            words.add(word);
        }

        int answer = 0;
        // 다른 단어의 접두사로 등장하지 않은 단어만 세기
        for (String word : words) {
            if (usedPrefix.add(word)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}