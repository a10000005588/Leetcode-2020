#include <iostream>
#include <vector>

using std::vector;

class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        vector<int> container;
        
        for (int num: nums) {
          // check whether the nums is exist in container
          for (int element: container) {
            if (num == element) {
              return num;
            }
          }

          container.push_back(num);
        }
        return 0;
    }
};