# 300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
```
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
```
Example 2:
```
Input: nums = [0,1,0,3,2,3]
Output: 4
```
Example 3:
```
Input: nums = [7,7,7,7,7,7,7]
Output: 1
```

* Constraints:
```
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
```

Follow up:
```
Could you come up with the O(n2) solution?
Could you improve it to O(n log(n)) time complexity?
```


### Compaines

* Apple : 8
* Amazon: 6
* Facebook: 5
* Google: 4
* Citrix: 4
* Microsoft: 3
* Bloomberg: 2
* ByteDance: 2
* Nagarro: 2
* Atlassian: 7
* Adobe: 4
* Uber: 3
* eBady: 3
* Codenation: 2

## 我的解法

題目要得最長的 ascending order的subseqeunce.

例如 Input : [10,9,2,5,3,7,101,18]

那依序從小到大，且不用連續出現的組合為 [2,3,7,18] 的長度

解法為：

1. 從Input的最尾端開始，一一找substring. 並用一個 `memo[index]` 紀錄每個index 最大的長度是多少

舉例：若 index=2 (為2), 那memo[index]=1


index=3,(為5), 那直接看memo[index=2] 再+1就好，因為已經計算完 [10,9,2]之 `index=2` 時的 longest increasing subsequence大小為 `[2]`了，故 index=3的`memo[index=3]`為 `memo[index=2]` + 1

2. 接著對每個index, 往後推導該index的最大subsequence是多少，使用i來代表目前比較的對象
   1. 若後面nums[i] 比 nums[index]的值還大，計算`i` 的Longest Increasing Subsequence : `memo[i]`
   2. 若 `i` 的 Longest Increasing Subsequence比 `index` 還大，更新index 的 Longest Increasing Subsequence : `memo[index]` = `memo[i] + 1`

範例 Input : [10,9,2,5,3,7,101,18]
index從最尾端 18 計算出 `memo[6] (18)` = 1

接著`memo[5] = 1 (101)`

計算到 `memo[4]`時，先確定index比i小，且遇到比自己的`memo`還要大，那`memo[4]`就是直接為`memo[i=6]` + 1, 等於 `memo[6] + 1` (7,18)`的值

### java

```java=
class Solution {
    private int[] memo;
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        
        memo = new int[nums.length];
        
        int max = Integer.MIN_VALUE;
        for(int i=nums.length-1; i>=0; i--) {
            int curMax = findMaxSubsequence(i, nums);
            if (max < curMax) {
                max = curMax;
            }
        }
        
        return max;
    }
    
    private int findMaxSubsequence(int index, int[] nums) {
        // 若是最尾巴的那隻 長度只有1
        if (index == nums.length-1) {
            this.memo[index] = 1;
        }
        
        if (this.memo[index] == 0) {
            // 預設先給1
            this.memo[index] = 1;
            // 計算目前index的最大subsequece有多少
            for (int i=index+1; i<nums.length; i++) {
                if (nums[index] < nums[i]) {
                    // 找到最近已經有算出他的max sub的lentgh
                    
                    // 且若在後面遇到比自己大的，檢查目前該值是否比之前找到的還大
                    if (this.memo[index] <= this.memo[i]) {
                       this.memo[index] = this.memo[i] + 1;
                    }
                }
            }            
        } 
        
        return this.memo[index];
    }
}
```

```
54 / 54 test cases passed.
Status: Accepted
Runtime: 70 ms
Memory Usage: 40.8 MB
```

### golang

```go=
func lengthOfLIS(nums []int) int {
    if len(nums) == 1 {
        return 1
    }
    
    memo := make([]int, 2500)
    
    max := -1000000 //   -10^4 < nums[i] < 10^4
    
    for i:=len(nums)-1; i>=0; i-- {
        curMax := findMaxSubsequence(i, nums, memo)
        if max < curMax {
            max = curMax
        }
    }
    
    return max
}

func findMaxSubsequence(index int, nums []int, memo []int) int {
    // 若是nums最末端，長度只有1
    if index == len(nums)-1 {
        memo[index] = 1
    }
    
    // 如果當前的index沒有計算longest increasing subsequence
    if memo[index] == 0 {
        // 設置初始值為1 (自己的長度)
       memo[index] = 1
       
        for i:=index+1; i<len(nums); i++ {
            // 找到比目前index的值，更大的對象，才能夠納入自己的subsequence
            if nums[index] < nums[i] {
                // 若找到比自己更大的longest increasing subsequence，進行更新
                if memo[index] <= memo[i] {
                    memo[index] = memo[i] + 1
                }
            }
        }
    }
    return memo[index]
}
```

```
Runtime: 52 ms, faster than 64.67% of Go online submissions for Longest Increasing Subsequence.
Memory Usage: 3.6 MB, less than 81.20% of Go online submissions for Longest Increasing Subsequence.
```

寫這題複習了golang的slice, 為pass by value, 但slice存的都是header, 指向值所在的位置。

參考資源：
* Stackoverflow: Are golang slices passed by value?
  * https://stackoverflow.com/questions/39993688/are-golang-slices-passed-by-value
