package main

import "fmt"

func main() {
	input := [...]int{1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}
	var ans = findMaxConsecutiveOnes(input)
	fmt.Print(ans)
}

func findMaxConsecutiveOnes(nums []int) int {
	i := -1
	j := -1
	max := -1
	hasOne := false
	for k := 0; k < len(nums); k++ {
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
		if nums[k] == 0 {
			curr := j - i
			if curr > max {
				max = curr
			}
			i = -1
			j = -1
		}
	}
	if i != -1 || j != -1 {
		if (j - i) > max {
			max = j - i
		}
	}
	if hasOne {
		max += 1
	}
	return max
}
