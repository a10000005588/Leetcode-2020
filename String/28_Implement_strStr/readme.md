# 28. Implement strStr()

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

Example 1:
```
Input: haystack = "hello", needle = "ll"]

Output: 2
```
Example 2:
```
Input: haystack = "aaaaa", needle = "bba"
Output: -1
```
Example 3:
```
Input: haystack = "", needle = ""
Output: 0
```

Constraints:

0 <= haystack.length, needle.length <= 5 * 104
haystack and needle consist of only lower-case English characters.

### 我的作法

1. 判斷needle 與 haystack 是否一樣，一樣就return 0, 不一樣return -1;

2. loop haystack, 若有跟needle第一個字元一樣，紀錄當前位置為index
那同時loop needle, 若全部都一樣就return index;

3. edge case: 
needle = "",
needle's size > haystack's size

### cpp 

about 50 mins

```cpp=

int strStr(string haystack, string needle) {
    // 比對haystack與needle第一個是否相同
    // 若相同，繼續比較，若全部都相同，return 所在的index
    //   若沒有全部相同，continue...
    // 若到了最後一個index也沒有，那就return -1
    // 如果needle = "" return 0

    if (needle.empty()) return 0;
    if (needle.size() >= haystack.size()) {
        if (needle != haystack) return -1;
    }
    // loop haystack
    for (string::size_type i = 0; i < haystack.size(); ++i) {
        // loop needle
        int curi = i;
        for (string::size_type j = 0; j < haystack.size(); ++j) {
            if (haystack[curi] == needle[j]) {
                if (j == needle.size()-1) {
                    return i;
                }
            } else {
                break;
            }
            curi = curi + 1;
        }
    }
    return -1;
}
```

### go

```golang=
func strStr(haystack string, needle string) int {
	if needle == "" {
		return 0
	}

	if haystack == needle {
		return 0
	}

	if len(needle) > len(haystack) {
		return -1
	}

	hay := []byte(haystack)
	nee := []byte(needle)
	counter := 0
	for i := 0; i < len(hay)-len(needle)+1; i++ {
		for j := 0; j < len(needle); j++ {
			if !checkIsExist(hay, nee[j], i+j) {
				counter = 0
				break
			}
			counter++
		}
		if counter == len(needle) {
			return i
		}
	}

	return -1
}

func checkIsExist(hay []byte, needle byte, pos int) bool {
	if hay[pos] == needle {
		return true
	}
	return false
}
```