# 344. Reverse String

Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 
```
Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## 我的作法

就for loop到一半, 將i與第char[] array的i.length - 1對調

### Java

#### 6/4 一刷

```java=
class Solution {
    public void reverseString(char[] s) {
        for(int i=0; i<s.length/2; i++) {
            char temp = s[s.length-i-1];
            s[s.length-i-1] = s[i];
            s[i] = temp;
        }
    }
}
```

Time Comlexity: O(N)

Space Complexity: O(1)

### Cpp

```cpp=
void reverseString(vector<char>& s) {
    int backwardIndex = s.size() - 1;
    for (vector<char>::size_type i=0; i< (s.size()/2); ++i) {
        char temp = s[i];
        s[i] = s[backwardIndex];
        s[backwardIndex] = temp;
        --backwardIndex;
    }
}
```

### Go

```go=
func reverseString(s string) string {
	x := []byte(s)

	for i := 0; i < len(s)/2; i++ {
		x[i], x[(len(s)-1)-i] = x[(len(s)-1)-i], x[i]
	}
	return string(x[:])
}
```