# 724. Find Pivot Index
Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

 

Example 1:
```
Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
```
Example 2:
```
Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
```

Constraints:

The length of nums will be in the range [0, 10000].
Each element nums[i] will be an integer in the range [-1000, 1000].

## 想法

### Brute force

先loop index,

對每個index, 用兩個loop 分別加總index左邊與右邊的值，在做比較看看是否相等

#### cpp

```cpp=
int pivotIndex(vector<int>& nums) {
    // loop every index value
    for (string::size_type i = 0; i < nums.size(); ++i) {
        // for each index value, check the sum of left side is equal to right or not
        int sumOfLeft = 0;
        int sumOfRight = 0;
        for (string::size_type leftIndex = 0; leftIndex < i; ++leftIndex) {
            sumOfLeft += nums[leftIndex];
        }
        for (string::size_type rightIndex = i+1; rightIndex < nums.size(); ++rightIndex) {
            sumOfRight += nums[rightIndex];
        }
        if (sumOfLeft == sumOfRight)
            return (int)i;
    }
    return -1;
}
```

O(n^2)

會遇到Time limited exceeded 狀況

### 改良版的BF

一開始loop每個index, 就可以先計算左邊的總和了

#### cpp
```cpp=
int pivotIndex(vector<int>& nums) {
    int sumOfLeft = 0;
    // loop every index value
    for (string::size_type i = 0; i < nums.size(); ++i) {
        // caculate the sum of right;
        int sumOfRight = 0;
        for (string::size_type rightIndex = i+1; rightIndex < nums.size(); ++rightIndex) {
            sumOfRight += nums[rightIndex];
        }
        if (sumOfLeft == sumOfRight)
            return (int)i;
        sumOfLeft += nums[i];
    }
    return -1;
}
```

O(n^2)

```
Runtime: 1800 ms, faster than 5.34% of C++ online submissions for Find Pivot Index.
Memory Usage: 31.4 MB, less than 45.36% of C++ online submissions for Find Pivot Index.
```

### O(n)作法

計算總和， loop時計算目前pivot's left sum
再比對 pivot左邊總和與right sum (sum- left sum)

#### cpp
```cpp=
int pivotIndex(vector<int>& nums) {
    // get sum first
    int sum = 0;
    for (string::size_type i =0; i<nums.size(); ++i) {
        sum += nums[i];
    }

    int leftSum = 0;
    int size = (int)nums.size();
    for (string::size_type i=0; i < size; ++i) {
        int rightSum = sum - leftSum - nums[i]; // need subtract the pivot value
        if (leftSum == rightSum)
            return (int)i;

        leftSum += nums[i];
    }
    return -1;
}
```