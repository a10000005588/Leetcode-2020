# 151. Reverse Words in a String

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.


Example 1:
```
Input: s = "the sky is blue"
Output: "blue is sky the"
```
Example 2:
```
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
```
Example 3:
```
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
```

Example 4:
```
Input: s = "  Bob    Loves  Alice   "
Output: "Alice Loves Bob"
```
Example 5:
```
Input: s = "Alice does not even like bob"
Output: "bob like even not does Alice"
```

## 我的作法

用一個for loop, loop 輸入的string, 
1. 遇到非空白字元就開始紀錄到一個new string內
2. 遇到下一個空白字元，放入到 array內
3. 最後從尾到頭將array內的string讀出來

### Golang

```go
func reverseWords(s string) string {
	var stringContainer []string

    for i:=0; i<len(s); i++ {
    	var newWord = ""

    	if s[i] != ' ' {
    		// keep record the char until next ' '
			for true {
				// if ' ', stop record
				if s[i] == ' ' {
					break
				}

				// append character into the newWord, until the other empty space
				newWord = newWord + string(s[i])
				// increment i.
				i++

				// if the end, stop record
				if i == len(s) {
					break
				}
				continue
			}
			// push the word into the stringContainer
			stringContainer = append(stringContainer, newWord)
		}
    }
    ans := ""
    for i, _ := range stringContainer {
    	ans += stringContainer[len(stringContainer)-1-i]
    	if i != len(stringContainer) -1 {
			ans += " "
		}
	}

	return ans
}
```

Time Complexity: O(n^2 + n) = O(n^2) 

Space Complexity: O(n)

Runtime: 4 ms, faster than 50.42% of Go online submissions for Reverse Words in a String.

Memory Usage: 6.5 MB, less than 28.57% of Go online submissions for Reverse Words in a String.