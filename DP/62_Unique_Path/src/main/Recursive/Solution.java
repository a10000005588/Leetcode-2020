class Solution {
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        // 將matrix[m-1][...]與 matrix[...][n-1]全部都設成1
        // matrix[m-1][n-1] = matrix[m][n-1] + matrix[m-1][n]
        for(int i=m-1; i >= 0; i--) {
            for(int j=n-1; j >= 0; j--) {
                if (i == m-1 || j == n-1) {
                    matrix[i][j] = 1;
                    continue;
                }

                matrix[i][j] = matrix[i+1][j] + matrix[i][j+1];
            }
        }
        return matrix[0][0];
    }
}
