#include <iostream>
#include <vector>

using namespace std;

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

int main() {
    vector<vector<int>> input = {{3},{2}};
    vector<int> ans = spiralOrder(input);
    return 0;
}
