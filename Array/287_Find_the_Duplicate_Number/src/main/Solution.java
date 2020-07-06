class Solution {
    public int findDuplicate(int[] nums) {
        // 用for loop 遍歷每一個, 紀錄當前位置 index
        // 對每一個進行binary search, 除了index外, 在看是否有沒有找到
        int ans = 0;
        for(int i=0; i<nums.length; i++) {
            if(doSearch(nums, i, nums[i])) {
                ans = nums[i];
                break;
            }
        }

        return ans;
    }

    boolean doSearch(int[] nums, int index, int target) {
        for(int i=0; i<nums.length; i++) {
            if (i == index) {
                continue;
            }

            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
