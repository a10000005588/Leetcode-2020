# 62. Unique Paths (medium)

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:

```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
```

Example 2:
```
Input: m = 7, n = 3
Output: 28
```

## 我的作法 (遞迴)

使用遞迴的方式, 畫出樹狀圖可得知所有不同的走法

![](https://i.imgur.com/aAF4MeD.jpg)


### code

> 花了半小時想 + coding
> 
```java=
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
```

但會有Time Limited Exceeded...

```
Submission Detail
41 / 62 test cases passed.
Status: Time Limit Exceeded
Submitted: 3 minutes ago
Last executed input:

51
9
```

## 我的作法 (DP)

有瞥到分類是array + DP, 那就直覺想到用二維矩陣來推算

![](https://i.imgur.com/bhZEXEH.jpg)


### code 

> 大概花了1小時想+coding

```java=
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
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 36.6 MB, less than 23.29% of Java online submissions for Unique Paths.
```
