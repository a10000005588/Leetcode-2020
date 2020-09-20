# 1277. Count Square Submatrices with All Ones [medium]

> (2020/05/21) 一刷

https://leetcode.com/problems/count-square-submatrices-with-all-ones/

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.


Example 1:
```
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
```

Example 2:

```
Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 ```

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1

## 我的作法

> 大概花了4.5小時....
> 
一開始先想到用暴力解, 若遇到一個矩陣如下:

```
(m * n) = (5 * 4)
[
[1,0,1,1,0],
[1,1,1,1,0],
[1,1,1,0,0],
[1,1,1,0,0]
]
```

首先會先用O(n^2)的for loop 計算有幾個1, 

然後計算該square中的最大正方形長度為 min(m, n).

接著開始計算每一個大小的正方形在矩陣中是否都為1, 其計算方式如下:

* 計算出正方型大小為2在矩陣中
    * 每一列有 (m - 2) 個
    * 每一行有 (n - 2) 個

並以 (startRight, startDown) = (0, 0) 為起點開始找每一個正方形在矩陣是否都為1

舉個例子:

```
(m * n) = (5 * 4)
[
[1,0,1,1,0],
[1,1,1,1,0],
[1,1,1,0,0],
[1,1,1,0,0]
]
```

第一列中會有
```
[1,0] [0,1] [1,1] [1,0]
[1,1] [1,1] [1,1] [1,0]
```

可看到(startRight, startDown)是這些小正方形的左上角為主, 故第一列只會遍歷matrix`[1,0,1,1,0]`中的`[1,0,1,1]`

以此類推

### Java

Time Complexity: O(n^4) .

24 / 32 test cases passed.

此範例會在leetcode的測資中會遇到 time limited exceed.

```java=
class Solution {
    public int countSquares(int[][] matrix) {
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
```

## 參考作法

參考解答: [[Python] DP Solution + Thinking Process Diagrams (O(mn) runtime; O(1) space)
](https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/643429/Python-DP-Solution-%2B-Thinking-Process-Diagrams-(O(mn)-runtime-O(1)-space))

medium的難度得用像Dynamic Programming才能避免time limited exceeded.
Time Complexity為 O(m*n)


其思路如下:

![](https://i.imgur.com/1PRScdD.png)

![](https://i.imgur.com/t47ox1i.png)

![](https://i.imgur.com/eX09r3x.png)

![](https://i.imgur.com/0323NQH.png)

![](https://i.imgur.com/K3lc0Aa.png)

![](https://i.imgur.com/hYAw9J2.png)

### Java

```java=
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

```
