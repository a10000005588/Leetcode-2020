# 137. Single Number II

Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
```
Input: [2,2,3,2]
Output: 3
```
Example 2:

```
Input: [0,1,0,1,0,1,99]
Output: 99
```

## 我的作法

> 6/22 1刷

用map去紀錄每一個值: x, x當作k, value為出現的次數

在用 Map.Entry<Integer,Integer> entry : map.EntrySet() 來遍歷每一個key的值, 找出只有出現一次的key即為答案

### code

```java=
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        
        int ans = 0;
        for (int element: nums) {
            if (countMap.containsKey(element)) {
                countMap.put(element, countMap.get(element) + 1);
            } else {
                countMap.put(element, 1);
            }
        } 
        
        // 找出只有一次的key
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans = entry.getKey();
            }
        }
        
        return ans;
    }
}
```

```
11 / 11 test cases passed.
Status: Accepted
Runtime: 4 ms
Memory Usage: 39.5 MB
```
但用了extra space !