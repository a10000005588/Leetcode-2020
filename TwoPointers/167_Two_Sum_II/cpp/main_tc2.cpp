#include <iostream>
#include <vector>

using namespace std;

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
int main() {
    vector<int> vec = {0,0,3,4};
    vector<int> ans = {6,7};
    vector<int> res = twoSum(vec, 9);
    assert(twoSum(vec, 9) == ans);
    return 0;
}
