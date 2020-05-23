package src.main

class Solution {
    public int countSquares(int[][] matrix) {
        // 暴力法
        // 先算出正方形的大小 min(m,n)
        int square = min(matrix.length, matrix[0].length);

        // 直接先計算陣列內為1的值有多少個
        int count = 0;

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] == 1) { count++; }
            }
        }

        if (square < 2) { return count; }

        // 將square * square大小從2,3,4,...square的範圍放入check()檢查
        int init = 2;
        while(init <= square) {
            // 假如現在是init=2 , 那一開始的話會是從 (0,0) 開始, 並往右邊開始找target, 若往右邊都找完後, 往下一格
            // 可往右邊移動的空間為 rightMax = maxtrix[0].length - init
            // 可往下面移動的空間為 downMax = maxtrix.length - init

            // 每一次方塊大小初始化設定
            int rightMax = matrix[0].length - init;
            int downMax = matrix.length - init;
            int startRight = 0;
            int startDown = 0;

            while(true) {
                boolean isSquare = true;

                // 如果matrix[startRight][startDown] = 0 直接忽略
                if (matrix[startDown][startRight] != 0) {
                    // 判斷是否是正方形的容器
                    int[][] target = new int[init][init];
                    int targetX = 0;
                    int targetY = 0;

                    for(int i=startDown; i< (startDown + init); i++) {
                        for(int j=startRight; j< (startRight + init); j++) {
                            target[targetX][targetY] = matrix[i][j];
                            targetY++;
                            if (matrix[i][j] != 1) {
                                isSquare = false;
                                break;
                            };
                        }
                        if (!isSquare) {
                            break;
                        }
                        targetX++;
                        // 重置Y
                        targetY = 0;
                    }
                } else {
                    isSquare = false;
                }

                // 如果是isSquare, 那就count + 1
                if (isSquare) {
                    count++;
                }

                // 移動startRight與startDown
                if (startRight < rightMax) {
                    startRight++;
                } else {
                    // 確認是否到底
                    if (startDown < downMax) {
                        startDown++;
                        // 將startRight歸0
                        startRight = 0;
                    } else {
                        // 到底了, 結束迴圈
                        break;
                    }
                }
            }
            init++;
        }

        return count;
    }


    private int min(int m, int n) {
        return m < n ? m : n;
    }
}
