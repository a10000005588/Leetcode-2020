# 287. Find the Duplicate Number (medium)

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:
```
Input: [1,3,4,2,2]
Output: 2
```
Example 2:
```
Input: [3,1,3,4,2]
Output: 3
```
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.


## 我的作法

> 6/25一刷 約花20分鐘

不小心瞥到分類為binary search, 大概就曉得要用搜尋的方式來解題

思路：
1. 先loop給一個值
2. 對每ㄧ值在到array內找是否存在 (除了自己)
    
* 若存在就是答案了


### Java

```java=
class Solution {
    public int findDuplicate(int[] nums) {
        // 用for loop 遍歷每一個, 紀錄當前位置 index
        // 對每一個進行binary search, 除了index外, 在看是否有沒有找到
        int ans = 0;
        for(int i=0; i<nums.length; i++) {
            if(doSearch(nums, i, nums[i])) {
                ans = nums[i];
                break;
            }
        }
        
        return ans;
    }
    
    boolean doSearch(int[] nums, int index, int target) {
        for(int i=0; i<nums.length; i++) {
            if (i == index) {
                continue;
            }
            
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
```

```
53 / 53 test cases passed.
Status: Accepted
Runtime: 548 ms
Memory Usage: 39.2 MB
```

