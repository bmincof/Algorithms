import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// author : bmincof
// date   : 2023-08-06
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        List<String> candidates = new LinkedList<>();

        for (int i = 0; i < word.length() - 2; i++) {
            for (int j = i + 1; j < word.length() - 1; j++) {
                String first = word.substring(0, i + 1);
                String second = word.substring(i + 1, j + 1);
                String third = word.substring(j + 1, word.length());

                StringBuilder sb = new StringBuilder();
                for (int k = first.length() - 1; k >= 0; k--) {
                    sb.append(first.charAt(k));
                }
                for (int k = second.length() - 1; k >= 0; k--) {
                    sb.append(second.charAt(k));
                }
                for (int k = third.length() - 1; k >= 0; k--) {
                    sb.append(third.charAt(k));
                }

                candidates.add(sb.toString());
            }
        }

        Collections.sort(candidates);
        System.out.println(candidates.get(0));
    }
}