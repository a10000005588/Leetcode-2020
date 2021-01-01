# 1640. Check Array Formation Through Concatenation

You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].

Return true if it is possible to form the array arr from pieces. Otherwise, return false.

Example 1:
```
Input: arr = [85], pieces = [[85]]
Output: true
```
Example 2:
```
Input: arr = [15,88], pieces = [[88],[15]]
Output: true
```
Explanation: Concatenate [15] then [88]
Example 3:
```
Input: arr = [49,18,16], pieces = [[16,18,49]]
Output: false
```
Explanation: Even though the numbers match, we cannot reorder pieces[0].
Example 4:
```
Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
Output: true
```
Explanation: Concatenate [91] then [4,64] then [78]
Example 5:
```
Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
Output: false
```

Constraints:
```
1 <= pieces.length <= arr.length <= 100
sum(pieces[i].length) == arr.length
1 <= pieces[i].length <= arr.length
1 <= arr[i], pieces[i][j] <= 100
The integers in arr are distinct.
The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
```

## 我的作法 version 1 (fail)

將arrStr 與 piece內的值 轉換成string值

用trim的方法 但遇到以下這種測試會爆掉
```
[1,2,3]
[[2],[1,3]]
```
因為 123, 先去掉 2  => 13,
在去掉13, => "",  這樣也會通過， 但不符合題目要求。。。


```go=
func canFormArray(arr []int, pieces [][]int) bool {
	// 將arr的內容組合成string
	var arrStr = ""
	for _, val := range arr {
		arrStr += strconv.Itoa(val)
	}
	// 將pieces內的各個piece都組合成string
	var piecesStrContainer = []string{}
	for _, piece := range pieces {
		var pieceStr = ""
		for _, c := range piece {
			pieceStr += strconv.Itoa(c)
		}
		piecesStrContainer = append(piecesStrContainer, pieceStr)
	}

	// 比較piecesStrContainer與arrStr內，有完全符合就從arrStr去掉
	for i:=0; i<len(piecesStrContainer); i++ {
		pos, hasMatch := findMatchPos(arrStr, piecesStrContainer[i])
		if hasMatch {
			arrStr = arrStr[:pos] + arrStr[ (pos+len(piecesStrContainer[i])):]
		}
	}

	if arrStr == "" {
		return true
	}
	return false
}


func findMatchPos(arrStr string, piece string) (int, bool) {
	if len(piece) <= len(arrStr) {
		for i:=0; i<=(len(arrStr) - len(piece)); i++ {
			if arrStr[i] == piece[0] {
				// 比較是否相同
				arrStrSlice := arrStr[i:i+len(piece)]
				if arrStrSlice == piece {
					return i, true
				}
			}
		}
	}

	return -1, false
}
```

## 我的作法 version 2 (fail)

用checkArr紀錄piece已經組合的位置
但尚未考慮到，若有較小長度的字數 會把較長的piece 給佔據掉...

```
[2,82,79,95,28]
[[2],[82],[28],[79,95]]
```
例如 2會把28的2給去掉了...
```go=
func canFormArray(arr []int, pieces [][]int) bool {
	// 將arr的內容組合成string
	var arrStr = ""
	for _, val := range arr {
		arrStr += strconv.Itoa(val)
	}
	// 將pieces內的各個piece都組合成string
	var piecesStrContainer = []string{}
	for _, piece := range pieces {
		var pieceStr = ""
		for _, c := range piece {
			pieceStr += strconv.Itoa(c)
		}
		piecesStrContainer = append(piecesStrContainer, pieceStr)
	}

	checkArr := make([]int, len(arrStr))
	// 比較piecesStrContainer與arrStr內，有完全符合就從arrStr去掉
	for i:=0; i<len(piecesStrContainer); i++ {
		pos, hasMatch := findMatchPos(arrStr, piecesStrContainer[i])
		if hasMatch {
			// 將piece組合過的位置 變成1
			for j:=pos; j<pos+len(piecesStrContainer[i]); j++ {
				checkArr[j] = 1
			}
		}
	}

	for _, checkVal := range checkArr {
		if checkVal == 0 {
			return false
		}
	}
	return true
}


func findMatchPos(arrStr string, piece string) (int, bool) {
	if len(piece) <= len(arrStr) {
		for i:=0; i<=(len(arrStr) - len(piece)); i++ {
			if arrStr[i] == piece[0] {
				// 比較是否相同
				arrStrSlice := arrStr[i:i+len(piece)]
				if arrStrSlice == piece {
					return i, true
				}
			}
		}
	}

	return -1, false
}
```

## 我的作法 version 3 (fail)

Submission Detail

64 / 82 test cases passed.

Status: Wrong Answer

Submitted: 0 minutes ago

Input:

```
[2,74,86,25,20,19,87,98,55,94,58,47,56,28,50,51,54,14,79,72,23,48]
[[55],[87,98],[94,58,47],[54,14],[20,19],[28],[72],[23],[48],[79],[2],[50],[86],[56],[25],[74],[51]]
```

