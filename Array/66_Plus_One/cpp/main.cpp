#include <iostream>
#include <vector>

using namespace std;

vector<int> plusOne(vector<int>& digits) {
    auto size = (int)digits.size();
    for (int i=size-1; i>=0; i--) {
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
    }

    vector<int> ans(digits.size()+1);
    ans[0] = 1;
    return ans;
}

int main() {
    vector<int> vec = {2,4,9,3,9};
    vector<int> ans = plusOne(vec);
    return 0;
}