# 747. Largest Number At Least Twice of Others

In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:
```
Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
```

Example 2:
```
Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
```

Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].

## 想法 

用brute force
先用一個loop找出最大值 max
用第二個loop 找出第二大的值

#### cpp

```cpp=
class Solution {
public:
  
int dominantIndex(vector<int>& nums) {
    if (nums.empty()) {
        return -1;
    }
    if (nums.size() == 1) {
        return 0;
    }
    // find the maximum num and the second, then compare...
    int max = -1;
    int index = -1;
    for(string::size_type i=0; i < nums.size(); ++i) {
        if (max < nums[i]) {
            max = nums[i];
            index = (int)i;
        }
    }
    int secondMax = -1;
    // find the second largest..
    for(string::size_type i=0; i < nums.size(); ++i) {
        if (secondMax < nums[i] && nums[i] < max)
            secondMax = nums[i];
    }
    if (max >= secondMax * 2)
        return index;
    return -1;
}
};
```