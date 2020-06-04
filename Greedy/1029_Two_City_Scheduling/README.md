# 1029. Two City Scheduling

There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 
```
Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 ```

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

## 我的作法

因為要分別分配N個人到A與B城市, 且要取得最小成本

那直覺的方式就是計算所有人到A city與B city的差異是多少

再來取差異最大的人到他最小成本的city

如果某city已經滿了, 那剩下的人就分配到另外一個city.


### Code

> 6/3號一刷, 大概花了3小時... (工作看了一整天code, 又在晚上思考leetcode如何解效率會有點差)

先看結果, 有點慘不忍睹

```
Runtime: 6 ms, faster than 6.13% of Java online submissions for Two City Scheduling.

Memory Usage: 38.8 MB, less than 63.89% of Java online submissions for Two City Scheduling.
```

```java=
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int countA = 0;
        int countB = 0;
        int maxCity = costs.length / 2;
        // 計算差值 與對應的people index
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<costs.length; i++) {
            map.put(i, Math.abs(costs[i][0] - costs[i][1]));
        }

        int curIndex = 0;
        int max = Integer.MIN_VALUE;

        // 找出差值最大的people
        while(!map.isEmpty()) {
            if (countA == maxCity && countB == maxCity) {
                break;
            }

            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (max < entry.getValue()) {
                    curIndex = entry.getKey();
                    max = entry.getValue();
                }
            }

            // 確認A或B是否已經滿了
            if (countA == maxCity) {
                ans += costs[curIndex][1];
                map.remove(curIndex);
                countB++;
                max = Integer.MIN_VALUE;
                continue;
            } else if (countB == maxCity) {
                ans += costs[curIndex][0];
                map.remove(curIndex);
                countA++;
                max = Integer.MIN_VALUE;
                continue;
            }


            if (costs[curIndex][0] < costs[curIndex][1]) {
                ans += costs[curIndex][0];
                countA++;
            } else {
                ans += costs[curIndex][1];
                countB++;
            }
            map.remove(curIndex);
            max = Integer.MIN_VALUE;
        }

        return ans;
    }
}
```

使用了 Hashmap紀錄people與他差異的對應值
透過 `Map.Entry<Integer,Integer> entry: map.entrySet()` 取得每一個people
