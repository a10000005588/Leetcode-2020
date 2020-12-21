class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
    vector<int> ans;
    for (vector<int>::size_type i=0; i<numbers.size(); i++) {
        int j=i+1;
        while(j < numbers.size()) {
            if ((numbers[i] + numbers[j]) == target) {
                ans.push_back(i+1);
                ans.push_back(j+1);
                return ans;
            }
            j++;
        }
    }
    return ans;
}
};