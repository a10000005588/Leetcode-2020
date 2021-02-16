func lengthOfLIS(nums []int) int {
	if len(nums) == 1 {
		return 1
	}

	memo := make([]int, 2500)

	max := -1000000 //   -10^4 < nums[i] < 10^4

	for i := len(nums) - 1; i >= 0; i-- {
		curMax := findMaxSubsequence(i, nums, memo)
		if max < curMax {
			max = curMax
		}
	}

	return max
}

func findMaxSubsequence(index int, nums []int, memo []int) int {
	// 若是nums最末端，長度只有1
	if index == len(nums)-1 {
		memo[index] = 1
	}

	// 如果當前的index沒有計算longest increasing subsequence
	if memo[index] == 0 {
		// 設置初始值為1 (自己的長度)
		memo[index] = 1

		for i := index + 1; i < len(nums); i++ {
			// 找到比目前index的值，更大的對象，才能夠納入自己的subsequence
			if nums[index] < nums[i] {
				// 若找到比自己更大的longest increasing subsequence，進行更新
				if memo[index] <= memo[i] {
					memo[index] = memo[i] + 1
				}
			}
		}
	}
	return memo[index]
}