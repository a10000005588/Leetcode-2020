# 528. Random Pick with Weight (medium)

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

```
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
```

```
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
```

### 題目解釋

如果給 Solution = [1,3] 表示 
* index=0 的權重為1
* index=1 的權重為3

那呼叫pickIndex 選到 index=0的機率為 1/ (1+3), index=1的機率為 3/ (1+3)


## 我的作法

> 6/6 一刷


由於看不懂Output的值, 所以直接參考了[影片](https://www.youtube.com/watch?v=v-_aEMtgnkI)的解析, 作法如下:

設定一個territory, 看每個index的權重有多少, 若權重越大, 在territory所佔的土地就越多

ex:  [1,3,2] , territory大小就為 (1+3+2) = 6, index=0佔據1個, index=1佔據3個, 以此類推:

territory = [0,1,1,1,2,2]

然後在pickIndex()

隨機產生 r = 0 ~ territory.length -1 的值, 回傳 territory[r] 即可


但再跑測資會有 `memory limit exceeded `的狀況

```java=
class Solution {
    int totalWeight = 0;
    int[] territory;
        
    public Solution(int[] w) {
         // 先計算w[] 內每個index的權重總和有多少  
        for (int i=0; i< w.length; i++) {
            totalWeight += w[i];
        }
        
        int count = 0;
        // 設定每個index在territory所佔的大小
        territory = new int[totalWeight]; 
        for (int i=0; i< w.length; i++) {
            // 看w[i]的權重是多少, 分配多少土地
            for (int j =0; j < w[i]; j++) {
                territory[count] = i;
                count++;
            }
        }
    }
    
    public int pickIndex() {
        int r = (int)(Math.random() * totalWeight + 1) - 1;
        return territory[r];
    }
}
```

```
/**
 Submission Detail
55 / 57 test cases passed.
Status: Memory Limit Exceeded
Submitted: 0 minutes ago

Last executed input:
["Solution", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "
```

### 參考做法

影片的解答

假設 Solution: [1,2,3,2], 計算每個index的累加分佈為何

![](https://i.imgur.com/016VGg8.png)

pickIndex() 會隨機產生 0~7的值 (因為weight總和為7, 在看"值"會落在哪個culmulativeWeight[index] 的區間內(為index的機率分佈), 對應的index即 pickIndex()的答案

假如pickIndex() 產出 ２
落在index = 1,
故回傳1

![](https://i.imgur.com/qd6RcYy.png)


```java=
class Solution {
    int totalWeight;
    int[] cumulativeWeight;
        
    public Solution(int[] w) {
        totalWeight = 0;
        cumulativeWeight = new int[w.length];
        
        for (int i=0; i< w.length; i++) {
            totalWeight += w[i];
            // 階段性的紀錄index在哪個區間
            cumulativeWeight[i] = totalWeight;
        }
    }
    
    public int pickIndex() {
        int index = (int)(Math.random() * totalWeight);
        return binarySearch(index + 1);
    }
                          
    public int binarySearch(int val) {
        int l = 0;
        int r = cumulativeWeight.length - 1;
        while(l < r) {
            int mid = l + (r-l)/2;
            if (cumulativeWeight[mid] < val)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
```
