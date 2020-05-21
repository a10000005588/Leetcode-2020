
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void TestSolution() {
        TreeNode right_right = new TreeNode(100);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(10, null, right_right);
        TreeNode root = new TreeNode(5, left, right);

        Solution sol = new Solution();
        assertEquals(sol.kthSmallest(root, 1), 2);
        assertEquals(sol.kthSmallest(root, 2), 5);
        assertEquals(sol.kthSmallest(root, 3), 10);
        assertEquals(sol.kthSmallest(root, 4), 100);

    }
}
