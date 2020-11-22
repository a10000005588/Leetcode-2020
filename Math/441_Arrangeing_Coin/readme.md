# 441. Arranging Coins (easy)

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:
```
n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
```
Example 2:
```
n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
```

## 我的作法

暴力解, 直接用for loop去計算每一行有多少, 每一回合就把n扣掉, 直到某回合的i比剩下的n還大, 那就找到答案了

### code

> 7/6 一刷 大概5分鐘

```java=
class Solution {
    public int arrangeCoins(int n) {
        int curTotal = 0;
        
        int i = 1;
        while(true) {
            if(i>n) {
                return i-1;
            }
            
            curTotal += i;
            n -= i; // 扣掉已經被拿來排第n排數量(i)的coin
            i++;
        }
    }
}
```

Time Complexity : O(n)