```go
func canFormArray(arr []int, pieces [][]int) bool {
	// 將arr的內容組合成string
	var arrStr = ""
	for _, val := range arr {
		arrStr += strconv.Itoa(val)
	}
	// 將pieces內的各個piece都組合成string
	var piecesStrContainer = []string{}
	for _, piece := range pieces {
		var pieceStr = ""
		for _, c := range piece {
			pieceStr += strconv.Itoa(c)
		}
		piecesStrContainer = append(piecesStrContainer, pieceStr)
	}

	if len(piecesStrContainer) == 1 {
		if arrStr == piecesStrContainer[0] {
			return true
		} else {
			return false
		}
	}

	var max = -1
	var maxPos = -1
	// 排序pieceStrContainer內的piece的長度，從最長開始放。。。
	for i:=0; i<len(piecesStrContainer); i++ {
		// 找到最長的長度..與i的位置值交換
		for j:=i; j<len(piecesStrContainer); j++ {
			if len(piecesStrContainer[j]) > max {
				max = len(piecesStrContainer[j])
				maxPos = j
			}
		}
		piecesStrContainer[i], piecesStrContainer[maxPos] = piecesStrContainer[maxPos], piecesStrContainer[i]
		maxPos = -1
		max = -1
	}

	checkArr := make([]int, len(arrStr))
	// 比較piecesStrContainer與arrStr內，有完全符合就從arrStr去掉
	for i:=0; i<len(piecesStrContainer); i++ {
		pos, hasMatch := findMatchPos(arrStr, piecesStrContainer[i], checkArr)
		if hasMatch {
			// 將piece組合過的位置 變成1
			for j:=pos; j<pos+len(piecesStrContainer[i]); j++ {
				checkArr[j] = 1
			}
		}
	}

	for _, checkVal := range checkArr {
		if checkVal == 0 {
			return false
		}
	}
	return true
}


func findMatchPos(arrStr string, piece string, checkArr []int) (int, bool) {
	if len(piece) <= len(arrStr) {
		var hasOccupied = false
		for i:=0; i<=(len(arrStr) - len(piece)); i++ {
			if arrStr[i] == piece[0] && checkArr[i] != 1 {
				// 比較是否相同
				arrStrSlice := arrStr[i:i+len(piece)]
				if arrStrSlice == piece {
					// 確認是否該piece沒有被用到
					for j:=i; j<i+len(arrStrSlice); j++ {
						if checkArr[j] == 1 {
							hasOccupied = true
						}
					}

					if hasOccupied {
						hasOccupied = false
						continue
					} else {
						return i, true
					}
				}
			}
		}
	}

	return -1, false
}
```

## 參考作法

將array內的值，用`!`做區分就簡單多了...
https://leetcode.com/problems/check-array-formation-through-concatenation/discuss/996476/Python-2-Solutions-explained

### Golang 

```go
func canFormArray(arr []int, pieces [][]int) bool {
	// 將arr的內容組合成string
	var arrStr = ""
	for _, val := range arr {
		arrStr += strconv.Itoa(val) + "!"
	}
	// 將pieces內的各個piece都組合成string
	var piecesStrContainer = []string{}
	for _, piece := range pieces {
		var pieceStr = ""
		for _, c := range piece {
			pieceStr += strconv.Itoa(c) + "!"
		}
		piecesStrContainer = append(piecesStrContainer, pieceStr)
	}

	if len(piecesStrContainer) == 1 {
		if arrStr == piecesStrContainer[0] {
			return true
		} else {
			return false
		}
	}

	var max = -1
	var maxPos = -1
	// 排序pieceStrContainer內的piece的長度，從最長開始放。。。
	for i:=0; i<len(piecesStrContainer); i++ {
		// 找到最長的長度..與i的位置值交換
		for j:=i; j<len(piecesStrContainer); j++ {
			if len(piecesStrContainer[j]) > max {
				max = len(piecesStrContainer[j])
				maxPos = j
			}
		}
		piecesStrContainer[i], piecesStrContainer[maxPos] = piecesStrContainer[maxPos], piecesStrContainer[i]
		maxPos = -1
		max = -1
	}

	checkArr := make([]int, len(arrStr))
	// 比較piecesStrContainer與arrStr內，有完全符合就從arrStr去掉
	for i:=0; i<len(piecesStrContainer); i++ {
		pos, hasMatch := findMatchPos(arrStr, piecesStrContainer[i], checkArr)
		if hasMatch {
			// 將piece組合過的位置 變成1
			for j:=pos; j<pos+len(piecesStrContainer[i]); j++ {
				checkArr[j] = 1
			}
		}
	}

	for _, checkVal := range checkArr {
		if checkVal == 0 {
			return false
		}
	}
	return true
}


func findMatchPos(arrStr string, piece string, checkArr []int) (int, bool) {
	if len(piece) <= len(arrStr) {
		var hasOccupied = false
		for i:=0; i<=(len(arrStr) - len(piece)); i++ {
			if arrStr[i] == piece[0] && checkArr[i] != 1 {
				// 比較是否相同
				arrStrSlice := arrStr[i:i+len(piece)]
				if arrStrSlice == piece {
					// 確認是否該piece沒有被用到
					for j:=i; j<i+len(arrStrSlice); j++ {
						if checkArr[j] == 1 {
							hasOccupied = true
						}
					}

					if hasOccupied {
						hasOccupied = false
						continue
					} else {
						return i, true
					}
				}
			}
		}
	}

	return -1, false
}
```

Runtime: 0 ms, faster than 100.00% of Go online submissions for Check Array Formation Through Concatenation.

Memory Usage: 3.2 MB, less than 6.20% of Go online submissions for Check Array Formation Through Concatenation.