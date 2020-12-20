class Solution {
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
}