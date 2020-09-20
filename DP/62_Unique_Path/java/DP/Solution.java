class Solution {
    int count = 0;
    public int uniquePaths(int m, int n) {
        int rightArrowCount = m - 1;
        int bottomArrowCount = n - 1;
        if (rightArrowCount == 0 || bottomArrowCount == 0) {
            return 1;
        }
        traverse(rightArrowCount-1, bottomArrowCount);  // 從 往右箭頭 開始
        traverse(rightArrowCount, bottomArrowCount-1);  // 從 往下箭頭 開始
        return count;
    }

    public void traverse(int m, int n) {
        if (m == 0 && n == 0) {
            this.count++;
            return;
        }
        if (m == 0 && n != 0) {
            traverse(0, n-1);
            return;
        }
        if (m != 0 && n == 0) {
            traverse(m-1, 0);
            return;
        }
        traverse(m-1, n);
        traverse(m, n-1);
        return;
    }
}
