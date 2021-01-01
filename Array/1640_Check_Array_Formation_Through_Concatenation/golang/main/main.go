package main

import (
	"fmt"
	"strconv"
)

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


func main() {
	//input := [][]int{{28,46,57},{37,19,40,38}}
	//arr := []int{100,2,98,28,44,55,37}
	//input := [][]int{{2},{1,3}}
	//arr := []int{1,2,3}
	//input := [][]int{{78}, {4,64}, {91}}
	//arr := []int{91,4,64,78}
	arr := []int{2,82,79,95,28}
	input := [][]int{{2},{82},{28},{79,95}}
	var ans = canFormArray(arr, input)
	if ans {
		fmt.Printf("pass")
	}
}