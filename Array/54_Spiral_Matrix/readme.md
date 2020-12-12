# 54. Spiral Matrix

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

![pic1](pics/spiral1.jpg)
```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

Example 2:

![pic1](pics/spiral2.jpg)
```
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```
Constraints:

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 10
* -100 <= matrix[i][j] <= 100


## 我的作法

用一個2D array紀錄走過的路


### cpp

```cpp=

vector<int> spiralOrder(vector<vector<int>>& matrix) {
    // 紀錄走過的
    vector<vector<int>> traverse(matrix.size(), vector<int>(matrix[0].size(),0));
    // store answer
    vector<int> ans;
    // 如果是空array reutrn ans
    if (matrix.size() == 0) {
        return ans;
    }
    int direction = 0;
    int m = matrix.size(); // row
    int n = matrix[0].size(); // column
    int x = 0;
    int y = 0;
    // 0: right: y+1, 1: down: x+1, 2: left: y-1, 3: top:x-1
    while(1) {
        ans.push_back(matrix[x][y]);
        traverse[x][y] = 1;
        if (direction == 0) {
            // 先判斷是否超過邊界 在判斷是否已經走過
            if (y+1>=n || traverse[x][y+1] == 1) {
                direction = 1;
                // 如果無法往下走 break;
                if (x+1>=m || traverse[x+1][y]) {
                    break;
                }
                x=x+1;
            } else {
                y=y+1;
            }
        } else if (direction == 1) {
            // 先判斷是否超過邊界 在判斷是否已經走過
            if (x+1>=m || traverse[x+1][y] == 1) {
                direction = 2;
                // 如果無法往左走 break;
                if (y-1<0 || traverse[x][y-1] == 1) {
                    break;
                }
                y=y-1;
            } else {
                x=x+1;
            }
        } else if (direction == 2) {
            if (y-1<0 || traverse[x][y-1] == 1) {
                direction = 3;
                // 如果無法往上走 break;
                if (traverse[x-1][y] == 1) {
                    break;
                }
                x=x-1;
            } else {
                y=y-1;
            }
        } else if (direction == 3) { // 往上走之後基本上就一定會是遇到走過的路徑
            if (traverse[x-1][y] == 1) {
                direction = 0;
                // 如果無法往右邊走 break;
                if (traverse[x][y+1] == 1) {
                    break;
                }
                y=y+1;
            } else {
                x=x-1;
            }
        }
    }
    return ans;
}
```

Runtime: 0 ms, faster than 100.00% of C++ online submissions for Spiral Matrix.
Memory Usage: 7.3 MB, less than 16.80% of C++ online submissions for Spiral Matrix.