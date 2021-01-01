# 557. Reverse Words in a String III

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
```

Note: In the string, each word is separated by single space and there will not be any extra space in the string.

## 我的作法

loop 輸入的string, 遇到非空白字元的, 加入新的string內

在用reverse order, 將新的string內讀入 最後要回傳的answer

### Edge case

* "" : 請處理輸入值為空字串

### Golang

```go
func reverseWords(s string) string {
    if len(s) == 0 {
		return ""
	}
    
	var ans = ""
	for i:=0; i<len(s); i++ {
		var incomingStr = ""
		for true {
			if s[i] == ' ' {
				break
			}
			incomingStr += string(s[i])
			i++
			if i == len(s) {
				break
			}
		}

		// record the string in reverse order
		for i := range incomingStr {
			ans += string(incomingStr[len(incomingStr)-1-i])
		}

		if (i != len(s)-1) {
			ans += " "
		}
	}
	// trim extra space in last char
	ans = ans[:len(ans)-1]
	return ans
}
```

Time Complexity: O(n^2)

Space Complexity: O(n)

Runtime: 500 ms, faster than 7.04% of Go online submissions for Reverse Words in a String III.

Memory Usage: 8.3 MB, less than 5.63% of Go online submissions for Reverse Words in a String III.