# 67. Add Binary

Given two binary strings a and b, return their sum as a binary string.

Example 1:
```
Input: a = "11", b = "1"
Output: "100"
```
Example 2:

```
Input: a = "1010", b = "1011"
Output: "10101"
 ```

Constraints:
```
1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
```

## 我的作法

用兩個index當作pointer, 指向輸入的兩個string的最尾端，並依序判斷該位元是否需要carry
更改最長位數的輸入為主。

若最後有超出輸入的位數 ex: '111' '1', 那就判斷是否有carry，

最後結果會是 '000' 有carry，則 return "1" + "000"

### Cpp

花了大約1小時

```c++=
string addBinary(string a, string b) {
    if (a.empty() && b.empty()) { return "";}
    if (a.empty() || b.empty()) { return a + b;}
    string ans;
    string shortStr;
    string longStr;
    // 先確認ab哪個比較多位數
    if (a.length() > b.length()) {
        longStr = a;
        shortStr = b;
    } else {
        shortStr = a;
        longStr = b;
    }

    bool isCarry = false;
    int indexLong = longStr.size()-1;
    int indexShort = shortStr.size()-1;
    // 將shortStr加到longStr上
    for(string::iterator it = longStr.begin(); it != longStr.end(); --it) {
        if (indexShort < 0) break;
        if (isCarry) {
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '0') {
                longStr[indexLong] = '1';
                isCarry = false;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '0') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '1') {
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
        } else {
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '0') {
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '0') {
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '1';
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
        }
    }

    // 把剩下的處理完
    if (indexLong >= 0) {
        for (int i=indexLong; i >=0; --i) {
            if (isCarry) {
                if (longStr[indexLong] == '0') {
                    longStr[indexLong] = '1';
                    --indexLong;
                    isCarry = false;
                } else if(longStr[indexLong] == '1') {
                    longStr[indexLong] = '0';
                    --indexLong;
                    isCarry = true;
                }
            } else {
                break;
            }
        }
    }
    // 最後一位若是1，還有進位，那就
    if (isCarry) {
        return "1" + longStr;
    }

    return longStr;
}
```
Runtime: 0 ms, faster than 100.00% of C++ online submissions for Add Binary.
Memory Usage: 6.7 MB, less than 63.20% of C++ online submissions for Add Binary.

## Reference

C++: For every character in string
https://stackoverflow.com/questions/9438209/for-every-character-in-string