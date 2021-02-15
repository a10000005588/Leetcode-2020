# 276. Paint Fence (easy)

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:
```
Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
```

### Companies

* JPMorgan: 2
* Google: 3
* Expedia: 2

## 我的解法 (暴力解)

直接透過排列組合方式，將所有解兜出來 (但後來沒寫出來)

## 參考解法

參考Leetcode Premium提供的解答，以 Dynamic Programming 1D - Top Down 方式。

n為fence之post的數目，k為顏色的數量。

定義 `totalWays(i)` 為對第i個fence的post進行著色，並可以用k個顏色

會有以下兩個案例：
1. 對第`i-1`個fence post取得共 `totalWays(i-1)`，由於 `i-1`個顏色不與`i`個post相同，故對`i`這個post來說，總共會有 `totalWays(i-1)` * `k-1` 種著色的方法
2. 對第 `i-1` 與 `i` 都有相同的顏色，那就表示第`i`個的著色數量是根據第`totalWays(i-2)`個 乘以 `(k-1)`個方法

綜合以上

* 若對第`i`個post進行著色，當n>2時，總共會有
  * `totalWays(i-1) * (k-1)` + `toatlWays(i-2) * (k-1)`個著色數量
* n = 1: 總共有 `k` 個數量
* n = 2: 總共有 `k*k` 個數量
  * 只有兩個post, 第一個post上個紅色, 那第二個post可上k種顏色 共k個
  * 總共有k個post, 那就共有 k*k個著色方式

## Java

```java=
class Solution {
    private int[] memo;
   
    public int numWays(int n, int k) {
        // n: number of posts, k: number of colour
        if (n == 0 || k == 0) {
            return 0;
        }
        
        memo = new int[n];
        return totalWays(n, k);
    }
    
    private int totalWays(int posts, int k) {
        if (posts == 1) {
            return k;
        }
        
        if (posts == 2) {
            return k*k;
        }
        
        if (this.memo[posts-1] == 0) {
            this.memo[posts-1] = this.totalWays(posts - 1, k);
        }
        if (this.memo[posts-2] == 0) {
            this.memo[posts-2] = this.totalWays(posts - 2, k);
        }
        
        return (this.memo[posts-1] + this.memo[posts-2]) * (k-1);
    }
   
}
```
```
79 / 79 test cases passed.
Status: Accepted
Runtime: 0 ms
Memory Usage: 37.4 MB
```

Time complexity : O(n)

Space complexity : O(n)

## Golang

第二次使用Golang時，在思考toatlWays回傳的值卡住，以為是要回傳

`(totalWay(posts-1) + totalWays(posts-2)) * (k-1)`，但這是錯誤的，要注意，思路需要再清晰些。

```go=
var memo []int

func numWays(n int, k int) int {
    memo = make([]int, n)
    
    if n == 0 || k == 0 {
        return 0
    }
    
    return totalWays(n, k)
}

func totalWays(posts int, k int) int {
    if (posts == 1) {
        return k;
    }
    
    if (posts==2) {
        return k*k;
    }
    
    if (memo[posts - 1] == 0) {
        memo[posts - 1] = totalWays(posts-1, k);
    }
    
    if (memo[posts - 2] == 0) {
        memo[posts - 2] = totalWays(posts-2, k);
    }
    
    return memo[posts-1] * (k-1) + memo[posts-2] * (k-1)
}
```

```
Runtime: 0 ms, faster than 100.00% of Go online submissions for Paint Fence.s
Memory Usage: 1.9 MB, less than 26.67% of Go online submissions for Paint Fence.
```