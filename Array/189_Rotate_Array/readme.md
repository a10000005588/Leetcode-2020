# 189. Rotate Array


Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?


Example 1:
```
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```

Example 2:
```
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```

Constraints:
```
1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
```


## 我的作法

使用 O(n) extra spaces

先用mod確定要走的位置=realK，如果 k mod (len(nums)) == 0 表示不用移動 (因為移動了k次還是跟原本一樣)

先找到要shuffle的位置，例如 size-k，從該位置依序將值複製到 new array的第一個位置...

然後再將原本index=0的位置的值，依序把size-k個連續的值貼到 new array 第k之後的值

最後再將new array的值，覆寫道原本的array.

### Golang

```go=
func rotate(nums []int, k int) {
    var realK = k % len(nums)
    if realK == 0 {
    	return
	}
	var startIndex = len(nums) - realK
	newArr := make([]int, len(nums))
	for i:=0; i<realK; i++ {
		newArr[i] = nums[startIndex]
		startIndex++
	}
	for i:=0; i<len(nums)-realK; i++ {
		newArr[i+realK] = nums[i]
	}
	for i:=0; i<len(nums); i++ {
		nums[i] = newArr[i]
	}
}
```

Time Complexity : O(n)

Runtime: 4 ms, faster than 91.61% of Go online submissions for Rotate Array.

Memory Usage: 3.3 MB, less than 28.26% of Go online submissions for Rotate Array.