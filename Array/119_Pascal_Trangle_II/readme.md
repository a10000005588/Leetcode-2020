# 119. Pascal's Triangle II

此題與119一樣，差別在
是否能達成只用 extra O(k) space

## 暴力解 (沒有達成只用 extra O(k))

先手動回傳 numRows=0, 1, 2的值回去

在宣告一個array, 根據上一個rows的值，依序產生當前row的內容

### Golang

```go=

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
```

Runtime: 0 ms, faster than 100.00% of Go online submissions for Pascal's Triangle II.

Memory Usage: 2 MB, less than 99.19% of Go online submissions for Pascal's Triangle II.

## 參考解法

達成 extra O(k) space

[1]

一開始先創建好rowindex大小的array ex: [1,0,0,0,0]

然後開始從i=1開始loop

第二個loop j從i開始往1處理  num[j] = num[j] + num[j-1]

[1,0,0,0,0] => [1,(0+1),0,0,0] = [1,1,0,0,0] 

j已經等於1 結束

在i位置放置1

下一回合
i=2時，
j=2
[1,1,(0+1),0,0] = [1,1,1,0,0]

[1,(1+1),1,0,0] = [1,2,1,0,0]

j已經等於1 結束

在i位置放置1

下一回合
[1,2,1,1,0] => [1,2,3,1,0]

[1,2,3,1] => [1,3,3,1,0]

....以此類推

```go=

func getRow(rowIndex int) []int {
	if rowIndex == 0 {
		ans := []int{1}
		return ans
	}
	if rowIndex == 1 {
		ans := []int{1,1}
		return ans
	}

	var newArr = make([]int, rowIndex+1)
	newArr[0] = 1
	for i:=1; i<rowIndex+1; i++ {
		for j:=i; j>0; j-- {
			newArr[j] = newArr[j] + newArr[j-1]
		}
		newArr[i] = 1
	}
	newArr[len(newArr)-1] = 1
	return newArr
}
```

Runtime: 0 ms, faster than 100.00% of Go online submissions for Pascal's Triangle II.

Memory Usage: 2 MB, less than 99.19% of Go online submissions for Pascal's Triangle II.
