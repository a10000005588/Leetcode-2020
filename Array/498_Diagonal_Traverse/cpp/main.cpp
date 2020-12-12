#include <iostream>
#include <vector>

using namespace std;

vector<int> findDiagonalOrder(vector<vector<int>>& matrix) {
    int x = 0;
    int y = 0;
    bool direction = true; // true: right-up, false: bottom-down
    vector<int> ans;
    // m: rows, n: columns
    int m = matrix.size()-1;
    int n = matrix[0].size()-1;
    if (matrix.size()==0) {return ans;}

    /* Rules */
    // start from (x,y) = (0,0), go right-up, if reach (0,?) or (n-1,?),
    //// if (x==0, y < n): y+1,
    //// if (y==n) : x+1,
    // go left-down, if reach (x==0, y) or (x, y==n-1), then reverse -> go left-down
    //// if (x<m, y==0): x+1;
    //// if (x==m): y+1;
    // go right-up, if reach (x,y==0) or (x==n-1, y) then reverse -> go right-up
    while(1) {
        ans.push_back(matrix[x][y]);
        if (x==m && n==y) { break; }

        if (direction) {
            if (y<n && x==0) {
                y=y+1;
                direction=!direction;
                continue;
            } else if (y==n) {
                x=x+1;
                direction=!direction;
                continue;
            } else {
                x = x-1;
                y = y+1;
            }
        } else {
            if (x<m && y==0) {
                x=x+1;
                direction=!direction;
                continue;
            } else if (x==m) {
                y=y+1;
                direction=!direction;
                continue;
            }
            x = x+1;
            y = y-1;
        }
    }
    return ans;
}

int main() {
    cout << "hello" << endl;
    vector<int> a = {1,2,3};
    vector<int> b = {4,5,6};
    vector<int> c = {7,8,9};
    vector<int> d = {10,11,12};

    vector<vector<int>> input;
    input.push_back(a);
    input.push_back(b);
    input.push_back(c);
    input.push_back(d);

    vector<int> ans = findDiagonalOrder(input);
    return 0;
}
