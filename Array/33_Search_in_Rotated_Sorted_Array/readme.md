# 33. Search in Rotated Sorted Array

You are given an integer array nums sorted in ascending order, and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

 

Example 1:
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```
Example 2:
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```
Example 3:
```
Input: nums = [1], target = 0
Output: -1
```

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is guranteed to be rotated at some pivot.
-10^4 <= target <= 10^4

## 想法

暴力解
因為rotate之後，還是可以用O(n)的方式，直接loop去找target在哪個index

### cpp

```cpp=
class Solution {
public:
  int search(vector<int>& nums, int target) {
      for(vector<int>::size_type i=0; i !=nums.size(); i++) {
          if (nums[i] == target) {
              return i;
          }
      }
      return -1;
  }
};
```

```
Success 
Runtime: X ms, faster than X.00% of C++ online submissions for Search in Rotated Sorted Array.
Memory Usage: 11.3 MB, less than 74.31% of C++ online submissions for Search in Rotated Sorted Array.
```