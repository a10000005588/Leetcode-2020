# 986. Interval List Intersections (medium)

> (2020/05/23) 一刷

https://leetcode.com/problems/interval-list-intersections/

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:

![](https://i.imgur.com/Yeg3zIA.png)


```
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 ```
 
Note:
```
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
```

## 我的作法

> 花了3.5小時...

暴力解, 先宣告一個陣列 trace[], 紀錄A擁有的值
在用loop去對照B與trace[]內是否有重複的地方, 若有的話就記錄到list內
最後回傳list內所有的值

### Java 

Submission Detail

```
76 / 86 test cases passed.
Status: Memory Limit Exceeded
```

殘念.....

Time Complexity: O(n^2)
Space Complexity: O(A二維陣列的大小)
```java=
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
```

## 參考解答

https://leetcode.com/problems/interval-list-intersections/solution/

![](https://i.imgur.com/P0bMXiE.png)

![](https://i.imgur.com/jOnUlja.png)

![](https://i.imgur.com/aOUmvpw.png)

![](https://i.imgur.com/cwy9iW3.png)

### code

```java=
class Solution {
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
```

Time Complexity: O(M + N)O(M+N), where M, NM,N are the lengths of A and B respectively.

Space Complexity: O(M + N)O(M+N), the maximum size of the answer.
