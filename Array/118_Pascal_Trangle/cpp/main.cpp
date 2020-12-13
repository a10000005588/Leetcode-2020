#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> generate(int numRows) {
    vector<vector<int>> ans;
    if (numRows == 0) {
        return ans;
    }
    int countForRows = 0;
    vector<int> row1 = {1};
    ans.push_back(row1);
    if (numRows == 1) {
        return ans;
    }

    vector<int> row2 = {1,1};
    ans.push_back(row2);
    if (numRows == 2) {
        return ans;
    }
    countForRows = countForRows + 2;
    while(countForRows < numRows) {
        vector<int> lastRow = ans.back();
        vector<int> newRow(countForRows+1, 1); // countForRows+1個0
        for(int i=1; i<countForRows;i++) {
            newRow[i] = lastRow[i]+lastRow[i-1];
        }
        ans.push_back(newRow);
        countForRows++;
    }
    return ans;
}

int main() {
    generate(5);
    return 0;
}
