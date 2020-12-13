# 118 Pascal's Trangle

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

![gif](pics/PascalTriangleAnimated2.gif)

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
```
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

## 我的作法

先手動回傳 numRows=0, 1, 2的值回去

在宣告一個array, 根據上一個rows的值，依序產生當前row的內容

### Cpp

about 15mins in 2020/12/13

```cpp=

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
```

Runtime: 0 ms, faster than 100.00% of C++ online submissions for Pascal's Triangle.
Memory Usage: 6.8 MB, less than 55.36% of C++ online submissions for Pascal's Triangle.

### java

2019
```java=
class Solution {
    public List<List<Integer>> generate(int numRows) {
        // 宣告一個 可以塞入List<Integer>的List...
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        List<Integer> previous = new ArrayList<Integer>();
        
        for(int i=1; i<=numRows; i++) {
            List<Integer> current = new ArrayList<Integer>();
            
            // 如果只有1排
            if(i == 1) {
                current.add(1);
                
            } else if(i == 2) {
                current.add(1);
                current.add(1);
            } else {
                for(int j=0; j<i; j++) {
                    // 第一個是1
                    if(j==0) {
                        current.add(1);
                    } else if (j==(i-1)) { // 最後一個也是1
                        current.add(1);
                    } else {
                        int value = previous.get(j) + previous.get(j-1);
                        current.add(value);
                    }
                }
            }
            result.add(current);
            previous = current;
        }
        
        return result;
    }
}
```

15 / 15 test cases passed.
Status: Accepted
Runtime: 0 ms
Memory Usage: 34 MB