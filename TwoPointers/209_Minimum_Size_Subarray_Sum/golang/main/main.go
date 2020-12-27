package main

import "fmt"

func main() {
	input := []int{2,3,1,2,4,3}

	var ans = minSubArrayLen(7, input)
	fmt.Printf("%d\n", ans)
}

func sumOfArr(nums []int, front int, end int) int {
	total := 0
	for i:=front; i<=end; i++ { // i<=end  because the case: front = end
		total += nums[i];
	}
	return total
}

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