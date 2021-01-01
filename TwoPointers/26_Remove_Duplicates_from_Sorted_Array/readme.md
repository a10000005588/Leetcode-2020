# 26. Remove Duplicates from Sorted Array

Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
 

Example 1:
```
Input: nums = [1,1,2]
Output: 2, nums = [1,2]
Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.
```

Example 2:
```
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4]
Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
```

Constraints:
```
0 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in ascending order.
```

## 我的作法 1 

遇到重複的char (index=i) 透過append方式 (i-1, i+1) 將其直接去掉

### Golang

```go=
func removeDuplicates(nums []int) int {
	for i := range nums {
		for j := i + 1; j < len(nums); {
			if nums[i] == nums[j] {
				// if next value j, equals i, which means j is duplicated val, then remove it.
				nums = append(nums[:i], nums[i+1:]...)
				continue
			}
			// if doesn't delete a value in number, increment the j.
			j++
		}
	}

	return len(nums)
}
```

## 我的作法 2: Two Pointer

使用two pointer技巧， i指定目前沒有重複的位置，如果有遇到重複的val

將i位置放置其val, 並increment i.

### Java

```java=
class Solution {
    public int removeDuplicates(int[] nums) {
        int i=0;
        
        for(int n:nums) {
            // if next value is not same as n, replace it with n.
            if(i == 0 || nums[i-1] < n) {
                nums[i] = n;
                i++;
            }
        }
        return i;
    }
}
```