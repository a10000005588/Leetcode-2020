class Solution {
    public int[] runningSum(int[] nums) {
        int[] ans = new int[nums.length];

        int curTotal = 0;
        for (int i=0; i<nums.length; i++) {
            curTotal = curTotal + nums[i];
            ans[i] = curTotal;
        }

        return ans;
    }
}
