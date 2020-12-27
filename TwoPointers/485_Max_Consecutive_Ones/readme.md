# 485. Max Consecutive Ones

Given a binary array, find the maximum number of consecutive 1s in this array.
```
Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
```

Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

## 我的做法

將i,j初始值設置成-1
使用i紀錄第一次遇到1的位置，
使用j紀錄目前連續遇到1的位置

如果遇到0，就把i,j重置成 -1


### Golang

```golang
func findMaxConsecutiveOnes(nums []int) int {
	i := -1
	j := -1
	max := -1
	hasOne := false
	for k:=0; k<len(nums); k++ {
		if nums[k] == 1 {
			hasOne = true
			if i == -1 {
				i = k // 將i放到第一次遇到1的位置
			}
			if j == -1 {
				j = k // 將k放到第一次遇到1的位置
			} else {
				j = j + 1
			}
		}
		if nums[k]==0 {
			curr := j-i
			if curr > max {
				max = curr
			}
			i = -1
			j = -1
		}
	}
	if i != -1 || j != -1 {
		if (j-i) > max {
			max = j-i
		}
	}
	if hasOne {
		max += 1
	}
	return max
}
```