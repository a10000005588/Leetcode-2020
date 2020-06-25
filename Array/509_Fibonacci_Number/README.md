# 509. Fibonacci Number (easy)

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).

 
```
Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
```

```
Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
```

```
Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 ```

Note:

0 ≤ N ≤ 30.


## 我的方法

### recursive的作法

> 6/17 一刷

使用遞迴

#### code

```java=
class Solution {
    public int fib(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        return fib(N-1) + fib(N-2);
    }
}
```

```
31 / 31 test cases passed.
Status: Accepted
Runtime: 0 ms
Memory Usage: 36.5 MB
```


### iterate的做法

![](https://i.imgur.com/0pf5s9w.png)

#### code

```java=
class Solution {
    // iterate方法
    public int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }

        int a = 0;
        int b = 1;

        int lastA = a;
        for (int i=0; i<N; i++) {
            lastA = a;

            if (i==0) {
                a = 0;
            } else {
                a = b;
            }

            b = b + lastA;
        }

        return b;
    }
}

```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
Memory Usage: 36.5 MB, less than 23.55% of Java online submissions for Fibonacci Number.
```

### Dynamic (Top-down) 作法

將計算過的 F(N) 給記錄在array內

#### code

```java=
class Solution {
    static int[] memo = new int[31]; //  0 < N < 30

    public int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }
        
        if (memo[N] == 0) {
            return memo[N] = fib(N-1) + fib(N-2);
        }

        return memo[N];
    }
}
```


```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
Memory Usage: 35.9 MB, less than 88.56% of Java online submissions for Fibonacci Number.
```
