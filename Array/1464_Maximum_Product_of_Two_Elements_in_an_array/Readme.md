# 1464. Maximum Product of Two Elements in an Array

Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 

Example 1:

Input: nums = [3,4,5,2]
Output: 12 
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12. 
Example 2:

Input: nums = [1,5,4,5]
Output: 16
Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
Example 3:

Input: nums = [3,7]
Output: 12
 

Constraints:

2 <= nums.length <= 500
1 <= nums[i] <= 10^3


## 我的作法

先用for loop找出第一大的, 並記錄第一大的index,

第二次用for loop找出第二大的 (排除第一大的index)

回傳 第一大的-1 * 第二大的-1

### code

```java=
class Solution {
    public int maxProduct(int[] nums) {
        int max = 0;
        int max_index = 0;
        int secMax = 0;
        int secMax_index = 0;
        
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                max_index = i;
            }
        }
        
        for(int i=0; i<nums.length; i++) {
            if(i == max_index) continue;
            if(nums[i] > secMax) {
                secMax = nums[i];
                secMax_index = i;
            }
        }
        
        return (max-1) * (secMax-1);
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Product of Two Elements in an Array.
Memory Usage: 39.2 MB, less than 62.14% of Java online submissions for Maximum Product of Two Elements in an Array.
```
