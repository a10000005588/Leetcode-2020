class ReferenceSolution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            // 取得最大的start point
            int startPoint = Math.max(A[i][0], B[j][0]);
            // 取得最小的end point
            int endPoint = Math.min(A[i][1], B[j][1]);

            if (startPoint <= endPoint) {
                ans.add(new int[]{startPoint, endPoint});
            }

            // 如果某邊的start point比較小, 往下一個interval移動
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
