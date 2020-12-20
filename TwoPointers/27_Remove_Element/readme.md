# 27. Remove Element

Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller as well.
```
Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeElement(nums, val);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

Example 1:
```
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2]
Explanation: Your function should return length = 2, with the first two elements of nums being 2.
It doesn't matter what you leave beyond the returned length. For example if you return 2 with nums = [2,2,3,3] or nums = [2,2,0,0], your answer will be accepted.
```
Example 2:
```
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3]
Explanation: Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4. Note that the order of those five elements can be arbitrary. It doesn't matter what values are set beyond the returned length.
 ```

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100

## 我的做法

使用two pointer
* i : 比較對象
  * 若有符合target的，與j交換.
    * i++, j--;
  * 當前回合不是
    * i++
* j : 指定最後一個位置，用來放置target
  * 若目前j已經是target了
    * j--
  * 若被交換
    * j--

### Java

```java=
public int removeElement(int[] nums, int val) {
    int i = 0;
    int j = nums.length-1; // 指向nums最後一個值
    
    while(i < nums.length && i <= j) {
        if(nums[i] == val) {
            if(nums[j] == val) {
                j--;
                continue;
            }
            // 若指到有與val一樣的值，從最後一個開始交換
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            j--;
            i++;
            continue;
        }
        i++;
    }
    return i;
}
```

### Cpp

```cpp=
int removeElement(vector<int>& nums, int val) {
   int i = 0;
   int j = nums.size()-1;
   while(i < nums.size() && i <= j) {
       if (nums[i] == val) {
           if (nums[j] == val) {
               j--;
               continue;
           }
           // 若指到有與val一樣的值，從j所指的(最後一個)開始交換
           int temp = nums[i];
           nums[i] = nums[j];
           nums[j] = temp;
           j--;
           i++;
           continue;
       }
       i++;
   }
   return i;
}
```

Runtime: 4 ms, faster than 53.90% of C++ online submissions for Remove Element.

Memory Usage: 9.2 MB, less than 72.51% of C++ online submissions for Remove Element.