#include <iostream>
#include <vector>

using namespace std;

int dominantIndex(vector<int>& nums) {
    if (nums.empty()) {
        return -1;
    }
    if (nums.size() == 1) {
        return 0;
    }
    // find the maximum num and the second, then compare...
    int max = -1;
    int index = -1;
    for(string::size_type i=0; i < nums.size(); ++i) {
        if (max < nums[i]) {
            max = nums[i];
            index = (int)i;
        }
    }
    int secondMax = -1;
    // find the second largest..
    for(string::size_type i=0; i < nums.size(); ++i) {
        if (secondMax < nums[i] && nums[i] < max)
            secondMax = nums[i];
    }
    if (max >= secondMax * 2)
        return index;
    return -1;
}

int main() {
    vector<int> arr = {0,0,1,2};
    cout << dominantIndex(arr) << endl;
    return 0;
}