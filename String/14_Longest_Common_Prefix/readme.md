# 14. Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:
```
Input: strs = ["flower","flow","flight"]
Output: "fl"
```
Example 2:
```
Input: strs = ["dog","racecar","car"]
Output: ""
```
Explanation: There is no common prefix among the input strings.

Constraints:

* 0 <= strs.length <= 200
* 0 <= strs[i].length <= 200
* strs[i] consists of only lower-case English letters.

## 我的作法

先取出list內長度最小的string出來作為基準，然後比較每個string是否相同。

### cpp

< 15 mins

```cpp=s
string longestCommonPrefix(vector<string>& strs) {
    if (strs.empty()) return "";

    int minIndex = 0;
    string minString = strs[0];
    for (vector<string>::size_type i=1; i != strs.size(); ++i) {
        if (strs[i].size() < minString.size()) {
            minString = strs[i];
            minIndex = i;
        }
    }

    string ans = "";
    for (string::size_type i=0; i<minString.size(); i++) {
        // loop each string in strs
        for (string::size_type j=0; j<strs.size(); j++) {
            string curString = strs[j];
            if (minString[i] != curString[i]) return ans;
        }
        ans += minString[i];
    }

    return ans;
}
```

Runtime: 12 ms, faster than 11.89% of C++ online submissions for Longest Common Prefix.

Memory Usage: 15.7 MB, less than 7.68% of C++ online submissions for Longest Common Prefix.


### go

```go=
func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
    
    if len(strs) == 1 {
		return strs[0]
	}
	var res []byte

	shortestStr := shortesttStr(strs)

	var bytesArr [][]byte
	for i := 0; i < len(strs); i++ {
		// if there's any str is empty, return "" instantly.
		if len(strs[i]) == 0 {
			return ""
		}

		byteStr := []byte(strs[i])
		bytesArr = append(bytesArr, byteStr)

	}

	for i := 0; i < shortestStr; i++ {
		for j := 0; j < len(strs)-1; j++ {
			if bytesArr[j][i] != bytesArr[j+1][i] {
				if len(res) == 0 {
					return ""
				}
				return string(res[:])
			}
		}
		res = append(res, bytesArr[0][i])
	}
	return string(res[:])
}

func shortesttStr(strs []string) int {
	var indexOfShortestStr = 10000000

	for i := 0; i < len(strs); i++ {
		byteStr := []byte(strs[i])
		if len(byteStr) < indexOfShortestStr {
			indexOfShortestStr = len(byteStr)
		}
	}

	return indexOfShortestStr
}
```