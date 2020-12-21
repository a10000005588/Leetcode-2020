# 167. Two Sum II

## 我的想法

答案要找target, 使用兩個pointer, i指向第一個對象，
接下來只要找到 j = nums[i] - target 就滿足答案，
又因為numbers為排序過後的，所以如果當前j已經大於目前的對象，後續就不用找了

### Cpp

TC version...

main_tc.cpp
```cpp=
#include <iostream>
#include <vector>

using namespace std;

vector<int> twoSum(vector<int>& numbers, int target) {
    vector<int> ans;
    for (vector<int>::size_type i=0; i<numbers.size(); i++) {
        int j=i+1;
        int actualTarget = target-numbers[i];
        while(j < numbers.size()) {
            // because the numbers is asc order,
            // if the actualTarget is less than the current value of nums[j]
            // then skip the remaining process.
            if (actualTarget < numbers[j]) {
                break;
            }
            if (numbers[j] == actualTarget) {
                ans.push_back(i+1);
                ans.push_back(j+1);
                return ans;
            }
            j++;
        }
    }
    return ans;
}
int main() {
    vector<int> vec = {2,7,11,15};
    vector<int> ans = {1,2};
    assert(twoSum(vec, 9) == ans);
    return 0;
}
```

### 改良方法 1

上一個方法若遇到 target = 5, [0,0,0,0,0,0,..........0,2,3,9,9,9,9,9.......9] 會爆掉

但由於9又比 target大，可以直接pass不用在找下去了

main_tc2.cpp
```cpp=

vector<int> twoSum(vector<int>& numbers, int target) {
    vector<int> ans;
    int breakpoint = numbers.size();
    for (vector<int>::size_type i=0; i<breakpoint; i++) {
        int j=i+1;
        int actualTarget = target-numbers[i];
        while(j < numbers.size()) {
            // because the numbers is asc order,
            // if the actualTarget is less than the current value of nums[j]
            // then skip the remaining process.
            if (actualTarget < numbers[j]) {
                breakpoint = j;
                break;
            }
            if (numbers[j] == actualTarget) {
                ans.push_back(i+1);
                ans.push_back(j+1);
                return ans;
            }
            j++;
        }
    }
    return ans;
}
```

### 改良方法 2

直覺的作法就是遇到前後兩個相同，若i與j (i+1)這兩個索引指向的值相加不是等於target, 就直接轉移 index i 與 j到下一個．．．
所以0,0,.....就會一直被pass掉 直到遇到2,3,9.... 再結合改良方法2... done

main.cpp
```cpp=

vector<int> twoSum(vector<int>& numbers, int target) {
    vector<int> ans;
    int breakpoint = numbers.size();

    for (vector<int>::size_type i=1; i<breakpoint; i++) {
        // compare if the two continuous value which comnination is as same as target or not
        if ((numbers[i] + numbers[i+1]) == target) {
            ans.push_back(i+1);
            ans.push_back(i+2);
            return ans;
        }
        // if the number is same as before : pass this round
        if (numbers[i] == numbers[i+1]) {
            continue;
        }
        int j=i+1;
        int actualTarget = target-numbers[i];
        while(j < numbers.size()) {
            // because the numbers is asc order,
            // if the actualTarget is less than the current value of nums[j]
            // then skip the remaining process.
            if (actualTarget < numbers[j]) {
                breakpoint = j;
                break;
            }
            if (numbers[j] == actualTarget) {
                ans.push_back(i+1);
                ans.push_back(j+1);
                return ans;
            }
            j++;
        }
    }
    return ans;
}
```

```
17 / 17 test cases passed.
Status: Accepted
Runtime: 8 ms
Memory Usage: 10.1 MB
```