#include <iostream>
#include <vector>

using namespace std;
//
//// BF
//int pivotIndex(vector<int>& nums) {
//    // loop every index value
//    for (string::size_type i = 0; i < nums.size(); ++i) {
//        // for each index value, check the sum of left side is equal to right or not
//        int sumOfLeft = 0;
//        int sumOfRight = 0;
//        for (string::size_type leftIndex = 0; leftIndex < i; ++leftIndex) {
//            sumOfLeft += nums[leftIndex];
//        }
//        for (string::size_type rightIndex = i+1; rightIndex < nums.size(); ++rightIndex) {
//            sumOfRight += nums[rightIndex];
//        }
//        if (sumOfLeft == sumOfRight)
//            return (int)i;
//    }
//    return -1;
//}

//// BF v2
//int pivotIndex(vector<int>& nums) {
//    int sumOfLeft = 0;
//    // loop every index value
//    for (string::size_type i = 0; i < nums.size(); ++i) {
//        // caculate the sum of right;
//        int sumOfRight = 0;
//        for (string::size_type rightIndex = i+1; rightIndex < nums.size(); ++rightIndex) {
//            sumOfRight += nums[rightIndex];
//        }
//        if (sumOfLeft == sumOfRight)
//            return (int)i;
//        sumOfLeft += nums[i];
//    }
//    return -1;
//}

int pivotIndex(vector<int>& nums) {
    // get sum first
    int sum = 0;
    for (string::size_type i =0; i<nums.size(); ++i) {
        sum += nums[i];
    }

    int leftSum = 0;
    int size = (int)nums.size();
    for (string::size_type i=0; i < size; ++i) {
        int rightSum = sum - leftSum - nums[i]; // need subtract the pivot value
        if (leftSum == rightSum)
            return (int)i;

        leftSum += nums[i];
    }
    return -1;
}

int main() {
//    vector<int> vc = {};
    vector<int> vc = {-1,-1,-1,0,1,1};
//    vector<int> vec = {1,7,3,6,5,6};
//    vector<int> vec1 = {1,2,3};
    cout << pivotIndex(vc) << endl;
//    cout << pivotIndex(vec) << endl;
//    cout << pivotIndex(vec1) << endl;
    return 0;
}