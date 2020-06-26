class Solution {
    public int maxProduct(int[] nums) {
        int max = 0;
        int max_index = 0;
        int secMax = 0;
        int secMax_index = 0;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                max_index = i;
            }
        }

        for(int i=0; i<nums.length; i++) {
            if(i == max_index) continue;
            if(nums[i] > secMax) {
                secMax = nums[i];
                secMax_index = i;
            }
        }

        return (max-1) * (secMax-1);
    }
}
