# 283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

## 我的做法

需要保持一樣的order
用loop找到0, 再用第二個loop找到非0的, 做swap...

### Golang v2021/01/01

```go=
func moveZeroes(nums []int) {
	for i:=0; i<len(nums); i++ {
		if nums[i] == 0 {
		    // start to looking for the 0
			for j:=i+1; j<len(nums); j++ {
				if nums[j] == 0 {
					continue
				}
				// do swap
				temp := nums[j]
				nums[j] = nums[i]
				nums[i] = temp
				break
			}
		}
	}
}
```

Time Complexity: O(n^2)

Runtime: 4 ms, faster than 91.04% of Go online submissions for Move Zeroes.

Memory Usage: 3.8 MB, less than 15.29% of Go online submissions for Move Zeroes.

### Golang v2018/8/30

```go=
func moveZeroes(nums []int)  {
    // 算幾個0...
	count := 0
	for i := range nums {
		if nums[i] == 0 {
			count++
		}
	}

	for i := 0; i < count; i++ {
		for j := range nums {
			if nums[j] == 0 && j < len(nums)-1 {
				nums[j], nums[j+1] = nums[j+1], nums[j]
			}
		}
	}
}
```

Runtime: 132 ms

Memory Usage: N/A