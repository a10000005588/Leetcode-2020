/*
    第一次寫的broken code...
*/
class Solution {
    public int findTilt(TreeNode root) {
        if (root == null) { return 0; }

        if (root.left == null && root.right != null) {
            return getTilt(countTilt(root.right), 0);
        }  else if (root.left != null && root.right == null) {
            return getTilt(countTilt(root.left), 0);
        }
        return  getTilt(countTilt(root.left), countTilt(root.right));
    }

    private int countTilt(TreeNode node) {
        int tilt = 0;
        if (node.left == null && node.right == null) {
            return tilt + node.val;
        } else if (node.left == null && node.right != null) {
            tilt = node.val + countTilt(node.right);
            return tilt;
        } else if (node.right == null && node.left != null) {
            tilt = node.val + countTilt(node.left);
            return tilt;
        }

        return tilt + countTilt(node.left) + countTilt(node.right) + node.val;
    }

    private int getTilt(int x, int y) {
        return x - y < 0 ? (x-y) * -1 : (x-y);
    }
}

/*
    第二次寫
*/
class Solution2 {
    int tilt;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        countTilt(root);
        return tilt;
    }

    private int countTilt(TreeNode root) {
        if (root == null) { return 0; }

        int left = countTilt(root.left);
        int right = countTilt(root.right);

        tilt += Math.abs(left-right);  // <- 到這裡有卡住, 需注意
        return left + right + root.val;
    }
}

/*
    第三次寫
*/
class Solution3 {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        countTilt(root);
        return tilt;
    }

    private int countTilt(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = countTilt(root.left);
        int right = countTilt(root.right);

        tilt += Math.abs(left-right); // <- 一樣在這裡又卡住, 需要計算left和right的絕對值
        return root.val + left + right;
    }

}
