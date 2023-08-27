import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();
        char[] word = sc.next().toCharArray();
        
        for (char c : word) {
            if ('a' <= c && c <= 'z') {
                answer.append((char)(c - 'a' + 'A'));
            } else {
                answer.append((char)(c - 'A' + 'a'));
            }
        }
        
        System.out.println(answer);
    }
}