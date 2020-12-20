#include <vector>

using namespace std;

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
int main() {
//    vector<int> vec = {0,1,2,2,3,0,4,2};
//    assert(removeElement(vec, 2) == 5);
    vector<int> vec2 = {4,5};
    assert(removeElement(vec2, 5)==0);
    return 0;
}
