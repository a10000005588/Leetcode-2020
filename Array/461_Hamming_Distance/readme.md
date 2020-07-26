# 461. Hamming Distance (easy)

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

```
Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
```

## 我的作法

上網查了一下定義, Hamming Distance距離為兩個數字的二進位, 進行XOR運算後, 看有幾個1即為Hamming distance距離

主要先計算出兩個數字最大的位數是多少 = maxDigits

創建兩個數字的binary array, x = XDigits, YDigits

創建一個ans array (長度等於maxDigits)

將x, y各別除以2, 從最後一個位置開始, 如果有餘數=1 那就放1, 反之則0

假如是 4和1
![](https://i.imgur.com/IGIMgQp.jpg)



### code 

> 1刷 30分

```java=
class Solution {
    public int hammingDistance(int x, int y) {
        // 先轉二進制, 然候用XOR, 看有多少個1

        // 先計算看有幾位數
        int maxDigitsLength = getMaxDigitsLength(x, y);

        int[] Xdigits = getDigits(x, maxDigitsLength);
        int[] Ydigits = getDigits(y, maxDigitsLength);

        // 做XOR
        int haminDistance;
        int[] XORarray = new int[maxDigitsLength];
        int index = 0;
        int ans = 0;
        while(index < maxDigitsLength) {
            if (Xdigits[index] != Ydigits[index]) {
                XORarray[index] = 1;
                ans++;
            }
            index++;
        }

        return ans;
    }

    public int getMaxDigitsLength(int x, int y) {
        int maxDigits = 0;
        while (x != 0 || y!= 0) {
            maxDigits += 1;
            x /= 2;
            y /= 2;
        }
        return maxDigits;
    }

    public int[] getDigits(int hexNum, int maxDigits) {
        int[] n = new int[maxDigits];
        int index = maxDigits - 1; // 從array的最後面開始放置
        while(hexNum != 0) {
            int remainder = hexNum % 2;
            if (remainder != 0) {
                n[index] = 1;
            }
            hexNum /= 2;
            index--;
        }

        return n;
    }
}
```

```
Runtime: 1 ms, faster than 30.25% of Java online submissions for Hamming Distance.
Memory Usage: 38 MB, less than 10.96% of Java online submissions for Hamming Distance.
```
