# 66. Plus One (easy)

Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:
```
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
```
Example 2:
```
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
```

## 我的作法

使用for loop, 從最小的位數開始++ (為digits[digits.length-1]開始)，過程中遇到有9的, 將digits[i]++, 回傳結果；

如果一直沒有的話，那就新增一個比digits長1的array, 在最大位數newArray[0] = 1, 回傳結果

### Java

> 7/6 一刷
```java=
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 最小位數為digits的最後一個
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // 因為digits[i]=9 +1 後變成0, 下一回合的loop就會自動+1做進位
            digits[i] =0;
        }
        //假如每一個回合都是9，那新增一個array, index=0放入1
        int[] newArray = new int[n+1];
        newArray[0] = 1;
        return newArray;
    }
}
```
