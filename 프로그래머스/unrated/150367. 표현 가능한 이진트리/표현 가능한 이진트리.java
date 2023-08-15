class Solution {
    int[] tree;
    
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            long number = numbers[i];
            tree = new int[64];
            // 숫자를 만드는데 필요한 최소 노드 수
            int nodes = 0;
            // 트리 만들기
            for (int j = 0; j < 64; j++) {
                if ((number & 1) != 0) {
                    tree[j] = 1;
                }
                number >>= 1;
                if (number == 0) {
                    nodes = j + 1;
                    break;
                }
            }
            
            // depth 계산하기
            int depth = 0;
            for (int j = 1; j < 64; j++) {
                if ((nodes >> j) == 0) {
                    depth = j;
                    break;
                }
            }
            
            // 포화 이진트리의 노드 개수 = 2^depth - 1
            int start = 0;
            int end = (1 << depth) - 1;
        
            // end의 인덱스는 최대 개수 - 1
            if (checkValidTree(start, end - 1)) {
                answer[i] = 1;
            }
        }
        
        
        return answer;
    }
    
    // target을 만들 수 없는 트리이면 false
    boolean checkValidTree(int start, int end) {
        if (start >= end) return true;
        
        int mid = (start + end) >> 1;
        
        if (tree[mid] == 0 && (isNotEmpty(start, mid - 1) || isNotEmpty(mid + 1, end))) {
            return false;
        }
        
        return checkValidTree(start, mid - 1) & checkValidTree(mid + 1, end);
    }
                                           
    boolean isNotEmpty(int start, int end) {
        int node = (start + end) >> 1;
        return tree[node] == 1;
    }
}