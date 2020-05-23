class ReferenceSolution {
    public int countSquares(int[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }

        int count = 0;

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                // 遇到0就pass
                if (matrix[i][j] == 1) {
                    // 若是最左邊(j=0)或最上方(i=0)的, 直接判斷, 因為沒有左或上方的鄰居
                    if (i==0 || j==0) {
                        count++;
                    } else {
                        // 根據鄰居更新其值
                        int cellVal = min(matrix[i-1][j], matrix[i][j-1], matrix[i-1][j-1]) + matrix[i][j];
                        count += cellVal;
                        matrix[i][j] = cellVal; // 記住目前matrix[i][j]可形成多大的正方形
                    }
                }
            }
        }

        return count;
    }

    private int min(int left, int top, int leftTop) {
        int min = Integer.MAX_VALUE;
        if (min > left)
            min = left;
        if (min > top)
            min = top;
        if (min > leftTop)
            min = leftTop;

        return min;
    }
}
