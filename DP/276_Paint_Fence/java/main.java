class Solution {
  private int[] memo;
 
  public int numWays(int n, int k) {
      // n: number of posts, k: number of colour
      if (n == 0 || k == 0) {
          return 0;
      }
      
      memo = new int[n];
      return totalWays(n, k);
  }
  
  private int totalWays(int posts, int k) {
      if (posts == 1) {
          return k;
      }
      
      if (posts == 2) {
          return k*k;
      }
      
      if (this.memo[posts-1] == 0) {
          this.memo[posts-1] = this.totalWays(posts - 1, k);
      }
      if (this.memo[posts-2] == 0) {
          this.memo[posts-2] = this.totalWays(posts - 2, k);
      }
      
      return (this.memo[posts-1] + this.memo[posts-2]) * (k-1);
  }
}