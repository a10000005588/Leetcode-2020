package main

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

func main () {
	input := []int{1,2}

	rotate(input, 3)
}