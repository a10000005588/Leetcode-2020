class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length ==0 || B.length ==0) { return new int[0][0]; }

        // 先宣告一個能夠cover A與B的array
        int end = max(A[A.length-1][1], B[B.length-1][1]);

        // 初始化trace內的值為0
        int[][] trace = new int[end+1][2]; // 需要判斷A的開頭(1)與結尾(2)

        // 紀錄A在trace中所佔據的值
        for (int i=0; i<A.length; i++) {
            for (int j=A[i][0]; j<=A[i][1]; j++) {
                if (j == A[i][0]) {
                    trace[j][1] = 1; // 開頭
                }
                if (j == A[i][1]) {
                    trace[j][1] = 2; // 結尾
                }
                trace[j][0] = 1;
            }
        }

        // 一開始會不曉得ans的大小為何, 用list
        ArrayList<int[]> list = new ArrayList<>();

        boolean isContinue = false;
        boolean isRecordStart = false;

        int startIndex = 0;
        int endIndex = 0;

        // loop B, 看有沒有跟trace=1的值重疊
        for (int i=0; i<B.length; i++) {
            // 從B[0].start開始, 到B[0].end
            for (int j=B[i][0]; j<=B[i][1]; j++) {
                // loop trace
                // 如果有重疊, 開始計算
                if (trace[j][0] == 1 && isContinue == false) {
                    // 紀錄開頭
                    if (!isRecordStart) {
                        startIndex = j;
                        isRecordStart = true;

                        // 如果連續都是0, 但遇到底卻是1, 或是遇到A區間的底
                        if (j == B[i][1] || trace[j][1] == 2) {
                            int[] overlapping = new int[2];
                            overlapping[0] = j;
                            overlapping[1] = j;
                            list.add(overlapping);

                            isRecordStart = false;
                            isContinue = false;
                        } else {
                            isContinue = true;
                        }
                    }
                } else if (trace[j][0] == 1 && isContinue == true && trace[j][1] != 2 && j != B[i][1]) { // 若要繼續的話, 不能為A的結尾, 以及B的結尾
                    continue;
                } else if ((trace[j][0] == 0 && isContinue == true)|| trace[j][1] == 2){ // 遇到A的結尾就停止, 並記錄
                    // 已到A的結尾, 紀錄該重複區段
                    endIndex = j;
                    int[] overlapping = new int[2];
                    overlapping[0] = startIndex;
                    overlapping[1] = endIndex;
                    list.add(overlapping);

                    isContinue = false;
                    isRecordStart = false;
                } else if (trace[j][0] == 1 && j == B[i][1]){ // 遇到B的結尾, 停止並記錄
                    // 已到B的結尾, 紀錄該重複區段
                    endIndex = j;
                    int[] overlapping = new int[2];
                    overlapping[0] = startIndex;
                    overlapping[1] = endIndex;
                    list.add(overlapping);

                    isContinue = false;
                    isRecordStart = false;
                } else {
                    isContinue = false;
                }
            }
        }

        // 將list轉換成int[][]
        int[][] ans = new int[list.size()][2];
        for (int i=0; i<ans.length; i++) {
            int[] overlap = list.get(i);
            ans[i][0] = overlap[0];
            ans[i][1] = overlap[1];
        }

        return ans;
    }

    public int min(int a, int b) {
        return a < b ? a : b;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }
}
