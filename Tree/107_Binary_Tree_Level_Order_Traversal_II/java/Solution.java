class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    int maxLevel = 0;

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 找出maxLevel
        findTotalLevel(root, 1);

        // 將list塞入 ans
        for (int i=0; i<maxLevel; i++) {
            ans.add(new ArrayList<Integer>());
        }
        // 開始traverse
        for (int i=this.maxLevel; i>0; i--) {
            // 從最高的level開始找, 並放入list
            traverse(root, i, 1);
        }

        return ans;
    }

    public void traverse(TreeNode root, int level, int cur) {
        if (cur == level) {
            // maxLevel - level (假如level為5為最底層, 放入 maxLevel - level = 0的位置)
            int pos = this.maxLevel - level;
            ans.get(pos).add(root.val);
            return;
        }

        if (root.left != null) {
            traverse(root.left, level, cur+1);
        }
        if (root.right != null) {
            traverse(root.right, level, cur+1);
        }
        return;
    }

    public void findTotalLevel(TreeNode root, int level) {
        if (root == null) { return; }

        if (level > this.maxLevel) {
            maxLevel = level;
        }

        findTotalLevel(root.right, level + 1);
        findTotalLevel(root.left, level + 1);
    }
}
