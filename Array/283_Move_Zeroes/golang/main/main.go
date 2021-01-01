package main

func main() {
    input := []int{0,1,0,3,12,0,0,19}
    moveZeroes(input)
}

// Remain the same order...
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
