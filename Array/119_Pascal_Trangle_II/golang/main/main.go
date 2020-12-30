package main

func getRow(rowIndex int) []int {
	if rowIndex == 0 {
		ans := []int{1}
		return ans
	}
	if rowIndex == 1 {
		ans := []int{1,1}
		return ans
	}

	// Create a new array that the size is as same as rowIndex
	oldArr := make([]int, rowIndex)
	oldArr[0] = 1
	oldArr[1] = 1
	var newArr = make([]int, rowIndex+1)
	for i:=2; i<rowIndex+1; i++ {
		for j:=0; j<i+1; j++ {
			if j==0 {
				newArr[j] = 1
				continue
			}
			if j==i {
				newArr[j] = 1
				break
			}
			newArr[j] = oldArr[j] + oldArr[j-1]
		}
		copy(oldArr, newArr)
	}
	return newArr
}

func main() {
	getRow(10)
}