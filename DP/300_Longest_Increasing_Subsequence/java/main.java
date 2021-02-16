class Solution {
  private int[] memo;
  public int lengthOfLIS(int[] nums) {
      if (nums.length == 1) {
          return 1;
      }
      
      memo = new int[nums.length];
      
      int max = Integer.MIN_VALUE;
      for(int i=nums.length-1; i>=0; i--) {
          int curMax = findMaxSubsequence(i, nums);
          if (max < curMax) {
              max = curMax;
          }
      }
      
      return max;
  }
  
  private int findMaxSubsequence(int index, int[] nums) {
      // 若是最尾巴的那隻 長度只有1
      if (index == nums.length-1) {
          this.memo[index] = 1;
      }
      
      if (this.memo[index] == 0) {
          // 預設先給1
          this.memo[index] = 1;
          // 計算目前index的最大subsequece有多少
          for (int i=index+1; i<nums.length; i++) {
              if (nums[index] < nums[i]) {
                  // 找到最近已經有算出他的max sub的lentgh
                  
                  // 且若在後面遇到比自己大的，檢查目前該值是否比之前找到的還大
                  if (this.memo[index] <= this.memo[i]) {
                     this.memo[index] = this.memo[i] + 1;
                  }
              }
          }            
      } 
      
      return this.memo[index];
  }
}