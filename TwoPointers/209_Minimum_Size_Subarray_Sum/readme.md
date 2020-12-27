# 209. Minumum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 
```
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
```

Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 


## 我的作法

### Edge Case

* input: empty array.

### 暴力解

直接用三個loop去找所有可能的值

前兩個shuffle i與j, 第三個loop計算i~j的總合
```shell script
[1,2,4,5,6,2,3]

 ij
 i->j
 i-->j
 i---->j
   i->j
   i--->j
```

Time Limited Exceeded

```golang=
func minSubArrayLen(s int, nums []int) int {
    var minLength = 10000000
	for i:=0; i<len(nums); i++ {
		for j:=i; j<len(nums); j++ {
			var curTotal = 0
			for k:=i; k<=j; k++ {
				curTotal += nums[k]
			}
			if curTotal >= s {
				curLength := (j-i)+1
				// check i-j is < minLength or not
				if curLength < minLength {
					minLength = curLength
				}
				// skip
				break
			}
		}
	}

	if minLength >= 10000000 {
		return 0
	}

	return minLength
}
```

Time Complexity: O(n^3)

## 參考想法

https://leetcode.com/problems/minimum-size-subarray-sum/solution/

### better的暴力解

原本暴力解會造成 每次要計算i~j時，多花費O(n)的時間計算, 使得總共花費成本達到 O(n^3)

可以先用sums[index] array，儲存從0到index的sum為多少, 花費 O(n)

若要得知 i->j的subarray的總和 = sums[j] - sums[i] + nums[i]

再用i與j迭代，並且透過索引sums曉得i到j的總和， 共花費可降低到 O(n^2)

```go=
func minSubArrayLen1(s int, nums []int) int {
	var minLength = 1000000
	if len(nums) == 0 {
		return 0
	}
	// calculate the sum of every subarray
	var sums = make([]int, len(nums))
	sums[0] = nums[0]
	for i:=1; i<len(nums); i++ {
		sums[i] = sums[i-1] + nums[i]
	}

	for i:=0; i<len(nums); i++ {
		for j:=i; j<len(nums); j++ {
			var curTotal = sums[j] - sums[i] + nums[i]
			if curTotal >= s {
				curLength := (j-i)+1
				// check i-j is < minLength or not
				if curLength < minLength {
					minLength = curLength
				}
				// skip
				break
			}
		}
	}
	if minLength >= 1000000 {
		return 0
	}

	return minLength
}
```

Runtime: 92 ms, faster than 5.06% of Go online submissions for Minimum Size Subarray Sum.

Memory Usage: 4.1 MB, less than 6.33% of Go online submissions for Minimum Size Subarray Sum.

### 使用Two Pointer

使用left指標，指向位置0，

初始化sum=0
使用for loop, 並用i依序找出總合sum大於s,   
---> 若有大於s, 那就sum- nums[left] (left目前所指的地方) , left往前進一格
---> 如果sum還是大於s, 那一樣sum - nums[left] (left新的地方) , left再往前一格

如此一來 每個值 只會走被走到兩遍

Time Complexity: O(n)

```go=

func min(x int, y int) int {
	if x > y {
		return y
	}
	return x
}

func minSub(s int, nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	var ans = 1000000000
	var sum = 0
	var left = 0
	for i:=0; i<len(nums); i++ {
		sum += nums[i]
		for sum >= s {
			ans = min(ans, i+1-left)
			sum -= nums[left]
			left += 1
		}
	}
	if ans == 1000000000 {
		return 0
	}
	return ans
}
```

Runtime: 4 ms, faster than 98.10% of Go online submissions for Minimum Size Subarray Sum.

Memory Usage: 3.9 MB, less than 65.82% of Go online submissions for Minimum Size Subarray Sum.
