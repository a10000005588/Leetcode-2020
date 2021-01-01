package main

func main() {

}
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
