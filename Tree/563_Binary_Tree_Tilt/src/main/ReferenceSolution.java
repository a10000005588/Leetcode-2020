class ReferenceSolution {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        countTilt(root);
        return tilt;
    }

    private int countTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = countTilt(node.left);
        int right = countTilt(node.right);
        tilt += cal(left, right);

        return left + right + node.val;
    }

    private int cal(int x, int y) {
        return x - y < 0 ? (x-y) * -1 : (x-y);
    }
}
