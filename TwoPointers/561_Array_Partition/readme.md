# 


## 我的作法

1. 暴力解

用兩個index紀錄 最小與第二小，
第一次loop找出最小值，紀錄最小值的index,
第二次loop找出第二小值，紀錄最該值的index

找完後將small 加入到 結果，以此類推。

### Cpp

O(n^2)  Time Limited Exceed (TC)

main_tc_1.cpp
```cpp=
int arrayPairSum(vector<int>& nums) {
    vector<bool> islookup(nums.size());
    int small = numeric_limits<int>::max();
    int smallIndex = 0;
    int secondSmall = numeric_limits<int>::max();
    int secondSmallIndex = 0;
    int ans = 0;
    int lastIndex = nums.size()-1;
    for(vector<int>::size_type i=0; i<nums.size()/2; ++i) {
        // first loop : find smallest
        for(vector<int>::size_type j=0; j<nums.size(); ++j) {
            // skip lookup
            if (islookup[j]) continue;
            if (nums[j] < small) {
                small = nums[j];
                smallIndex = j;
            }
        }
        islookup[smallIndex] = true;
        // second loop: find second smallest
        for(vector<int>::size_type j=0; j<nums.size(); ++j) {
            // skip lookup
            if (islookup[j]) continue;
            if (nums[j] < secondSmall) {
                secondSmall = nums[j];
                secondSmallIndex = j;
            }
        }
        islookup[secondSmallIndex] = true;
        ans += small;  // 只需要加入small的就好
        // reset
        small = numeric_limits<int>::max();
        secondSmall = numeric_limits<int>::max();
    }
    return ans;
}
```

2. 排序並找出規則

先用sort()方法排序後，index為偶數的相加就是結果了

main.cpp
```cpp=
int arrayPairSum(vector<int>& nums) {
    int ans = 0;
    sort(nums.begin(), nums.end());
    for (vector<int>::size_type i=0; i<nums.size(); ++i) {
        if (i%2==0) ans += nums[i];
    }
    return ans;
}
```

Runtime: 128 ms, faster than 78.25% of C++ online submissions for Array Partition I.

Memory Usage: 28.6 MB, less than 43.80% of C++ online submissions for Array Partition I.
