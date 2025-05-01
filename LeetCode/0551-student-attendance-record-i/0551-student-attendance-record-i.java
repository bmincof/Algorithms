class Solution {
    public boolean checkRecord(String s) {
        int absent = 0;
        int late = 0;
        int serial = 0;

        for (char criteria : s.toCharArray()) {
            if (criteria == 'A' && ++absent == 2) {
                return false;
            } else if (criteria == 'L') {
                late++;
                if (++serial == 3) {
                    return false;
                }
            } else {
                serial = 0;
            }
        }

        return true;
    }
}