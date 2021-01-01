class Solution {
  public int removeDuplicates(int[] nums) {
      int i=0;
      
      for(int n:nums) {
          // if next value is not same as n, replace it with n.
          if(i == 0 || nums[i-1] < n) {
              nums[i] = n;
              i++;
          }
      }
      return i;
  }
}