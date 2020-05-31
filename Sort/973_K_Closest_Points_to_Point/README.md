# 973. K Closest Points to Origin (medium)

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:
```
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
```
Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
```

## 我的作法

> 5/31 一刷

先建立一個array, 紀錄各個point的長度

在排序該array, 取得array中第k-1個array的值. 當作標準

然後在loop原本的array, 若小於標準則納入答案, 因為題目確定每一個point的Euclidean值都不會重複

### Code

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        // O(NlogN), N =  length of points

        int N = points.length;
        int[] dists = new int[N];
        for (int i=0; i < N; i++) {
            dists[i] = calDistance(points[i]);
        }
        
        // find the K shortest distance.
        Arrays.sort(dists);
        int distK = dists[K-1];
        
        int[][] ans = new int[K][2];
        int t =0;
        
        // compare with the distK one by one...
        for (int i=0; i<N; i++) {
            if(calDistance(points[i]) <= distK) {
                ans[t] = points[i];
                t++;
            }
        }
        return ans;
    }
    
    public int calDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
```


